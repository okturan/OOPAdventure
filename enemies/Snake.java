package enemies;

import java.util.Random;

import items.armor.Heavy;
import items.armor.Light;
import items.armor.Medium;
import items.weapons.Pistol;
import items.weapons.Rifle;
import items.weapons.Sword;
import players.Player;

public class Snake extends Enemy {
    private static final String SNAKE_EMOJI = "🐍";

    // Defining probabilities as constants
    private static final double NOTHING_CHANCE = 0.45;

    private static final double WEAPON_GAIN_CHANCE = 0.15;
    private static final double RIFLE_WIN_PROBABILITY = 0.20;
    private static final double SWORD_WIN_PROBABILITY = 0.30;
    private static final double PISTOL_WIN_PROBABILITY = 0.50;

    private static final double ARMOR_GAIN_PROBABILITY = 0.15;
    private static final double HEAVY_ARMOR_GAIN_PROBABILITY = 0.20;
    private static final double MEDIUM_ARMOR_GAIN_PROBABILITY = 0.30;
    private static final double LIGHT_ARMOR_GAIN_PROBABILITY = 0.50;

    private static final double GOLD_PROBABILITY = 0.25;
    private static final double TEN_GOLD_PROBABILITY = 0.20;
    private static final double FIVE_GOLD_PROBABILITY = 0.30;
    private static final double ONE_GOLD_PROBABILITY = 0.50;
    private final Random random = new Random();

    public Snake() {
        super(3, 6, 12, 0, SNAKE_EMOJI);
    }

    @Override
    public void dropLoot(Player player) {
        setDrop(player);
    }

    public void setDrop(Player player) {
        double roll = random.nextDouble();
        calculateDrop(roll, player);
    }

    private void calculateDrop(double roll, Player player) {
        if (roll < NOTHING_CHANCE) {
            System.out.println("-No Drop-");
        } else if (roll < NOTHING_CHANCE + WEAPON_GAIN_CHANCE) {
            getWeapon(player);
        } else if (roll < NOTHING_CHANCE + WEAPON_GAIN_CHANCE + ARMOR_GAIN_PROBABILITY) {
            getArmor(player);
        } else {
            setGold(player);
        }
    }

    private void getWeapon(Player player) {
        double roll = random.nextDouble();
        if (roll < RIFLE_WIN_PROBABILITY) {
            player.getInventory().addItem(new Rifle());
        } else if (roll < RIFLE_WIN_PROBABILITY + SWORD_WIN_PROBABILITY) {
            player.getInventory().addItem(new Sword());
        } else {
            player.getInventory().addItem(new Pistol());
        }
    }

    private void getArmor(Player player) {
        double roll = random.nextDouble();
        if (roll < HEAVY_ARMOR_GAIN_PROBABILITY) {
            player.getInventory().addItem(new Heavy());
        } else if (roll < HEAVY_ARMOR_GAIN_PROBABILITY + MEDIUM_ARMOR_GAIN_PROBABILITY) {
            player.getInventory().addItem(new Medium());
        } else {
            player.getInventory().addItem(new Light());
        }
    }

    private void setGold(Player player) {
        double roll = random.nextDouble();
        if (roll < TEN_GOLD_PROBABILITY) {
            this.setGoldDrop(10);
        } else if (roll < TEN_GOLD_PROBABILITY + FIVE_GOLD_PROBABILITY) {
            this.setGoldDrop(5);
        } else {
            this.setGoldDrop(1);
        }
        player.getCharacter().setMoney(player.getCharacter().getMoney() + this.getGoldDrop());
        System.out.println("💰 You looted " + this.getGoldDrop() + " gold.");
    }
}

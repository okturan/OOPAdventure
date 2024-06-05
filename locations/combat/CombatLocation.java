package locations.combat;

import java.util.Random;

import enemies.Enemy;
import items.Item;
import items.armor.Armor;
import items.weapons.Weapon;
import locations.Location;
import locations.safe.BaseCamp;
import players.Player;
import players.UserInteraction;

public abstract class CombatLocation extends Location {

    private final UserInteraction userInteraction;
    private final BaseCamp baseCamp;
    private boolean isCleared = false;

    public CombatLocation() {
        this.userInteraction = new UserInteraction();
        this.baseCamp = new BaseCamp();
    }

    @Override
    public void enterLocation(Player player) {
        if (isCleared) {
            System.out.println("\n\uD83D\uDD30This area has been cleared and cannot be re-entered.");
            return;
        }

        System.out.printf("Entering the %s...%n", this.getClass().getSimpleName());
        boolean fled = false;
        int numEnemies = getNumEnemies();

        for (int i = 0; i < numEnemies; i++) {
            if (battle(player, createEnemy())) {
                fled = true; // Player fled, exit method without clearing the area or giving loot
                break;
            }
        }

        if (player.getCharacter().getHealth() <= 0) {
            return; // Exit if player is defeated
        }

        if (!fled) {
            loot(player, getLootItem());
            isCleared = true;
            System.out.println("\n✅ You have successfully cleared the " + this.getClass().getSimpleName() + ".");
        }

        baseCamp.enterLocation(player); // Return to basecamp and restore health.
    }

    public boolean battle(Player player, Enemy enemy) {
        System.out.println("\n⤵ You encounter a " + enemy.getClass().getSimpleName() + " " + enemy.getEmoji());
        Random rand = new Random();
        boolean playerTurn = rand.nextBoolean(); // 50% chance to decide who starts

        while (player.getCharacter().getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println("Your HP ❤️️: " + player.getCharacter().getHealth());
            if (userInteraction.getFightOrFleeDecision()) {
                if (playerTurn) {
                    int playerDamage = calculateTotalDamage(player);
                    enemy.setHealth(enemy.getHealth() - playerDamage);
                    System.out.printf("\n\uD83D\uDCA2 You hit the %s for %d damage. Enemy HP 💜: %d%n", enemy.getClass().getSimpleName(), playerDamage, enemy.getHealth());
                } else {
                    int totalDefense = calculateTotalDefense(player);
                    int enemyDamage = Math.max(0, enemy.getDamage() - totalDefense);
                    player.getCharacter().setHealth(player.getCharacter().getHealth() - enemyDamage);
                    System.out.printf("\nThe %s hits you for %d damage \uD83D\uDCA2\n", enemy.getClass().getSimpleName(), enemyDamage);
                }
                playerTurn = !playerTurn; // Switch turns
            } else {
                System.out.println("\nYou have fled back to the base camp.");
                return true; // Indicate that the player has fled
            }
        }

        if (player.getCharacter().getHealth() > 0) {
            System.out.println("\n💀 You have defeated the " + enemy.getClass().getSimpleName() + " " + enemy.getEmoji());
            enemy.dropLoot(player);
            return false; // Indicate that the enemy was defeated
        } else {
            System.out.println("\n🪦 You have been defeated by the " + enemy.getClass().getSimpleName() + " " + enemy.getEmoji() + "\n####GAME OVER!####");
            return true; // Indicate that the player has been defeated
        }
    }

    public int getNumEnemies() {
        return new Random().nextInt(3) + 1;
    }

    protected abstract Enemy createEnemy();

    public void loot(Player player, Item item) {
        if (item != null) {
            System.out.println("✨ You found " + item.getClass().getSimpleName());
            player.getInventory().addItem(item);
        } else {
            System.out.println("No special drop in Mine!");
        }
    }

    protected abstract Item getLootItem();

    private int calculateTotalDamage(Player player) {
        int totalDamage = player.getCharacter().getDamage();
        for (Item item : player.getInventory().getItems()) {
            if (item instanceof Weapon) {
                totalDamage += ((Weapon) item).getDamage();
            }
        }
        return totalDamage;
    }

    private int calculateTotalDefense(Player player) {
        int totalDefense = 0;
        for (Item item : player.getInventory().getItems()) {
            if (item instanceof Armor) {
                totalDefense += ((Armor) item).getDefense();
            }
        }
        return totalDefense;
    }

    public boolean isCleared() {
        return isCleared;
    }
}

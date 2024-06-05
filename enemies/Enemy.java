package enemies;

import java.util.Random;

import players.Player;

public abstract class Enemy {
    private final int minDamage;
    private final int maxDamage;
    private final String emoji;
    private final boolean hasDamageRange;
    private int health;
    private int goldDrop;

    // Constructor for fixed damage
    public Enemy(int damage, int health, int goldDrop, String emoji) {
        this.health = health;
        this.minDamage = damage;
        this.maxDamage = damage;
        this.emoji = emoji;
        this.goldDrop = goldDrop;
        this.hasDamageRange = false;
    }

    // Constructor for damage range
    public Enemy(int minDamage, int maxDamage, int health, int goldDrop, String emoji) {
        this.health = health;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.emoji = emoji;
        this.goldDrop = goldDrop;
        this.hasDamageRange = true;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getEmoji() {
        return emoji;
    }

    public int getGoldDrop() {
        return goldDrop;
    }

    protected void setGoldDrop(int goldDrop) {
        this.goldDrop = goldDrop;
    }

    public int getDamage() {
        if (hasDamageRange) {
            return new Random().nextInt((maxDamage - minDamage) + 1) + minDamage;
        } else {
            return minDamage; // fixed damage
        }
    }

    public void dropLoot(Player player) {
        player.getCharacter().setMoney(player.getCharacter().getMoney() + this.getGoldDrop());
        System.out.println("💰 You looted " + this.getGoldDrop() + " gold.");
    }
}

package characters;

public abstract class GameCharacter {

    private final int damage;
    private final int maxHealth;
    private int health;
    private int money;

    public GameCharacter(int damage, int health, int money) {
        this.damage = damage;
        this.health = health;
        this.maxHealth = health; // Initialize maxHealth to initial health value
        this.money = money;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}

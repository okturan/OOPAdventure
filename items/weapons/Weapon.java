package items.weapons;

import items.Item;

public abstract class Weapon extends Item {

    private final int damage;

    public Weapon(int value,
                  int damage) {
        super(value);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return super.toString() + " | " + "Damage: " + damage;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }
}

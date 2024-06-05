package items.armor;

import items.Item;

public abstract class Armor extends Item {

    private final int defense;

    public Armor(int value,
                 int defense) {
        super(value);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public String toString() {
        return super.toString() + " | " + "Defense: " + defense;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }
}

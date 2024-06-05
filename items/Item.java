package items;

public abstract class Item {

    private final int value;

    public Item(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " -> " + "Value: " + value;
    }

    public boolean isRepeatable() {
        return true;
    }
}

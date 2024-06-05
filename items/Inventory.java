package items;

import java.util.Collections;
import java.util.List;

public class Inventory {

    private final List<Item> items;

    public Inventory() {
        this.items = ItemFactory.createStarterItems();
    }

    public void addItem(Item item) {
        boolean shouldAdd = true;

        for (int i = 0; i < items.size(); i++) {
            Item existingItem = items.get(i);

            // Check if the existing item is of the same class or superclass as the new item
            if (item != null && !item.isRepeatable() && existingItem.getClass().getSuperclass().equals(item.getClass().getSuperclass())) {
                if (existingItem.getValue() >= item.getValue()) {
                    System.out.print("Enemy drops a: " + item.getClass().getSimpleName());
                    printBetterItemWarning();
                    shouldAdd = false;
                    break;
                } else {
                    // Remove the existing item from the same class since the new item has a higher value
                    items.remove(i);
                    break;
                }
            }
        }

        if (shouldAdd) {
            items.add(item);
            System.out.println("\uD83D\uDCE6 " + item + " added to the inventory.");
        }
    }

    public static void printBetterItemWarning() {
        System.out.println("\nYou already have this item or a better item.");
    }


    public void listItems() {
        if (items.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("\nYour items:");
            for (Item item : items) {
                System.out.println(item);
            }
        }
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }
}

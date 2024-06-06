package items;

import java.util.Collections;
import java.util.List;

public class Inventory {

    private final List<Item> items;

    public Inventory() {
        this.items = ItemFactory.createStarterItems();
    }

    public static void printBetterItemWarning() {
        System.out.println("\nYou already have this item or a better item.");
    }

    public void addItem(Item item) {
        boolean shouldAdd = true;

        for (int i = 0; i < items.size(); i++) {
            Item existingItem = items.get(i);

            // If the new item is not repeatable and the existing item is of the same class or superclass
            if (item != null && !item.isRepeatable() && existingItem.getClass().getSuperclass().equals(item.getClass().getSuperclass())) {
                // Compare the values of the existing item and the new item
                if (existingItem.getValue() >= item.getValue()) {
                    // If the existing item has an equal or greater value, do not add the new item
                    System.out.print("Enemy drops a: " + item.getClass().getSimpleName());
                    printBetterItemWarning();
                    shouldAdd = false;
                } else {
                    // Remove the existing item if the new item has a higher value
                    items.remove(i);
                }
                break;
            }
        }

        // Add the new item to the inventory if it passes the checks
        if (shouldAdd) {
            items.add(item);
            System.out.println("\n\uD83D\uDCE6 " + item + " added to the inventory.");
        }
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

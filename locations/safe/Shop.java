package locations.safe;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import items.Inventory;
import items.Item;
import items.armor.Heavy;
import items.armor.Light;
import items.armor.Medium;
import items.weapons.Pistol;
import items.weapons.Rifle;
import items.weapons.Sword;
import players.Player;

public class Shop extends SafeLocation {

    private static final Map<Integer, Item> ITEMS_FOR_SALE = new HashMap<>();

    public Shop() {
        initializeItemsForSale();
    }

    private void initializeItemsForSale() {
        ITEMS_FOR_SALE.put(1, new Pistol());
        ITEMS_FOR_SALE.put(2, new Sword());
        ITEMS_FOR_SALE.put(3, new Rifle());
        ITEMS_FOR_SALE.put(4, new Light());
        ITEMS_FOR_SALE.put(5, new Medium());
        ITEMS_FOR_SALE.put(6, new Heavy());
    }

    @Override
    public void enterLocation(Player player) {
        while (true) {
            displayItemsForSale(player);
            int choice = getItemChoiceFromUser();
            if (choice == 0) {
                System.out.println("Exiting the shop.");
                break;
            }
            processPurchase(player, choice);
        }
    }

    private void displayItemsForSale(Player player) {
        System.out.println("\nWelcome to the shop! Here are the items available for purchase:");
        System.out.println("Your current money: $" + player.getCharacter().getMoney());
        for (Map.Entry<Integer, Item> entry : ITEMS_FOR_SALE.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("0: Exit shop");
    }

    private void processPurchase(Player player, int choice) {
        if (!ITEMS_FOR_SALE.containsKey(choice)) {
            System.out.println("Invalid choice. Please select a valid item number.");
            return;
        }

        Item item = ITEMS_FOR_SALE.get(choice);
        int price = item.getValue();

        // Check if the player has enough money
        if (player.getCharacter().getMoney() < price) {
            System.out.println("\nYou do not have enough money.");
            return;
        }

        // Check if the item is worth buying based on existing inventory
        boolean shouldBuy = true;
        for (Item existingItem : player.getInventory().getItems()) {
            if (existingItem.getClass().getSuperclass().equals(item.getClass().getSuperclass())) {
                if (existingItem.getValue() >= item.getValue()) {
                    Inventory.printBetterItemWarning();
                    shouldBuy = false;
                    break;
                }
            }
        }

        if (shouldBuy) {
            player.getInventory().addItem(item);
            player.getCharacter().setMoney(player.getCharacter().getMoney() - price);
            System.out.println("Thank you for your purchase!");
        }
    }

    private int getItemChoiceFromUser() {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter the number of the item you wish to purchase or 0 to exit: ");
                String userInput = input.nextLine();
                int choice = Integer.parseInt(userInput.trim());

                if (choice < 0 || choice > ITEMS_FOR_SALE.size()) {
                    System.out.println("Invalid choice. Please select a valid item number.");
                } else {
                    return choice;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}

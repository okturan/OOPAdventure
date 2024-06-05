package items;

import java.util.ArrayList;
import java.util.List;

import items.armor.Rags;
import items.weapons.Fists;


public class ItemFactory {

    public static List<Item> createStarterItems() {
        List<Item> starterItems = new ArrayList<>();
        starterItems.add(new Fists());
        starterItems.add(new Rags());
        return starterItems;
    }
}

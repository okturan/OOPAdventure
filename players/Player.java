package players;

import characters.GameCharacter;
import items.Inventory;

public class Player {

    private final String userName;
    private final GameCharacter character;
    private final Inventory inventory;

    public Player(String userName,
                  GameCharacter character) {
        this.userName = userName;
        this.character = character;
        this.inventory = new Inventory();
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public String getUserName() {
        return userName;
    }

    public Inventory getInventory() {
        return inventory;
    }
}

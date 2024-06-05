package game;

import characters.CharacterFactory;
import characters.GameCharacter;
import locations.combat.Cave;
import locations.combat.CombatLocation;
import locations.combat.Forest;
import locations.combat.Mine;
import locations.combat.River;
import locations.safe.Shop;
import players.Player;
import players.UserInteraction;

public class Game {

    private final UserInteraction userInteraction;
    private boolean gameOver;
    private Player player;
    private Cave cave;
    private Forest forest;
    private River river;
    private Mine mine;
    private Shop shop;

    public Game() {
        this.userInteraction = new UserInteraction();
    }

    public void initializeGame() {
        selectCharacter();
        displayCharacter();
        initializeLocations();
    }

    private void selectCharacter() {
        String playerName = userInteraction.getPlayerName();
        GameCharacter[] characters = CharacterFactory.createCharacters();
        GameCharacter chosenCharacter = userInteraction.chooseCharacter(characters);
        player = new Player(playerName, chosenCharacter);
    }

    private void displayCharacter() {
        userInteraction.displayChosenCharacter(player.getCharacter());
    }

    private void initializeLocations() {
        cave = new Cave();
        forest = new Forest();
        river = new River();
        mine = new Mine();
        shop = new Shop();
    }

    public void runGame() {
        displayWelcomeMessage();

        while (!gameOver) {
            int choice = userInteraction.displayAndGetMenuChoice();
            handleMenuChoice(choice);
        }
    }

    private void displayWelcomeMessage() {
        System.out.println("\n########################");
        System.out.printf("Welcome, %s %s! %n",
                player.getCharacter().getClass().getSimpleName(),
                player.getUserName());
        System.out.println("########################");
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                player.getInventory().listItems();
                break;
            case 2:
                shop.enterLocation(player);
                break;
            case 3:
                combat(cave);
                break;
            case 4:
                combat(forest);
                break;
            case 5:
                combat(river);
                break;
            case 6:
                combat(mine);
                break;
            case 7:
                System.out.println("Exiting the game...");
                gameOver = true;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void combat(CombatLocation location) {
        location.enterLocation(player);
        if (player.getCharacter().getHealth() <= 0) {
            gameOver = true; // Set gameOver to true if the player is defeated
        } else {
            checkGameWinCondition();
        }
    }

    private void checkGameWinCondition() {
        if (cave.isCleared() && forest.isCleared() && river.isCleared() && mine.isCleared()) {
            System.out.println("\nCongratulations! You have collected all rewards and won the game!");
            gameOver = true;
        }
    }
}

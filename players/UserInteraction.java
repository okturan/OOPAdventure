package players;

import java.util.Scanner;

import characters.GameCharacter;

public class UserInteraction {

    private static final int FIGHT_OPTION = 1;
    private static final int FLEE_OPTION = 2;
    private static final Scanner input = new Scanner(System.in);

    public String getPlayerName() {
        System.out.println("What is your name, adventurer?");
        return input.nextLine();
    }

    public GameCharacter chooseCharacter(GameCharacter[] characters) {
        displayCharacters(characters);
        System.out.print("Choose your character: ");
        int choice = getInputInRange(1, characters.length);
        return characters[choice - 1];
    }

    public void displayCharacters(GameCharacter[] characters) {
        System.out.println("Available Characters:");
        System.out.println("=====================");
        for (int i = 0; i < characters.length; i++) {
            System.out.printf("%d. ", i + 1);
            displayCharacterDetails(characters[i]);
        }
    }

    private int getInputInRange(int min, int max) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(input.nextLine());
                if (choice >= min && choice <= max) {
                    break;
                } else {
                    System.out.printf("Invalid choice. Please choose a number between %d and %d:%n ", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid input. Please enter a number between %d and %d:%n", min, max);
            }
        }
        return choice;
    }

    private void displayCharacterDetails(GameCharacter character) {
        System.out.printf("%-8s-> Damage: %-1d | Health: %-2d | Money: %-2d%n", character.getClass().getSimpleName(),
                character.getDamage(),
                character.getHealth(),
                character.getMoney());
    }

    public void displayChosenCharacter(GameCharacter chosenCharacter) {
        System.out.println("\nYou have chosen:");
        displayCharacterDetails(chosenCharacter);
    }

    public boolean getFightOrFleeDecision() {
        System.out.print("\t(1) Fight (2) Flee : ");
        int choice = getInputInRange(FIGHT_OPTION, FLEE_OPTION);
        return choice == FIGHT_OPTION; // Return true if the player chooses to fight
    }

    public int displayAndGetMenuChoice() {
        System.out.println("\nPlease select an option:");
        System.out.println("1. List my items");
        System.out.println("2. Visit shop");
        System.out.println("3. Go to Cave");
        System.out.println("4. Go to Forest");
        System.out.println("5. Go to River");
        System.out.println("6. Go to Mine");
        System.out.println("7. Exit");

        System.out.print("Enter your choice: ");
        return getInputInRange(1, 7);
    }
}

# Simple Adventure Game

This project is a simple text-based adventure game created for a coding assignment. The goal of the game is to explore different locations, fight enemies, collect items, and ultimately win by clearing all combat areas.

## How to Run

Clone the repository:

```bash
git clone https://github.com/yourusername/simple-adventure-game.git
cd simple-adventure-game
```

Compile the code:

```bash
javac -d ../out/OOPAdventure Main.java
```

Run the game:

```bash
java -cp ../out/OOPAdventure/ Main
```

## Project Structure

- **src/Main.java**: Entry point of the application.
- **src/game/Game.java**: Manages the main game loop and initialization.
- **src/players/Player.java**: Represents the player with attributes like username, character, and inventory.
- **src/players/UserInteraction.java**: Handles user input and interactions.
- **src/characters/**: Contains various character classes (e.g., Samurai, Knight, Archer).
- **src/items/**: Defines item-related classes including weapons, armor, and drops.
- **src/locations/**: Includes safe and combat location classes.
- **src/enemies/**: Defines enemy types encountered in combat locations.

## Classes Overview

### Player and User Interaction

- **Player**: Holds player's information such as username, chosen character, and inventory.
- **UserInteraction**: Facilitates interaction with the player through console inputs and outputs.

### Game Management

- **Game**: Initializes the game, manages the game loop, and handles player actions based on menu choices.

### Locations

#### Safe Locations:

- **Shop**: Allows players to buy items.
- **BaseCamp**: Restores player's health.

#### Combat Locations:

- **Cave, Forest, River, Mine**: Different combat zones where players can fight enemies and collect loot.

### Items

- **Weapons**: Pistol, Sword, Rifle, etc.
- **Armor**: Light, Medium, Heavy, etc.
- **Drops**: Food, Water, Firewood.

### Characters

- **Playable Characters**: Samurai, Knight, Archer.
- **Enemies**: Zombie, Vampire, Bear, Snake.

## Gameplay

- **Character Selection**: Choose your character from available options.
- **Exploration**: Navigate through different locations using the menu.
- **Combat**: Engage in battles with enemies in combat locations.
- **Shopping**: Buy items from the shop to enhance your abilities.
- **Winning Condition**: Clear all combat locations to win the game.

## Notes

This project is intended for educational purposes and may not include advanced features or optimizations. Contributions are welcome to improve the code quality and add new features.

Enjoy your adventure!

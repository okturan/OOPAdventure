package characters;

public class CharacterFactory {

    public static GameCharacter[] createCharacters() {
        Samurai samurai = new Samurai();
        Knight knight = new Knight();
        Archer archer = new Archer();

        return new GameCharacter[]{samurai, knight, archer};
    }
}

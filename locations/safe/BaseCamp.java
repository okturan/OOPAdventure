package locations.safe;

import players.Player;

public class BaseCamp extends SafeLocation {

    @Override
    public void enterLocation(Player player) {
        player.getCharacter().setHealth(player.getCharacter().getMaxHealth());
        System.out.println(
                "💖 Your health has been fully restored to: " + player.getCharacter().getHealth());
    }
}

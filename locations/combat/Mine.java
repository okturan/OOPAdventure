package locations.combat;

import java.util.Random;

import enemies.Snake;
import items.Item;

public class Mine extends CombatLocation {

    @Override
    protected Snake createEnemy() {
        return new Snake();
    }

    @Override
    protected Item getLootItem() {
        return null;
    }

    @Override
    public int getNumEnemies() {
        return new Random().nextInt(5) + 1;
    }
}

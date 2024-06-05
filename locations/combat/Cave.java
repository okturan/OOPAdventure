package locations.combat;

import enemies.Zombie;
import items.drops.Food;

public class Cave extends CombatLocation {

    @Override
    protected Zombie createEnemy() {
        return new Zombie();
    }

    @Override
    protected Food getLootItem() {
        return new Food();
    }
}

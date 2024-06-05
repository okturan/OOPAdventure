package locations.combat;

import enemies.Vampire;
import items.drops.Firewood;

public class Forest extends CombatLocation {

    @Override
    protected Vampire createEnemy() {
        return new Vampire();
    }

    @Override
    protected Firewood getLootItem() {
        return new Firewood();
    }
}

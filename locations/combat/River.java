package locations.combat;

import enemies.Bear;
import items.drops.Water;

public class River extends CombatLocation {

    @Override
    protected Bear createEnemy() {
        return new Bear();
    }

    @Override
    protected Water getLootItem() {
        return new Water();
    }
}

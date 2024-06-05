package enemies;

public class Zombie extends Enemy {

    private static final String ZOMBIE_EMOJI = "🧟";

    public Zombie() {
        super(3, 10, 4, ZOMBIE_EMOJI);
    }
}

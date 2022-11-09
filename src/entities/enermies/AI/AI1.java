package entities.enermies.AI;

public class AI1 extends AI {
    /**
     * Calculate direction.
     *
     * @return direction
     */
    @Override
    public int calculateDirection() {
        return random.nextInt(4);
    }
}

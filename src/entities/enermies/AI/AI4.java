package entities.enermies.AI;

import entities.character.Bomber;
import entities.enermies.Enemy;

public class AI4 extends AI3 {
    /**
     * Constructor AI4 2 parameters.
     *
     * @param b - b
     * @param e - e
     */
    public AI4(Bomber b, Enemy e) {
        super(b, e);
    }

    /**
     * Calculate direction.
     *
     * @return direction
     */
    @Override
    public int calculateDirection() {
        if (calcCol() != -1) {
            if (Math.abs(this.bomber.getX() - this.enermy.getX())
                    < Math.abs(this.bomber.getY() - this.enermy.getY()))
                return calcCol();
        }
        if (calcRow() != -1) {
            return calcRow();
        } else if (calcCol() != -1) {
            return calcCol();
        }
        return -1;
    }
}
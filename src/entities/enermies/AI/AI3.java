package entities.enermies.AI;

import entities.character.Bomber;
import entities.enermies.Enemy;
import graphics.Sprite;

public class AI3 extends AI2 {
    /**
     * Constructor AI3 2 parameters.
     *
     * @param b - b
     * @param e - e
     */
    public AI3(Bomber b, Enemy e) {
        super(b, e);
    }

    /**
     * Calculate direction row.
     *
     * @return direction
     */
    @Override
    public int calcRow() {
        if ((int) this.bomber.getX() / Sprite.DEFAULT_SIZE
                < (int) this.enermy.getX() / Sprite.DEFAULT_SIZE) {
            return 2;
        } else if ((int) this.bomber.getX() / Sprite.DEFAULT_SIZE
                > (int) this.enermy.getX() / Sprite.DEFAULT_SIZE) {
            return 1;
        } else return -1;
    }

    /**
     * Calculate direction column.
     *
     * @return direction
     */
    @Override
    public int calcCol() {
        if ((int) this.bomber.getY() / Sprite.DEFAULT_SIZE
                < (int) this.enermy.getY() / Sprite.DEFAULT_SIZE) {
            if (!this.enermy.canMoveUp()) {
                return -1;
            }
            return 3;
        } else if ((int) this.bomber.getY() / Sprite.DEFAULT_SIZE
                > (int) this.enermy.getY() / Sprite.DEFAULT_SIZE) {
            return 0;
        }
        return -1;
    }
}

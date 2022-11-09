package entities.enermies.AI;

import entities.character.Bomber;
import entities.enermies.Enemy;
import graphics.Sprite;

public class AI2 extends AI {
    protected Bomber bomber;

    protected Enemy enermy;

    /**
     * Constructor AI@ 2 parameters.
     *
     * @param b - b
     * @param e - e
     */
    public AI2(Bomber b, Enemy e) {
        this.bomber = b;
        this.enermy = e;
    }

    /**
     * Calculate direction row.
     *
     * @return direction
     */
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
    public int calcCol() {
        if ((int) this.bomber.getY() / Sprite.DEFAULT_SIZE
                < (int) this.enermy.getY() / Sprite.DEFAULT_SIZE) {
            return 3;
        } else if ((int) this.bomber.getY() / Sprite.DEFAULT_SIZE
                > (int) this.enermy.getY() / Sprite.DEFAULT_SIZE) {
            return 0;
        }
        return -1;
    }

    /**
     * Calculate direction.
     *
     * @return direction
     */
    @Override
    public int calculateDirection() {
        if (calcCol() != -1) {
            return calcCol();
        }
        if (calcRow() != -1) {
            return calcRow();
        }
        return -1;
    }

    public Bomber getBomber() {
        return bomber;
    }
}
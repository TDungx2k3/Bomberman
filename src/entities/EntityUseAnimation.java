package entities;

import graphics.Screen;

public abstract class EntityUseAnimation extends Entity {
    protected static int _animate = 0;

    protected final int MAX_ANIMATE = 7500;

    /**
     * Animate.
     */
    protected void animate() {
        if (_animate < MAX_ANIMATE) {
            _animate++;
        } else {
            _animate = 0;
        }
    }

    /**
     * Move.
     */
    public void move() {

    }
}

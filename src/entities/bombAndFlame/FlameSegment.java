package entities.bombAndFlame;

import entities.Entity;
import entities.character.Bomber;
import entities.enermies.Enemy;
import graphics.Screen;
import graphics.Sprite;

public class FlameSegment extends Entity {
    protected boolean _last;

    /**
     * Constructor FlameSegment 4 parameters.
     *
     * @param x         - x
     * @param y         - y
     * @param direction - direction
     * @param last      - last
     */
    public FlameSegment(int x, int y, int direction, boolean last) {
        _x = x;
        _y = y;
        _last = last;
        switch (direction) {
            case 0:
                if (!last) {
                    _sprite = Sprite.explosion_vertical2;
                } else {
                    _sprite = Sprite.explosion_vertical_top_last2;
                }
                break;
            case 1:
                if (!last) {
                    _sprite = Sprite.explosion_horizontal2;
                } else {
                    _sprite = Sprite.explosion_horizontal_right_last2;
                }
                break;
            case 2:
                if (!last) {
                    _sprite = Sprite.explosion_vertical2;
                } else {
                    _sprite = Sprite.explosion_vertical_down_last2;
                }
                break;
            case 3:
                if (!last) {
                    _sprite = Sprite.explosion_horizontal2;
                } else {
                    _sprite = Sprite.explosion_horizontal_left_last2;
                }
                break;
        }
    }

    /**
     * Render.
     *
     * @param screen - screen
     */
    @Override
    public void render(Screen screen) {
        int xt = (int) _x * 16;
        int yt = (int) _y * 16;
        if (!super.isRemoved()) {
            screen.renderEntity(xt, yt, this);
        }
    }

    /**
     * Update.
     */
    @Override
    public void update() {

    }

    /**
     * Check collision.
     *
     * @param e - e
     * @return true if collided, false if not
     */
    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomber) {
            ((Bomber) e).kill();
        }
        if (e instanceof Enemy) {
            ((Enemy) e).kill();
        }
        return true;
    }

    /**
     * Check can through.
     *
     * @param e - e
     * @return true if can through, false if not
     */
    @Override
    public boolean canThrough(Entity e) {
        return false;
    }
}
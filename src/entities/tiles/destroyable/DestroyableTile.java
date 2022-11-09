package entities.tiles.destroyable;

import java.util.Objects;

import entities.Entity;
import entities.bombAndFlame.Bomb;
import entities.bombAndFlame.Flame;
import entities.character.Bomber;
import entities.enermies.Ghost;
import entities.enermies.Kondoria;
import entities.enermies.Minvo;
import entities.tiles.Tile;
import graphics.Sprite;

public class DestroyableTile extends Tile {
    protected boolean isDestroyed = false;

    protected Sprite belowSprite = Sprite.grass;

    protected int animation = 0;

    protected final int maxAnimation = 7500;

    protected int timeAnimation = 30;

    protected final int TIMEANIMATION = timeAnimation;

    /**
     * Constructor DestroyableTile 3 parameters.
     *
     * @param x      - x
     * @param y      - y
     * @param sprite - sprite
     */
    public DestroyableTile(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    /**
     * Update.
     */
    @Override
    public void update() {
        if (isDestroyed) {
            if (animation < maxAnimation) {
                animation++;
            } else {
                animation = 0;
            }
            if (timeAnimation >= 0) {
                timeAnimation--;
            } else {
                this.remove();
            }
        }
    }

    /**
     * Check collision.
     *
     * @param e - e
     * @return true if collided, false if not
     */
    @Override
    public boolean collide(Entity e) {
        if (e instanceof Flame) {
            isDestroyed = true;
        }
        if (e instanceof Kondoria || e instanceof Minvo || e instanceof Ghost) {
            return true;
        }
        if (Objects.equals(this._sprite, Sprite.grass)) {
            return true;
        }
        if (isWallPass && e instanceof Bomber) {
            return true;
        }
        return false;
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

    /**
     * Add below sprite.
     *
     * @param sprite - sprite
     */
    public void addBelowSprite(Sprite sprite) {
        belowSprite = sprite;
    }
}

package entities.tiles;

import entities.Entity;
import graphics.Sprite;

public class Wall extends Tile {
    /**
     * Constructor Wall 3 parameters.
     *
     * @param x      - x
     * @param y      - y
     * @param sprite - sprite
     */
    public Wall(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    /**
     * Constructor Wall 2 parameters.
     *
     * @param x - x
     * @param y - y
     */
    public Wall(int x, int y) {
        super(x, y, Sprite.wall);
    }

    /**
     * Check can through.
     *
     * @param e - e
     * @return true if can through, false if not
     */
    @Override
    public boolean canThrough(Entity e) {
        return isWallPass;
    }
}

package entities.tiles;

import entities.Entity;
import graphics.Sprite;

public class Grass extends Tile {
    /**
     * Constructor Grass 3 parameters.
     *
     * @param x      - x
     * @param y      - y
     * @param sprite - sprite
     */
    public Grass(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    /**
     * Constructor Grass 2 parameters.
     *
     * @param x - x
     * @param y - y
     */
    public Grass(int x, int y) {
        super(x, y, Sprite.grass);
    }

    /**
     * Check can through.
     *
     * @param e - e
     * @return true if can through, false if not
     */
    @Override
    public boolean canThrough(Entity e) {
        return true;
    }

    /**
     * Check collision.
     *
     * @param e - e
     * @return true if collided, false if not
     */
    @Override
    public boolean collide(Entity e) {
        return true;
    }
}

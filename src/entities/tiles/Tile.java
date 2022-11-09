package entities.tiles;

import entities.Entity;
import entities.character.Bomber;
import entities.enermies.Ghost;
import entities.enermies.Kondoria;
import entities.enermies.Minvo;
import graphics.Screen;
import graphics.Sprite;

public class Tile extends Entity {
    protected static boolean isWallPass = false;

    /**
     * Constructor Tile 3 parameters.
     *
     * @param x      - x
     * @param y      - y
     * @param sprite - sprite
     */
    public Tile(int x, int y, Sprite sprite) {
        this._x = x;
        this._y = y;
        this._sprite = sprite;
    }

    /**
     * Render.
     *
     * @param screen - screen
     */
    @Override
    public void render(Screen screen) {
        screen.renderEntity((int) this._x * Sprite.DEFAULT_SIZE,
                (int) this._y * Sprite.DEFAULT_SIZE, this);
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
        if (e instanceof Kondoria || e instanceof Minvo || e instanceof Ghost) {
            return true;
        }
        if (isWallPass && e instanceof Bomber) {
            return true;
        }
        return false;
    }

    /**
     * Pass wall.
     */
    public static void passWall() {
        isWallPass = true;
    }
}

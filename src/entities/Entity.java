package entities;

import entities.Entity;
import entities.character.Bomber;
import game.Board;
import graphics.Screen;
import graphics.Sprite;
import javafx.scene.image.Image;

public abstract class Entity {
    protected double _x;

    protected double _y;

    protected boolean _removed = false;

    protected Sprite _sprite;

    protected Board board;

    public abstract void update();

    public abstract void render(Screen screen);

    /**
     * Remove.
     */
    public void remove() {
        _removed = true;
    }

    /**
     * Is removed.
     *
     * @return removed
     */
    public boolean isRemoved() {
        return _removed;
    }

    public Sprite getSprite() {
        return _sprite;
    }

    public abstract boolean canThrough(Entity e);

    public double getX() {
        return _x;
    }

    public double getY() {
        return _y;
    }

    /**
     * Get x tile.
     *
     * @return x tile
     */
    public int getXTile() {
        return (int) ((_x + Sprite.DEFAULT_SIZE / 2) / Sprite.DEFAULT_SIZE);
    }

    /**
     * Get y tile.
     *
     * @return y tile
     */
    public int getYTile() {
        return (int) ((_y + Sprite.DEFAULT_SIZE
                / 2) / Sprite.DEFAULT_SIZE);
    }

    public abstract boolean collide(Entity e);
}

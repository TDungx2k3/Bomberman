package entities.character;

import entities.EntityUseAnimation;
import game.Board;
import game.Game;
import graphics.Screen;
import graphics.Sprite;

public abstract class Character extends EntityUseAnimation {
    protected int direction = -1;

    protected boolean isAlive = true;

    protected boolean isMoving = false;

    protected Board board;

    protected int timeAnimate = 40;

    @Override
    public abstract void update();

    /**
     * Remove.
     */
    @Override
    public void remove() {
        _removed = true;
    }

    /**
     * Is removed.
     *
     * @return removed
     */
    @Override
    public boolean isRemoved() {
        return _removed;
    }

    @Override
    public abstract void render(Screen screen);

    /**
     * Check can move right.
     *
     * @return true
     */
    public boolean canMoveRight() {
        return true;
    }

    /**
     * Check can move left.
     *
     * @return true
     */
    public boolean canMoveLeft() {
        return true;
    }

    /**
     * Check can move up.
     *
     * @return true
     */
    public boolean canMoveUp() {
        return true;
    }

    /**
     * Check can move down.
     *
     * @return true
     */
    public boolean canMoveDown() {
        return true;
    }

    /**
     * Move right.
     *
     * @param xa - xa
     * @param ya - ya
     */
    public void moveRight(double xa, double ya) {
        this._x += xa;
    }

    /**
     * Move left.
     *
     * @param xa - xa
     * @param ya - ya
     */
    public void moveLeft(double xa, double ya) {
        this._x += xa;
    }

    /**
     * Move up.
     *
     * @param xa - xa
     * @param ya - ya
     */
    public void moveUp(double xa, double ya) {
        this._y += ya;
    }

    /**
     * Move down.
     *
     * @param xa - xa
     * @param ya - ya
     */
    public void moveDown(double xa, double ya) {
        this._y += ya;
    }

    public abstract void kill();

    public abstract void die();

    /**
     * Get x message.
     *
     * @return x message
     */
    public double getXMessage() {
        return _x * Game.scale + _sprite.SIZE / 2 * Game.scale;
    }

    /**
     * Get y message.
     *
     * @return y message
     */
    public double getYMessage() {
        return _y * Game.scale - _sprite.SIZE / 2 * Game.scale;
    }

    public boolean getIsMoving() {
        return isMoving;
    }
}

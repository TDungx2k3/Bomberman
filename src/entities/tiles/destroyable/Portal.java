package entities.tiles.destroyable;

import java.util.Objects;

import entities.Entity;
import entities.bombAndFlame.Flame;
import entities.character.Bomber;
import entities.enermies.Ghost;
import entities.enermies.Kondoria;
import entities.enermies.Minvo;
import game.Board;
import graphics.Screen;
import graphics.Sprite;

public class Portal extends DestroyableTile {
    /**
     * Constructor Portal 3 parameters.
     *
     * @param x      - x
     * @param y      - y
     * @param sprite - sprite
     */
    public Portal(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    /**
     * Construtor Portal 3 parameters.
     *
     * @param x - x
     * @param y - y
     * @param b - b
     */
    public Portal(int x, int y, Board b) {
        super(x, y, Sprite.brick);
        this.addBelowSprite(Sprite.portal);
        this.board = b;
    }

    /**
     * Update.
     */
    @Override
    public void update() {
        super.update();
    }

    /**
     * Remove.
     */
    @Override
    public void remove() {
        this._removed = true;
        this._sprite = this.belowSprite;
    }

    /**
     * Destroy.
     */
    public void destroy() {
        isDestroyed = true;
    }

    /**
     * Render.
     *
     * @param screen - screen
     */
    @Override
    public void render(Screen screen) {
        int x = (int) this._x;
        int y = (int) this._y;
        if (isDestroyed && !isRemoved()) {
            _sprite = Sprite.movingSprite(Sprite.brick_exploded,
                    Sprite.brick_exploded1, Sprite.brick_exploded2, animation, TIMEANIMATION);
            screen.renderEntityWithBelowSprite(x * Sprite.DEFAULT_SIZE,
                    y * Sprite.DEFAULT_SIZE, this, belowSprite);
        } else {
            screen.renderEntity(x * Sprite.DEFAULT_SIZE,
                    y * Sprite.DEFAULT_SIZE, this);
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
        if (isWallPass && e instanceof Bomber) {
            return true;
        }
        if (e instanceof Kondoria || e instanceof Minvo || e instanceof Ghost) {
            return true;
        }
        if (e instanceof Flame) {
            isDestroyed = true;
        }
        if (Objects.equals(this._sprite, Sprite.portal)) {
            if (Board.detectNoEnemies()) {
                this.board.addPoints(this.board.getTime() * this.board.getLevel());
                this.board.nextLevel();
            } else {

            }
            return true;
        }
        return false;
    }

    public boolean canThrough(Entity e) {
        // TODO Auto-generated method stub

        return false;
    }

}

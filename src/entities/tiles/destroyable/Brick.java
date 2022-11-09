package entities.tiles.destroyable;

import entities.Entity;
import entities.character.Bomber;
import entities.enermies.Doll;
import game.Game;
import graphics.Sprite;
import graphics.Screen;

public class Brick extends DestroyableTile {
    /**
     * Constructor Brick 3 parameters.
     *
     * @param x      - x
     * @param y      - y
     * @param sprite - sprite
     */
    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        this.addBelowSprite(Sprite.grass);
    }

    /**
     * Constructor Brick 2 parameters.
     *
     * @param x - x
     * @param y - y
     */
    public Brick(int x, int y) {
        super(x, y, Sprite.brick);
        this.addBelowSprite(Sprite.grass);
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
     * Check can through.
     *
     * @param e - e
     * @return true if can through, false if not
     */
    public boolean canThrough(Entity e) {
        return true;
    }
}

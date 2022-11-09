package entities.tiles.Item;

import entities.Entity;
import entities.tiles.Tile;
import entities.tiles.destroyable.DestroyableTile;
import graphics.Screen;
import graphics.Sprite;

public class Item extends DestroyableTile {
    private Sprite lastSp = Sprite.grass;

    protected boolean isThrough = false;

    /**
     * Constructor Item 3 parameters.
     *
     * @param x      - x
     * @param y      - y
     * @param sprite - sprite
     */
    public Item(int x, int y, Sprite sprite) {
        super(x, y, sprite);
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
        this.isDestroyed = false;
        this._sprite = this.belowSprite;
        this.belowSprite = lastSp;
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
        return false;
    }
}
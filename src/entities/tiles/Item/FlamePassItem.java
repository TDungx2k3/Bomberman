package entities.tiles.Item;

import java.util.Objects;

import entities.Entity;
import entities.bombAndFlame.Flame;
import entities.character.Bomber;
import game.Music;
import graphics.Sprite;

public class FlamePassItem extends Item {
    /**
     * Constructor FlamePassItem 3 parameters.
     *
     * @param x      - x
     * @param y      - y
     * @param sprite - sprite
     */
    public FlamePassItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    /**
     * Constructor FlamePassItem 2 parameters.
     *
     * @param x - x
     * @param y - y
     */
    public FlamePassItem(int x, int y) {
        super(x, y, Sprite.brick);
        this.addBelowSprite(Sprite.powerup_flamepass);
    }

    /**
     * Check collision.
     *
     * @param e - e
     * @return true if collided, false if not
     */
    @Override
    public boolean collide(Entity e) {
        if (Objects.equals(this._sprite, Sprite.brick)) {
            if (e instanceof Flame) {
                isDestroyed = true;
            }
            return false;
        }
        if (Objects.equals(this._sprite, Sprite.powerup_flamepass)) {
            if (e instanceof Bomber) {
                String fileName = "res/musics/ItemSound.wav";
                Music sound = new Music();
                sound.setFile(fileName);
                sound.play();
                ((Bomber) e).passFlame();
                remove();
            }
            return true;
        }
        return true;
    }
}

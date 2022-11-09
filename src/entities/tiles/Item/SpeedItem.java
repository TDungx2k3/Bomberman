package entities.tiles.Item;

import entities.Entity;
import entities.bombAndFlame.Flame;
import entities.character.Bomber;
import game.Game;
import game.Music;
import graphics.Sprite;

import java.util.Objects;

public class SpeedItem extends Item {
    /**
     * Construtor SpeedItem 3 parameteres.
     *
     * @param x      - x
     * @param y      - y
     * @param sprite - sprite
     */
    public SpeedItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    /**
     * Constructor SpeedItem 2 parameters.
     *
     * @param x - x
     * @param y - y
     */
    public SpeedItem(int x, int y) {
        super(x, y, Sprite.brick);
        this.addBelowSprite(Sprite.powerup_speed);
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
            if (isWallPass && e instanceof Bomber) {
                return true;
            }
            return false;
        }
        if (Objects.equals(this._sprite, Sprite.powerup_speed)) {
            if (e instanceof Bomber) {
                String fileName = "res/musics/ItemSound.wav";
                Music sound = new Music();
                sound.setFile(fileName);
                sound.play();
                Game.addBomberSpeed(0.5);
                remove();
            }
            return true;
        }
        return true;
    }
}

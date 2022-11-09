package entities.tiles.Item;

import entities.Entity;
import entities.bombAndFlame.Flame;
import entities.character.Bomber;
import game.Game;
import game.Music;
import graphics.Sprite;

import java.util.Objects;

public class BombItem extends Item {
    /**
     * Constructor BombItem 3 parameters.
     *
     * @param x      - x
     * @param y      - y
     * @param sprite - sprite
     */
    public BombItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    /**
     * Constructor BombItem 2 parameters.
     *
     * @param x - x
     * @param y - y
     */
    public BombItem(int x, int y) {
        super(x, y, Sprite.brick);
        this.addBelowSprite(Sprite.powerup_bombs);
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
        if (Objects.equals(this._sprite, Sprite.powerup_bombs)) {
            if (e instanceof Bomber) {
                Game.addBombRate(1);
                String fileName = "res/musics/ItemSound.wav";
                Music sound = new Music();
                sound.setFile(fileName);
                sound.play();
                remove();
            }
            return true;
        }
        return true;
    }
}

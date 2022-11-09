package entities.tiles.Item;

import java.util.Objects;

import entities.Entity;
import entities.bombAndFlame.Flame;
import entities.character.Bomber;
import game.Game;
import game.Music;
import graphics.Sprite;

public class BombPassItem extends Item {
    /**
     * Constructor BombPassItem 3 parameters.
     *
     * @param x      - x
     * @param y      - y
     * @param sprite - sprite
     */
    public BombPassItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    /**
     * Constructor BombPassItem 2 parameters.
     *
     * @param x - x
     * @param y - y
     */
    public BombPassItem(int x, int y) {
        super(x, y, Sprite.brick);
        this.addBelowSprite(Sprite.powerup_bombpass);
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
        if (Objects.equals(this._sprite, Sprite.powerup_bombpass)) {
            if (e instanceof Bomber) {
                Bomber.passBomb();
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

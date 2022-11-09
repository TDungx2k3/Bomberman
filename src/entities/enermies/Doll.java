package entities.enermies;

import entities.Entity;
import entities.enermies.AI.AI2;
import entities.enermies.AI.AI3;
import entities.tiles.Message;
import entities.tiles.Wall;
import entities.tiles.destroyable.Brick;
import game.Board;
import game.Game;
import game.Music;
import graphics.Sprite;

import java.awt.*;

public class Doll extends Enemy {
    public static final int DOLLPOINT = 200;

    public static final double DOLLSPEED = 0.8;

    /**
     * Constructor Doll 3 parameters.
     *
     * @param x     - x
     * @param y     - y
     * @param board - board
     */
    public Doll(int x, int y, Board board) {
        super(x, y, board, Sprite.doll_dead, DOLLSPEED, DOLLPOINT);
        _sprite = Sprite.doll_left1;
        ai = new AI3(board.getBomber(), this);
        direction = ai.calculateDirection();
    }

    /**
     * Choosing sprite.
     */
    @Override
    public void choosingSprite() {
        switch (direction) {
            case 0:
            case 1:
                if (isMoving) {
                    _sprite = Sprite.movingSprite(Sprite.doll_right1,
                            Sprite.doll_right2, Sprite.doll_right3, _animate, 60);
                } else {
                    _sprite = Sprite.doll_left1;
                }
                break;
            case 2:
            case 3:
                if (isMoving) {
                    _sprite = Sprite.movingSprite(Sprite.doll_left1,
                            Sprite.doll_left2, Sprite.doll_left3, _animate, 60);
                } else {
                    _sprite = Sprite.doll_left1;
                }
                break;
        }
    }

    /**
     * Kill.
     */
    @Override
    public void kill() {
        if (!isAlive) {
            return;
        }
        isAlive = false;
        String soundTrack = "res/musics/enemydie.wav";
        Music sound = new Music();
        sound.setFile(soundTrack);
        sound.play();
        board.addPoints(points);
        Message message = new Message("+" + DOLLPOINT, getXMessage(),
                getYMessage(), 2, Color.black, 20);
        board.addMessage(message);
    }

    /**
     * Check can through.
     *
     * @param e - e
     * @return true if can through, false if not
     */
    @Override
    public boolean canThrough(Entity e) {
        if (e instanceof Wall) {
            return true;
        }
        if (e instanceof Brick) {
            return true;
        }
        return false;
    }

    /**
     * Check can move right.
     *
     * @return true if can move right, false if not
     */
    @Override
    public boolean canMoveRight() {
        Entity a = board.getEntity((this._x + 4)
                / Game.TILES_SIZE, (this._y) / Game.TILES_SIZE, this);
        return a.collide(this);
    }

    /**
     * Check can move left.
     *
     * @return true if can move left, false if not
     */
    @Override
    public boolean canMoveLeft() {
        Entity a = board.getEntity((this._x - 4)
                / Game.TILES_SIZE, (this._y) / Game.TILES_SIZE, this);
        return a.collide(this);
    }

    /**
     * Check can move up.
     *
     * @return true if can move up, false if not
     */
    @Override
    public boolean canMoveUp() {
        Entity a = board.getEntity(this._x
                / Game.TILES_SIZE, (this._y - 4) / Game.TILES_SIZE, this);
        return a.collide(this);
    }

    /**
     * Check can move down.
     *
     * @return true if can move down, false if not
     */
    @Override
    public boolean canMoveDown() {
        Entity a = board.getEntity(this._x
                / Game.TILES_SIZE, (this._y + 4) / Game.TILES_SIZE, this);
        return a.collide(this);
    }
}

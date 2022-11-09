package entities.enermies;

import java.awt.Color;

import entities.Entity;
import entities.enermies.AI.AI3;
import entities.enermies.AI.AI4;
import entities.tiles.Message;
import game.Board;
import game.Music;
import graphics.Sprite;

public class Ghost extends Enemy {
    public static final int GHOSTPOINT = 300;

    public static final double GHOSTSPEED = 1;

    /**
     * Constructor Ghost 3 parameters.
     *
     * @param x     - x
     * @param y     - y
     * @param board - board
     */
    public Ghost(int x, int y, Board board) {
        super(x, y, board, Sprite.kondoria_dead, GHOSTSPEED, GHOSTPOINT);
        _sprite = Sprite.minvo_left1;
        ai = new AI4(board.getBomber(), this);
        direction = ai.calculateDirection();
    }

    /**
     * Choosing sprite.
     */
    @Override
    public void choosingSprite() {
        switch (direction) {
            case 0:
                if (this._x > this.board.getBomber().getX()) {
                    _sprite = Sprite.movingSprite(Sprite.ghost_moving_right1,
                            Sprite.ghost_moving_right2, _animate, 60);
                } else {
                    _sprite = Sprite.movingSprite(Sprite.ghost_moving_left1,
                            Sprite.ghost_moving_left2, _animate, 60);
                }
                break;
            case 1:
                if (isMoving) {
                    _sprite = Sprite.ghost_right;
                } else {
                    Sprite.movingSprite(Sprite.ghost_moving_right1,
                            Sprite.ghost_moving_right2, _animate, 60);
                }
                break;
            case 3:
                if (this._x > this.board.getBomber().getX()) {
                    _sprite = Sprite.movingSprite(Sprite.ghost_moving_right1,
                            Sprite.ghost_moving_right2, _animate, 60);
                } else {
                    _sprite = Sprite.movingSprite(Sprite.ghost_moving_left1,
                            Sprite.ghost_moving_left2, _animate, 60);
                }
                break;
            case 2:
                if (isMoving) {
                    _sprite = Sprite.ghost_left;
                } else {
                    Sprite.movingSprite(Sprite.ghost_moving_right1,
                            Sprite.ghost_moving_right2, _animate, 60);
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
        board.addPoints(points);
        String soundTrack = "res/musics/enemydie.wav";
        Music sound = new Music();
        sound.setFile(soundTrack);
        sound.play();
        Message message = new Message("+" + GHOSTPOINT, getXMessage(),
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
        return true;
    }
}

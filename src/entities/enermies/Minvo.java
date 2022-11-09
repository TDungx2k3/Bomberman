package entities.enermies;

import entities.Entity;
import entities.enermies.AI.AI2;
import entities.enermies.AI.AI3;
import entities.tiles.Message;
import entities.tiles.Wall;
import entities.tiles.destroyable.Brick;
import game.Board;
import game.Music;
import graphics.Sprite;

import java.awt.*;

public class Minvo extends Enemy {
    public static final int MINVOPOINT = 250;

    public static final double MINVOSPEED = 0.8;

    /**
     * Constructor Minvo 3 parameters.
     * @param x - x
     * @param y - y
     * @param board - board
     */
    public Minvo(int x, int y, Board board) {
        super(x, y, board, Sprite.kondoria_dead, MINVOSPEED, MINVOPOINT);
        _sprite = Sprite.minvo_left1;
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
                    _sprite = Sprite.movingSprite(Sprite.minvo_right1,
                            Sprite.minvo_right2, Sprite.minvo_right3, _animate, 60);
                } else {
                    _sprite = Sprite.minvo_left1;
                }
                break;
            case 2:
            case 3:
                if (isMoving) {
                    _sprite = Sprite.movingSprite(Sprite.minvo_left1,
                            Sprite.minvo_left2, Sprite.minvo_left3, _animate, 60);
                } else {
                    _sprite = Sprite.minvo_left1;
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
        String soundTrack="res/musics/enemydie.wav";
        Music sound=new Music();
        sound.setFile(soundTrack);
        sound.play();
        Message message = new Message("+" + MINVOPOINT, getXMessage(),
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

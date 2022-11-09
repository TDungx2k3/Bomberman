package entities.enermies;

import entities.Entity;
import entities.enermies.AI.AI1;
import entities.tiles.Message;
import game.Board;
import game.Music;
import graphics.Sprite;

import java.awt.*;

public class Oneal extends Enemy {
    public static final int ONEALPOINT = 150;

    public static final double ONEALSPEED = 0.8;

    /**
     * Constructor Oneal 6 parameters.
     *
     * @param x      - x
     * @param y      - y
     * @param board  - board
     * @param dead   - dead
     * @param speed  - speed
     * @param points - point
     */
    public Oneal(int x, int y, Board board, Sprite dead, double speed, int points) {
        super(x, y, board, dead, speed, points);
        this._sprite = Sprite.oneal_left1;
        this.ai = new AI1();
        this.direction = ai.calculateDirection();
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
        Message message = new Message("+" + ONEALPOINT, getXMessage(),
                getYMessage(), 2, Color.black, 20);
        board.addMessage(message);
    }

    /**
     * Constructor Oneal 3 parameters.
     *
     * @param x     - x
     * @param y     - y
     * @param board - board
     */
    public Oneal(int x, int y, Board board) {
        super(x, y, board, Sprite.oneal_dead, ONEALSPEED, ONEALPOINT);
        this._sprite = Sprite.oneal_left1;
        this.ai = new AI1();
        this.direction = ai.calculateDirection();
    }

    /**
     * Choosing sprite.
     */
    @Override
    public void choosingSprite() {
        switch (this.direction) {
            case 0:
            case 1:
                this._sprite = Sprite.movingSprite(Sprite.oneal_right1,
                        Sprite.oneal_right2, Sprite.oneal_right3, _animate, 60);
                break;
            case 2:
            case 3:
                this._sprite = Sprite.movingSprite(Sprite.oneal_left1,
                        Sprite.oneal_left2, Sprite.oneal_left3, _animate, 60);
                break;

        }
    }

    /**
     * Check can through.
     *
     * @param e - e
     * @return true if can through, false if not
     */
    @Override
    public boolean canThrough(Entity e) {
        return false;
    }
}

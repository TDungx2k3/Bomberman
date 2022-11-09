package entities.enermies;

import entities.Entity;
import entities.enermies.AI.AI1;
import entities.tiles.Message;
import game.Board;
import game.Music;
import graphics.Sprite;

import java.awt.*;

public class Balloon extends Enemy {
    public static final int BALLOONPOINT = 100;

    public static final double BALLOONSPEED = 0.5;

    /**
     * Constructor Balloon 6 parameters.
     *
     * @param x      - x
     * @param y      - y
     * @param board  - board
     * @param dead   - dead
     * @param speed  - speed
     * @param points - point
     */
    public Balloon(int x, int y, Board board, Sprite dead, double speed, int points) {
        super(x, y, board, dead, speed, points);
        this._sprite = Sprite.balloom_left1;
        this.ai = new AI1();
        this.direction = ai.calculateDirection();
    }

    /**
     * Kill.
     */
    @Override
    public void kill() {
        String soundTrack = "res/musics/enemydie.wav";
        Music sound = new Music();
        sound.setFile(soundTrack);
        sound.play();
        if (!isAlive) {
            return;
        }
        board.addPoints(points);
        isAlive = false;
        Message message = new Message("+" + BALLOONPOINT, getXMessage(),
                getYMessage(), 2, Color.black, 20);
        board.addMessage(message);
    }

    /**
     * Constructor Balloon 3 parameters.
     *
     * @param x     - x
     * @param y     - y
     * @param board - board
     */
    public Balloon(int x, int y, Board board) {
        super(x, y, board, Sprite.balloom_dead, BALLOONSPEED, BALLOONPOINT);
        this._sprite = Sprite.balloom_left1;
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
                this._sprite = Sprite.movingSprite(Sprite.balloom_right1,
                        Sprite.balloom_right2, Sprite.balloom_right3, _animate, 60);
                break;
            case 2:
            case 3:
                this._sprite = Sprite.movingSprite(Sprite.balloom_left1,
                        Sprite.balloom_left2, Sprite.balloom_left3, _animate, 60);
                break;

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

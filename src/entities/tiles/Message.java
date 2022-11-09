package entities.tiles;

import entities.Entity;
import graphics.Screen;

import java.awt.*;

public class Message extends Entity {
    protected String message;

    protected int duration;

    protected Color color;

    protected int size;

    /**
     * Constructor Message 6 parameters.
     *
     * @param message  - message
     * @param x        - x
     * @param y        - y
     * @param duration - duration
     * @param color    - color
     * @param size     - size
     */
    public Message(String message, double x, double y, int duration, Color color, int size) {
        _x = x;
        _y = y;
        this.message = message;
        this.duration = duration * 60;
        this.color = color;
        this.size = size;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Update.
     */
    @Override
    public void update() {

    }

    /**
     * Render.
     *
     * @param screen - screen
     */
    @Override
    public void render(Screen screen) {

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

    /**
     * Check collision.
     *
     * @param e - e
     * @return true if collided, false if not
     */
    @Override
    public boolean collide(Entity e) {
        return true;
    }
}

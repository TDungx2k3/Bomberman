package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {
    private boolean[] keys = new boolean[90];

    public boolean up;

    public boolean down;

    public boolean right;

    public boolean left;

    public boolean space;

    /**
     * Update.
     */
    public void update() {
        if (keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W]) {
            up = true;
        } else {
            up = false;
        }
        if (keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S]) {
            down = true;
        } else {
            down = false;
        }
        if (keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A]) {
            left = true;
        } else {
            left = false;
        }
        if (keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D]) {
            right = true;
        } else {
            right = false;
        }
        space = keys[KeyEvent.VK_SPACE];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Key pressed.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    /**
     * Key released.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}

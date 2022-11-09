package menu;

import game.Frame;

import javax.swing.*;

public class Menu extends JMenuBar {
    /**
     * Constructor Menu 1 parameter.
     * @param frame - frame
     */
    public Menu(Frame frame) {
        add(new Game(frame));
        add(new About(frame));
    }
}
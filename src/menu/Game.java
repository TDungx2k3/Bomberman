package menu;

import game.Board;
import game.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Game extends JMenu {
    public Frame frame;

    class MenuActionListener implements ActionListener {
        public Frame frame;

        /**
         * Menu action listener
         *
         * @param frame - frame
         */
        public MenuActionListener(Frame frame) {
            this.frame = frame;
        }

        /**
         * Action performed
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("New game")) {
                System.out.println("You've pressed new game button!");
                frame.startANewGame();
            }
            if (e.getActionCommand().equals("Pause")) {
                System.out.println("You've pressed pause button!");
                frame.pause();
            }
            if (e.getActionCommand().equals("Resume")) {
                System.out.println("You've pressed resume button!");
                frame.resume();
            }
            if (e.getActionCommand().equals("Exit")) {
                System.out.println("You've pressed exit button!");
                System.exit(0);
            }
        }
    }

    /**
     * Constructor Game 1 parameter.
     *
     * @param frame - frame
     */
    public Game(Frame frame) {
        super("Game");
        this.frame = frame;
        /**
         * New game option.
         */
        JMenuItem newGame = new JMenuItem("New game");
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newGame.addActionListener(new MenuActionListener(frame));
        add(newGame);
        /**
         * Pause option.
         */
        JMenuItem pause = new JMenuItem("Pause");
        pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        pause.addActionListener(new MenuActionListener(frame));
        add(pause);
        /**
         * Resume option.
         */
        JMenuItem resume = new JMenuItem("Resume");
        resume.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        resume.addActionListener(new MenuActionListener(frame));
        add(resume);
        /**
         * Exit option.
         */
        JMenuItem exit = new JMenuItem("Exit");
        //exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.CTRL_MASK));
        exit.addActionListener(new MenuActionListener(frame));
        add(exit);
    }
}
package menu;

import game.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class About extends JMenu {
    /**
     * Constructor About 1 parameter.
     *
     * @param frame - frame
     */
    public About(Frame frame) {
        /**
         * How to play.
         */
        super("About");
        JMenuItem instruction = new JMenuItem("How to play?");
        instruction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        instruction.addActionListener(new MenuActionListener(frame));
        add(instruction);
        /**
         * Credits.
         */
        JMenuItem credits = new JMenuItem("Credits");
        credits.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        credits.addActionListener(new MenuActionListener(frame));
        add(credits);
    }

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
            if (e.getActionCommand().equals("How to play?")) {
                new InfoDialog(frame, "How to play?", "Use W, A, S, D or UP, DOWN,RIGHT, LEFT to move.\n" +
                        "Use SPACE to put bombs.", JOptionPane.QUESTION_MESSAGE);
            }
            if (e.getActionCommand().equals("Credits")) {
                new InfoDialog(frame, "Credits", "Write by:\n" +
                        "21020762 Nguyen Cao Duc\n" +
                        "21020753 Dang Tien Dung\n" +
                        "21020755 Nguyen Hoang Duy", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}

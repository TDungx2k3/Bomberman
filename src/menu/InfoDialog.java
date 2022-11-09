package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import game.Frame;

public class InfoDialog implements WindowListener {
    private final Frame frame;

    /**
     * Constructor InfoDialog 4 parameters.
     *
     * @param frame   - frame
     * @param title   - title
     * @param message - message
     * @param option  - option
     */
    public InfoDialog(Frame frame, String title, String message, int option) {
        this.frame = frame;
        JFrame dialog = new JFrame(title);
        JButton button = new JButton("Cancel");
        button.addActionListener(e -> dialog.dispose());
        JButton[] buttons = {button};
        JOptionPane optionPane = new JOptionPane(message, option, 0,
                null, buttons, button);
        dialog.getContentPane().add(optionPane);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.addWindowListener(this);
    }

    /**
     * Window opened.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowOpened(WindowEvent e) {
        frame.pause();
    }

    /**
     * Window closing.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowClosing(WindowEvent e) {

    }

    /**
     * Window closed.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowClosed(WindowEvent e) {
        frame.resume();
    }

    /**
     * Window iconified.
     *
     * @param e the event to be processed
     */
    @Override
    public void windowIconified(WindowEvent e) {

    }

    /**
     * Window deiconified
     *
     * @param e the event to be processed
     */
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    /**
     * Window activated
     *
     * @param e the event to be processed
     */
    @Override
    public void windowActivated(WindowEvent e) {

    }

    /**
     * Window deactivated
     *
     * @param e the event to be processed
     */
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

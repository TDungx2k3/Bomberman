package game;

import menu.Menu;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class Frame extends JFrame {
    public GamePanel gamePane;

    private JPanel containerPane;

    private InfoPanel infoPanel;

    private Game game;

    /**
     * Constructor Frame 0 parameter.
     */
    public Frame() {
        setJMenuBar(new Menu(this));
        containerPane = new JPanel(new BorderLayout());
        gamePane = new GamePanel(this);
        infoPanel = new InfoPanel(gamePane.getGame());
        containerPane.add(infoPanel, BorderLayout.PAGE_START);
        containerPane.add(gamePane, BorderLayout.PAGE_END);
        containerPane.setVisible(true);
        game = gamePane.getGame();
        this.add(containerPane);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        game.start();
    }

    /**
     * Start a new game.
     */
    public void startANewGame() {
        game.getBoard().startANewGame();
    }

    /**
     * Pause.
     */
    public void pause() {
        game.getBoard().pauseGame();
    }

    /**
     * Resume.
     */
    public void resume() {
        game.getBoard().resumeGame();
    }

    /**
     * Set time.
     *
     * @param time - time
     */
    public void setTime(int time) {
        infoPanel.setTime(time);
    }

    /**
     * Set point.
     *
     * @param points - point
     */
    public void setPoints(int points) {
        infoPanel.setPoints(points);
    }

    public GamePanel getGamePane() {
        return gamePane;
    }

    public void setGamePane(GamePanel gamePane) {
        this.gamePane = gamePane;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public void setInfoPanel(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
    }
}
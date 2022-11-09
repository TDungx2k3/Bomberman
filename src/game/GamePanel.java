package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

import graphics.Sprite;

public class GamePanel extends JPanel {
    private Game game;

    /**
     * Constructor GamePanel 1 parameter.
     *
     * @param frame - frame
     */
    public GamePanel(Frame frame) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(Game.WIDTH * Game.scale
                , Game.HEIGHT * Game.scale));
        game = new Game(frame);
        this.add(game);
        game.setVisible(true);
        this.setVisible(true);
        this.setFocusable(true);
    }

    public Game getGame() {
        return game;
    }
}

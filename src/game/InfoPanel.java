package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
    JLabel time;

    JLabel point;

    /**
     * Constructor InfoPanel 1 parameter.
     *
     * @param game - game
     */
    public InfoPanel(Game game) {
        this.setLayout(new GridLayout());
        time = new JLabel("Time : " + game.getTime());
        time.setForeground(Color.white);
        time.setHorizontalAlignment(JLabel.CENTER);
        point = new JLabel("Point : " + game.getPoints());
        point.setForeground(Color.white);
        point.setHorizontalAlignment(JLabel.CENTER);
        this.add(time);
        this.add(point);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(0, 40));
    }

    /**
     * Update.
     *
     * @param time  - time
     * @param point - point
     */
    public void update(int time, int point) {
        this.time.setText("Time : " + time);
        this.point.setText("Point : " + point);
    }

    public void setTime(int t) {
        time.setText("Time: " + t);
    }

    public void setPoints(int t) {
        point.setText("Point: " + t);
    }
}

package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import entities.Entity;
import entities.character.Bomber;
import game.Board;
import game.Game;

public class Screen {
    private int screenWidth;

    private int screenHeight;

    private int _transparentColor = 0xffff00ff;

    public static int startX = 0;

    public static int startY = 0;


    public int[] _pixels;

    /**
     * Constructor Screen 2 parameters.
     *
     * @param screenWidth  - screen width
     * @param screenHeight - screen height
     */
    public Screen(int screenWidth, int screenHeight) {
        super();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        _pixels = new int[screenHeight * screenWidth];
    }

    /**
     * Clear.
     */
    public void clear() {
        for (int i = 0; i < _pixels.length; i++) {
            _pixels[i] = 0;
        }
    }

    /**
     * Draw end game.
     *
     * @param g      - g
     * @param points - point
     */
    public void drawEndGame(Graphics g, int points) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getRealWidth(), getRealHeight());
        Font font = new Font("Arial", Font.PLAIN, 20 * Game.scale);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("GAME OVER", getScreenWidth() * Game.scale,
                getScreenHeight() * Game.scale, g);
        font = new Font("Arial", Font.PLAIN, 10 * Game.scale);
        g.setFont(font);
        g.setColor(Color.yellow);
        drawCenteredString("POINTS: " + points, getRealWidth(),
                getRealHeight() + (Game.TILES_SIZE * 2) * Game.scale, g);
    }

    /**
     * Draw win game.
     *
     * @param g      - g
     * @param points - point
     */
    public void drawWinGame(Graphics g, int points) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getRealWidth(), getRealHeight());
        Font font = new Font("Arial", Font.PLAIN, 20 * Game.scale);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("Congratulation!!!", getScreenWidth() * Game.scale,
                getScreenHeight() * Game.scale, g);
        font = new Font("Arial", Font.PLAIN, 10 * Game.scale);
        g.setFont(font);
        g.setColor(Color.yellow);
        drawCenteredString("POINTS: " + points, getRealWidth(),
                getRealHeight() + (Game.TILES_SIZE * 2) * Game.scale, g);
    }

    /**
     * Draw change level.
     *
     * @param g     - g
     * @param level - level
     */
    public void drawChangeLevel(Graphics g, int level) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getScreenWidth() * Game.scale,
                getScreenHeight() * Game.scale);
        Font font = new Font("Arial", Font.PLAIN, 20 * Game.scale);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("LEVEL " + level, getRealWidth(), getRealHeight(), g);
    }

    /**
     * Draw paused.
     *
     * @param g - g
     */
    public void drawPaused(Graphics g) {
        Font font = new Font("Arial", Font.PLAIN, 20 * Game.scale);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("PAUSED", getRealWidth(), getRealHeight(), g);
    }

    /**
     * Draw centered string.
     *
     * @param s - s
     * @param w - w
     * @param h - h
     * @param g - g
     */
    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * Get real width.
     *
     * @return real width
     */
    public int getRealWidth() {
        return screenWidth * Game.scale;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    /**
     * Get real height.
     *
     * @return real height
     */
    public int getRealHeight() {
        return screenHeight * Game.scale;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int get_transparentColor() {
        return _transparentColor;
    }

    public void set_transparentColor(int _transparentColor) {
        this._transparentColor = _transparentColor;
    }

    /**
     * Clear screen.
     */
    public void clearScreen() {
        for (int i = 0; i < this._pixels.length; i++) {
            _pixels[i] = 0;
        }
    }

    /**
     * Render entity.
     *
     * @param x      - x
     * @param y      - y
     * @param entity - entity
     */
    public void renderEntity(int x, int y, Entity entity) {
        x = x - startX;
        y = y - startY;
        for (int i = 0; i < Sprite.DEFAULT_SIZE; i++) {
            for (int j = 0; j < Sprite.DEFAULT_SIZE; j++) {
                int xTmp = j + x;
                int yTmp = i + y;
                if (xTmp < -Sprite.DEFAULT_SIZE || xTmp >= screenWidth
                        || yTmp < 0 || yTmp >= screenHeight) {
                    break;
                }
                if (xTmp < 0) {
                    xTmp = 0;
                }
                if (yTmp < 0) {
                    yTmp = 0;
                }
                if (entity.getSprite() == null) return;
                int color = entity.getSprite().getPixel(j + i
                        * entity.getSprite().getSize());
                if (color != _transparentColor) {
                    this._pixels[xTmp + yTmp * screenWidth] = color;
                }
            }
        }
    }

    /**
     * Render entity with below sprite.
     *
     * @param xp     - xp
     * @param yp     - yp
     * @param entity - entity
     * @param below  - below
     */
    public void renderEntityWithBelowSprite(int xp, int yp, Entity entity, Sprite below) {
        xp -= startX;
        yp -= startY;
        for (int y = 0; y < entity.getSprite().getSize(); y++) {
            int ya = y + yp;
            for (int x = 0; x < entity.getSprite().getSize(); x++) {
                int xa = x + xp;
                if (xa < -entity.getSprite().getSize() || xa >= screenWidth
                        || ya < 0 || ya >= screenHeight)
                    break;
                if (xa < 0) {
                    xa = 0;
                }
                int color = entity.getSprite().getPixel(x + y
                        * entity.getSprite().getSize());
                if (color != _transparentColor) {
                    _pixels[xa + ya * screenWidth] = color;
                } else {
                    _pixels[xa + ya * screenWidth] = below.getPixel(x
                            + y * below.getSize());
                }
            }
        }
    }

    /**
     * Calculate x start position.
     *
     * @param board  - board
     * @param bomber - bomber
     * @return x start position
     */
    public static int calculateXStartPosition(Board board, Bomber bomber) {
        if (bomber == null) {
            return 0;
        }
        int tmp = startX;
        double BomberX = bomber.getX() / 16;
        double complement = 0.5;
        int firstBreakpoint = board.getWidth() / 4;
        int lastBreakpoint = board.getWidth() - firstBreakpoint;
        if (BomberX > firstBreakpoint + complement
                && BomberX < lastBreakpoint - complement) {
            tmp = (int) bomber.getX() - (Game.WIDTH / 2);
        }
        return tmp;
    }

    /**
     * Set start position.
     *
     * @param x - x
     * @param y - y
     */
    public static void setStartPosition(int x, int y) {
        startX = x;
        startY = y;
    }
}

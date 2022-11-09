package game;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.awt.*;

import graphics.Screen;
import graphics.Sprite;
import input.Input;

public class Game extends Canvas {
    public static final int TILES_SIZE = 16,
            WIDTH = TILES_SIZE * 15,
            HEIGHT = 13 * TILES_SIZE;

    public static int isPlayBackGroundMusic = 0;
    
    public static int scale = Sprite.SCALED_SIZE / Sprite.DEFAULT_SIZE;

    public static final String TITLE = "BombermanGame";

    public static final int TIME = 200;

    public static final int POINTS = 0;

    protected static int SCREENDELAY = 3;

    private static final int BOMBRATE = 1;
    private static final int BOMBRADIUS = 1;
    private static final double BOMBERSPEED = 1.0;

    protected static int bombRate = BOMBRATE;

    protected static int bombRadius = BOMBRADIUS;

    protected static double bomberSpeed = BOMBERSPEED;

    protected int screenDelay = SCREENDELAY;

    private Input input;

    private boolean isRunning = false;

    public boolean isPaused = false;

    private Board board;

    private Screen screen;

    private Frame frame;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT
            , BufferedImage.TYPE_INT_RGB);

    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    /**
     * Constructor Game 1 parameter.
     *
     * @param frame - frame
     */
    public Game(Frame frame) {
        this.frame = frame;
        this.frame.setTitle(TITLE);
        screen = new Screen(WIDTH, HEIGHT);
        input = new Input();
        board = new Board(this, input, screen);
        
        this.addKeyListener(input);
    }

    /**
     * Start.
     */
    public void start() {
    	String fileName = "src\\res\\musics\\background.wav";
        Music sound = new Music();
        sound.setFile(fileName);
        isRunning = true;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while (isRunning) {
        	
        	if(isPlayBackGroundMusic == 1200) {
            	System.out.println("loop");
            	sound = new Music();
                sound.setFile(fileName);
                sound.playLoop();
            }
        	if(isPlayBackGroundMusic == -1)
				try {
					sound.stop();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	if(isPlayBackGroundMusic == 1) 
        	{
        		try {
					sound.stop();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	if(isPlayBackGroundMusic < 1201 && this.board.getBomber().getIsAlive())
        	{
        		System.out.println(isPlayBackGroundMusic);
        		isPlayBackGroundMusic ++;
        	}
        	
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            if (isPaused) {
                if (screenDelay <= 0) {
                    board.setShow(-1);
                    isPaused = false;
                }
                renderScreen();
            } else {
                renderGame();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                board.subtractTime();
                frame.getInfoPanel().update(board.getTime(), board.getPoints());
                timer += 1000;
                frame.setTitle(TITLE + " | " + updates + " rate, " + frames + " fps");
                updates = 0;
                frames = 0;
                if (board.getShow() == 2)
                    --screenDelay;
            }
        }
    }

    /**
     * Render game.
     */
    private void renderGame() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        screen.clear();
        board.render(screen);
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen._pixels[i];
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        board.renderMessage(g);
        g.dispose();
        bs.show();
    }

    /**
     * Render screen.
     */
    private void renderScreen() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        screen.clear();
        Graphics g = bs.getDrawGraphics();
        board.drawScreen(g);
        g.dispose();
        bs.show();
    }

    /**
     * Update.
     */
    private void update() {
        input.update();
        board.update();
    }

    /**
     * Run.
     */
    public void run() {
        isRunning = true;
        isPaused = false;
    }

    /**
     * Pause.
     */
    public void pause() {
        isPaused = true;
    }

    /**
     * Is paused.
     *
     * @return paused
     */
    public boolean isPaused() {
        return isPaused;
    }

    public int getTime() {
        return TIME;
    }

    public int getPoints() {
        return POINTS;
    }

    /**
     * Reset screen delay.
     */
    public void resetScreenDelay() {
        screenDelay = SCREENDELAY;
    }

    public static int getBombRate() {
        return bombRate;
    }

    public static int getBombRadius() {
        return bombRadius;
    }

    public static double getBomberSpeed() {
        return bomberSpeed;
    }

    public static void setBombRate(int bombRate) {
        Game.bombRate = bombRate;
    }

    public static void setBombRadius(int bombRadius) {
        Game.bombRadius = bombRadius;
    }

    public static void setBomberSpeed(double bomberSpeed) {
        Game.bomberSpeed = bomberSpeed;
    }

    /**
     * Add bomb rate.
     *
     * @param i - i
     */
    public static void addBombRate(int i) {
        bombRate += i;
    }

    /**
     * Add bomb radius.
     *
     * @param i - i
     */
    public static void addBombRadius(int i) {
        bombRadius += i;
    }

    /**
     * Add bomber speed.
     *
     * @param i - i
     */
    public static void addBomberSpeed(double i) {
        bomberSpeed += i;
    }

    public Board getBoard() {
        return board;
    }
}

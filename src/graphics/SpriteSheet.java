package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteSheet {
    private String _path;

    public final int SIZE;

    public int[] _pixels;

    public BufferedImage image;

    public static SpriteSheet tiles = new SpriteSheet("/res/textures/classic.png", 256);

    /**
     * Constructor SpriteSheet 2 parameters.
     *
     * @param path - path
     * @param size - size
     */
    public SpriteSheet(String path, int size) {
        _path = path;
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        load();
    }

    /**
     * Load.
     */
    private void load() {
        try {
            URL a = SpriteSheet.class.getResource(_path);
            System.out.println(a);
            BufferedImage image = ImageIO.read(a);
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, _pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}

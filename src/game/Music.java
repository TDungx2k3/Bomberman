package game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    Clip clip;

    AudioInputStream sound;

    /**
     * Set file.
     *
     * @param soundFileName - sound file name
     */
    public void setFile(String soundFileName) {
        try {
            File file = new File(soundFileName).getAbsoluteFile();
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Play.
     */
    public void play() {
        clip.start();
    }

    public void playLoop() {
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stop.
     *
     * @throws IOException
     */
    public void stop() throws IOException {
        sound.close();
        clip.close();
        clip.stop();
    }
}

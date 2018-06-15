package shooter.music;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class MusicManager {

    /**
     * 
     * @param str name of file WITHOUT .wav and /resources/
     */
    public static void playClip(String name) {
        Thread th = new Thread(() -> {
            AudioInputStream ais;
            try {
                InputStream bufferedIn = new BufferedInputStream(MusicManager.class.getResourceAsStream("/resources/" + name + ".wav"));
                ais = AudioSystem.getAudioInputStream(bufferedIn);
                Clip c = AudioSystem.getClip();
                c.open(ais);

                c.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        });
        th.start();
    }

    /**
     * 
     * @param name name of file WITHOUT .wav and /resources/
     * @param loops number of times to loop
     */
    public static void playClip(String name, int loops) {
        Thread th = new Thread(() -> {
            AudioInputStream ais;
            try {
                InputStream bufferedIn = new BufferedInputStream(MusicManager.class.getResourceAsStream("/resources/" + name + ".wav"));
                ais = AudioSystem.getAudioInputStream(bufferedIn);
                Clip c = AudioSystem.getClip();
                c.open(ais);

                c.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        });
        th.start();
    }

}

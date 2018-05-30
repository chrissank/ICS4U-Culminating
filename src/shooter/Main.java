package shooter;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import shooter.events.EventHandler;
import shooter.events.types.GameInitiateEvent;
import shooter.game.GameDisplay;
import shooter.menu.shooterMenu;


/**
 * 
 * @author Chris Sankey & Mitchell LeLiever 29/5/2018
 *
 */
public class Main {

    static Main instance;
    GameFrame frame;
    shooterMenu menu;
    //Main.class.getResourceAsStream("/resources/strings.txt"))
    public static void main(String[] args) throws IOException {
        instance = new Main();
        instance.initialize();
    }
    
    
    public void initialize() {
        registerListeners();
        //playClip();
        frame = new GameFrame();
        menu = new shooterMenu();
        frame.add(menu, "menu");
        frame.add(new GameDisplay(), "gamedisplay");
        frame.lay.show(menu.getParent(), "menu");
        EventHandler.callEvent(new GameInitiateEvent(100));
    }
    
    public void playClip() {
        Thread th = new Thread(() -> {
            AudioInputStream ais;
            try {
                ais = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream("/resources/bomb_x.wav"));
                Clip test = AudioSystem.getClip();  

                test.open(ais);
                test.start();
                Thread.sleep(5000);

                test.close();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        });
        th.start();
    }
    
    /**
     * Make sure correct listeners are registered first in terms of priority
     */
    public void registerListeners() {
        EventHandler.registerListener(new TestListener());
    }
    
    public GameFrame getFrame() {
        return this.frame;
    }
    
    public static Main getInstance() {
        return instance;
    }
    
}

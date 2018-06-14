package shooter;

import shooter.events.EventListener;
import shooter.events.Listener;
import shooter.events.types.InitiateEvent;
import shooter.game.GameDisplay;
import shooter.menu.ShooterMenu;
import shooter.music.MusicManager;

public class InitializeListener implements Listener {

    Main main;
    
    public InitializeListener() {
        main = Main.getInstance();
    }
    
    @EventListener
    public void onInit(InitiateEvent e) {
        //playClip();    	
        main.frame = new GameFrame();
        main.menu = new ShooterMenu();
        main.gamedisplay = new GameDisplay(); // causes the game to lag on load (textures)
        main.frame.add(main.menu, "menu");
        main.frame.add(main.gamedisplay, "gamedisplay");
        main.frame.lay.show(main.menu.getParent(), "menu");
        System.out.println("play");
        MusicManager.playClip("Menu Theme");
    }

}

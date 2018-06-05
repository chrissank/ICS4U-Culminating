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
        main.game = new GameDisplay();
        main.frame.add(main.menu, "menu");
        main.frame.add(main.game, "gamedisplay");
        main.frame.lay.show(main.getMenu().getParent(), "menu");
        MusicManager.playClip("bomb_x");
    }

}

package shooter;

import shooter.events.EventListener;
import shooter.events.Listener;
import shooter.events.types.InitiateEvent;
import shooter.game.GameDisplay;
import shooter.menu.shooterMenu;
import shooter.music.MusicManager;

public class InitializeListener implements Listener {

    Main main;
    
    @EventListener
    public void onInit(InitiateEvent e) {
        main = Main.getInstance();
        //playClip();
        main.frame = new GameFrame();
        main.menu = new shooterMenu();
        main.frame.add(main.menu, "menu");
        main.frame.add(new GameDisplay(), "gamedisplay");
        main.frame.lay.show(main.menu.getParent(), "menu");
        MusicManager.playClip("bomb_x");
    }

}

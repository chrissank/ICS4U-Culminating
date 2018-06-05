package shooter.game;

import shooter.Main;
import shooter.events.EventListener;
import shooter.events.Listener;
import shooter.events.types.GameInitiateEvent;

public class GameListeners implements Listener {
    
    Main main;
    public GameListeners() {
        main = Main.getInstance();
    }
    
    @EventListener
    public void onGameInit(GameInitiateEvent e) {
        main.getFrame().getLayout().show(main.getDisplay().getParent(), "gamedisplay");
    }

}

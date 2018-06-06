package shooter.game;

import shooter.Main;
import shooter.events.EventListener;
import shooter.events.Listener;
import shooter.events.types.GameInitiateEvent;
import shooter.events.types.PreGameInitiateEvent;
import shooter.events.types.RepaintEvent;

public class GameListeners implements Listener {
    
    Main main;
    public GameListeners() {
        main = Main.getInstance();
    }
    
    @EventListener
    public void onGamePreInit(PreGameInitiateEvent e) {
        main.thread = new GameThread();
    }
    
    
    @EventListener
    public void onGameInit(GameInitiateEvent e) {
        main.thread.start();
        main.frame.getLayout().show(main.gamedisplay.getParent(), "gamedisplay");
    }

    
    @EventListener
    public void onRepaint(RepaintEvent e) {
        
    }
}

package shooter.game;

import shooter.Main;
import shooter.events.EventListener;
import shooter.events.Listener;
import shooter.events.types.GameInitiateEvent;
import shooter.events.types.PreGameInitiateEvent;

public class GameListeners implements Listener {
    
    Main main;
    public GameListeners() {
        main = Main.getInstance();
    }
    
    @EventListener
    public void onGamePreInit(PreGameInitiateEvent e) {
        main.thread = new GameThread();
        main.in = new GameInput();
    }
    
    
    @EventListener
    public void onGameInit(GameInitiateEvent e) {
        main.frame.getLayout().show(main.gamedisplay.getParent(), "gamedisplay");
        main.frame.addKeyListener(main.in);
        main.frame.setFocusable(true);
        main.frame.requestFocus();
        main.thread.start();
    }
}

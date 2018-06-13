package shooter.events;

import shooter.Main;
import shooter.events.types.GameInitiateEvent;
import shooter.events.types.GameTickEvent;
import shooter.events.types.InitiateEvent;
import shooter.events.types.MenuInitiateEvent;
import shooter.events.types.PreGameInitiateEvent;
import shooter.events.types.RepaintEvent;

public class CoreListeners implements Listener {
    
    Main main;
    public CoreListeners() {
        main = Main.getInstance();
    }
    
    @EventListener
    public void onInitiateEvent(InitiateEvent e) {
        EventHandler.callEvent(new MenuInitiateEvent());
    }
    
    @EventListener
    public void onPreGameInitiateEvent(PreGameInitiateEvent e) {
        EventHandler.callEvent(new GameInitiateEvent(e.getDifficulty()));
    }
    
    @EventListener
    public void onGameTick(GameTickEvent e)  {
        main.gamedisplay.repaint();
    }
}

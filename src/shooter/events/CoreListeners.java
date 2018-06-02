package shooter.events;

import shooter.events.types.GameInitiateEvent;
import shooter.events.types.InitiateEvent;
import shooter.events.types.MenuInitiateEvent;

public class CoreListeners implements Listener {
    
    @EventListener
    public void onInitiateEvent(InitiateEvent e) {
        EventHandler.callEvent(new MenuInitiateEvent());
    }

    @EventListener
    public void onGameInitiateEvent(GameInitiateEvent e) {
        System.out.println("game starting with " + e.getDifficulty() + " difficulty");
    }
}

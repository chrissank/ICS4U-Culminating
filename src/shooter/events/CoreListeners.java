package shooter.events;

import shooter.events.types.InitiateEvent;
import shooter.events.types.MenuInitiateEvent;

public class CoreListeners implements Listener {
    
    @EventListener
    public void onInitiateEvent(InitiateEvent e) {
        System.out.println("..");
        EventHandler.callEvent(new MenuInitiateEvent());
    }

}

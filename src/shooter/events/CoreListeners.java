package shooter.events;

import shooter.Main;
import shooter.events.types.GameInitiateEvent;
import shooter.events.types.InitiateEvent;
import shooter.events.types.MenuInitiateEvent;
import shooter.events.types.PreGameInitiateEvent;

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
    public void onGameInitiateEvent(GameInitiateEvent e) {
        System.out.println("game starting with " + e.getDifficulty() + " difficulty");
        main.getFrame().
        getLayout().show(
                main.getDisplay().getParent(), 
                "gamedisplay");
        System.out.println("Panel switched");
    }
    
    @EventListener
    public void onPreGameInitiateEvent(PreGameInitiateEvent e) {
        EventHandler.callEvent(new GameInitiateEvent(e.getDifficulty()));
    }
}

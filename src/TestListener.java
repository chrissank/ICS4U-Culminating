import shooter.events.EventListener;
import shooter.events.Listener;
import shooter.events.types.GameInitiateEvent;

public class TestListener implements Listener {

    @EventListener
    public void onGameInit(GameInitiateEvent e) {
        System.out.println("test");
    }

}

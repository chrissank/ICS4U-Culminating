import java.io.IOException;

import shooter.events.EventHandler;
import shooter.events.types.GameInitiateEvent;

public class Main {

    //Main.class.getResourceAsStream("/resources/strings.txt"))
    public static void main(String[] args) throws IOException {
        registerListeners();
        EventHandler.callEvent(new GameInitiateEvent());
    }
    
    public static void registerListeners() {
        EventHandler.registerListener(new TestListener());
    }
    
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import shooter.events.EventHandler;
import shooter.events.ListenerType;

public class Main {

    //Main.class.getResourceAsStream("/resources/strings.txt"))
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/resources/strings.txt")));
        
    }
    
    public static void registerListeners() {
        EventHandler.registerListener(new TestListener(), ListenerType.GAME_INIT);
    }
    
}

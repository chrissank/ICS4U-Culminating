package shooter.events;

import java.util.ArrayList;
import java.util.HashMap;

public class EventHandler {

    private static HashMap<ListenerType, ArrayList<Listener>> listeners = new HashMap<>();
    
    public static void registerListener(Listener l, ListenerType t) {
        if(!listeners.containsKey(t)) listeners.put(t, new ArrayList<>());
        listeners.get(t).add(l);
    }
    
    public static void callEvent(ListenerType e) {
        for(Listener listener : listeners.get(e)) {
            listener.called(e);
        }
    }
    
}

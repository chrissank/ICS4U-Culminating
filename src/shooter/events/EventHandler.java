 package shooter.events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class EventHandler {

    private static HashMap<Class<? extends Event>, ArrayList<Listener>> listeners = new HashMap<>();
    
    public static void registerListener(Listener cl) {
        Method[] methods = cl.getClass().getMethods();
        for(Method m : methods) {
            EventListener anno = m.getAnnotation(EventListener.class);
            if(anno == null) continue;
            @SuppressWarnings("unchecked") // Could be unchecked bc we don't have any above method checks.. but you could verify that this parameter is actually correct
            Class<? extends Event> param = (Class<? extends Event>) m.getParameterTypes()[0];
            if(!listeners.containsKey(param)) listeners.put(param, new ArrayList<>());
            listeners.get(param).add(cl);
        }
    }
    
    
    public static void callEvent(Event e) {
        if(!listeners.containsKey(e.getClass())) return;
        for(Listener l : listeners.get(e.getClass())) {
            for(Method m : l.getClass().getMethods()) {
                EventListener anno = m.getAnnotation(EventListener.class);
                if(anno == null) continue;
                try {
                    m.invoke(l, e);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    
}

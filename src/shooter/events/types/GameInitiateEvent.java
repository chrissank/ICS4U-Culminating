package shooter.events.types;

import shooter.events.Event;

public class GameInitiateEvent extends Event {
    
    long time;
    public GameInitiateEvent(long time) {
        this.time = time;
    }
    
    public long getTime() {
        return this.time;
    }
}

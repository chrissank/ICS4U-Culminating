package shooter.events.types;

import shooter.events.Event;

public class InitiateEvent extends Event {
    
    long time;
    public InitiateEvent(long time) {
        this.time = time;
    }
    
    public long getTime() {
        return this.time;
    }
}

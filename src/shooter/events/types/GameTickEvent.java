package shooter.events.types;

import shooter.events.Event;

public class GameTickEvent extends Event {
    
    int tick;
    public GameTickEvent(int tick) {
        this.tick = tick;
    }
   
    public int getTick() {
        return this.tick;
    }
}

package shooter.events.types;

import shooter.events.Event;

public class PreGameInitiateEvent extends Event {
    
    private int diff;
    public PreGameInitiateEvent(int difficulty) {
        diff = difficulty;
    }
    
    public int getDifficulty() {
        return this.diff;
    }
}

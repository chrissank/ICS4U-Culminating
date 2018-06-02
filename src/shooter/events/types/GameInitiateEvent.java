package shooter.events.types;

import shooter.events.Event;

public class GameInitiateEvent extends Event {
    
    private int diff;
    public GameInitiateEvent(int difficulty) {
        diff = difficulty;
    }
    
    public int getDifficulty() {
        return this.diff;
    }
}

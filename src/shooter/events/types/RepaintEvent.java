package shooter.events.types;

import java.awt.Graphics2D;

import shooter.events.Event;

public class RepaintEvent extends Event {
    
    Graphics2D g2;
    public RepaintEvent(Graphics2D g2) {
        this.g2 = g2;
    }
    
    public Graphics2D getGraphics() {
        return this.g2;
    }
    
    

}

package shooter.entities;

import javafx.geometry.Rectangle2D;

public class Wall {

    int x1, x2, y1, y2;
    public Wall(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
    
    public Rectangle2D getBounds() {
        Rectangle2D r = new Rectangle2D(x1, y2, x1-x2, y1-y2);
        return r;
    }
}

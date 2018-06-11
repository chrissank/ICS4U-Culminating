package shooter.entities;

import javafx.geometry.Rectangle2D;

public class Bullet {
    
    int x, y;
    double dir;
    public Bullet(int x, int y, double dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    
    public Rectangle2D getBounds() {
        return new Rectangle2D(x, y, x + 1, y + 1);
    }
    
    public void move() {
        int speed = 20;
        x -= speed * Math.sin(dir);
        y += speed * Math.cos(dir);
    }

}

package shooter.entities;

import javafx.geometry.Rectangle2D;

public class Bullet {

    int x, y, id;
    double dir;
    public Bullet(int x, int y, double dir, int id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getRotation() {
        return this.dir;
    }
    
    public int getID() {
        return this.id;
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D(x, y, 2, 2);
    }

    public void move() {
        int speed = 20;
        x -= speed * Math.sin(dir);
        y += speed * Math.cos(dir);
    }

    public void setX(int i) {
        this.x = i;
    }

}

package shooter.entities;

import javafx.geometry.Rectangle2D;

public class Bullet {

    int x, y, id;
    double dir;
    WeaponType type;
    public Bullet(int x, int y, double dir, WeaponType type, int id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.type = type;
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
        return new Rectangle2D(x, y, 1, 1);
    }

    public void move() {
        int speed = 20;
        if(type != WeaponType.PISTOL) {
            speed *= 1.5;
        }
        x -= speed * Math.sin(dir);
        y += speed * Math.cos(dir);
    }

    public WeaponType getType() {
        return this.type;
    }

    public void setX(int i) {
        this.x = i;
    }

}

package shooter.entities;

import java.awt.Toolkit;
import java.util.Random;

import javafx.geometry.Rectangle2D;
import shooter.level.LevelManager;

public class Enemy {

    Random r;
    public int startZone, health, speed, id, x, y;
    double rotation;
    public Enemy(int zone, int health, int speed) {
        this.startZone = zone;
        this.health = health;
        this.speed = speed;
        r = new Random();
        if(zone == 0) {
            // left
            x = r.nextInt(50) + 20;
            y = r.nextInt(Toolkit.getDefaultToolkit().getScreenSize().height) - 20;
        } else if(zone == 1) {
            // right
            x = Toolkit.getDefaultToolkit().getScreenSize().width - r.nextInt(50) - 40;
            y = r.nextInt(Toolkit.getDefaultToolkit().getScreenSize().height) - 20;
        } else {
            // top
            x = r.nextInt(Toolkit.getDefaultToolkit().getScreenSize().width);
            y = r.nextInt(50) + 20;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setID(int id) {
        this.id = id;
    }
    
    public double getRotation() {
        return this.rotation;
    }

    public Rectangle2D getBounds(int dx, int dy) {
        return new Rectangle2D(dx, dy, 40, 40);
    }
    
    public void move() {
        float dx = LevelManager.getPlayer().getX() - x;
        float dy = LevelManager.getPlayer().getY() - y;
        rotation = Math.atan2(dy, dx) - 1.5708;
        int tempX = x, tempY = y;
        tempX -= speed * Math.sin(rotation);
        tempY += speed * Math.cos(rotation);
        for(Wall w : LevelManager.getCurrentLevel().getWalls()) {
            if(getBounds(tempX, tempY).intersects(w.getBounds())) {
                return;
            }
        }
        x += (tempX - x);
        y += (tempY - y);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int i) {
        this.health = i;
    }

    public void setX(int i) {
        this.x = i;
    }

    public Integer getID() {
        return this.id;
    }

}

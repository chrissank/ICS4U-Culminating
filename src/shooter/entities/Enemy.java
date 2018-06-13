package shooter.entities;

import java.awt.Toolkit;
import java.util.Random;

import javafx.geometry.Rectangle2D;
import shooter.level.LevelManager;

public class Enemy {

    Random r;
    public int startZone, health, speed, id, x, y;
    double rot;
    public Enemy(int zone, int health, int speed) {
        this.startZone = zone;
        this.health = health;
        this.speed = speed;
        r = new Random();
        if(zone == 0) {
            // left
            x = r.nextInt(50);
            y = r.nextInt(Toolkit.getDefaultToolkit().getScreenSize().height) - 10;
        } else if(zone == 1) {
            // right
            x = Toolkit.getDefaultToolkit().getScreenSize().width - r.nextInt(50) - 10;
            y = r.nextInt(Toolkit.getDefaultToolkit().getScreenSize().height) - 10;
        } else {
            // top
            x = r.nextInt(Toolkit.getDefaultToolkit().getScreenSize().width);
            y = r.nextInt(50);
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
        return this.rot;
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D(x, y, 20, 20);
    }
    
    public void move() {
        float dx = LevelManager.getPlayer().getX() - x;
        float dy = LevelManager.getPlayer().getY() - y;
        rot = Math.atan2(dy, dx) - 1.5708;
        x -= speed * Math.sin(rot);
        y += speed * Math.cos(rot);
        for(Wall w : LevelManager.getCurrentLevel().getWalls()) {
            if(getBounds().intersects(w.getBounds())) {
                return;
            }
        }
    }

}

package shooter.entities;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Rectangle2D;
import shooter.level.LevelManager;

public class Player {

    private int nextID;
    int x, y, health, pistolAmmo, rifleAmmo;
    double rotation;
    WeaponType weapon;
    ArrayList<Bullet> shot;
    boolean left, right, forward, backward, shoot = false;
    public Player(int x, int y, int health, WeaponType t, int pistolAmmo, int rifleAmmo, double rot) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.weapon = t;
        this.pistolAmmo = pistolAmmo;
        this.rifleAmmo = rifleAmmo;
        this.rotation = rot;
        this.shot = new ArrayList<>();
        this.nextID = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public int getY() {
        return y;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    public int getPistolAmmo() {
        return pistolAmmo;
    }
    
    public int getRifleAmmo() {
    	return rifleAmmo;
    }

    public void setPistolAmmo(int newAmmo) {
        this.pistolAmmo = newAmmo;
    }
    
    public void setRifleAmmo(int newAmmo) {
    	this.rifleAmmo = newAmmo;
    }
    
    public WeaponType getWeapon() {
        return this.weapon;
    }
    
    public void setWeapon(WeaponType newWep) {
        this.weapon = newWep;
    }
    
    public double getRotation() {
        return this.rotation;
    }
    
    public void setRotation(double rot) {
        this.rotation = rot;
    }
    
    public void setLeft(boolean b) {
        this.left = b;
    }

    public void setRight(boolean b) {
        this.right = b;
    }
    
    public void setForward(boolean b) {
        this.forward = b;
    }
    
    public void setBackward(boolean b) {
        this.backward = b;
    }
    
    public void setShooting(boolean b) {
        this.shoot = b;
    }
    
    public boolean isLeft() {
        return this.left;
    }
    
    public boolean isRight() {
        return this.right;
    }
    
    public boolean isForward() {
        return this.forward;
    }
    
    public boolean isBackward() {
        return this.backward;
    }
    
    public boolean isShooting() {
        return this.shoot;
    }
    
    public String toString() {
        return "x: " + x + " y: " + y + " health: " + health + " weapon: " + weapon.toString() + " pistol ammo: " + pistolAmmo + " rifle ammo: " + rifleAmmo;
    }
    
    public void move(String dir) {
        int speed = 8;
        if(left && forward) speed /= 2;
        if(left && backward) speed /= 2;
        if(right && forward) speed /= 2;
        if(right && backward) speed /= 2;
        int tempX = x, tempY = y;
        if(dir.equals("W")) {
            tempX -= speed * Math.sin(rotation);
            tempY += speed * Math.cos(rotation);
        } else if(dir.equals("D")) {
            tempX -= speed * Math.sin(rotation - 1.5708);
            tempY += speed * Math.cos(rotation - 1.5708);
        } else if(dir.equals("S")) {
            tempX += speed * Math.sin(rotation);
            tempY -= speed * Math.cos(rotation);
        } else if(dir.equals("A")) {
            tempX += speed * Math.sin(rotation - 1.5708);
            tempY -= speed * Math.cos(rotation - 1.5708);
        }
        for(Wall w : LevelManager.getCurrentLevel().getWalls()) {
            if(getBounds(tempX, tempY).intersects(w.getBounds())) {
                return;
            }
        }
        x += (tempX - x);
        y += (tempY - y);
    }
    
    public void shoot(int tick) {
        if(this.weapon == WeaponType.PISTOL) {
            if(pistolAmmo <= 0) return;
            if(tick % 10 != 0) return;
            pistolAmmo--;
        } else {
            if(rifleAmmo <= 0) return;
            if(tick % 5 != 0) return;
            rifleAmmo--;
        }
        shot.add(new Bullet(x, y, rotation, weapon, nextID));
        nextID++;
        //EventHandler.callEvent(new );
    }

    public List<Bullet> getBullets() {
        return shot;
    }

    public Rectangle2D getBounds(int bx, int by) {
        return new Rectangle2D(bx, by, 20, 20);
    }
}

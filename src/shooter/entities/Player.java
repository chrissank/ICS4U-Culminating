package shooter.entities;

public class Player {

    int x, y, health, pistolAmmo, rifleAmmo;
    double rotation;
    WeaponType weapon;
    public Player(int x, int y, int health, WeaponType t, int pistolAmmo, int rifleAmmo, double rot) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.weapon = t;
        this.pistolAmmo = pistolAmmo;
        this.rifleAmmo = rifleAmmo;
        this.rotation = rot;
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
    
    public String toString() {
        return "x: " + x + " y: " + y + " health: " + health + " weapon: " + weapon.toString() + " pistol ammo: " + pistolAmmo + " rifle ammo: " + rifleAmmo;
    }
    
    public void move(String dir) {
        int speed = 10;
        if(dir.equals("w")) {
            x -= speed * Math.sin(rotation);
            y += speed * Math.cos(rotation);
        } else if(dir.equals("a")) {
            x -= speed * Math.sin(rotation - 1.5708);
            y += speed * Math.cos(rotation - 1.5708);
        } else if(dir.equals("s")) {
            x += speed * Math.sin(rotation);
            y -= speed * Math.cos(rotation);
        } else if(dir.equals("d")) {
            x += speed * Math.sin(rotation - 1.5708);
            y -= speed * Math.cos(rotation - 1.5708);
        }
    }
    
    public void shoot() {
        
    }
}

package shooter.entities;

public class Player {

    int x, y, health, ammo;
    WeaponType weapon;
    public Player(int x, int y, int health, WeaponType t, int ammo) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.weapon = t;
        this.ammo = ammo;
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

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int newAmmo) {
        this.ammo = newAmmo;
    }
    
    public WeaponType getWeapon() {
        return this.weapon;
    }
    
    public void setWeapon(WeaponType newWep) {
        this.weapon = newWep;
    }
    
    public String toString() {
        return "x " + x + " y " + y + " health " + health + " weapon " + weapon.toString() + " ammo " + ammo;
    }

}

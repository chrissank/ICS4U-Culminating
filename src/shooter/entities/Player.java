package shooter.entities;

public class Player {

    int x, y, health, pistolAmmo, rifleAmmo;
    WeaponType weapon;
    public Player(int x, int y, int health, WeaponType t, int pistolAmmo, int rifleAmmo) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.weapon = t;
        this.pistolAmmo = pistolAmmo;
        this.rifleAmmo = rifleAmmo;
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
    
    public String toString() {
        return "x: " + x + " y: " + y + " health: " + health + " weapon: " + weapon.toString() + " pistol ammo: " + pistolAmmo + " rifle ammo: " + rifleAmmo;
    }

}

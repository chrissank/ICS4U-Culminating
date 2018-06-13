package shooter.entities;

public class Enemy {
    
    public int startZone, health, speed, id;
    public Enemy(int startZone, int health, int speed, int id) {
        this.startZone = startZone;
        this.health = health;
        this.speed = speed;
        this.id = id;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public void move() {
        
    }

}

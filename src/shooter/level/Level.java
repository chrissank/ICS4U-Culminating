package shooter.level;

import java.util.ArrayList;

import shooter.entities.Enemy;
import shooter.entities.Wall;

public class Level {
    
    private Wall[] walls;
    private ArrayList<Enemy> enemies;
    private int time, healthx, healthy, nextEnemyID;
    public Level(Wall[] walls, int time, int healthx, int healthy) {
        this.walls = walls;
        this.enemies = new ArrayList<>();
        this.time = time;
        this.healthx = healthx;
        this.healthy = healthy;
        this.nextEnemyID = 0;
    }

    public Wall[] getWalls() {
        return this.walls;
    }
    
    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    public void addEnemy(Enemy e) {
        e.setID(nextEnemyID);
        nextEnemyID++;
        this.enemies.add(e);
    }
    
    public int getTime() {
        return this.time;
    }
    
    public int getHealthpackX() {
        return this.healthx;
    }
    
    public int getHealthpackY() {
        return this.healthy;
    }
}

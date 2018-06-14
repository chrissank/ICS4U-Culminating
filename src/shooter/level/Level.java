package shooter.level;

import java.util.ArrayList;
import java.util.Random;

import shooter.entities.Enemy;
import shooter.entities.Wall;

public class Level {
    
    Random r;
    private Wall[] walls;
    private ArrayList<Enemy> enemies;
    private int time, healthx, healthy, nextEnemyID, difficulty;
    
    public Level(Wall[] walls, int time, int healthx, int healthy, int difficulty) {
        this.walls = walls;
        this.enemies = new ArrayList<>();
        this.time = time;
        this.healthx = healthx;
        this.healthy = healthy;
        this.nextEnemyID = 0;
        this.difficulty = difficulty;
        r = new Random();
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
    
    public int getDifficulty() {
        return this.difficulty;
    }

    public void spawn() {
        addEnemy(new Enemy(r.nextInt(3), 100, 2));
    }
}

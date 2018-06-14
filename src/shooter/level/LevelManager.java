package shooter.level;

import java.util.ArrayList;
import java.util.Random;

import shooter.GameFrame;
import shooter.entities.Player;
import shooter.entities.Wall;
import shooter.entities.WeaponType;

public class LevelManager {

    private static Player player;
    private static Level currentLevel;
    
    public static ArrayList<Integer> x = new ArrayList<Integer>();
    public static ArrayList<Integer> y = new ArrayList<Integer>();
    public static ArrayList<Integer> width = new ArrayList<Integer>();
    public static ArrayList<Integer> height = new ArrayList<Integer>();
    
    public static void nextLevel() {
        //reset bullet id and clear the shot list
        GenerateLevel();
    }
    
    public static void init() {
        player = new Player(GameFrame.width / 2, GameFrame.height / 2, 100, WeaponType.PISTOL, 75, 250, 0);
        
        GenerateLevel();
    }
    
    public static Player getPlayer() {
        return player;
    }
    
    public static Level getCurrentLevel() {
        return currentLevel;
    }
    
    private static void GenerateLevel() {
    	int borderX = GameFrame.width / 12;
    	int borderY = GameFrame.height / 7;
    	
    	x.clear();
        y.clear();
        width.clear();
        height.clear();
    	
    	Random rand = new Random();
    	
    	int numberOfWalls = (rand.nextInt(50) % 5) + 4;
    	
    	Wall[] levelOneWalls = new Wall[numberOfWalls];
    	
    	for (int i = 0; i < numberOfWalls; i++) {
        	if (getRandomBoolean()) {
        		width.add(50);
        		height.add(rand.nextInt(75) + 100);
        	}
        	
        	else {
        		height.add(50);
        		width.add(rand.nextInt(75) + 100);
        	}
        	
        	int correctedBorderX = borderX + width.get(i);
        	int correctedBorderY = borderY + height.get(i);
        	
        	x.add(rand.nextInt(GameFrame.width - (correctedBorderX * 2)) + correctedBorderX);
        	y.add(rand.nextInt(GameFrame.height - (correctedBorderY * 2)) + correctedBorderY);        		
        	     	
        	levelOneWalls[i] = new Wall(x.get(i), y.get(i), width.get(i), height.get(i));
    	}
    	
        currentLevel = new Level(levelOneWalls, 60, 600, 600);
    }
    
    private static boolean getRandomBoolean() {
    	return Math.random() < 0.5;
    }
}

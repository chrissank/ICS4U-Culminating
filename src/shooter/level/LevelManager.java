package shooter.level;

import java.util.ArrayList;
import java.util.Random;

import shooter.GameFrame;
import shooter.entities.Player;
import shooter.entities.Wall;
import shooter.entities.WeaponType;

public class LevelManager {

    private static int diff;
    private static Player player;
    private static Level currentLevel;
    
    public static ArrayList<Integer> x = new ArrayList<Integer>();
    public static ArrayList<Integer> y = new ArrayList<Integer>();
    public static ArrayList<Integer> width = new ArrayList<Integer>();
    public static ArrayList<Integer> height = new ArrayList<Integer>();
    
    public static void nextLevel() {
        //reset bullet id and clear the shot list
    }
    
    public static void init(int difference) {
        player = new Player(GameFrame.width / 2, GameFrame.height / 2, 100, WeaponType.PISTOL, 75, 250, 0);
        diff = difference;
        GenerateLevel();
    }
    
    public static Player getPlayer() {
        return player;
    }
    
    public static Level getCurrentLevel() {
        return currentLevel;
    }
    
    private static void GenerateLevel() {
    	int borderX = GameFrame.width / 9;
    	int borderY = GameFrame.height / 5;
    	
    	x.clear();
        y.clear();
        width.clear();
        height.clear();
    	
    	Random rand = new Random();
    	
    	int numberOfWalls = (rand.nextInt(50) % 5) + 3;
    	
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
        	
        	int correctedBorderX = borderX < width.get(i) ? width.get(i) : borderX;
        	int correctedBorderY = borderY < height.get(i) ? height.get(i) : borderY;
        	
        	x.add(rand.nextInt(GameFrame.width - (correctedBorderX * 2)) + correctedBorderX);
        	y.add(rand.nextInt(GameFrame.height - (correctedBorderY * 2)) + correctedBorderY);        		
        	
        	//CheckForProperDimensions(i, borderX, borderY);
        	
        	levelOneWalls[i] = new Wall(x.get(i), y.get(i), width.get(i), height.get(i));
    	}
    	
        currentLevel = new Level(levelOneWalls, 60, 600, 600, diff);
    }
    
    /*private static void CheckForProperDimensions(int index, int borderX, int borderY) {
    	Random rand = new Random();
    	
    	while (x.get(index) + width.get(index) > (GameFrame.width - borderX) || y.get(index) + height.get(index) > (GameFrame.height - borderY)) {
        	x.add(rand.nextInt(GameFrame.width - (borderX * 2)) + borderX);
        	y.add(rand.nextInt(GameFrame.height - (borderY * 2)) + borderY);  
    		width.set(index, rand.nextInt(50) + 100);
    		height.set(index, rand.nextInt(50) + 100);
    		System.out.println("looping");
    	}
    }*/
    
    private static boolean getRandomBoolean() {
    	return Math.random() < 0.5;
    }
}

package shooter.level;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Rectangle2D;
import shooter.GameFrame;
import shooter.Main;
import shooter.entities.Player;
import shooter.entities.Wall;
import shooter.entities.WeaponType;
import shooter.events.EventHandler;
import shooter.events.types.PreGameInitiateEvent;

public class LevelManager {

    private static int diff;
    private static Player player;
    private static Level currentLevel;
    private static int levelCount = 1;

    public static ArrayList<Integer> x = new ArrayList<Integer>();
    public static ArrayList<Integer> y = new ArrayList<Integer>();
    public static ArrayList<Integer> width = new ArrayList<Integer>();
    public static ArrayList<Integer> height = new ArrayList<Integer>();

    public static void nextLevel() {
        EventHandler.callEvent(new PreGameInitiateEvent(diff, Main.getInstance().gamedisplay, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
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
        int borderX = GameFrame.width / 12;
        int borderY = GameFrame.height / 7;

        x.clear();
        y.clear();
        width.clear();
        height.clear();

        Random rand = new Random();

        int numberOfWalls = (rand.nextInt(50) % 5) + 4;
        //int numberOfWalls = 50;
        ArrayList<Wall> walls = new ArrayList<>();
        
        for (int i = 0; i < numberOfWalls; i++) {
            boolean wallType = getRandomBoolean();
            width.add(wallType ? 50 : rand.nextInt(75) + 100);
            height.add(wallType ? rand.nextInt(75) + 100 : 50);

            int correctedBorderX = borderX + width.get(i);
            int correctedBorderY = borderY + height.get(i);

            x.add(rand.nextInt(GameFrame.width - (correctedBorderX * 2)) + correctedBorderX);
            y.add(rand.nextInt(GameFrame.height - (correctedBorderY * 2)) + correctedBorderY);        		

            Wall w = new Wall(x.get(i), y.get(i), width.get(i), height.get(i));
            if(!w.getBounds().intersects(player.getBounds(player.getX(), player.getY()))) {
                walls.add(w);
            }
        }
        walls.add(new Wall(0, -40, Toolkit.getDefaultToolkit().getScreenSize().width, 40));
        walls.add(new Wall(-40, 0, 40, Toolkit.getDefaultToolkit().getScreenSize().height));
        walls.add(new Wall(0, Toolkit.getDefaultToolkit().getScreenSize().height, Toolkit.getDefaultToolkit().getScreenSize().width, 40));
        walls.add(new Wall(Toolkit.getDefaultToolkit().getScreenSize().width, 0, 40, Toolkit.getDefaultToolkit().getScreenSize().height));
      
        int healthX = 0, healthY = 0, ammoX = 0, ammoY = 0;
        boolean b = false;
        while(!b) {
            healthX = rand.nextInt(Toolkit.getDefaultToolkit().getScreenSize().width - 50) + 50;
            healthY = rand.nextInt(Toolkit.getDefaultToolkit().getScreenSize().height - 50) + 50;
            for(Wall w : walls) {
                if(!new Rectangle2D(healthX, healthY, 20, 20).intersects(w.getBounds())) {
                    b = true;
                    break;
                }
            }
        }

        b = false;
        while(!b) {
            ammoX = rand.nextInt(Toolkit.getDefaultToolkit().getScreenSize().width - 50) + 50;
            ammoY = rand.nextInt(Toolkit.getDefaultToolkit().getScreenSize().height - 50) + 50;
            for(Wall w : walls) {
                if(!new Rectangle2D(ammoX, ammoY, 20, 20).intersects(w.getBounds())) {
                    b = true;
                    break;
                }
            }
        }

        currentLevel = new Level(walls, 30, healthX, healthY, ammoX, ammoY, diff, levelCount);
        levelCount++;
    }

    private static boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }
}

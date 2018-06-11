package shooter.level;

import shooter.entities.Player;
import shooter.entities.WeaponType;

public class LevelManager {

    private static Player player;
    private static Level currentLevel;
    
    public static void nextLevel() {
        
    }
    
    public static void init() {
        player = new Player(100, 100, 100, WeaponType.PISTOL, 50, 150, 0);
    }
    
    public static Player getPlayer() {
        return player;
    }
    
    public static Level getCurrentLevel() {
        return currentLevel;
    }
}

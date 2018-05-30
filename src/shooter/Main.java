package shooter;

import java.io.IOException;

import shooter.events.EventHandler;
import shooter.events.types.GameInitiateEvent;
import shooter.menu.shooterMenu;


/**
 * 
 * @author Chris Sankey & Mitchell LeLiever 29/5/2018
 *
 */
public class Main {

    static Main instance;
    GameFrame frame;
    shooterMenu menu;
    //Main.class.getResourceAsStream("/resources/strings.txt"))
    public static void main(String[] args) throws IOException {
        instance = new Main();
        instance.registerListeners();
        instance.initialize();
    }

    public void initialize() {
        EventHandler.callEvent(new GameInitiateEvent(100));
    }

    /**
     * Make sure correct listeners are registered first in terms of priority
     */
    public void registerListeners() {
        EventHandler.registerListener(new InitializeListener());
    }

    public GameFrame getFrame() {
        return this.frame;
    }

    public static Main getInstance() {
        return instance;
    }

}

package shooter.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import shooter.Main;
import shooter.level.LevelManager;

public class GameInput implements KeyListener {

    Main main;
    public GameInput() {
        this.main = Main.getInstance();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        String in = KeyEvent.getKeyText(e.getKeyCode()).toLowerCase();
        if(in.equals("w") || in.equals("a") || in.equals("s") || in.equals("d")) {
            LevelManager.getPlayer().move(in);
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {}

    @Override
    public void keyTyped(KeyEvent arg0) {}

    

}

package shooter.game;

import java.awt.event.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import shooter.Main;
import shooter.entities.Player;
import shooter.entities.WeaponType;
import shooter.events.EventListener;
import shooter.events.Listener;
import shooter.events.types.GameInitiateEvent;
import shooter.events.types.GameTickEvent;
import shooter.events.types.PreGameInitiateEvent;
import shooter.level.LevelManager;

public class GameListeners implements Listener {

    Main main;
    public GameListeners() {
        main = Main.getInstance();
    }

    @EventListener
    public void onGamePreInit(PreGameInitiateEvent e) {
        main.thread = new GameThread();
        LevelManager.init();
    }

    @EventListener
    public void onGameInit(GameInitiateEvent e) {
        main.frame.getLayout().show(main.gamedisplay.getParent(), "gamedisplay");
        registerKeys();

        main.frame.setFocusable(true);
        main.frame.requestFocus();
        main.thread.start();
    }

    @EventListener
    public void gameTickEvent(GameTickEvent e) {
        Player player = LevelManager.getPlayer();
        if(player.isForward()) player.move("W");
        if(player.isBackward()) player.move("S");
        if(player.isLeft()) player.move("D");
        if(player.isRight()) player.move("A");
        if(player.isShooting()) player.shoot(e.getTick());
    }

    public void registerKeys() {
        InputMap m = main.gamedisplay.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = main.gamedisplay.getActionMap();

        m.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "UP_PRESS");
        m.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "UP_RELEASE");
        m.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "LEFT_PRESS");
        m.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "LEFT_RELEASE");
        m.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "RIGHT_PRESS");
        m.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "RIGHT_RELEASE");
        m.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "BACK_PRESS");
        m.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "BACK_RELEASE");
        m.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "SHOOT_PRESS");
        m.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "SHOOT_RELEASE");
        m.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, false), "PISTOL");
        m.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0, false), "RIFLE");
        
        am.put("UP_PRESS", new GameInput(e -> {
            LevelManager.getPlayer().setForward(true);
        }));
        am.put("UP_RELEASE", new GameInput(e -> {
            LevelManager.getPlayer().setForward(false);
        }));
        am.put("LEFT_PRESS", new GameInput(e -> {
            LevelManager.getPlayer().setLeft(true);
        }));
        am.put("LEFT_RELEASE", new GameInput(e -> {
            LevelManager.getPlayer().setLeft(false);
        }));
        am.put("RIGHT_PRESS", new GameInput(e -> {
            LevelManager.getPlayer().setRight(true);
        }));
        am.put("RIGHT_RELEASE", new GameInput(e -> {
            LevelManager.getPlayer().setRight(false);
        }));
        am.put("BACK_PRESS", new GameInput(e -> {
            LevelManager.getPlayer().setBackward(true);
        }));
        am.put("BACK_RELEASE", new GameInput(e -> {
            LevelManager.getPlayer().setBackward(false);
        }));
        am.put("SHOOT_PRESS", new GameInput(e -> {
            LevelManager.getPlayer().setShooting(true);
        }));
        am.put("SHOOT_RELEASE", new GameInput(e -> {
            LevelManager.getPlayer().setShooting(false);
        }));
        am.put("RIFLE", new GameInput(e -> {
            LevelManager.getPlayer().setWeapon(WeaponType.RIFLE);
        }));
        am.put("PISTOL", new GameInput(e -> {
            LevelManager.getPlayer().setWeapon(WeaponType.PISTOL);
        }));

    }
}

package shooter.game;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import javafx.geometry.Rectangle2D;
import shooter.Main;
import shooter.entities.Enemy;
import shooter.entities.Player;
import shooter.events.EventHandler;
import shooter.events.EventListener;
import shooter.events.Listener;
import shooter.events.types.GameOverEvent;
import shooter.events.types.GameTickEvent;
import shooter.events.types.PreGameInitiateEvent;
import shooter.events.types.RepaintEvent;
import shooter.level.LevelManager;
import shooter.music.MusicManager;

public class PaintPlayerListener implements Listener {

    Player player;
    BufferedImage playerImage;
    Random r;
    public PaintPlayerListener() {
        player = LevelManager.getPlayer();
        r = new Random();
    }

    @EventListener
    public void onPreGame(PreGameInitiateEvent e) {
        try {
            playerImage = ImageIO.read(PaintHUDListener.class.getResource("/resources/player_1.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.player = LevelManager.getPlayer();
    }

    @EventListener
    public void onGameTick(GameTickEvent e) {
        checkZombieCollision();
        checkCrateCollision();
    }

    @EventListener
    public void onPaint(RepaintEvent e) {
        drawPlayer(e.getGraphics());
    }
    
    public void drawPlayer(Graphics2D g2) {
        Point m = MouseInfo.getPointerInfo().getLocation();
        float x = m.x - player.getX();
        float y = m.y - player.getY();
        double rot = Math.atan2(y, x) - 1.5708;
        this.player.setRotation(rot);
        AffineTransform af = new AffineTransform();
        af.rotate(rot, player.getX() + 34/2, player.getY() + 39/2);
        g2.setTransform(af);
        g2.drawImage(playerImage, player.getX(), player.getY(), Main.getInstance().gamedisplay);

    }
    
    public void checkZombieCollision() {
        for(Enemy e : LevelManager.getCurrentLevel().getEnemies()) {
            if(e.getBounds(e.getX(), e.getY()).intersects(player.getBounds(player.getX(), player.getY()))) {
                int dmg = r.nextInt(6) + 8;
                if(player.getHealth() - dmg <= 0) {
                    player.setHealth(0);
                    EventHandler.callEvent(new GameOverEvent());
                    break;
                }
                player.setHealth(player.getHealth() - dmg);
                // play sound
                player.move("S");
                player.move("S");
                player.move("S");
                player.move("S");
            }
        }
    }
    
    public void checkCrateCollision() {
        Rectangle2D ammobox = new Rectangle2D(LevelManager.getCurrentLevel().getAmmoX(), LevelManager.getCurrentLevel().getAmmoY(), 20, 20);
        if(player.getBounds(player.getX(), player.getY()).intersects(ammobox)) {
            player.setRifleAmmo(250);
            // play sound
            MusicManager.playClip("Pick_Up");
            LevelManager.getCurrentLevel().setAmmoX(-5000);
        }
        Rectangle2D healthpack = new Rectangle2D(LevelManager.getCurrentLevel().getHealthpackX(), LevelManager.getCurrentLevel().getHealthpackY(), 20, 20);
        if(player.getBounds(player.getX(), player.getY()).intersects(healthpack)) {
            player.setHealth(100);
            // play sound
            MusicManager.playClip("Pick_Up");
            LevelManager.getCurrentLevel().setHealthpackX(-5000);
        }
    }
}

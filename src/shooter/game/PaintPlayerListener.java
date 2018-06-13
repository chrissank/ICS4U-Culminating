package shooter.game;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import shooter.Main;
import shooter.entities.Player;
import shooter.entities.Wall;
import shooter.events.EventListener;
import shooter.events.Listener;
import shooter.events.types.GameTickEvent;
import shooter.events.types.PreGameInitiateEvent;
import shooter.events.types.RepaintEvent;
import shooter.level.LevelManager;

public class PaintPlayerListener implements Listener {

    Player player;
    BufferedImage playerImage;

    public PaintPlayerListener() {
        player = LevelManager.getPlayer();
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
        checkCollision();
    }

    @EventListener
    public void onPaint(RepaintEvent e) {
        drawPlayer(e.getGraphics());
    }
    
    public void checkCollision() {
        for(Wall w : LevelManager.getCurrentLevel().getWalls()) {
            if(w.getBounds().intersects(player.getBounds())) {
                player.setBackward(false);
                player.setForward(false);
                player.setRight(false);
                player.setLeft(false);
            }
        }
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
}

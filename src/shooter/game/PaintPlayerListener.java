package shooter.game;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import shooter.entities.Player;
import shooter.events.EventListener;
import shooter.events.Listener;
import shooter.events.types.RepaintEvent;
import shooter.level.LevelManager;

public class PaintPlayerListener implements Listener {

    Player player;
    public PaintPlayerListener() {
        player = LevelManager.getPlayer();
    }
    @EventListener
    public void onPaint(RepaintEvent e) {
        drawPlayer();
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
        g2.drawImage(playerImage, player.getX(), player.getY(), this);
        
    }
}

package shooter.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import shooter.entities.Bullet;
import shooter.entities.Wall;
import shooter.entities.WeaponType;
import shooter.events.EventListener;
import shooter.events.Listener;
import shooter.events.types.RepaintEvent;
import shooter.level.LevelManager;

public class PaintEntityListener implements Listener {
    
    AffineTransform blank;
    ArrayList<Integer> toRemove;
    int width, height;
    public PaintEntityListener() {
        this.width = Toolkit.getDefaultToolkit().getScreenSize().width + 2;
        this.height = Toolkit.getDefaultToolkit().getScreenSize().height + 2;
        toRemove = new ArrayList<>();
        blank = new AffineTransform();
    }
    
    @EventListener
    public void onPaint(RepaintEvent e) {
        paintBullets(e.getGraphics());
        paintWalls(e.getGraphics());
    }
    
    private void paintWalls(Graphics2D g2) {
        for(Wall w : LevelManager.getCurrentLevel().getWalls()) {
            //System.out.println(w.getX() + " " + w.getY());
            g2.drawRect(w.getX(), w.getY(), w.getHeight(), w.getWidth());
        }
    }

    public void paintBullets(Graphics2D g2) {
        g2.setTransform(blank);
        g2.setColor(Color.BLACK);
        for(Bullet b : LevelManager.getPlayer().getBullets()) {
            if(b.getType() == WeaponType.PISTOL) {
                g2.setColor(new Color(132, 55, 0));
            }
            g2.drawRect(b.getX(), b.getY(), 1, 1);
            b.move();
            if(b.getX() > width) {
                toRemove.add(b.getID());
                continue;
            }
            if(b.getY() > height) {
                toRemove.add(b.getID());
                continue;
            }
        }
        for(int i : toRemove) LevelManager.getPlayer().getBullets().remove(i);
        toRemove.clear();
    }

}

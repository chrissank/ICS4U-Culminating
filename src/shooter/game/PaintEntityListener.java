package shooter.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import shooter.Main;
import shooter.entities.Bullet;
import shooter.entities.Enemy;
import shooter.entities.Wall;
import shooter.entities.WeaponType;
import shooter.events.EventListener;
import shooter.events.Listener;
import shooter.events.types.GameTickEvent;
import shooter.events.types.RepaintEvent;
import shooter.level.LevelManager;

public class PaintEntityListener implements Listener {
    
    AffineTransform blank;
    ArrayList<Integer> toRemove;
    int width, height;
    BufferedImage playerImage;
    
    public PaintEntityListener() {
        this.width = Toolkit.getDefaultToolkit().getScreenSize().width + 2;
        this.height = Toolkit.getDefaultToolkit().getScreenSize().height + 2;
        toRemove = new ArrayList<>();
        blank = new AffineTransform();
        try {
            playerImage = ImageIO.read(PaintEntityListener.class.getResource("/resources/Zombie.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @EventListener
    public void onTick(GameTickEvent e) {
        checkCollision();
    }
    
    @EventListener
    public void onPaint(RepaintEvent e) {
        paintBullets(e.getGraphics());
        paintWalls(e.getGraphics());
        paintEnemy(e.getGraphics());
    }
    
    private void checkCollision() {
        for(Wall w : LevelManager.getCurrentLevel().getWalls()) {
            for(Bullet b : LevelManager.getPlayer().getBullets()) {
                if(b.getBounds().intersects(w.getBounds())) {
                    b.setX(-5000);
                    toRemove.add(b.getID());
                }
            }
        }
        for(int i : toRemove) LevelManager.getPlayer().getBullets().remove(i);
        toRemove.clear();
    }

    public void paintWalls(Graphics2D g2) {
        g2.setColor(new Color(45, 47, 49));
        for(Wall w : LevelManager.getCurrentLevel().getWalls()) {
            g2.fillRect(w.getX(), w.getY(), w.getWidth(), w.getHeight());
        }
    }

    public void paintEnemy(Graphics2D g2) {
        for(Enemy e : LevelManager.getCurrentLevel().getEnemies()) {
            AffineTransform af = new AffineTransform();
            af.rotate(e.getRotation() + 1.5708, e.getX() + 34/2, e.getY() + 39/2);
            g2.setTransform(af);
            g2.drawImage(playerImage, e.getX(), e.getY(), Main.getInstance().gamedisplay);
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

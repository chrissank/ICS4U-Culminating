package shooter.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

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
    ArrayList<Integer> toRemoveBullet;
    ArrayList<Integer> toRemoveEnemy;
    int width, height;
    BufferedImage enemyImage;
    BufferedImage healthPackImg;
    BufferedImage ammoBoxImg;
    Color[] redColour = new Color[4];
    public static ConcurrentHashMap<Integer, Integer> xy;
    
    public PaintEntityListener() {
        this.width = Toolkit.getDefaultToolkit().getScreenSize().width + 2;
        this.height = Toolkit.getDefaultToolkit().getScreenSize().height + 2;
        toRemoveBullet = new ArrayList<>();
        toRemoveEnemy = new ArrayList<>();
        blank = new AffineTransform();
        try {
            enemyImage = ImageIO.read(PaintEntityListener.class.getResource("/resources/Zombie.png"));
            healthPackImg = ImageIO.read(PaintHUDListener.class.getResource("/resources/Health Pack.png"));
            ammoBoxImg = ImageIO.read(PaintHUDListener.class.getResource("/resources/Ammo Box.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        xy = new ConcurrentHashMap<>();
        redColour[0] = new Color(181, 17, 17);
        redColour[1] = new Color(192, 19, 19);
        redColour[2] = new Color(155, 11, 11);
        redColour[3] = new Color(183, 43, 43);
        
    }
    
    @EventListener
    public void onTick(GameTickEvent e) {
        checkBulletCollision();
        for(Enemy en : LevelManager.getCurrentLevel().getEnemies()) {
            en.move();
        }
    }
    
    @EventListener
    public void onPaint(RepaintEvent e) {
        paintBullets(e.getGraphics());
        paintWalls(e.getGraphics());
        paintEnemy(e.getGraphics());
        paintCrates(e.getGraphics());
        paintBlood(e.getGraphics());
    }
    
    private void paintBlood(Graphics2D g2) {
        for(Entry<Integer, Integer> ent : xy.entrySet()) {
            generateBloodSplatter(g2, ent.getKey(), ent.getValue());
        }
    }
    
    private void checkBulletCollision() {
        for(Enemy e : LevelManager.getCurrentLevel().getEnemies()) {
            for(Bullet b : LevelManager.getPlayer().getBullets()) {
                if(b.getBounds().intersects(e.getBounds(e.getX(), e.getY()))) {
                    e.setHealth(e.getHealth() - (b.getType() == WeaponType.PISTOL ? 40 : 20));
                    b.setX(-50000);
                    toRemoveBullet.add(b.getID());
                    if(e.getHealth() <= 0) {
                        e.setX(-50000);
                        toRemoveEnemy.add(e.getID());
                    }
                    xy.put(e.getX(), e.getY());
                    //generateBloodSplatter(Graphics2D g2, int x, int y)
                }
            }
        }
        for(Wall w : LevelManager.getCurrentLevel().getWalls()) {
            for(Bullet b : LevelManager.getPlayer().getBullets()) {
                if(b.getBounds().intersects(w.getBounds())) {
                    b.setX(-5000);
                    toRemoveBullet.add(b.getID());
                }
            }
        }
        for(int i : toRemoveBullet) LevelManager.getPlayer().getBullets().remove(i);
        toRemoveBullet.clear();
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
            af.rotate(e.getRotation() + 1.5708, e.getX() + 20, e.getY() + 20);
            g2.setTransform(af);
            g2.drawImage(enemyImage, e.getX(), e.getY(), Main.getInstance().gamedisplay);
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
                toRemoveBullet.add(b.getID());
                continue;
            }
            if(b.getY() > height) {
                toRemoveBullet.add(b.getID());
                continue;
            }
        }
        for(int i : toRemoveBullet) LevelManager.getPlayer().getBullets().remove(i);
        toRemoveBullet.clear();
    }
    
    public void paintCrates(Graphics2D g2) {
        g2.setTransform(blank);
        g2.drawImage(healthPackImg, LevelManager.getCurrentLevel().getHealthpackX(), LevelManager.getCurrentLevel().getHealthpackY(), Main.getInstance().gamedisplay);
        g2.drawImage(ammoBoxImg, LevelManager.getCurrentLevel().getAmmoX(), LevelManager.getCurrentLevel().getAmmoY(), Main.getInstance().gamedisplay);
    }

    private void generateBloodSplatter(Graphics2D g2, int x, int y) {
        g2.setColor(Color.RED);
        g2.fill(new Ellipse2D.Double(x - 1, y + 2, 12, 7));
        g2.fill(new Ellipse2D.Double(x - 2, y, 9, 13));
        g2.fill(new Ellipse2D.Double(x + 11, y + 13, 11, 6));
        g2.fill(new Ellipse2D.Double(x - 2, y +1, 11, 6));
        g2.fill(new Ellipse2D.Double(x + 2, y -5, 11, 6));
    }
}

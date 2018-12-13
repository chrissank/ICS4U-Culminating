package shooter.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import shooter.Main;
import shooter.entities.Bullet;
import shooter.entities.Enemy;
import shooter.entities.Wall;
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
    ArrayList<BufferedImage> paintImgs;
    ArrayList<BufferedImage> bloodImgs;
    public static int[][] bloodXYI;
    public static int[][] paintXYI;
    public static int count1 = 0;
    public static int count2 = 0;
    
    public PaintEntityListener() {
        this.width = Toolkit.getDefaultToolkit().getScreenSize().width + 2;
        this.height = Toolkit.getDefaultToolkit().getScreenSize().height + 2;
        toRemoveBullet = new ArrayList<>();
        toRemoveEnemy = new ArrayList<>();
        blank = new AffineTransform();
        paintImgs = new ArrayList<>();
        bloodImgs = new ArrayList<>();
        try {
            enemyImage = ImageIO.read(PaintEntityListener.class.getResource("/resources/Zombie.png"));
            healthPackImg = ImageIO.read(PaintHUDListener.class.getResource("/resources/Health Pack.png"));
            paintImgs.add(ImageIO.read(PaintHUDListener.class.getResource("/resources/paintball1.png")));
            paintImgs.add(ImageIO.read(PaintHUDListener.class.getResource("/resources/paintball2.png")));
            paintImgs.add(ImageIO.read(PaintHUDListener.class.getResource("/resources/paintball3.png")));
            bloodImgs.add(ImageIO.read(PaintHUDListener.class.getResource("/resources/blood1.png")));
            bloodImgs.add(ImageIO.read(PaintHUDListener.class.getResource("/resources/blood2.png")));
            bloodImgs.add(ImageIO.read(PaintHUDListener.class.getResource("/resources/blood3.png")));
            ammoBoxImg = ImageIO.read(PaintHUDListener.class.getResource("/resources/Ammo Box.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        bloodXYI = new int[10000][3];
        paintXYI = new int[10000][3];
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
        paintPaintballs(e.getGraphics());
        paintWalls(e.getGraphics());
        paintEnemy(e.getGraphics());
        paintCrates(e.getGraphics());
        paintBlood(e.getGraphics());
    }

    private void paintBlood(Graphics2D g2) {
        for(int[] arr : bloodXYI) {
            if(arr[0] == arr[1] && arr[1] == 0) continue;
            g2.drawImage(bloodImgs.get(arr[2]), arr[0], arr[1], Main.getInstance().gamedisplay);
        }
    }
    
    private void checkBulletCollision() {
        for(Enemy e : LevelManager.getCurrentLevel().getEnemies()) {
            for(Bullet b : LevelManager.getPlayer().getBullets()) {
                if(b.getBounds().intersects(e.getBounds(e.getX(), e.getY()))) {
                    e.setHealth(e.getHealth() - 20);
                    b.setX(-50000);
                    toRemoveBullet.add(b.getID());
                    if(e.getHealth() <= 0) {
                        e.setX(-50000);
                        toRemoveEnemy.add(e.getID());
                    }
                    bloodXYI[count2] = new int[]{e.getX(), e.getY(), ThreadLocalRandom.current().nextInt(bloodImgs.size())};
                    count2++;
                }
            }
        }
        for(Wall w : LevelManager.getCurrentLevel().getWalls()) {
            for(Bullet b : LevelManager.getPlayer().getBullets()) {
                if(b.getBounds().intersects(w.getBounds())) {
                    addPaintball(b.getX(), b.getY());
                    b.setX(-5000);
                    toRemoveBullet.add(b.getID());
                }
            }
        }
        for(int i : toRemoveBullet) LevelManager.getPlayer().getBullets().remove(i);
        toRemoveBullet.clear();
    }
    
    private void addPaintball(int x, int y) {
        paintXYI[count1] = new int[]{x, y, ThreadLocalRandom.current().nextInt(paintImgs.size())};
        count1++;
    }
    
    private void paintPaintballs(Graphics2D g2) {
        for(int[] arr : paintXYI) {
            if(arr[0] == arr[1] && arr[1] == 0) continue;
            g2.drawImage(paintImgs.get(arr[2]), arr[0], arr[1], Main.getInstance().gamedisplay);
        }
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
}

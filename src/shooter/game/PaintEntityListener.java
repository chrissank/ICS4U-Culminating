package shooter.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
    Color[] redColour = new Color[4];
    
    public PaintEntityListener() {
        this.width = Toolkit.getDefaultToolkit().getScreenSize().width + 2;
        this.height = Toolkit.getDefaultToolkit().getScreenSize().height + 2;
        toRemoveBullet = new ArrayList<>();
        toRemoveEnemy = new ArrayList<>();
        blank = new AffineTransform();
        try {
            enemyImage = ImageIO.read(PaintEntityListener.class.getResource("/resources/Zombie.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

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
    }
    
    private void checkBulletCollision() {
        for(Enemy e : LevelManager.getCurrentLevel().getEnemies()) {
            for(Bullet b : LevelManager.getPlayer().getBullets()) {
                if(b.getBounds().intersects(e.getBounds(e.getX(), e.getY()))) {
                    e.setHealth(e.getHealth() - (b.getType() == WeaponType.PISTOL ? 20 : 10));
                    b.setX(-5000);
                    toRemoveBullet.add(b.getID());
                    if(e.getHealth() == 0) {
                        e.setX(-50000);
                        toRemoveEnemy.add(e.getID());
                    }
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
        /*for(int i : toRemoveEnemy) LevelManager.getCurrentLevel().getEnemies().remove(i);
        toRemoveEnemy.clear();*/
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

    private void generateBloodSplatter(Graphics2D g2, int x, int y) {
        Random rand = new Random();
        int numberOfSplats = (rand.nextInt(10) % 4) + 1;
        int sizeOfSplat = rand.nextInt(10) + 10;
        int colorIndex = rand.nextInt(10) % 4;
        
        for (int i = 0; i < numberOfSplats; i++) {
            int randomSpaceX = (rand.nextInt(50) % 10) + 1;
            int randomSpaceY = (rand.nextInt(50) % 10) + 1;
            drawBloodSplat(g2, x, y, sizeOfSplat, colorIndex, randomSpaceX, randomSpaceY);
        }
    }
    
    private void drawBloodSplat(Graphics2D g2, int x, int y, int sizeOfSplat, int colorIndex, int randomSpaceX, int randomSpaceY) {
        
        if (getRandomBoolean() == true) {
            x += randomSpaceX;
            y -= randomSpaceY;
        }
        
        else {
            x -= randomSpaceX;
            y += randomSpaceY;
        }
        
        g2.setPaint(redColour[colorIndex]);
        g2.fill(new Ellipse2D.Double(x, y, sizeOfSplat, sizeOfSplat));
    }
    
    private boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }
}

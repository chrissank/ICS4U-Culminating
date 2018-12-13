package shooter.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import shooter.GameFrame;
import shooter.Main;
import shooter.entities.Player;
import shooter.events.EventListener;
import shooter.events.Listener;
import shooter.events.types.PreGameInitiateEvent;
import shooter.events.types.RepaintEvent;
import shooter.level.LevelManager;

public class PaintHUDListener implements Listener {

    Player player;
    Main main;
    BufferedImage rifleImg;
    BufferedImage rifleAmmoImg;

    JButton rifleButton;

    int width;
    int height;
    

    public PaintHUDListener() {
        this.main = Main.getInstance();
    }

    @EventListener
    public void onGameStart(PreGameInitiateEvent e) {
        try {
            rifleImg = ImageIO.read(PaintHUDListener.class.getResource("/resources/Rifle.png"));
            rifleAmmoImg = ImageIO.read(PaintHUDListener.class.getResource("/resources/Rifle_Ammo_Icon.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.player = LevelManager.getPlayer();
        this.width = e.getWidth();
        this.height = e.getHeight();
        
        initializeGUIDetails();
    }

    @EventListener
    public void onRepaint(RepaintEvent e) {
        drawHUD(e.getGraphics());
    }

    public void drawHUD(Graphics2D g2)
    {
    	g2.setFont(new Font("ZombieA", Font.PLAIN, 50));
    	g2.drawString("Level " + LevelManager.getCurrentLevel().getNumber() + " - " + (LevelManager.getCurrentLevel().getTime() - GameThread.time), (GameFrame.width / 2) - 110, 75);
    	
        drawHealthBar(g2);
        drawAmmo(g2);
        //IF ZOMBIE DIES
    }

    public void drawHealthBar(Graphics2D g2) {
       
    	int healthBarX = width / 2;
        int healthBarY = height - 80;

        g2.setPaint(Color.BLACK);
        g2.setStroke(new BasicStroke(6));
        g2.draw(new Rectangle2D.Double(healthBarX - 150, healthBarY, 300, 40)); //Health bar border

        if (player.getHealth() < 100) {
            g2.setPaint(Color.GRAY);
            g2.fill(new Rectangle2D.Double(healthBarX - 150, healthBarY, 300, 40)); //Health bar grey background portion
        }

        g2.setPaint(Color.RED);
        g2.fill(new Rectangle2D.Double(healthBarX - 150, healthBarY, player.getHealth() * 3, 40)); //Health bar filled portion

        g2.setPaint(Color.BLACK);
        g2.setFont(new Font("ZombieA", Font.BOLD, 22));
        g2.drawString(Integer.toString(player.getHealth()), healthBarX - 10, healthBarY + 70); // Health number below health bar
    }

    public void drawAmmo(Graphics2D g2) {
        int ammoX = (width / 2) + 205;
        int ammoY = height - 100;
        g2.drawImage(rifleImg, ammoX, ammoY, Main.getInstance().gamedisplay);
        g2.drawString(Integer.toString(player.getRifleAmmo()), ammoX + 75, ammoY + 50);
    }

	public void initializeGUIDetails()
    {
        rifleButton = new JButton();
        rifleButton.setFocusable(false);

        rifleButton.setIcon(new ImageIcon(rifleImg));

        main.gamedisplay.add(rifleButton);
        main.gamedisplay.setLayout(null);
    }

}

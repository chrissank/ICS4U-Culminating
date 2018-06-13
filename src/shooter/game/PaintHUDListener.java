package shooter.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import shooter.Main;
import shooter.entities.Player;
import shooter.entities.WeaponType;
import shooter.events.EventListener;
import shooter.events.Listener;
import shooter.events.types.PreGameInitiateEvent;
import shooter.events.types.RepaintEvent;
import shooter.level.LevelManager;

public class PaintHUDListener implements Listener {

    Player player;
    Main main;
    BufferedImage backgroundImg;
    BufferedImage pistolImg;
    BufferedImage pistolAmmoImg;
    BufferedImage rifleImg;
    BufferedImage rifleAmmoImg;

    JButton pistolButton;
    JButton rifleButton;

    int width;
    int height;
    
    Color[] redColour = new Color[4];

    public PaintHUDListener() {
        this.main = Main.getInstance();
    }

    @EventListener
    public void onGameStart(PreGameInitiateEvent e) {
        try {
            backgroundImg = ImageIO.read(PaintHUDListener.class.getResource("/resources/Background.png"));
            pistolImg = ImageIO.read(PaintHUDListener.class.getResource("/resources/Pistol.png"));
            pistolAmmoImg = ImageIO.read(PaintHUDListener.class.getResource("/resources/Pistol_Ammo_Icon.png"));
            rifleImg = ImageIO.read(PaintHUDListener.class.getResource("/resources/Rifle.png"));
            rifleAmmoImg = ImageIO.read(PaintHUDListener.class.getResource("/resources/Rifle_Ammo_Icon.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.player = LevelManager.getPlayer();
        this.width = e.getWidth();
        this.height = e.getHeight();
        
        redColour[0] = new Color(216, 55, 55);
        redColour[1] = new Color(206, 17, 17);
        redColour[2] = new Color(155, 11, 11);
        redColour[3] = new Color(183, 43, 43);
        
        initializeGUIDetails();
    }

    @EventListener
    public void onRepaint(RepaintEvent e) {
        drawHUD(e.getGraphics());
    }

    public void drawHUD(Graphics2D g2)
    {        
        drawHealthBar(g2);
        drawAmmo(g2);
        drawWeaponButtons(g2);
        
        //IF ZOMBIE DIES
        generateBloodSplatter(g2, 454, 876);
        
        //BACKGROUND IMAGE NOT WORKING AT THE MOMENT
        //g2.drawImage(backgroundImg, 0, 0, this);
    }
    

   
    private void drawHealthBar(Graphics2D g2) {
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
        g2.setFont(new Font("Roboto", Font.BOLD, 22));
        g2.drawString(Integer.toString(player.getHealth()), healthBarX - 10, healthBarY + 70); // Health number below health bar
    }

   
    private void drawAmmo(Graphics2D g2) {
        int ammoX = (width / 2) + 205;
        int ammoY = height - 100;
        BufferedImage ammoType = player.getWeapon().equals(WeaponType.PISTOL) ? pistolAmmoImg : rifleAmmoImg;
        int ammoNum = player.getWeapon().equals(WeaponType.PISTOL) ? player.getPistolAmmo() : player.getRifleAmmo();
        g2.drawImage(ammoType, ammoX, ammoY, Main.getInstance().gamedisplay);
        g2.drawString(Integer.toString(ammoNum), ammoX + 75, ammoY + 50);
    }

   
    private void drawWeaponButtons(Graphics2D g2) {
        int weaponX = width - 120;
        int weaponY = height - 300;
        pistolButton.setBounds(weaponX, weaponY, 75, 75);
        rifleButton.setBounds(weaponX, weaponY + 105, 75, 75);
        g2.drawString("1", weaponX - 20, weaponY + 20);
        g2.drawString("2", weaponX - 20, weaponY + 125);
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

	public void initializeGUIDetails()
    {
        pistolButton = new JButton();
        rifleButton = new JButton();
        pistolButton.setFocusable(false);
        rifleButton.setFocusable(false);
        pistolButton.addActionListener((e) -> {
            player.setWeapon(WeaponType.PISTOL);
            pistolButton.setBackground(Color.GREEN);
            rifleButton.setBackground(Color.WHITE);
        });
        rifleButton.addActionListener((e) -> {
            player.setWeapon(WeaponType.RIFLE);
            pistolButton.setBackground(Color.WHITE);
            rifleButton.setBackground(Color.GREEN);
        });
        pistolButton.setActionCommand("Pistol");
        rifleButton.setActionCommand("Rifle");
        pistolButton.setBackground(Color.GREEN);

        pistolButton.setIcon(new ImageIcon(pistolImg));
        rifleButton.setIcon(new ImageIcon(rifleImg));

        main.gamedisplay.add(pistolButton);
        main.gamedisplay.add(rifleButton);
        main.gamedisplay.setLayout(null);
    }

}

package shooter.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import shooter.entities.Player;
import shooter.entities.WeaponType;

public class GameDisplay extends JPanel implements ActionListener{
    private static final long serialVersionUID = 1L;
    
    int width;
    int height;
    
    Player player;
    
    BufferedImage playerImage;
    BufferedImage backgroundImg;
    BufferedImage pistolImg;
    BufferedImage pistolAmmoImg;
    BufferedImage rifleImg;
    
    JButton pistolButton;
    JButton rifleButton;
    
    public GameDisplay() {
        try {
			playerImage = ImageIO.read(GameDisplay.class.getResource("/resources/player_1.png"));
			backgroundImg = ImageIO.read(GameDisplay.class.getResource("/resources/Background.png"));
			pistolImg = ImageIO.read(GameDisplay.class.getResource("/resources/Pistol.png"));
			pistolAmmoImg = ImageIO.read(GameDisplay.class.getResource("/resources/Pistol_Ammo_Icon.png"));
			rifleImg = ImageIO.read(GameDisplay.class.getResource("/resources/Rifle.png"));			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        player = new Player(100, 100, 100, WeaponType.PISTOL, 100, 0);
        
        InitializeGUI();
    }
    
    public void InitializeGUI()
    {
    	pistolButton = new JButton();
    	rifleButton = new JButton();
    	pistolButton.addActionListener(this);
    	rifleButton.addActionListener(this);
    	pistolButton.setActionCommand("Pistol");
    	rifleButton.setActionCommand("Rifle");
    	pistolButton.setBackground(Color.GREEN);
    	
    	pistolButton.setIcon(new ImageIcon(pistolImg));
    	rifleButton.setIcon(new ImageIcon(rifleImg));
    	
    	this.add(pistolButton);
    	this.add(rifleButton);
    	this.setLayout(null);
        player = new Player(100, 100, 100, WeaponType.PISTOL, 100, 0);
    }
    
	public void paint(Graphics g)
	{
		super.paint(g);
		
		// background, walls, players, enemies
		
		Graphics2D g2 = (Graphics2D) g;
		
		DrawHUD(g2);
		
		drawPlayer(g2);
	}
	
	public void DrawHUD(Graphics2D g2)
	{
	    width = this.getWidth();
	    height = this.getHeight();
		
		//Background
		//g2.drawImage(backgroundImg, 0, 0, this);
		
		//Health bar
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
		
		//Pistol ammo
		int ammoX = (width / 2) + 205;
		int ammoY = height - 100;
		g2.drawImage(pistolAmmoImg, ammoX, ammoY, this);
		g2.drawString(Integer.toString(player.getAmmo()), ammoX + 75, ammoY + 50);
		
    	int weaponX = width - 120;
    	int weaponY = height - 300;
    	pistolButton.setBounds(weaponX, weaponY, 75, 75);
    	rifleButton.setBounds(weaponX, weaponY + 20 + 85, 75, 75);	
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

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Pistol"))
		{
			player.setWeapon(WeaponType.PISTOL);
		}
		
		else if (arg0.getActionCommand().equals("Rifle"))
		{
			player.setWeapon(WeaponType.RIFLE);
		}
		
		pistolButton.setBackground(player.getWeapon().equals(WeaponType.PISTOL) ? Color.GREEN : Color.WHITE);
		rifleButton.setBackground(player.getWeapon().equals(WeaponType.RIFLE) ? Color.GREEN : Color.WHITE);
	}
}
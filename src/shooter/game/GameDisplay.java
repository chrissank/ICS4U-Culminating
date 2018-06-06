package shooter.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import shooter.entities.Player;
import shooter.entities.WeaponType;

public class GameDisplay extends JPanel {
    private static final long serialVersionUID = 1L;
    
    Player player;
    
    BufferedImage pistolAmmoImg;
    BufferedImage backgroundImg;
    BufferedImage playerImage;
    
    public GameDisplay() {
        try {
			pistolAmmoImg = ImageIO.read(GameDisplay.class.getResource("/resources/Pistol_Ammo_Icon.png"));
			backgroundImg = ImageIO.read(GameDisplay.class.getResource("/resources/Background.png"));
			playerImage = ImageIO.read(GameDisplay.class.getResource("/resources/player_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        player = new Player(100, 100, 100, WeaponType.PISTOL, 100);
    }
    
    @Override
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
		//Background
		g2.drawImage(backgroundImg, 0, 0, this);
		
		//Health bar
		int healthBarX = this.getWidth() / 2;
		int healthBarY = this.getHeight() - 80;
		g2.setPaint(Color.BLACK);
		g2.setStroke(new BasicStroke(6));
		g2.draw(new Rectangle2D.Double(healthBarX - 150, healthBarY, 300, 40)); //Health bar border
		g2.setPaint(Color.RED);
		g2.fill(new Rectangle2D.Double(healthBarX - 150, healthBarY, player.getHealth() * 3, 40)); //Health bar filled portion
		
		if (player.getHealth() < 100) {
			g2.setPaint(Color.GRAY);
			int emptyBarStart = (healthBarX - 150) + (player.getHealth() * 3);
			int emptyBarFinish = 300 - (player.getHealth() * 3);
			g2.fill(new Rectangle2D.Double(emptyBarStart, healthBarY, emptyBarFinish, 40)); //Health bar empty portion
		}
		
		g2.setPaint(Color.BLACK);
		g2.setFont(new Font("Roboto", Font.BOLD, 22));
		g2.drawString(Integer.toString(player.getHealth()), healthBarX - 10, healthBarY + 70); // Health number below health bar
		
		//Pistol ammo
		int ammoX = (this.getWidth() / 2) + 205;
		int ammoY = this.getHeight() - 100;
		g2.drawImage(pistolAmmoImg, ammoX, ammoY, this);
		g2.drawString(Integer.toString(player.getAmmo()), ammoX + 75, ammoY + 50); 
	
	}
	
	public void drawPlayer(Graphics2D g2) {
	    Point m = MouseInfo.getPointerInfo().getLocation();
	    float x = m.x - player.getX();
	    float y = m.y - player.getY();
	    double rot = Math.atan2(y, x);
	    AffineTransform af = new AffineTransform();
	    af.rotate(rot - 1.4, player.getX() + 34/2, player.getY() + 39/2);
	    g2.setTransform(af);
        g2.drawImage(playerImage, player.getX(), player.getY(), this);
        
	}
}
package shooter.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
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
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		DrawHUD(g2);
		
		drawPlayer(g2);
	}
	
	public void DrawHUD(Graphics2D g2)
	{
		//Background
		g2.drawImage(backgroundImg, 0, 0, this);
		
		//Health bar
		g2.setPaint(Color.RED);
		int healthBarX = this.getWidth() / 2;
		int healthBarY = this.getHeight() - 70;
		g2.draw(new Rectangle2D.Double(healthBarX - 150, healthBarY, 300, 40)); //Health bar border
		g2.fill(new Rectangle2D.Double(healthBarX - 150, healthBarY, player.getHealth() * 3, 40)); //Health bar fill
		
		g2.setPaint(Color.BLACK);
		g2.setFont(new Font("Roboto", Font.PLAIN, 20));
		g2.drawString(Integer.toString(player.getHealth()), healthBarX, healthBarY + 60); // Health number below health bar
		
		//Pistol ammo
		g2.drawImage(pistolAmmoImg, 1165, 990, this);
		g2.drawString(Integer.toString(player.getAmmo()), 1240, 1040); 
		
	
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

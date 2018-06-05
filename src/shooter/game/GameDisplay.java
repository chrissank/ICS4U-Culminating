package shooter.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameDisplay extends JPanel {
    private static final long serialVersionUID = 1L;
    
    int playerHealthDemo = 75;
    BufferedImage pistolAmmo;
<<<<<<< HEAD
=======
    //
>>>>>>> branch 'master' of https://github.com/Pixelatorx2/ICS4U-Culminating.git
    
    
    public GameDisplay() {
        try {
			pistolAmmo = ImageIO.read(GameDisplay.class.getResource("/resources/Pistol_Ammo_Icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		//Health bar
		g2.setPaint(Color.RED);
		int healthBarX = this.getWidth() / 2;
		int healthBarY = this.getHeight() - 70;
		g2.draw(new Rectangle2D.Double(healthBarX - 150, healthBarY, 300, 40)); //Health bar border
		g2.fill(new Rectangle2D.Double(healthBarX - 150, healthBarY, playerHealthDemo * 3, 40)); //Health bar fill
		
		g2.setPaint(Color.BLACK);
		g2.setFont(new Font("Roboto", Font.PLAIN, 20));
		g2.drawString(Integer.toString(playerHealthDemo), healthBarX, healthBarY + 60); // Health number below health bar
<<<<<<< HEAD
		
		g2.drawImage(pistolAmmo, 0, 0, this);
		
		System.out.println("repaint");
=======
	
>>>>>>> branch 'master' of https://github.com/Pixelatorx2/ICS4U-Culminating.git
	}
	
	public void drawPlayer(Graphics2D g2) {
	    
	}
}

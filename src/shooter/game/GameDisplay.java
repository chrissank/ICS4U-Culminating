package shooter.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameDisplay extends JPanel {
    private static final long serialVersionUID = 1L;
    
    int playerHealthDemo = 75;
    
    public GameDisplay() {
        
    }
    
    @Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		// background, walls, players, enemies
		
		Graphics2D g2 = (Graphics2D) g;
		
		//Health bar
		HealthBar(g2);
		
		drawPlayer(g2);
	}
	
	public void HealthBar(Graphics2D g2)
	{
		g2.setPaint(Color.RED);
		int healthBarX = this.getWidth() / 2;
		int healthBarY = this.getHeight() - 70;
		g2.draw(new Rectangle2D.Double(healthBarX - 150, healthBarY, 300, 40)); //Health bar border
		g2.fill(new Rectangle2D.Double(healthBarX - 150, healthBarY, playerHealthDemo * 3, 40)); //Health bar fill
		
		g2.setPaint(Color.BLACK);
		g2.setFont(new Font("Roboto", Font.PLAIN, 20));
		g2.drawString(Integer.toString(playerHealthDemo), healthBarX, healthBarY + 60); // Health number below health bar
		
	}
	
	public void drawPlayer(Graphics2D g2) {
	    
	}
}

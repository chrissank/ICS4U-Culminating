package shooter.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class GameDisplay extends JPanel {
    private static final long serialVersionUID = 1L;
    
    int playerHealthDemo = 75;
    
    public GameDisplay() {
    }
    
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		//Health bar
		HealthBar(g2);
	}
	
	public void HealthBar(Graphics2D g2)
	{
		g2.setPaint(Color.RED);
		int healthBarX = this.getWidth() / 2;
		int healthBarY = this.getHeight() - 70;
		g2.draw(new Rectangle2D.Double(healthBarX - 150, healthBarY, 300, 40)); //Border
		g2.fill(new Rectangle2D.Double(healthBarX - 150, healthBarY, playerHealthDemo * 3, 40)); //Health
	}
}

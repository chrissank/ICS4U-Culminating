package shooter.game;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import shooter.events.EventHandler;
import shooter.events.types.RepaintEvent;

public class GameDisplay extends JPanel {
    private static final long serialVersionUID = 1L;
    
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		EventHandler.callEvent(new RepaintEvent(g2));
	}
	
}
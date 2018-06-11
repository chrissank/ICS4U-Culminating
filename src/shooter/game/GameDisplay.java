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

import shooter.entities.WeaponType;
import shooter.events.EventHandler;
import shooter.events.types.RepaintEvent;

public class GameDisplay extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    
    public GameDisplay() {
        
    }
    
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		EventHandler.callEvent(new RepaintEvent(g2));
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
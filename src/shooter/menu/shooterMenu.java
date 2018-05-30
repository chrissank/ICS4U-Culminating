package shooter.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class shooterMenu extends JPanel implements ActionListener {

	JLabel title = new JLabel("SHOOTER GAME");
	JButton play = new JButton("Play");
	JButton quit = new JButton("Quit");
	JPanel buttonsPanel = new JPanel();
	
	public shooterMenu() {
		play.addActionListener(this);
		quit.addActionListener(this);
		
		play.setActionCommand("Play");
		quit.setActionCommand("Quit");
		
		buttonsPanel.add(play);
		buttonsPanel.add(quit);
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		
		this.add(title);
		this.add(buttonsPanel);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Play"))
		{
			System.out.println("Call game event.");
			//Call game event
		}
		
		if (arg0.getActionCommand().equals("Quit"))
		{
			System.exit(0);
		}
	}
}

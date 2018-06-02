package shooter.menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import shooter.events.EventHandler;
import shooter.events.types.GameInitiateEvent;

public class shooterMenu extends JPanel implements ActionListener {

	JLabel title = new JLabel("SHOOTER GAME (Name TBD)");
	JButton play = new JButton("Play");
	JButton quit = new JButton("Quit");
	JPanel buttonsPanel = new JPanel();
	
	JRadioButton easyDifficulty = new JRadioButton("Easy", true);
	JRadioButton mediumDifficulty = new JRadioButton("Medium", false);
	JRadioButton hardDifficulty = new JRadioButton("Hard", true);
	ButtonGroup difficultiesGroup = new ButtonGroup();
	JPanel confirmPlayPanel = new JPanel();
	JButton confirmPlay = new JButton("Go!");
	
	public shooterMenu() {	
	
		InitializeGuiDetails();
		
		this.add(title);
		this.add(buttonsPanel);
		this.add(confirmPlayPanel);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Play"))
		{
			confirmPlayPanel.setVisible(true);
			repaint();
		}
		
		else if (arg0.getActionCommand().equals("Quit"))
		{
			int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","Exit?",JOptionPane.YES_NO_OPTION);
		       
	        if(confirmed == JOptionPane.YES_OPTION)
	        {
	            System.exit(0);
	        }
		}
		
		else if (arg0.getActionCommand().equals("Go"))
		{
			System.out.println("Playing game...");
			//Call game event
		}
		
		if (arg0.getSource() == easyDifficulty) 
		{
			//Easy difficulty (0)
		    EventHandler.callEvent(new GameInitiateEvent(0));
		}
		
		else if (arg0.getSource() == mediumDifficulty) 
		{
			//Medium difficulty (1)
            EventHandler.callEvent(new GameInitiateEvent(1));
		}
		
		else if (arg0.getSource() == hardDifficulty) 
		{
			//Hard difficulty (2)
            EventHandler.callEvent(new GameInitiateEvent(2));
		}
	}
	
	public void InitializeGuiDetails() {
		title.setFont(new Font("Roboto", Font.ITALIC, 50));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		play.addActionListener(this);
		quit.addActionListener(this);
		play.setActionCommand("Play");
		quit.setActionCommand("Quit");
		buttonsPanel.add(play);
		buttonsPanel.add(quit);
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		
		confirmPlay.addActionListener(this);
		confirmPlay.setActionCommand("Go");
		difficultiesGroup.add(easyDifficulty);
		difficultiesGroup.add(mediumDifficulty);
		difficultiesGroup.add(hardDifficulty);
		confirmPlayPanel.add(easyDifficulty);
		confirmPlayPanel.add(mediumDifficulty);
		confirmPlayPanel.add(hardDifficulty);
		confirmPlayPanel.add(confirmPlay);
		confirmPlayPanel.setLayout(new BoxLayout(confirmPlayPanel, BoxLayout.Y_AXIS));
		confirmPlayPanel.setVisible(false);
	}
}
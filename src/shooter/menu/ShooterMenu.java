package shooter.menu;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import shooter.Main;
import shooter.events.EventHandler;
import shooter.events.types.PreGameInitiateEvent;

public class ShooterMenu extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    JLabel title = new JLabel("SHOOTER GAME (Name TBD)");
	JButton play = new JButton("Play");
	JButton controls = new JButton("Controls");
	JButton quit = new JButton("Quit");
	JPanel buttonsPanel = new JPanel();
	
	JLabel weaponOne = new JLabel("(1) - to use Pistol");
	JLabel weaponTwo  = new JLabel("(2) - to use Rifle");
	JLabel forwards = new JLabel("(W) - to move forward towards cursor");
	JLabel backwards = new JLabel("(S) - to move backward from cursor");
	JLabel left = new JLabel("(A) - to move left of cursor");
	JLabel right = new JLabel("(D) - to move right of cursor");
	JLabel shoot = new JLabel("(SPACEBAR) - to shoot");
	JPanel controlsPanel = new JPanel();
	
	JRadioButton easyDifficulty = new JRadioButton("Easy", true);
	JRadioButton mediumDifficulty = new JRadioButton("Medium", false);
	JRadioButton hardDifficulty = new JRadioButton("Hard", false);
	ButtonGroup difficultiesGroup = new ButtonGroup();
	JPanel confirmPlayPanel = new JPanel();
	JButton confirmPlay = new JButton("Go!");
	
	JPanel bottomSection = new JPanel();
	
	Font titleFont = new Font("Roboto", Font.ITALIC, 50);
	Font defaultFont = new Font("Roboto", Font.PLAIN, 15);
	
	public ShooterMenu() {	
	
		InitializeGuiDetails();
		
		this.add(title);
		this.add(buttonsPanel);
		this.add(bottomSection);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Play"))
		{
			confirmPlayPanel.setVisible(!confirmPlayPanel.isVisible());
			repaint();
		}
		
		else if (arg0.getActionCommand().equals("Controls"))
		{
			controlsPanel.setVisible(!controlsPanel.isVisible());
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
			
			int diff = 0;
            if(mediumDifficulty.isSelected()) diff += 1;
            if(hardDifficulty.isSelected()) diff += 2;
            EventHandler.callEvent(new PreGameInitiateEvent(diff, Main.getInstance().gamedisplay, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
		}
	}
	
	private void InitializeGuiDetails() {
		title.setFont(titleFont);
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		setMainButtons(play, "Play");
		setMainButtons(controls, "Controls");
		setMainButtons(quit, "Quit");
		buttonsPanel.setLayout(new GridLayout(1, 3, 50, 50));
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		confirmPlay.addActionListener(this);
		confirmPlay.setActionCommand("Go");
		setConfirmPlayPanelDetails(easyDifficulty);
		setConfirmPlayPanelDetails(mediumDifficulty);
		setConfirmPlayPanelDetails(hardDifficulty);
		confirmPlayPanel.add(confirmPlay);
		confirmPlay.setFont(defaultFont);
		confirmPlayPanel.setLayout(new BoxLayout(confirmPlayPanel, BoxLayout.Y_AXIS));
		confirmPlayPanel.setVisible(false);	
		
		setControlLabels(weaponOne);
		setControlLabels(weaponTwo);
		setControlLabels(forwards);
		setControlLabels(backwards);
		setControlLabels(left);
		setControlLabels(right);
		setControlLabels(shoot);
		controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));
		controlsPanel.setVisible(false);
		
		bottomSection.add(confirmPlayPanel);
		bottomSection.add(controlsPanel);
		bottomSection.setLayout(new BoxLayout(bottomSection, BoxLayout.X_AXIS));
	}
	
	private void setMainButtons(JButton button, String actionCommand) {
		button.setFont(defaultFont);
		button.addActionListener(this);
		button.setActionCommand(actionCommand);
		buttonsPanel.add(button);
	}
	
	private void setConfirmPlayPanelDetails(JRadioButton radioButton) {
		radioButton.setFont(defaultFont);
		difficultiesGroup.add(radioButton);
		confirmPlayPanel.add(radioButton);
	}
	
	private void setControlLabels(JLabel label) {
		controlsPanel.add(label);
		label.setFont(defaultFont);
	}
}
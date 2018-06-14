package shooter.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import shooter.GameFrame;
import shooter.Main;
import shooter.events.EventHandler;
import shooter.events.types.PreGameInitiateEvent;

public class ShooterMenu extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    BufferedImage backgroundImage;
    JLabel background = new JLabel();

    JLabel title = new JLabel("   ZOMBIE INFESTATION");
    JButton play = new JButton("Play");
    JButton controls = new JButton("Controls");
    JButton quit = new JButton("Quit");
    JPanel buttonsPanel = new JPanel();

    JLabel weaponOne = new JLabel("1 - to use pistol");
    JLabel weaponTwo  = new JLabel("2 - to use rifle");
    JLabel forwards = new JLabel("W - to move forward towards cursor");
    JLabel backwards = new JLabel("S - to move backward from cursor");
    JLabel left = new JLabel("A - to move left of cursor");
    JLabel right = new JLabel("D - to move right of cursor");
    JLabel shoot = new JLabel("SPACEBAR - to shoot");
    boolean controlsShown = false;

    JRadioButton easyDifficulty = new JRadioButton("Easy", true);
    JRadioButton mediumDifficulty = new JRadioButton("Medium", false);
    JRadioButton hardDifficulty = new JRadioButton("Hard", false);
    ButtonGroup difficultiesGroup = new ButtonGroup();
    JButton confirmPlay = new JButton("Go!");
    boolean confirmPlayShown = false;

    Font titleFont = new Font("ZombieTreats", Font.PLAIN, 53);
    Font defaultFont = new Font("ZombieA", Font.PLAIN, 18);
    Color whiteColor = new Color(255, 255, 255);

    public ShooterMenu() {	
        Font font = null;
        InputStream ff = new BufferedInputStream(ShooterMenu.class.getResourceAsStream("/resources/ZOMBIE.TTF"));
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, ff);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        InputStream ff2 = new BufferedInputStream(ShooterMenu.class.getResourceAsStream("/resources/ZombieTreats_0.ttf"));
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, ff2);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge2.registerFont(font);
        InitializeGuiDetails();

        this.add(title);

        this.add(play);
        this.add(controls);
        this.add(quit);

        this.add(easyDifficulty);
        this.add(mediumDifficulty);
        this.add(hardDifficulty);
        this.add(confirmPlay);

        this.add(weaponOne);
        this.add(weaponTwo);
        this.add(forwards);
        this.add(backwards);
        this.add(left);
        this.add(right);
        this.add(shoot);

        this.add(background);

        this.setLayout(null);
    }

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getActionCommand().equals("Play"))
        {
            confirmPlayShown = !confirmPlayShown;
            confirmPlayVisibility();
            repaint();
        }

        else if (arg0.getActionCommand().equals("Controls"))
        {
            controlsShown = !controlsShown;
            controlLabelsVisibility();
            repaint();
        }

        else if (arg0.getActionCommand().equals("Quit"))
        {
            JLabel confirmationQuestion = new JLabel("Are you sure you want to exit?");
            confirmationQuestion.setFont(defaultFont);

            int confirmed = JOptionPane.showConfirmDialog(null, confirmationQuestion, null,JOptionPane.YES_NO_OPTION);

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
        try {
            backgroundImage = ImageIO.read(ShooterMenu.class.getResource("/resources/Main Menu Background.png"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        background.setIcon(new ImageIcon(backgroundImage));
        background.setBounds(0, 0, GameFrame.width, GameFrame.height);

        title.setFont(titleFont);
        title.setBounds(GameFrame.width / 3, 30, 1000, 50);
        title.setForeground(whiteColor);

        setMainButtons(play, "Play", 0);
        setMainButtons(controls, "Controls", 250);
        setMainButtons(quit, "Quit", 500);

        setConfirmPlayPanelDetails(easyDifficulty, 0);
        setConfirmPlayPanelDetails(mediumDifficulty, 40);
        setConfirmPlayPanelDetails(hardDifficulty, 80);
        confirmPlay.addActionListener(this);
        confirmPlay.setActionCommand("Go");
        confirmPlay.setFont(defaultFont);
        confirmPlay.setVisible(confirmPlayShown);
        confirmPlay.setBounds(GameFrame.width / 3, (GameFrame.height / 5) + 120, 140, 50);

        setControlLabels(weaponOne, 20);
		setControlLabels(weaponTwo, 40);
		setControlLabels(forwards, 70);
		setControlLabels(backwards, 90);
		setControlLabels(left, 110);
		setControlLabels(right, 130);
		setControlLabels(shoot, 160);
    }

    private void setMainButtons(JButton button, String actionCommand, int space) {
        button.setFont(defaultFont);
        button.addActionListener(this);
        button.setActionCommand(actionCommand);

        int startX = (GameFrame.width / 3) + space;
        int startY = GameFrame.height / 8;
        button.setBounds(startX, startY, 160, 70);
    }

    private void setConfirmPlayPanelDetails(JRadioButton radioButton, int space) {
        radioButton.setFont(defaultFont);
        difficultiesGroup.add(radioButton);
        radioButton.setVisible(confirmPlayShown);
        radioButton.setOpaque(false);
        radioButton.setForeground(whiteColor);

        int startX = GameFrame.width / 3;
        int startY = (GameFrame.height / 5) + space;
        radioButton.setBounds(startX, startY, 200, 20);
    }

    private void setControlLabels(JLabel label, int space) {
        label.setFont(defaultFont);
        label.setVisible(controlsShown);
        label.setForeground(whiteColor);

        int startX = (GameFrame.width / 3) + 250;
        int startY = (GameFrame.height / 5) + space;
        label.setBounds(startX, startY, 400, 20);
    }

    private void confirmPlayVisibility() {
        easyDifficulty.setVisible(confirmPlayShown);
        mediumDifficulty.setVisible(confirmPlayShown);
        hardDifficulty.setVisible(confirmPlayShown);
        confirmPlay.setVisible(confirmPlayShown);
    }

    private void controlLabelsVisibility() {
        weaponOne.setVisible(controlsShown);
        weaponTwo.setVisible(controlsShown);
        forwards.setVisible(controlsShown);
        backwards.setVisible(controlsShown);
        left.setVisible(controlsShown);
        right.setVisible(controlsShown);
        shoot.setVisible(controlsShown);
    }
}
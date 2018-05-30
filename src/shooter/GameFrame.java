package shooter;

import java.awt.CardLayout;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public GameFrame() {
        this.setTitle("Shooter Game");
        this.setLayout(new CardLayout());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(true);
        this.setVisible(true);
    }

}

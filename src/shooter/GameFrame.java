package shooter;

import java.awt.CardLayout;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    CardLayout lay;
    public GameFrame() {
        this.setTitle("Shooter Game");
        lay = new CardLayout();
        this.setLayout(lay);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(true);
        this.setVisible(true);
    }

    public CardLayout getLayout() {
        return this.lay;
    }
}

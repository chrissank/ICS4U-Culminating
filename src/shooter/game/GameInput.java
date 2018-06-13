package shooter.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;

public class GameInput extends AbstractAction {
    private static final long serialVersionUID = 1L;
    
    ActionListener e;
    public GameInput(ActionListener e) {
        this.e = e;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.e.actionPerformed(e);
    }

}

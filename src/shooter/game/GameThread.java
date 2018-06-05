package shooter.game;

import shooter.Main;

/*
 * Heartbeat of the ingame - will try to keep to 40 ticks per second -- 25ms
 */
public class GameThread extends Thread {
    
    public static boolean STATUS = true;
    
    public void run() {
        while(STATUS) {
            Main.getInstance().getDisplay().repaint();
            
            
            try {
                Thread.sleep(1000 / 40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void cancel() {
        STATUS = false;
    }
}

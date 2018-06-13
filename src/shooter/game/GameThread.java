package shooter.game;

import shooter.events.EventHandler;
import shooter.events.types.GameTickEvent;

/*
 * Heartbeat of the ingame - will try to keep to 40 ticks per second -- 25ms
 */
public class GameThread extends Thread {
    
    public static boolean STATUS = true;
    public static int time = 0;
    int tick;
    
    public void run() {
        tick = 0;
        while(STATUS) {
            EventHandler.callEvent(new GameTickEvent(tick));
            tick++;
            if(tick == 41) {
                time++;
                tick = 0;
            }
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

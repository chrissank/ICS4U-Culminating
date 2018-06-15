package shooter.game;

import shooter.events.EventHandler;
import shooter.events.types.GameOverEvent;
import shooter.events.types.GameTickEvent;
import shooter.level.LevelManager;

/*
 * Heartbeat of the ingame - will try to keep to 40 ticks per second -- 25ms
 */
public class GameThread extends Thread {
    
    public boolean STATUS = true;
    public static int time = 0;
    int tick;
    
    public void run() {
        STATUS = true;
        tick = 0;
        time = 0;
        while(STATUS) {
            EventHandler.callEvent(new GameTickEvent(tick));
            tick++;
            if(tick == 41) {
                time++;
                tick = 0;
            }
            if(time == LevelManager.getCurrentLevel().getTime()) {
                EventHandler.callEvent(new GameOverEvent());
            }
            if(LevelManager.getCurrentLevel().getDifficulty() == 0) {
                if(time % 2 == 0 && tick == 40) {
                    LevelManager.getCurrentLevel().spawn();
                }
            } else if(LevelManager.getCurrentLevel().getDifficulty() == 1) {
                if(tick % 20 == 0) {
                    LevelManager.getCurrentLevel().spawn();
                }
            } else {
                if(tick % 10 == 0) {
                    LevelManager.getCurrentLevel().spawn();
                }
            }
            try {
                Thread.sleep(1000 / 40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

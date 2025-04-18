package Game;

import javax.swing.*;
import java.awt.*;

public class CountUp {
    private int secondsElapsed;
    private int targetSeconds;
    private Timer swingTimer;
    private boolean running = false;

    public CountUp(int targetSeconds) {

        this.targetSeconds = targetSeconds;
        this.secondsElapsed = 0;

        swingTimer = new Timer(1000, e -> {
            if (secondsElapsed < targetSeconds) {
                secondsElapsed++;
            } else {
                swingTimer.stop();
                running = false;
            }
        });
    }

    public void start() {
        if (!running) {
            swingTimer.start();
            running = true;
        }
    }

    public void reset(){
        swingTimer.stop();
        this.secondsElapsed = 0;
        running = false;
    }

    public int getSecondsElapsed() {
        return secondsElapsed;
    }

    public boolean isRunning() { // Check if the timer is running
        return running;
    }

    public void draw(Graphics g){

    int boxWidth = 250;
    int boxHeight = 70;
    int margin = 20;

    int x = GameManager.GAME_WIDTH - boxWidth - margin;
    int y = margin;

    // Draw background box
    g.setColor(new Color(0, 0, 0, 150)); // Semi-transparent black
    g.fillRoundRect(x, y, boxWidth, boxHeight, 25, 25);

    // Draw border
    g.setColor(Color.WHITE);
    g.drawRoundRect(x, y, boxWidth, boxHeight, 25, 25);

    // Draw timer text
    g.setFont(new Font("Arial", Font.BOLD, 30));
    
    if (secondsRemaining <= 10) {
        g.setColor(Color.RED);
    } else {
        g.setColor(Color.WHITE);
    }
    
    g.drawString("Time Left: " + secondsRemaining, x+25, y+45); // Adjusted position for better centering
    }


}

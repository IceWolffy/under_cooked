package Game;

import javax.swing.*;
import java.awt.*;

public class CountUp {
    private int secondsRemaining;
    private Timer swingTimer;
    private boolean running = false;

    public CountUp(int totalSeconds) {
        this.secondsRemaining = totalSeconds;

        swingTimer = new Timer(1000, e -> {
            if (secondsRemaining > 0) {
                secondsRemaining--;
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

    public void reset(int totalSeconds){
        swingTimer.stop();
        this.secondsRemaining = totalSeconds;
        running = false;
    }

    public int getSecondsRemaining() {
        return secondsRemaining;
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

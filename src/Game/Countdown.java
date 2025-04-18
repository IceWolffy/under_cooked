package Game;

import javax.swing.*;
import java.awt.*;

public class Countdown {
    private int secondsRemaining;
    private Timer swingTimer;
    private boolean running = false;

    public Countdown(int totalSeconds) {
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

    // Draw background box
    g.setColor(new Color(0, 0, 0, 150)); // Semi-transparent black
    g.fillRoundRect(1500, 100, 200, 70, 25, 25);

    // Draw border
    g.setColor(Color.WHITE);
    g.drawRoundRect(1500, 100, 200, 70, 25, 25);

    // Draw timer text
    g.setFont(new Font("Arial", Font.BOLD, 30));
    
    if (secondsRemaining <= 10) {
        g.setColor(Color.RED);
    } else {
        g.setColor(Color.WHITE);
    }
    
    g.drawString("Time Left: " + secondsRemaining+" sec", 1550, 140); // Adjusted position for better centering
    }


}

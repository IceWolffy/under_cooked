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

    public void draw(Graphics g, int x, int y){
        Graphics2D g2 = (Graphics2D) g;

    // Draw background box
    g2.setColor(new Color(0, 0, 0, 150)); // Semi-transparent black
    g2.fillRoundRect(x, y, 200, 70, 25, 25);

    // Draw border
    g2.setColor(Color.WHITE);
    g2.setStroke(new BasicStroke(3));
    g2.drawRoundRect(x, y, 200, 70, 25, 25);

    // Draw timer text
    g2.setFont(new Font("Arial", Font.BOLD, 30));
    
    if (secondsRemaining <= 10) {
        g2.setColor(Color.RED);
    } else {
        g2.setColor(Color.WHITE);
    }
    
    g2.drawString("Time Left: " + secondsRemaining, x + 15, y + 45);
    }


}

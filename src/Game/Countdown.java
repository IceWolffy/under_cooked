package Game;

import javax.swing.*;
import java.awt.*;

public class Countdown {
    private int secondsRemaining;
    private int totalSeconds;
    private Timer swingTimer;
    private boolean running = false;

    public Countdown(int totalSeconds) {

        this.secondsRemaining = totalSeconds;

        swingTimer = new Timer(1000, e -> {
            secondsRemaining--;
            if (secondsRemaining <= 0) {
                secondsRemaining = 0; // safety
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

    int boxWidth = 200;
    int boxHeight = 60;
    int margin = 10;

    int x = GameManager.GAME_WIDTH - boxWidth - margin;
    int y = GameManager.GAME_HEIGHT - boxHeight - margin;

    // Draw background box
    g.setColor(new Color(0, 0, 0, 150)); // Semi-transparent black
    g.fillRoundRect(x, y, boxWidth, boxHeight, 25, 25);

    // Draw border
    g.setColor(Color.WHITE);
    g.drawRoundRect(x, y, boxWidth, boxHeight, 25, 25);

    // Draw timer text
    g.setFont(new Font("Arial", Font.BOLD, 30));
    
    if (secondsRemaining < 30) {
        g.setColor(Color.RED);
    } else {
        g.setColor(Color.WHITE);
    }
    
    g.drawString("Time Left: " + secondsRemaining, x+8, y+40); // Adjusted position for better centering


}

//used for winpanel logic
public boolean isFinished() {
    return secondsRemaining <= 0;
}


}

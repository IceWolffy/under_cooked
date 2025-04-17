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
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Arial", Font.BOLD, 32));
        String time = String.format("%02d:%02d", secondsRemaining / 60, secondsRemaining % 60);
        g.drawString(time, x, y);
    }


}

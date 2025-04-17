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

}

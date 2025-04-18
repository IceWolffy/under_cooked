package Game;

import javax.swing.*;
import java.awt.*;

public class CountUp {
    private int secondsElapsed;
    private int targetSeconds;
    private Timer swingTimer;
    private boolean running = false;
    private String grade;

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
    
    if (secondsElapsed < 30) {
        grade = "S";
        g.setColor(Color.RED);
    } else if (secondsElapsed < 60) {
        grade = "A";
        g.setColor(Color.ORANGE);
    } else if (secondsElapsed < 90) {
        grade = "B";
        g.setColor(Color.YELLOW);
    } else if (secondsElapsed < 120) {
        grade = "C";
        g.setColor(Color.GREEN);
    } else {
        grade = "D";
        g.setColor(Color.WHITE);
    }

    // Draw the grade
    g.drawString("Grade: " + grade, x + 25, y + 30); 
    g.drawString("Time Elapsed: " + secondsElapsed + "s", x + 25, y + 50);


}
}

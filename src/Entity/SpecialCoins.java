package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class SpecialCoins {

    public enum Type { SPEED_UP, SLOW_DOWN }

    private int x, y;
    private boolean collected;
    private static final int SIZE = 32;

    private BufferedImage questionImage;

    public SpecialCoins(int x, int y) {
        this.x = x;
        this.y = y;
        this.collected = false;

        try {
            questionImage = ImageIO.read(getClass().getResourceAsStream("/specialcoins/speed_or_slow.png"));
        } catch (Exception e) {
            System.err.println("Failed to load question mark coin image.");
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        if (!collected && questionImage != null) {
            g.drawImage(questionImage, x, y, SIZE, SIZE, null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    public boolean isCollected() {
        return collected;
    }

    public void collect() {
        this.collected = true;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}

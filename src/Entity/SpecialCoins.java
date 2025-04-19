package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class SpecialCoins {
    public enum Type { SPEED_UP, SLOW_DOWN }

    private int x, y;
    private Type type;
    private boolean collected;
    private BufferedImage image;
    private static final int SIZE = 32;

    public SpecialCoins(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.collected = false;

        try {
            if (type == Type.SPEED_UP) {
                image = ImageIO.read(getClass().getResourceAsStream("/specialcoins/speed_coin.png"));
            } else {
                image = ImageIO.read(getClass().getResourceAsStream("/specialcoins/slow_coin.png"));
            }
        } catch (Exception e) {
            System.err.println("Failed to load special ingredient image.");
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        if (!collected && image != null) {
            g.drawImage(image, x, y, SIZE, SIZE, null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    public Type getType() {
        return type;
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

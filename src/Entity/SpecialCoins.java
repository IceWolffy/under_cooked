package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpecialCoins {
    public int x, y;
    public boolean collected = false;

    private static final int FRAME_WIDTH = 32;
    private static final int FRAME_HEIGHT = 32;
    private static final int FRAME_COUNT = 8;
    private static final int FRAME_DELAY = 5;

    private BufferedImage spriteSheet;
    private int currentFrame = 0;
    private int tickCount = 0;

    public SpecialCoins(int x, int y) {
        this.x = x;
        this.y = y;

        try {
            spriteSheet = ImageIO.read(getClass().getResourceAsStream("/SpecialCoins/Bush.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error loading special coin sprite sheet: " + e.getMessage());
        }
    }

    // Returns true 50% of the time for speed boost, false for slow down
    public boolean getSpeedBoost() {
        return Math.random() < 0.5;
    }



    public void draw(Graphics g) {
        if (!collected && spriteSheet != null) {
            int col = currentFrame % 4;
            int row = currentFrame / 4;
            g.drawImage(spriteSheet, x, y, x + FRAME_WIDTH, y + FRAME_HEIGHT,
                        col * FRAME_WIDTH, row * FRAME_HEIGHT,
                        col * FRAME_WIDTH + FRAME_WIDTH, row * FRAME_HEIGHT + FRAME_HEIGHT, null);

            tickCount++;
            if (tickCount >= FRAME_DELAY) {
                currentFrame = (currentFrame + 1) % FRAME_COUNT;
                tickCount = 0;
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, FRAME_WIDTH, FRAME_HEIGHT);
    }
}

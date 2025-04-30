package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class SpecialCoins {
    public int x, y;
    public boolean collected = false;
    private static final int SIZE = 40;

    private BufferedImage image;
    private static final Random rand = new Random();

    public SpecialCoins(int x, int y) {
        this.x = x;
        this.y = y;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/SpecialCoins/specialcoin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    public void draw(Graphics g) {
        if (!collected && image != null) {
            g.drawImage(image, x, y, SIZE, SIZE, null);
        }
    }

    // Random effect: true = speed up, false = slow down
    public static boolean randomEffect() {
        return rand.nextBoolean();
    }
}

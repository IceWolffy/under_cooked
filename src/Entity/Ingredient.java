package Entity;

//import java.awt.image.BufferedImage;
import java.awt.Color; 
import java.awt.*;

public class Ingredient {
    public int x, y;
    //public BufferedImage image;
    public boolean collected = false;
    public static final int SIZE = 20;

    public Ingredient(int x, int y) {
        this.x = x;
        this.y = y;
        //this.image = image;
    }

    public void draw(Graphics g) {
        if (!collected) {
            //g.drawImage(image, x, y, 64, 64, null);
            g.setColor(Color.GREEN);
            g.fillOval(x, y, SIZE, SIZE);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }
}
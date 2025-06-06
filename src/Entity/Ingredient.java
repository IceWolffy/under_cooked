package Entity;

import java.awt.image.BufferedImage;
//import java.awt.Color;  for circle spawning 
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class Ingredient {
    public int x, y;
    //public BufferedImage image;
    public boolean collected = false;
    public static final int SIZE = 70; //20 incase of circles

    private BufferedImage image;

    //when adding more png, the correspondent name should be added too
    private static final String[] imageNames = {
        "carrot.png",
        "chamomile.png",
        "onion.png",
        "oyster.png",
        "potato.png",
        "radish.png",
        "rose.png",
        "spinach.png",
        "sunflower.png",
        "turnip.png"

    };

    public Ingredient(int x, int y) {
        this.x = x;
        this.y = y;
        
        // Randomly load one of the vegetable images
        String chosen = imageNames[new Random().nextInt(imageNames.length)];
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ingredients/" + chosen));
        } catch (IOException | NullPointerException e) {
            System.out.println("Could not load: " + chosen);
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        if (!collected) {
    
            //g.setColor(Color.GREEN);   'for circle'
            //g.fillOval(x, y, SIZE, SIZE);

            //draw ingredient
            if (!collected && image != null) {
                //only drawing first frame
                int frameWidth = 32;  // adjust if needed
                int frameHeight = 32;
                g.drawImage(image,
                    x, y, x + SIZE, y + SIZE,  // where to draw on screen
                    0, 0, frameWidth, frameHeight, // what part of the image to draw
                    null
                );
            }

        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }
}
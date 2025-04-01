package Entity;

import java.awt.*;
import Game.KeyHandler;

public class Player extends Entity {
    private KeyHandler keyH; // Store key handler

    public Player(KeyHandler keyH) {
        this.keyH = keyH; // Assign key handler
        this.x = 100;  // Starting position
        this.y = 700;  // Start above the ground
        this.speed = 4; // Movement speed
    }

    public void update() {
    	//OG you can implement the movements here, if any changes are needed tell me.
    	//Also the player needs to be then imported into the level1 which I can do after you finish the movement sutff
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);  // Set player color
        g.fillRect(x, y, 32, 32); // Draw player as a blue square
    }
}

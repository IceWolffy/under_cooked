package Entity;

import java.awt.*;
import Game.KeyHandler;
import Constants.Constants;

public class Player extends Entity {
    private KeyHandler keyH; // Store key handler
    private Constants constants; // Create an instance of Constants class
    

    public Player(KeyHandler keyH) {
        this.keyH = keyH; // Assign key handler
        this.x = 100;  // Starting position
        this.y = 700;  // Start above the ground
        this.speed = 4; // Movement speed
    }

    public void update() {
    	//OG you can implement the movements here, if any changes are needed tell me.
    	//Also the player needs to be then imported into the level1 which I can do after you finish the movement sutff
    	
    	// Handle horizontal movement
        if (keyH.leftPressed) {
            x -= speed;
        }
        if (keyH.rightPressed) {
            x += speed;
        }
        
        // Handle jumping
        if (keyH.jumpPressed && !constants.isJumping) {
            constants.isJumping = true;
            constants.velocityY = constants.jumpForce;
        }
        
        // Apply gravity
        y += constants.velocityY;
        
        if (constants.isJumping) {
            constants.velocityY += constants.gravity;
        }    
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);  // Set player color
        g.fillRect(x, y, 32, 32); // Draw player as a blue square
    }
}

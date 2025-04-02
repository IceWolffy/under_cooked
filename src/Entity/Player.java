package Entity;

import java.awt.*;
import Game.KeyHandler;

public class Player extends Entity {
    private KeyHandler keyH; // Store key handler
    
    // Physics variables
    private boolean isJumping = false;
    private int velocityY = 0;
    private final int gravity = 1;
    private final int jumpForce = -15;
    private final int groundLevel = 800 - 32; // Bottom of screen - player height

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
        if (keyH.jumpPressed && !isJumping) {
            isJumping = true;
            velocityY = jumpForce;
        }
        
        // Apply gravity
        y += velocityY;
        
        if (isJumping) {
            velocityY += gravity;
        }    
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);  // Set player color
        g.fillRect(x, y, 32, 32); // Draw player as a blue square
    }
}

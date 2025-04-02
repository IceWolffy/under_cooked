package Entity;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

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
        
        getPlayer1Image();
    }
    
    public void getPlayer1Image() {
    	try {
    		walk01 = ImageIO.read(getClass().getResourceAsStream("/player1/walk01.png"));
    		walk02 = ImageIO.read(getClass().getResourceAsStream("/player1/walk02.png"));
    		walk03 = ImageIO.read(getClass().getResourceAsStream("/player1/walk03.png"));
    		walk04 = ImageIO.read(getClass().getResourceAsStream("/player1/walk04.png"));
    		walk05 = ImageIO.read(getClass().getResourceAsStream("/player1/walk05.png"));
    		walk06 = ImageIO.read(getClass().getResourceAsStream("/player1/walk06.png"));
    		walk07 = ImageIO.read(getClass().getResourceAsStream("/player1/walk07.png"));
    		walk08 = ImageIO.read(getClass().getResourceAsStream("/player1/walk08.png"));
    		
    		
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    }

    public void update() {
    	
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
        //Collision for ground level
        if (y >= groundLevel) {  
            y = groundLevel; // Stop falling at the ground level
            isJumping = false; // Reset jump state
            velocityY = 0; // Stop downward velocity
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);  // Set player color
        g.fillRect(x, y, 32, 32); // Draw player as a blue square
    }
}

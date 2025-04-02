package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.KeyHandler;
import Constants.Constants;

public class Player extends Entity {
    private KeyHandler keyH; // Store key handler
    private Constants constants; // Create an instance of Constants class
    

    public Player(KeyHandler keyH) {
        this.keyH = keyH; // Assign key handler
        this.x = 100;  // Starting position
        this.y = 700;  // Start above the ground
        this.speed = 6; // Movement speed
        this.direction = "right";
        
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
        	direction = "left";
            x -= speed;
        }
        if (keyH.rightPressed) {
        	direction = "right";
            x += speed;
        }
        
        // Handle jumping
        if (keyH.jumpPressed && !constants.isJumping) {
            constants.isJumping = true;
            constants.velocityY = constants.jumpForce;
        }
        
        // Apply gravity
        y += constants.velocityY;
        

    }

    public void draw(Graphics g) {
    	BufferedImage image = null;
        switch(direction) {
        case "right":
        	image = walk01;
        	break;
        case "left":
        	image = walk01;
        	break;
        }
        g.drawImage(image, x, y, 256, 256, null);
    }
}

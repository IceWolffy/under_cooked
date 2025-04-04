package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Constants.Constants;
import Game.KeyHandler;

public class Player2 extends Entity {

    private KeyHandler keyH2; // Store key handler
    private boolean isJumping = false;
    private int velocityY = 0;
    private final int gravity = Constants.gravity;
    private final int jumpForce = Constants.jumpForce;
    private final int groundLevel = Constants.groundLevel;

    public Player2(KeyHandler keyH2) {
        this.keyH2 = keyH2; // Assign key handler
        this.x = 200; // Starting position for Player 2
        this.y = 700; // Start above the ground
        this.speed = 5;
        this.direction = "right";
        
        getPlayer2Image();
    }
    //Getters and setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public int getGroundLevel() {
        return groundLevel;
    }


    public void getPlayer2Image() {
        try {
            walk01 = ImageIO.read(getClass().getResourceAsStream("/player1/walk01.png"));
            walk02 = ImageIO.read(getClass().getResourceAsStream("/player1/walk02.png"));
            walk03 = ImageIO.read(getClass().getResourceAsStream("/player1/walk03.png"));
            walk04 = ImageIO.read(getClass().getResourceAsStream("/player1/walk04.png"));
            walk05 = ImageIO.read(getClass().getResourceAsStream("/player1/walk05.png"));
            walk06 = ImageIO.read(getClass().getResourceAsStream("/player1/walk06.png"));
            walk07 = ImageIO.read(getClass().getResourceAsStream("/player1/walk07.png"));
            walk08 = ImageIO.read(getClass().getResourceAsStream("/player1/walk08.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void update() {                        //old update fonction with no collision
        // Handle horizontal movement
        if (keyH2.leftPressed) {
            direction = "left";
            x -= speed;
        }

        if (keyH2.rightPressed) {
            direction = "right";
            x += speed;
        }

        // Handle jumping
        if (keyH2.jumpPressed && !isJumping) {
            isJumping = true;
            velocityY = jumpForce;
        }

        // Apply gravity
        y += velocityY;
        if (isJumping) {
            velocityY += gravity;
        }

        // Collision for ground level
        if (y >= groundLevel) {
            y = groundLevel; // Stop falling at the ground level
            isJumping = false; // Reset jump state
            velocityY = 0; // Stop downward velocity
        }
    } */
    public void update() {
        // horizontal movement
        if (keyH2.leftPressed) {
            direction = "left";
            x -= speed;
        }
    
        if (keyH2.rightPressed) {
            direction = "right";
            x += speed;
        }
    
        // jumping
        if (keyH2.jumpPressed && !isJumping) {
            isJumping = true;
            velocityY = jumpForce;
        }
    
        // gravity
        y += velocityY;
        if (isJumping) {
            velocityY += gravity;
        }
    
        // Collision for ground level
        if (y >= groundLevel) {
            y = groundLevel;
            isJumping = false;
            velocityY = 0;
        }
    
        // Platform collision check
        Rectangle platform1 = new Rectangle(470, 800, 1000, 25);
        Rectangle platform2 = new Rectangle(0, 600, 450, 25);
        Rectangle platform3 = new Rectangle(1500, 600, 450, 25);
    
        // Player2's feet
        Rectangle feet = new Rectangle(x + 50, y + 256, 156, 5);
    
        if (feet.intersects(platform1) || feet.intersects(platform2) || feet.intersects(platform3)) {
            isJumping = false;
            velocityY = 0;
    
            if (feet.intersects(platform1)) y = platform1.y - 256;
            if (feet.intersects(platform2)) y = platform2.y - 256;
            if (feet.intersects(platform3)) y = platform3.y - 256;
        }
    }
    

    public void draw(Graphics g) {
        BufferedImage image = null;

        switch (direction) {
            case "right":
                image = walk01; // Use appropriate sprite for walking right
                break;
            case "left":
                image = walk01; // Use appropriate sprite for walking left
                break;
        }

        g.drawImage(image, x, y, 256, 256, null);
    }
}

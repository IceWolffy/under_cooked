package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Constants.Constants;
import Game.KeyHandler;

public class Player extends Entity {


	private KeyHandler keyH; // Store key handler
	private boolean isJumping = false;
	private int velocityY = 0;
	private final int gravity = Constants.gravity;
	private final int jumpForce = Constants.jumpForce;
	private final int groundLevel = Constants.groundLevel;
	private BufferedImage image;
	private BufferedImage[] idleAnimation;
	private int aniTick, aniIndex, aniSpeed = 30;

	// Differentiates between Player 1 and Player 2
    private int playerId;

    public Player(KeyHandler keyH, int playerId) {
        this.keyH = keyH; // Assign key handler
        this.playerId = playerId; // Assign player ID

        // Set starting position based on player ID
        if (playerId == 1) {
            this.x = 300; // Starting position for Player 1
        } else {
            this.x = 200; // Starting position for Player 2
        }
        this.y = 700; // Start above the ground
        this.speed = 5;
        this.direction = "right";

        loadPlayerImages();
        idleAnimation();
    }

	// Getters and Setters
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

	public void loadPlayerImages() {
        try {
            walk = ImageIO.read(getClass().getResourceAsStream("/player1/walk.png"));
            idle = ImageIO.read(getClass().getResourceAsStream("/player1/idle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	private void idleAnimation() {
		idleAnimation = new BufferedImage[3];
		for (int i = 0; i < idleAnimation.length; i++) {
			idleAnimation[i] = idle.getSubimage(i * 80, 0, 80, 80);
		}
	}

	/*
	 * public void update() { //old update fonction
	 * 
	 * // Handle horizontal movement if (keyH.leftPressed) { direction = "left"; x
	 * -= speed; } if (keyH.rightPressed) { direction = "right"; x += speed; }
	 * 
	 * // Handle jumping if (keyH.jumpPressed && !isJumping) { isJumping = true;
	 * velocityY = jumpForce; }
	 * 
	 * // Apply gravity y += velocityY; if (isJumping) { velocityY += gravity; }
	 * 
	 * //Collision for ground level if (y >= groundLevel) { y = groundLevel; // Stop
	 * falling at the ground level isJumping = false; // Reset jump state velocityY
	 * = 0; // Stop downward velocity } }
	 */
	public void update() {

		// horizontal movement
		if (keyH.leftPressed) {
			direction = "left";
			x -= speed;
		}
		if (keyH.rightPressed) {
			direction = "right";
			x += speed;
		}

		// jumping
		if (keyH.jumpPressed && !isJumping) {
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

		 checkPlatformCollision();

	}
	
	private void checkPlatformCollision() {
        Rectangle platform1 = new Rectangle(470, 800, 1000, 25);
        Rectangle platform2 = new Rectangle(0, 600, 450, 25);
        Rectangle platform3 = new Rectangle(1500, 600, 450, 25);

        Rectangle feet = new Rectangle(x + 50, y + 256, 156, 5);

        if (feet.intersects(platform1) || feet.intersects(platform2) || feet.intersects(platform3)) {
            isJumping = false;
            velocityY = 0;

            if (feet.intersects(platform1)) 
            	y = platform1.y - 256;
            if (feet.intersects(platform2)) 
            	y = platform2.y - 256;
            if (feet.intersects(platform3)) 
            	y = platform3.y - 256;
        }
    }

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= idleAnimation.length) {
				aniIndex = 0;
			}
		}

	}

	// Draws the player img
	public void draw(Graphics g) {
		switch (direction) {
		case "right":
			image = walk;
			break;
		case "left":
			image = walk;
			break;
		}
		updateAnimationTick();

		if (keyH.leftPressed || keyH.rightPressed) {
			// Optional: Draw walk frame instead
			g.drawImage(walk.getSubimage(0, 0, 80, 80), x, y, 256, 256, null); // Temporary static walk frame
		} else {
			// Idle animation
			g.drawImage(idleAnimation[aniIndex], x, y, 256, 256, null);
		}

	}
}

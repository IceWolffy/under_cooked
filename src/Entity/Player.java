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
	private BufferedImage[] walkAnimation;
	private int walkTick, walkIndex, walkSpeed = 5;
	private int idleTick, idleIndex, idleSpeed = 30;

	// Differentiates between Player 1 and Player 2
	private int playerId;

	public Player(KeyHandler keyH, int playerId) {
		super(0, 0, 33 , 50);
		
		this.keyH = keyH; // Assign key handler
		this.playerId = playerId; // Assign player ID

		// Set starting position based on player ID
		if (playerId == 1) {
			this.startX = 300; // Starting position for Player 1
		} else {
			this.startX = 200; // Starting position for Player 2
		}
		this.startY = 700; // Start above the ground
		this.speed = 5;
		this.direction = "right";
		this.x = this.startX;
		this.y = this.startY;

		loadPlayerImages();
		idleAnimation();
		walkAnimation();
	}
	

	// Getters and Setters
	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int newY) {
		this.startY = newY;
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

	private void walkAnimation() {
		walkAnimation = new BufferedImage[8];
		for (int i = 0; i < walkAnimation.length; i++) {
			walkAnimation[i] = walk.getSubimage(i * 80, 0, 80, 80);
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

		updateHitbox();

	}
	@Override
	public void updateHitbox() {
	    // Update the hitbox to follow the player's position and match the sprite size
	    hitbox.setBounds(x + 110, y + 102, 30, 30);  
	}



	private void updateAnimationTick() {
		if (keyH.leftPressed || keyH.rightPressed) {
			// Walk animation logic
			walkTick++;
			if (walkTick >= walkSpeed) {
				walkTick = 0;
				walkIndex++;
				if (walkIndex >= walkAnimation.length) {
					walkIndex = 0;
				}
			}
		} else {
			// Idle animation logic
			idleTick++;
			if (idleTick >= idleSpeed) {
				idleTick = 0;
				idleIndex++;
				if (idleIndex >= idleAnimation.length) {
					idleIndex = 0;
				}
			}
		}
	}

	// Draws the player img
	public void draw(Graphics g) {
		
		drawHitbox(g);
		updateAnimationTick();

		if (keyH.leftPressed || keyH.rightPressed) {

			g.drawImage(walkAnimation[walkIndex], x, y, 256, 256, null);

		} else {
			// Idle animation
			g.drawImage(idleAnimation[idleIndex], x, y, 256, 256, null);
		}

	}
}

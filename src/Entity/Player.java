package Entity;

import java.awt.*;
import utils.HelpMethods;
import utils.LoadSave;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Constants.Constants;
import Game.GameManager;
import Game.KeyHandler;
import utils.HelpMethods;

public class Player extends Entity {

	private int[][] lvlData;
	private KeyHandler keyH; // Store key handler
	private boolean isJumping = false;
	private int velocityY = 0;
	private final int gravity = Constants.gravity;
	private final int jumpForce = Constants.jumpForce;
	private BufferedImage image;
	private BufferedImage[] idleAnimation;
	private BufferedImage[] walkAnimation;
	private int walkTick, walkIndex, walkSpeed = 5;
	private int idleTick, idleIndex, idleSpeed = 30;

	// Differentiates between Player 1 and Player 2
	private int playerId;

	public Player(KeyHandler keyH, int playerId, int spawnX, int spawnY, int[][] lvlData) {
		super(spawnX, spawnY, 33, 50);

		this.keyH = keyH;
		this.playerId = playerId;
		this.startX = spawnX;
		this.startY = spawnY;
		this.x = spawnX;
		this.y = spawnY;
		this.lvlData = lvlData;
		
		// Sets the horizontal movement speed
		this.speed = 5;

		// Load images, animations etc.
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

	// horizontal movement

	public void update() {
		// Movement speeds
		float xSpeed = 0;

		if (keyH.leftPressed) {
			direction = "left";
			xSpeed -= speed;
		}
		if (keyH.rightPressed) {
			direction = "right";
			xSpeed += speed;
		}

		// Jump
		if (keyH.jumpPressed && !isJumping) {
			isJumping = true;
			velocityY = jumpForce;
		}

		// Gravity
		velocityY += gravity;
		float ySpeed = velocityY;

		// Separate movement checks
		if (HelpMethods.canMoveHere((int) (x + xSpeed), y, width, height, lvlData)) {
			x += xSpeed;
		}
		if (HelpMethods.canMoveHere(x, (int) (y + ySpeed), width, height, lvlData)) {
			y += ySpeed;
		} else {
			if (velocityY > 0) {
				isJumping = false;
			}
			velocityY = 0;
		}

		updateHitbox();

	}

	@Override
	public void updateHitbox() {
		// Update the hitbox to follow the player's position and match the sprite size
		hitbox.setBounds(x, y, 33, 50);
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

			g.drawImage(walkAnimation[walkIndex], x - 110, y - 99, 256, 256, null);

		} else {
			// Idle animation
			g.drawImage(idleAnimation[idleIndex], x - 110, y - 99, 256, 256, null);
		}
		
		// Debug info (Just for testing, delete later)
	    g.setColor(Color.black);
	    g.drawString("X: " + x + ", Y: " + y, x, y - 10);
	    g.drawString("Can move left: " + HelpMethods.canMoveHere((int)(x - speed), y, width, height, lvlData), x, y - 25);
	    g.drawString("Can move right: " + HelpMethods.canMoveHere((int)(x + speed), y, width, height, lvlData), x, y - 40);

	}

}

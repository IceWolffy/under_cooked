package Entity;

import java.awt.*;
import utils.HelpMethods;
import utils.LevelData;
import utils.LoadSave;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Constants.Constants;
import Game.GameManager;
import Game.KeyHandler;
import Game.SoundEffects;

public class Player extends Entity {

	private LevelData lvlData;
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
	
	private String direction = "right";  // Default direction is right


	// Differentiates between Player 1 and Player 2
	private int playerId;
	
	// Add inventory for the player
	private Inventory inventory;
	private long lastWalkSoundTime = 0;
	private final long walkCooldown = 300;


	public Player(KeyHandler keyH, int playerId, int spawnX, int spawnY, LevelData levelData) {

	//cooldown field for player movment sound effect
	 // ms between sound plays
		super(spawnX, spawnY, 33, 50);

		this.keyH = keyH;
		this.playerId = playerId;
		this.startX = spawnX;
		this.startY = spawnY;
		this.x = spawnX;
		this.y = spawnY;
		this.lvlData = levelData;
		
		// Initialize the inventory object
		this.inventory = new Inventory(playerId);
		
		// Sets the horizontal movement speed
		this.speed = 8; // Testing inventory, the original was 5

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
	public void setStartX(int newX) {
		this.startX = newX;
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
	
	// Add getter for inventory
	public Inventory getInventory() {
		return inventory;
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

	public void update() {
		// Movement speeds
		float xSpeed = 0;

		//movment block
		/*if (keyH.leftPressed) {
			direction = "left";
			xSpeed -= speed;
		}
		if (keyH.rightPressed) {
			direction = "right";
			xSpeed += speed;
		}*/ // new movment block supports sound effect
		if (keyH.leftPressed || keyH.rightPressed) {
			direction = keyH.leftPressed ? "left" : "right";
			x += keyH.rightPressed ? speed : -speed;
		
			long now = System.currentTimeMillis();
			if (now - lastWalkSoundTime >= walkCooldown) {
				SoundEffects.play("/sounds/Playermovement.wav");  // Walk sound effect
				lastWalkSoundTime = now;
			}
		}
		

		// Jump
		if (keyH.jumpPressed && !isJumping) {
			SoundEffects.play("/sounds/PlayerJump.wav");  // Jump sound effect
			isJumping = true;
			velocityY = jumpForce;
		}

		// Gravity
		velocityY += gravity;
		float ySpeed = velocityY * 2; // Testing inventory, can remove the "* 2" later

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
		
		// Draw player's inventory at appropriate position
		int inventoryX = playerId == 1 ? 10 : GameManager.GAME_WIDTH - 160;
		inventory.draw(g, inventoryX, 10);
		
		// Debug info (Just for testing, delete later)
		g.setColor(Color.black);
		g.drawString("X: " + x + ", Y: " + y, x, y - 10);
		g.drawString("Inventory: " + inventory.getItemCount() + "/3", x, y - 25);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 80, 80);
	}
}
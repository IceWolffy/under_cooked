package Tests;

import Entity.Ingredient;
import Entity.Player;
import Game.KeyHandler;
import utils.LoadSave;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

class PlayerTest {
	private Player player; // Variables for testing
	private Player player2;
	private KeyHandler keyH;
	private KeyHandler keyH2;

	@BeforeEach
	void setUp() throws Exception { // Before each test set up a keyHandler and a new player
		keyH = new KeyHandler(true); // Player 1 uses WASD controls
		keyH2 = new KeyHandler(false); // Player 2 uses Arrow controls
		player = new Player(keyH, 1,LoadSave.player1X, LoadSave.player1Y, LoadSave.getLevelData());
		player2 = new Player(keyH2, 2,LoadSave.player2X, LoadSave.player2Y, LoadSave.getLevelData());
	}

	@Test
	void testPlayerMoveRight() { // Test that checks if the character can move right
		int initialX = player.x;
		keyH.rightPressed = true;
		player.update();
		assertTrue(player.x > initialX, "Player 1 should move right");
	}

	@Test
	void testPlayerMoveLeft() { // Test that checks if the character can move left
		int initialX = player.x;
		keyH.leftPressed = true;
		player.update();
		assertTrue(player.x < initialX, "Player 1 should move left");
	}

	@Test
	void testPlayerJump() { // Test that checks if the character can jump
		int initialY = player.getStartY();
		keyH.jumpPressed = true;
		player.update();
		assertTrue(player.isJumping(), "Player should be in jumping state");
		assertTrue(player.getVelocityY() < 0, "Player 1 should have negative velocity when jumping");
	}

	@Test
	void testPlayer2MoveRight() { // Test that checks if the character can move right
		int initialX = player2.x;
		keyH2.rightPressed = true;
		player2.update();
		assertTrue(player2.x > initialX, "Player 2 should move right");
	}

	@Test
	void testPlayer2MoveLeft() { // Test that checks if the character can move left
		int initialX = player2.x;
		keyH2.leftPressed = true;
		player2.update();
		assertTrue(player2.x < initialX, "Player 2 should move left");
	}

	@Test
	void testPlayer2Jump() { // Test that checks if the character can jump
		int initialY = player2.getStartY();
		keyH2.jumpPressed = true;
		player2.update();
		assertTrue(player2.isJumping(), "Player 2 should be in jumping state");
		assertTrue(player2.getVelocityY() < 0, "Player 2 should have negative velocity when jumping");
	}

	// Test starting positions
	@Test
	void testPlayerStartingPositions() {
		assertEquals(256, player.getStartX(), "Player 1 should start at x=256");
		assertEquals(128, player2.getStartX(), "Player 2 should start at x=128");
		assertEquals(704, player.getStartY(), "Both players should start at y=704");
		assertEquals(704, player2.getStartY(), "Both players should start at y=704");
	}

	
	//Tests for Images/Sprites and Image loading
	@Test
	void testIdleAnimationSprite() {
		InputStream stream = getClass().getResourceAsStream("/player1/idle.png");
		assertNotNull(stream, "idle.png resource stream should not be null");
		try {
			BufferedImage img = ImageIO.read(stream);
			assertNotNull(img, "BufferedImage should not be null");
		} catch (IOException e) {
			fail("IOException while reading walk.png: " + e.getMessage());
		}
	}
	@Test
	void testWalkAnimationSprite() {
		InputStream stream = getClass().getResourceAsStream("/player1/walk.png");
		assertNotNull(stream, "walk.png resource stream should not be null");
		try {
			BufferedImage img = ImageIO.read(stream);
			assertNotNull(img, "BufferedImage should not be null");
		} catch (IOException e) {
			fail("IOException while reading walk.png: " + e.getMessage());
		}
	}
	
	@Test
	void testInventoryInitialization() {
	    // Test that the inventory is properly initialized and empty at the start
	    assertEquals(0, player.getInventory().getItemCount(), "Player inventory should start empty");
	    assertEquals(0, player.getInventory().getScore(), "Player score should start at 0");
	    assertEquals(0, player2.getInventory().getItemCount(), "Player 2 inventory should start empty");
	    assertEquals(0, player2.getInventory().getScore(), "Player 2 score should start at 0");
	}

	@Test
	void testInventoryAddIngredient() {
	    // Create a test ingredient
	    Ingredient testIngredient = new Ingredient(100, 100);
	    
	    // Add the ingredient to the inventory and check if it was added successfully
	    assertTrue(player.getInventory().addIngredient(testIngredient), "Ingredient should be added successfully");
	    assertEquals(1, player.getInventory().getItemCount(), "Inventory should contain one item");
	    
	    // Add ingredients until full and verify capacity is respected
	    Ingredient ingredient2 = new Ingredient(120, 120);
	    Ingredient ingredient3 = new Ingredient(140, 140);
	    Ingredient ingredient4 = new Ingredient(160, 160);
	    
	    player.getInventory().addIngredient(ingredient2);
	    player.getInventory().addIngredient(ingredient3);
	    assertEquals(3, player.getInventory().getItemCount(), "Inventory should contain three items");
	    
	    // Try to add beyond capacity
	    assertFalse(player.getInventory().addIngredient(ingredient4), "Inventory should reject items beyond capacity");
	    assertEquals(3, player.getInventory().getItemCount(), "Inventory should still contain three items");
	    assertTrue(player.getInventory().isFull(), "Inventory should be reported as full");
	}

	@Test
	void testInventoryClear() {
	    // Add some ingredients to the inventory
	    player.getInventory().addIngredient(new Ingredient(100, 100));
	    player.getInventory().addIngredient(new Ingredient(120, 120));
	    assertEquals(2, player.getInventory().getItemCount(), "Inventory should have two items");
	    
	    // Clear the inventory and verify it's empty
	    player.getInventory().clearInventory();
	    assertEquals(0, player.getInventory().getItemCount(), "Inventory should be empty after clearing");
	    assertFalse(player.getInventory().isFull(), "Inventory should not be full after clearing");
	}

	@Test
	void testPlayersHaveSeparateInventories() {
	    // Fill player 1's inventory
	    for (int i = 0; i < 3; i++) {
	        player.getInventory().addIngredient(new Ingredient(100 + i*20, 100));
	    }
	    assertTrue(player.getInventory().isFull(), "Player 1's inventory should be full");
	    
	    // Player 2's inventory should still be empty
	    assertEquals(0, player2.getInventory().getItemCount(), "Player 2's inventory should be empty");
	    assertFalse(player2.getInventory().isFull(), "Player 2's inventory should not be full");
	    
	    // Add score only to player 2
	    player2.getInventory().addScore(50);
	    assertEquals(0, player.getInventory().getScore(), "Player 1's score should still be 0");
	    assertEquals(50, player2.getInventory().getScore(), "Player 2's score should be 50");
	}

	@Test
	void testInventoryCapacityLimit() {
	    // Test the capacity limit of 3 ingredients
	    for (int i = 0; i < 3; i++) {
	        assertTrue(player.getInventory().addIngredient(new Ingredient(100 + i*20, 100)), 
	                   "Should be able to add ingredient " + (i+1));
	    }
	    
	    // The 4th ingredient should be rejected
	    assertFalse(player.getInventory().addIngredient(new Ingredient(160, 100)), 
	                "Should not be able to add a 4th ingredient");
	    
	    // Clear one space and try again
	    player.getInventory().clearInventory();
	    assertTrue(player.getInventory().addIngredient(new Ingredient(100, 100)), 
	               "Should be able to add ingredient after clearing");
	}
	
	@Test
	void testInventoryScoreSystem() {
	    // Test that score is properly updated
	    assertEquals(0, player.getInventory().getScore(), "Score should start at 0");
	    
	    player.getInventory().addScore(10);
	    assertEquals(10, player.getInventory().getScore(), "Score should be 10 after adding 10 points");
	    
	    player.getInventory().addScore(15);
	    assertEquals(25, player.getInventory().getScore(), "Score should be 25 after adding another 15 points");
	}

}

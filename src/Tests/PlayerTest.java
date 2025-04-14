package Tests;

import Entity.Player;
import Game.KeyHandler;
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
		player = new Player(keyH, 1);
		player2 = new Player(keyH2, 2);
	}

	@Test
	void testPlayerMoveRight() { // Test that checks if the character can move right
		int initialX = player.getX();
		keyH.rightPressed = true;
		player.update();
		assertTrue(player.getX() > initialX, "Player 1 should move right");
	}

	@Test
	void testPlayerMoveLeft() { // Test that checks if the character can move left
		int initialX = player.getX();
		keyH.leftPressed = true;
		player.update();
		assertTrue(player.getX() < initialX, "Player 1 should move left");
	}

	@Test
	void testPlayerJump() { // Test that checks if the character can jump
		int initialY = player.getY();
		keyH.jumpPressed = true;
		player.update();
		assertTrue(player.isJumping(), "Player should be in jumping state");
		assertTrue(player.getVelocityY() < 0, "Player 1 should have negative velocity when jumping");
	}

	@Test
	void testPlayer2MoveRight() { // Test that checks if the character can move right
		int initialX = player2.getX();
		keyH2.rightPressed = true;
		player2.update();
		assertTrue(player2.getX() > initialX, "Player 2 should move right");
	}

	@Test
	void testPlayer2MoveLeft() { // Test that checks if the character can move left
		int initialX = player2.getX();
		keyH2.leftPressed = true;
		player2.update();
		assertTrue(player2.getX() < initialX, "Player 2 should move left");
	}

	@Test
	void testPlayer2Jump() { // Test that checks if the character can jump
		int initialY = player2.getY();
		keyH2.jumpPressed = true;
		player2.update();
		assertTrue(player2.isJumping(), "Player 2 should be in jumping state");
		assertTrue(player2.getVelocityY() < 0, "Player 2 should have negative velocity when jumping");
	}

	// Test starting positions
	@Test
	void testPlayerStartingPositions() {
		assertEquals(300, player.getX(), "Player 1 should start at x=300");
		assertEquals(200, player2.getX(), "Player 2 should start at x=200");
		assertEquals(700, player.getY(), "Both players should start at y=700");
		assertEquals(700, player2.getY(), "Both players should start at y=700");
	}

	@Test
	void testIdleAnimationSprite() {
		InputStream stream = getClass().getResourceAsStream("/player1/walk.png");
		assertNotNull(stream, "walk.png resource stream should not be null");
		try {
			BufferedImage img = ImageIO.read(stream);
			assertNotNull(img, "BufferedImage should not be null");
		} catch (IOException e) {
			fail("IOException while reading walk.png: " + e.getMessage());
		}
	}

}

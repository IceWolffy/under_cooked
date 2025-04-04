package Tests;

import Entity.Player;
import Game.KeyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
	private Player player; // Variables for testing
	private KeyHandler keyH;

	@BeforeEach
	void setUp() throws Exception { // Before each test set up a keyHandler and a new player
		keyH = new KeyHandler(true);
		player = new Player(keyH);
	}

	@Test
	void testMoveRight() {
		int initialX = player.getX();
		keyH.rightPressed = true;
		player.update();
		assertTrue(player.getX() > initialX, "Player should move right");
	}
	@Test
	void testMoveLeft() {
        int initialX = player.getX();
        keyH.leftPressed = true;
        player.update();
        assertTrue(player.getX() < initialX, "Player should move left");
    }
	@Test
	void testJump() {
        int initialY = player.getY();
        keyH.jumpPressed = true;
        player.update();
        assertTrue(player.isJumping(), "Player should be in jumping state");
        assertTrue(player.getVelocityY() < 0, "Player should have negative velocity when jumping");
    }

}

package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.LevelHandler;
import utils.LoadSave;

public class LevelTest {

    private LevelHandler handler;

    @BeforeEach
    void setUp() {
        handler = new LevelHandler();
    }

    @Test
    void testTerrainSpritesAreLoaded() {
        BufferedImage[] sprites = handler.getTerrainSprites();
        assertNotNull(sprites, "Terrain sprites should not be null");
        assertTrue(sprites.length > 0, "Terrain sprite array should have elements");

        for (BufferedImage sprite : sprites) {
            assertNotNull(sprite, "Each terrain sprite should not be null");
            assertEquals(32, sprite.getWidth(), "Sprite width should be 32");
            assertEquals(32, sprite.getHeight(), "Sprite height should be 32");
        }

    }
    @Test
    void testBackgroundSpritesAreLoaded() {
        BufferedImage[] sprites = handler.getBackgroundSprites();
        assertNotNull(sprites, "Background sprites should not be null");
        assertTrue(sprites.length > 0, "Background sprite array should have elements");

        for (BufferedImage sprite : sprites) {
            assertNotNull(sprite, "Each background sprite should not be null");
        }
    }
}

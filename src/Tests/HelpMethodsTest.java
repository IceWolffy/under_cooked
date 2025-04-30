package Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import utils.HelpMethods;
import utils.LevelData;
import Game.GameManager;

public class HelpMethodsTest {

    private LevelData levelData;

    @Before
    public void setUp() {
        // Create a large enough 2D tile array (27 rows high, 48 columns wide)
        levelData = new LevelData();
        levelData.terrain = new int[27][48];
        levelData.foreground = new int[27][48];

        // Set all tiles to walkable (0)
        for (int y = 0; y < 27; y++) {
            for (int x = 0; x < 48; x++) {
                levelData.terrain[y][x] = 0;
                levelData.foreground[y][x] = 0;
            }
        }

        // Add a solid tile at tile (5,5) => pixel position (160, 160)
        levelData.terrain[5][5] = 1;
    }

    @Test
    public void testCanMoveHere() {
        // Test moving to a walkable tile (should return true)
        assertTrue(HelpMethods.canMoveHere(160, 160, GameManager.TILES_SIZE, GameManager.TILES_SIZE, levelData, 0, 0));
        
        // Test moving to a solid tile (should return false)
        assertFalse(HelpMethods.canMoveHere(160, 160, GameManager.TILES_SIZE, GameManager.TILES_SIZE, levelData, 0, -GameManager.TILES_SIZE));
    }

    
    
}

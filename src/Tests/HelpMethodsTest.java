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
    public void testCanMoveHere_walkableTile_returnsTrue() {
        // Pixel (96,96) => tile (3,3), which is walkable
        boolean result = HelpMethods.canMoveHere(96, 96, 32, 32, levelData, 0, 0);
        assertTrue(result);
    }

    @Test
    public void testCanMoveHere_solidTile_returnsFalse() {
        // Pixel (160,160) => tile (5,5), which is solid
        boolean result = HelpMethods.canMoveHere(160, 160, 32, 32, levelData, 0, 0);
        assertFalse(result);
    }

    @Test
    public void testIsSolid_walkable_returnsFalse() {
        boolean result = HelpMethods.isSolid(96, 96, levelData); // tile (3,3)
        assertFalse(result);
    }

    @Test
    public void testIsSolid_solidTile_returnsTrue() {
        boolean result = HelpMethods.isSolid(160, 160, levelData); // tile (5,5)
        assertTrue(result);
    }

    
    
}

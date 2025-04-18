package utils;

import Game.GameManager;

public class HelpMethods {

    public static boolean canMoveHere(int x, int y, int width, int height, LevelData lvlData) {
        // Check if player can move here based on terrain
        if (!isSolid(x, y, lvlData)) {
            if (!isSolid(x + width, y + height, lvlData)) {
                if (!isSolid(x + width, y, lvlData)) {
                    if (!isSolid(x, y + height, lvlData)) {
                        return true; // All checks passed, player can move here
                    }
                }
            }
        }
        return false; // Player cannot move here if any of the checks failed
    }

    public static boolean isSolid(int x, int y, LevelData lvlData) {
        // Ensure the coordinates are within the level bounds
        if (x < 0 || x > GameManager.GAME_WIDTH) {
            return true; // Outside game area, considered solid
        }
        if (y < 0 || y > GameManager.GAME_HEIGHT) {
            return true; // Outside game area, considered solid
        }

        // Calculate the tile coordinates from the pixel positions
        int xIndex = x / GameManager.TILES_SIZE;
        int yIndex = y / GameManager.TILES_SIZE;

        // Check the terrain array of the level
        int value = lvlData.terrain[yIndex][xIndex]; // Correct access to terrain data
        
        // Determine if the tile is solid based on the terrain value
        if (value > 247 || value < 0 || value == 22) {
            return true; // If solid, return true
        }
        return false; // If not solid, return false
    }
}

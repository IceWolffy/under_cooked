package utils;

import Game.GameManager;

public class HelpMethods {

	public static boolean canMoveHere(int x, int y, int width, int height, LevelData lvlData, int velocityY) {
	    if (!isSolid(x, y, lvlData, velocityY)) {
	        if (!isSolid(x + width, y + height, lvlData, velocityY)) {
	            if (!isSolid(x + width, y, lvlData, velocityY)) {
	                if (!isSolid(x, y + height, lvlData, velocityY)) {
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	}

    public static boolean isSolid(int x, int y, LevelData lvlData, int velocityY) {
        if (x < 0 || x > GameManager.GAME_WIDTH) {
            return true;
        }
        if (y < 0 || y > GameManager.GAME_HEIGHT) {
            return true;
        }

        int xIndex = x / GameManager.TILES_SIZE;
        int yIndex = y / GameManager.TILES_SIZE;

        int terrainValue = lvlData.terrain[yIndex][xIndex];
        int foregroundValue = lvlData.foreground[yIndex][xIndex];

        if (foregroundValue == 13) {
            if (velocityY < 0) {
                // Moving up: don't block
                return false;
            } else {
                // Moving down or standing: block
                return true;
            }
        }

        // Normal terrain blocking
        if (terrainValue > 247 || terrainValue < 0 || terrainValue == 22) {
            return true;
        }

        return false;
    }

}

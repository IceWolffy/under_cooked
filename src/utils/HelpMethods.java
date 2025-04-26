package utils;

import Game.GameManager;

public class HelpMethods {

	public static boolean canMoveHere(int x, int y, int width, int height, LevelData lvlData, int xSpeed, int ySpeed) {
	    int futureX = x + xSpeed;
	    int futureY = y + ySpeed;

	    return !isSolidArea(futureX, futureY, width, height, lvlData);
	}


	public static boolean isSolid(int x, int y, LevelData lvlData) {
	    if (x < 0 || x >= GameManager.GAME_WIDTH) return true;
	    if (y < 0 || y >= GameManager.GAME_HEIGHT) return true;

	    int xIndex = x / GameManager.TILES_SIZE;
	    int yIndex = y / GameManager.TILES_SIZE;

	    int terrainValue = lvlData.terrain[yIndex][xIndex];
	    int foregroundValue = lvlData.foreground[yIndex][xIndex];


	    return terrainValue != 0;
	}

	private static boolean isSolidArea(int x, int y, int width, int height, LevelData lvlData) {
	    for (int i = 0; i <= width; i += GameManager.TILES_SIZE / 2) {
	        if (isSolid(x + i, y, lvlData) || isSolid(x + i, y + height, lvlData)) {
	            return true;
	        }
	    }

	    for (int i = 0; i <= height; i += GameManager.TILES_SIZE / 2) {
	        if (isSolid(x, y + i, lvlData) || isSolid(x + width, y + i, lvlData)) {
	            return true;
	        }
	    }

	    return false;
	}


}

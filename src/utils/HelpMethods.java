package utils;

import Game.GameManager;

public class HelpMethods {

	public static boolean canMoveHere(int x, int y, int width, int height, int[][] lvlData) {
		
		if(!isSolid(x,y,lvlData)) {
			if(!isSolid(x+width, y+height, lvlData))
				if(!isSolid(x+width,y,lvlData))
					if(!isSolid(x,y+height,lvlData))
						return true;
		}
		return false;
	}
	
	
	
	public static boolean isSolid(int x, int y, int[][] lvlData) {
		if(x< 0 || x > GameManager.GAME_WIDTH) {
			return true;
		}
		if(y < 0 || y > GameManager.GAME_HEIGHT) {
			return true;
		}
		
		int xIndex = x / GameManager.TILES_SIZE;
		int yIndex = y / GameManager.TILES_SIZE;
		
		int value = lvlData[yIndex][xIndex];
		
		if(value > 247 || value < 0 || value == 21) {
			return true;
		}
		return false;
	}

}

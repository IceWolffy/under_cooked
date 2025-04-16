package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Game.GameManager;

public class LoadSave {

	public static final String LEVEL_ATLAS = "terrainSprite.png";
	public static final String LEVEL_ONE_DATA = "levelOne.png";

	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		try {
			img = ImageIO.read(is);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				is.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return img;
	}
	public static int[][] getLevelData(){
		int[][] lvlData	= new int[GameManager.TILES_IN_HEIGHT][GameManager.TILES_IN_WIDTH];
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		
		for (int i = 0; i < img.getHeight(); i++){
			for (int j = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= 247) {
					value = 0;
					lvlData[i][j] = color.getRed();
				}
				
			}
		}
		return lvlData;
	}
}

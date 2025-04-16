package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Game.GameManager;

public class LoadSave {

	public static final String LEVEL_ATLAS = "/level/terrainSprite.png";
	public static final String LEVEL_ONE_DATA = "/level/levelOne.png";

	public static BufferedImage GetSpriteAtlas(String path) {
	    BufferedImage img = null;

	    try (InputStream is = LoadSave.class.getResourceAsStream(path)) {
	        if (is == null) {
	            System.err.println("Error: Resource not found at " + path);
	            return null;
	        }
	        img = ImageIO.read(is);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return img;
	}
	public static int[][] getLevelData(){
		int[][] lvlData	= new int[GameManager.TILES_IN_HEIGHT][GameManager.TILES_IN_WIDTH];
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		
		for (int j = 0; j < img.getHeight(); j++) {
		    for (int i = 0; i < img.getWidth(); i++) {
		        Color color = new Color(img.getRGB(i, j)); // correct order
		        int value = color.getRed();
		        if (value >= 247) {
		            value = 0;
		        }
		        lvlData[j][i] = value;
		    }
		}

		System.out.println("First few tile values:");
		for (int i = 0; i < 5; i++) {
		    System.out.print(lvlData[0][i] + " ");
		}
		System.out.println();
		return lvlData;
	}
}

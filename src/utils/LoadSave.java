package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Game.GameManager;

public class LoadSave {

	public static final String BACKGROUND_ATLAS = "/level/background.png";  // new background atlas
	public static final String LEVEL_ATLAS = "/level/terrain.png";  // c
	public static final String FOREGROUND_ATLAS = "/level/foreground.png";  // new foreground atlas
	public static final String LEVEL_ONE_DATA = "/level/levelOne.png";  // path to your level data file (e.g., image or tilemap)

	public static int player1X, player1Y;
	public static int player2X, player2Y;

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

	public static int[][] getLevelData() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		int[][] lvlData = new int[img.getHeight()][img.getWidth()];

		for (int j = 0; j < img.getHeight(); j++) {
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int red = color.getRed();
				int blue = color.getBlue();

				if (blue == 254) {
				    player1X = i * GameManager.TILES_SIZE;
				    player1Y = j * GameManager.TILES_SIZE;
				    System.out.println("Player 1 spawn found at: " + player1X + ", " + player1Y);
				    red = 0;
				} else if (blue == 253) {
				    player2X = i * GameManager.TILES_SIZE;
				    player2Y = j * GameManager.TILES_SIZE;
				    System.out.println("Player 2 spawn found at: " + player2X + ", " + player2Y);
				    red = 0;
				
				} else if (red >= 247) {
				    red = 0;
				}
				lvlData[j][i] = red;

			}
		}
		return lvlData;
	}
}

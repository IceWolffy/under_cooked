package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Game.GameManager;

public class LoadSave {

    public static final String BACKGROUND_ATLAS = "/level/background.png";
    public static final String LEVEL_ATLAS = "/level/terrain.png";
    public static final String FOREGROUND_ATLAS = "/level/foreground.png";
    
    public static final String LEVEL_ONE_DATA = "/level/levelOne.png";

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

    public static LevelData getLevelData() {
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        int height = img.getHeight();
        int width = img.getWidth();

        int[][] terrain = new int[height][width];
        int[][] background = new int[height][width];
        int[][] foreground = new int[height][width];

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Color color = new Color(img.getRGB(i, j));
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                
                int originalR = r;
                int originalG = g;
                int originalB = b;

                if (r == 247) {
                    player1X = i * GameManager.TILES_SIZE;
                    player1Y = j * GameManager.TILES_SIZE;
                    System.out.println("Player 1 spawn found at: " + player1X + ", " + player1Y);
                    r = g = 0;
                } else if (r == 246) {
                    player2X = i * GameManager.TILES_SIZE;
                    player2Y = j * GameManager.TILES_SIZE;
                    System.out.println("Player 2 spawn found at: " + player2X + ", " + player2Y);
                    r = g = 0;
                }

                if (r >= 248) r = 0;
                if (g >= 248) g = 0;
                if (b >= 248) b = 0;

                terrain[j][i] = r;
                foreground[j][i] = g;
                background[j][i] = b;
            }
        }

        LevelData lvlData = new LevelData();
        lvlData.terrain = terrain;
        lvlData.foreground = foreground;
        lvlData.background = background;

        return lvlData;
    }
}

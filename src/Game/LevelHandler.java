package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSave;

public class LevelHandler {
    private BufferedImage[] backgroundSprites;
    private BufferedImage[] terrainAndDecoSprites;
    private BufferedImage[] foregroundSprites;
    private Level levelOne;

    // Assuming the tile counts are known for each category
    private static final int BACKGROUND_TILE_COUNT = 100; // Example number, adjust based on your actual sprite sheet
    private static final int TERRAIN_TILE_COUNT = 200;   // Adjust based on your sprite sheet
    private static final int FOREGROUND_TILE_COUNT = 100; // Adjust based on your sprite sheet

    public LevelHandler() {
        levelOne = new Level(LoadSave.getLevelData());
        importBackgroundSprites();
        importTerrainAndDecoSprites();
        importForegroundSprites();
    }

    private void importBackgroundSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.BACKGROUND_ATLAS);
        backgroundSprites = new BufferedImage[BACKGROUND_TILE_COUNT];  // Adjust based on your background tile count
        int index = 0;

        for (int j = 0; j < img.getHeight() / 32; j++) {
            for (int i = 0; i < img.getWidth() / 32; i++) {
                backgroundSprites[index++] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    private void importTerrainAndDecoSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        terrainAndDecoSprites = new BufferedImage[TERRAIN_TILE_COUNT]; // combined tiles
        int index = 0;

        for (int j = 0; j < img.getHeight() / 32; j++) {
            for (int i = 0; i < img.getWidth() / 32; i++) {
                terrainAndDecoSprites[index++] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    private void importForegroundSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.FOREGROUND_ATLAS);
        foregroundSprites = new BufferedImage[FOREGROUND_TILE_COUNT];  // Adjust based on your foreground tile count
        int index = 0;

        for (int j = 0; j < img.getHeight() / 32; j++) {
            for (int i = 0; i < img.getWidth() / 32; i++) {
                foregroundSprites[index++] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics g) {
        // Draw background first
        for (int j = 0; j < GameManager.GAME_HEIGHT / GameManager.TILES_SIZE; j++) {
            for (int i = 0; i < GameManager.GAME_WIDTH / GameManager.TILES_SIZE; i++) {
                int index = levelOne.getBackgroundSpriteIndex(i, j); // Call the correct index method
                if (index < backgroundSprites.length) {
                    g.drawImage(backgroundSprites[index], GameManager.TILES_SIZE * i, GameManager.TILES_SIZE * j, GameManager.TILES_SIZE, GameManager.TILES_SIZE, null);
                }
            }
        }

        // Draw terrain and decoration
        for (int j = 0; j < GameManager.GAME_HEIGHT / GameManager.TILES_SIZE; j++) {
            for (int i = 0; i < GameManager.GAME_WIDTH / GameManager.TILES_SIZE; i++) {
                int index = levelOne.getTerrainAndDecoSpriteIndex(i, j); // Call the correct index method
                if (index < terrainAndDecoSprites.length) {
                    g.drawImage(terrainAndDecoSprites[index], GameManager.TILES_SIZE * i, GameManager.TILES_SIZE * j, GameManager.TILES_SIZE, GameManager.TILES_SIZE, null);
                }
            }
        }

        // Draw foreground last (on top of everything else)
        for (int j = 0; j < GameManager.GAME_HEIGHT / GameManager.TILES_SIZE; j++) {
            for (int i = 0; i < GameManager.GAME_WIDTH / GameManager.TILES_SIZE; i++) {
                int index = levelOne.getForegroundSpriteIndex(i, j); // Call the correct index method
                if (index < foregroundSprites.length) {
                    g.drawImage(foregroundSprites[index], GameManager.TILES_SIZE * i, GameManager.TILES_SIZE * j, GameManager.TILES_SIZE, GameManager.TILES_SIZE, null);
                }
            }
        }
    }

    public void update() {
        // Optional: Update logic for your level
    }
}

package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Entity.Player;
import utils.LoadSave;

public class LevelHandler {
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelHandler() {
        importTerrainSprites();
        levelOne = new Level(LoadSave.getLevelData());
    }

    private void importTerrainSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[247]; // Assuming 247 tiles
        for (int j = 0; j < 13; j++) {  // Loop over rows
            for (int i = 0; i < 19; i++) { // Loop over columns
                int index = j * 19 + i;
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics g) {
    	
        for (int j = 0; j < GameManager.GAME_HEIGHT / GameManager.TILES_SIZE; j++) {
            for (int i = 0; i < GameManager.GAME_WIDTH / GameManager.TILES_SIZE; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], GameManager.TILES_SIZE * i, GameManager.TILES_SIZE * j, GameManager.TILES_SIZE, GameManager.TILES_SIZE, null);
            }
        }
    }

    public void update() {
        
    }
}

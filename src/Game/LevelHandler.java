package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSave;

public class LevelHandler {

	private BufferedImage[] levelSprite;
	private Level levelOne;
	
	public LevelHandler() {
		//levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		importTerrainSprites();
	}
	
	private void importTerrainSprites() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[247];
		for(int i = 0; i < 13; i++) {
			for(int j = 0; i < 19; i++) {
				int index = j*19 + i;
				levelSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
			}
		}
		
	}

	public void draw(Graphics g) {
		g.drawImage(levelSprite[2], 0, 0, null);
	}
	public void update() {
		
	}
}

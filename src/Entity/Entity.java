package Entity;

import java.awt.image.BufferedImage;

public class Entity { //Parent class, that can be used for other entities
	public int x, y;
	public int speed;

	public BufferedImage walk01, walk02, walk03, walk04, walk05, walk06, walk07, walk08; //Sprites
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
}

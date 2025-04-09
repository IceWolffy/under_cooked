package Entity;

import java.awt.image.BufferedImage;

public class Entity { //Parent class, that can be used for other entities
	public int x, y;
	public int speed;

	public BufferedImage walk; //Sprites
	public BufferedImage idle;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
}

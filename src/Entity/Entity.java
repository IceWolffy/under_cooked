package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity { //Parent class, that can be used for other entities
	public int startX, startY;
	public int x, y;
	public int speed;
	public int width, height;
	public Rectangle hitbox;
	
	public BufferedImage walk; //Sprites
	public BufferedImage idle;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		initHitbox();
	}
	public void drawHitbox(Graphics g) { //Helps with debugging, since it shows the hitbox
		g.setColor(Color.RED);
		g.drawRect(hitbox.x,hitbox.y,width, height);
	}
	private void initHitbox() {
		hitbox = new Rectangle(x, y, width, height);
	}
	public void updateHitbox() {
		hitbox.x = x;
		hitbox.y = y;
	}
	public Rectangle getHitbox() {
		return hitbox;
	}
}

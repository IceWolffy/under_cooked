package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class DropOffPoint {
    private int x, y;
    private int width = 64;
    private int height = 64;
    
    public DropOffPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void draw(Graphics g) {
    
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
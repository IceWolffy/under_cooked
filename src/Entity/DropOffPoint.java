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
        // Draw drop-off point
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, width, height);
        g.setColor(Color.RED);
        g.drawRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawString("DROP", x + 15, y + 35);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
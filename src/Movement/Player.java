package Movement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Player extends JPanel implements KeyListener {
    private int x, y;
    private int width = 50, height = 50;
    private int speed = 5;
    private boolean jumping = false;
    private int velocityY = 0;
    private final int gravity = 1;
    
    public Player() {
        this.x = 100;
        this.y = 750;
        setFocusable(true);
        addKeyListener(this);
    }
    
    public void move() {
        if (jumping) {
            y += velocityY;
            velocityY += gravity;
            if (y >= 750) {
                y = 750;
                jumping = false;
            }
        }
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x -= speed;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x += speed;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE && !jumping) {
            jumping = true;
            velocityY = -15;
        }
        move();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    @Override
    public void keyTyped(KeyEvent e) {}
}

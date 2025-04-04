package Game;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {

    public boolean leftPressed, rightPressed, jumpPressed;

    private boolean isPlayerOne; // Flag to differentiate between players

    public KeyHandler(boolean isPlayerOne) {
        this.isPlayerOne = isPlayerOne; // Assign flag during initialization
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (isPlayerOne) {
            // Controls for Player 1 (WASD keys)
            if (code == KeyEvent.VK_A) leftPressed = true;
            if (code == KeyEvent.VK_D) rightPressed = true;
            if (code == KeyEvent.VK_W) jumpPressed = true;
        } else {
            // Controls for Player 2 (Arrow keys)
            if (code == KeyEvent.VK_LEFT) leftPressed = true;
            if (code == KeyEvent.VK_RIGHT) rightPressed = true;
            if (code == KeyEvent.VK_UP) jumpPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (isPlayerOne) {
            // Release controls for Player 1 (WASD keys)
            if (code == KeyEvent.VK_A) leftPressed = false;
            if (code == KeyEvent.VK_D) rightPressed = false;
            if (code == KeyEvent.VK_W) jumpPressed = false;
        } else {
            // Release controls for Player 2 (Arrow keys)
            if (code == KeyEvent.VK_LEFT) leftPressed = false;
            if (code == KeyEvent.VK_RIGHT) rightPressed = false;
            if (code == KeyEvent.VK_UP) jumpPressed = false;
        }
    }
}

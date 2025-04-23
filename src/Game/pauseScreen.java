package Game;
import javax.imageio.ImageIO;
import javax.swing.*;

import static Game.GameManager.GAME_HEIGHT;
import static Game.GameManager.GAME_WIDTH;

import java.awt.*;
import java.io.File;

public class PauseScreen extends JPanel {
    private Image[] backgrounds = new Image[3];
    private int currentBackgroundIndex = 0;

    public PauseScreen(){
        try {
            backgrounds[0] = ImageIO.read(new File("res" + File.separator + "level" + File.separator + "pause1.png"));
            backgrounds[1] = ImageIO.read(new File("res" + File.separator + "level" + File.separator + "pause2.png"));
            backgrounds[2] = ImageIO.read(new File("res" + File.separator + "level" + File.separator + "pause3.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Call this method when ESC is pressed
    



    }

    // Call this method when ESC is pressed
    public void switchBackground() {
        currentBackgroundIndex = (currentBackgroundIndex + 1) % backgrounds.length;
        repaint(); // Refresh the panel to show the new background
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgrounds[currentBackgroundIndex] != null) {
            g.drawImage(backgrounds[currentBackgroundIndex], 0, 0, getWidth(), getHeight(), this);
        }
    }



}

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
            backgrounds[0] = ImageIO.read(new File("res" + File.separator + "level" + File.separator + ""));
            backgrounds[1] = ImageIO.read(new File("res" + File.separator + "level" + File.separator + ""));
            backgrounds[2] = ImageIO.read(new File("res" + File.separator + "level" + File.separator + ""));
        } catch (Exception e) {
            e.printStackTrace();
        }



    }



}

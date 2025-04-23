package Game;
import javax.imageio.ImageIO;
import javax.swing.*;

import static Game.GameManager.GAME_HEIGHT;
import static Game.GameManager.GAME_WIDTH;

import java.awt.*;
import java.io.File;

public class PauseScreen extends JPanel {
    private Image bgImage1;
    private Image bgImage2;
    private Image bgImage3;

    public PauseScreen(){
        try {
            String imagePath = "res" + File.separator + "level" + File.separator + "undercooked_bg.png";
            bgImage1 = ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        

    }



}

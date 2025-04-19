package Game;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Initialization {
	
	// You have to run the game twice. We'll fix this soon

    public static BufferedImage winBackgroundImage;
    public static void main(String[] args) {
    	// Preload sounds before launching the GUI
        SoundEffects.preload("/sounds/gameStart.wav");
        SoundEffects.preload("/sounds/Mainmenu.wav");
        SoundEffects.preload("/sounds/Playermovement.wav");
        SoundEffects.preload("/sounds/PlayerJump.wav");
        SoundEffects.preload("/sounds/vegtableCollection.wav");

        // Preload image
        try {
            winBackgroundImage = ImageIO.read(Initialization.class.getResourceAsStream("/winpanel/win_background.jpeg"));
        } catch (IOException e) {
            System.err.println("Could not load win background image");
            e.printStackTrace();
        }

     SwingUtilities.invokeLater(() -> {
            new GameManager(); // Runs on the EDT
        });

}
} 
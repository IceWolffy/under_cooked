package Game;

import javax.swing.*;
import java.awt.*;
import Constants.Constants;

public class MainMenu extends JPanel{
    private Image bgImage;

    public MainMenu(){

        try {
            bgImage = new ImageIcon(getClass().getResource("/Images/Background.png")).getImage(); // Load the background image
        } catch (Exception e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(1080,1080)); // Set the size of the panel to the screen size												// the panel
		setLayout(null);

    }
}

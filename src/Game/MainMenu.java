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


        //play button
        JButton playButton = new JButton("Play");
        playButton.setBounds(400, 500, 280, 100); // Set the position and size of the button
        playButton.setFont(new Font("Arial", Font.BOLD, 30)); // Set the font of the button text
        add(playButton);


    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(bgImage !=null){
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this); // Draw the background image
        }
    }
}

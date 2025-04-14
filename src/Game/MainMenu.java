package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import Constants.Constants;

public class MainMenu extends JPanel{
    private Image bgImage;

    public MainMenu(){

        try {
            String imagePath = "res" + File.separator + "player1" + File.separator + "undercooked_bg.png";
            bgImage = ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(1080,1080)); // Set the size of the panel to the screen size												// the panel
		setLayout(null);


        //play button
        JButton playButton = new JButton("Play");
        playButton.setBounds(435, 370, 200, 80); //x, y, width, height
        playButton.setFont(new Font("Arial", Font.BOLD, 30)); // Set the font of the button text
        playButton.setBackground(new Color(222, 206, 59));
        add(playButton);

        playButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this); // Get the parent frame of the panel
            frame.remove(this); // Remove the main menu panel from the frame
            frame.add(new Level1()); // Add the game level panel to the frame
            frame.revalidate(); // Revalidate the frame to update the layout
            frame.repaint(); // Repaint the frame to show the new panel
        });

        //exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(435, 470, 200, 80); // Set the position and size of the button
        exitButton.setFont(new Font("Arial", Font.BOLD, 30)); // Set the font of the button text
        exitButton.setBackground(new Color(222, 206, 59)); // Set the background color of the button
        add(exitButton);

        exitButton.addActionListener(e -> {
            System.exit(0); // Exit the application when the button is clicked
        });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(bgImage !=null){
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this); // Draw the background image
        }
    }
}

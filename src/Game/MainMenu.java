package Game;

import javax.imageio.ImageIO;
import javax.swing.*;

import static Game.GameManager.GAME_HEIGHT;
import static Game.GameManager.GAME_WIDTH;

import java.awt.*;
import java.io.File;

public class MainMenu extends JPanel{
    private Image bgImage;
    private GameManager gameManager;

    public MainMenu(GameManager gameManager) {
        this.gameManager = gameManager;

        try {
            String imagePath = "res" + File.separator + "level" + File.separator + "undercooked_bg.png";
            bgImage = ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT)); // Set the size of the panel to the screen size
        setLayout(null);

        // Play button
        JButton playButton = new JButton("Play");
        playButton.setBounds(715, 290, 200, 70); // x, y, width, height
        playButton.setFont(new Font("Arial", Font.BOLD, 30)); // Set the font of the button text
        playButton.setBackground(new Color(222, 206, 59));
        add(playButton);

        playButton.addActionListener(e -> {
            SoundEffects.stop();// stop any music first
            SoundEffects.play("/sounds/gameStart.wav");  // Game start sound

            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);


            // Pass the level map data when initializing the LevelPanel
            gameManager.startGame();
            frame.revalidate(); // Revalidate the layout
            frame.repaint(); // Force repaint to update the frame
            frame.getContentPane().requestFocusInWindow(); // Ensure focus
        });

        // Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(715, 380, 200, 70); // Set the position and size of the button
        exitButton.setFont(new Font("Arial", Font.BOLD, 30)); // Set the font of the button text
        exitButton.setBackground(new Color(222, 206, 59)); // Set the background color of the button
        add(exitButton);

        exitButton.addActionListener(e -> {
            System.exit(0); // Exit the application when the button is clicked
        });

        SoundEffects.loop("/sounds/Mainmenu.wav");  // Looping menu music

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this); // Draw the background image
        }
    }

}

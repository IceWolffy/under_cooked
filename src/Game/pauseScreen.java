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
    private JButton menuButton;
    private JButton resumeButton;
    private JButton restartButton;

    private GameManager gameManager;


    public PauseScreen(GameManager gameManager) {
        this.gameManager = gameManager;

        setLayout(null); // Use null layout for absolute positioning

        try {
            backgrounds[0] = ImageIO.read(new File("res" + File.separator + "level" + File.separator + "pause1.png"));
            backgrounds[1] = ImageIO.read(new File("res" + File.separator + "level" + File.separator + "pause2.png"));
            backgrounds[2] = ImageIO.read(new File("res" + File.separator + "level" + File.separator + "pause3.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        menuButton = createTransparentButton();
        resumeButton = createTransparentButton();
        restartButton = createTransparentButton();
        

        //menu button
        menuButton.setBounds(321,300, 280, 220); //x,y,width,height
        menuButton.addActionListener(e -> {
            System.out.println("Menu button clicked");
            gameManager.goToMenu();
        });

        //resume button
        resumeButton.setBounds(700, 300, 270, 220);
        resumeButton.addActionListener(e -> {
            System.out.println("Resume button clicked");
            gameManager.resumeGame(); // Call the method to resume the game
        });

        //restart button
        restartButton.setBounds(1070, 300, 270, 220);
        restartButton.addActionListener(e -> {
            gameManager.restartGame();         
        });

        // Add buttons to the panel
        add(menuButton);
        add(resumeButton);
        add(restartButton);
    }

    private JButton createTransparentButton() {
        JButton button = new JButton();
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBorder(null);
        button.setFocusable(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    // Call this method when ESC is pressed
    public void switchBackground() {
        currentBackgroundIndex = (currentBackgroundIndex + 1) % backgrounds.length;
        repaint(); // Refreshes the panel to show the new background
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgrounds[currentBackgroundIndex] != null) {
            g.drawImage(backgrounds[currentBackgroundIndex], 0, 0, getWidth(), getHeight(), this);
        }
    }



}

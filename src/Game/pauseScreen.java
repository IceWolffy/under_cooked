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

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Countdown countdown;
    private LevelPanel levelPanel;


    public PauseScreen(CardLayout layout, JPanel container, Countdown countdown, LevelPanel levelPanel){
        this.cardLayout = layout;
        this.mainPanel = container;
        this.countdown = countdown;
        this.levelPanel = levelPanel;

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
        menuButton.setBounds(GAME_WIDTH / 2 - 100, GAME_HEIGHT / 2 - 100, 200, 50);
        menuButton.addActionListener(e -> {
            System.out.println("Menu button clicked");
            countdown.stop();
            cardLayout.show(mainPanel, "menuScreen");
        });

        //resume button
        resumeButton.setBounds(GAME_WIDTH / 2 - 100, GAME_HEIGHT / 2 - 50, 200, 50);
        resumeButton.addActionListener(e -> {
            System.out.println("Resume button clicked");
            countdown.start();
            cardLayout.show(mainPanel, "gameScreen");
        });

        //restart button
        restartButton.setBounds(GAME_WIDTH / 2 - 100, GAME_HEIGHT / 2, 200, 50);
        restartButton.addActionListener(e -> {
            System.out.println("Restart button clicked");
            countdown.stop();
            levelPanel.restartLevel();
            cardLayout.show(mainPanel, "gameScreen");
        });

    }

    private JButton createTransparentButton() {
        JButton button = new JButton();
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
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

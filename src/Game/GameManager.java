package Game;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.*;

public class GameManager {
	
    
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 2.0f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE *  SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    
    private CardLayout layout;
    private JPanel mainPanel;

    private LevelPanel levelPanel;
    private MainMenu mainMenu;
    private pauseScreen pauseScreen;
    private Countdown countdown;

    private boolean paused = false;

    private int currentLevel = 1;
    private final int maxLevel = 3;

    public GameManager(){
        layout = new CardLayout();
        mainPanel = new JPanel(layout);

        countdown = new Countdown(60); // 60 seconds countdown

        levelPanel = new LevelPanel(this, currentLevel); // Pass GameManager to LevelPanel
        pauseScreen = new pauseScreen(this);
        mainMenu = new MainMenu(this);

        mainPanel.add(mainMenu, "menuScreen");
        mainPanel.add(levelPanel, "gameScreen");
        mainPanel.add(pauseScreen, "pauseScreen");

        JFrame frame = new JFrame("Undercooked");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setResizable(false); // Prevent window resizing
        frame.setSize(GAME_WIDTH, GAME_HEIGHT); // Set explicit size
        frame.setLocationRelativeTo(null); // Center the window
        frame.pack();
        frame.setVisible(true);

        showScreen("menuScreen");
    }
    
    public void showScreen(String screenName) {
        layout.show(mainPanel, screenName);
        if (screenName.equals("gameScreen")) {
            // Use invokeLater to guarantee focus after layout change
            SwingUtilities.invokeLater(() -> levelPanel.requestFocusInWindow());
    }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getLevelPanel() {
        return levelPanel;
    }

    public Countdown getCountdown() {
        return countdown;
    }

    public void pauseGame() {
        if (!paused) {
            countdown.stop();
            showScreen("pauseScreen");
            paused = true;
        }
    }

    public void resumeGame() {
        if (paused) {
        	paused = false;
            countdown.resume();
            showScreen("gameScreen");
            levelPanel.requestFocusInWindow();
        }
    }

    public void restartGame() {
        paused = false;
        
        // Reset countdown
        countdown.reset(60);
        
        // Remove old LevelPanel
        mainPanel.remove(levelPanel);
        
        // Create a new LevelPanel instance
        levelPanel = new LevelPanel(this, currentLevel); // Pass GameManager
        
        // Add the new LevelPanel to the card layout
        mainPanel.add(levelPanel, "gameScreen");
        
        // Show the game screen with the new LevelPanel
        showScreen("gameScreen");
        
        // Start the countdown
        countdown.start();
        
        // Request focus for the new LevelPanel
        levelPanel.requestFocusInWindow();
    }

    public void goToMenu() {
        paused = false;
        
        // Stop the countdown but don't reset it yet
        countdown.stop();
        
        // Remove old LevelPanel to free resources
        mainPanel.remove(levelPanel);
        
        // Create a new LevelPanel for next time game is started
        levelPanel = new LevelPanel(this, currentLevel); // Pass GameManager
        mainPanel.add(levelPanel, "gameScreen");
        
        // Don't create a new MainMenu - use the existing one
        showScreen("menuScreen");
    }

    public boolean isPaused() {
        return paused;
    }

    public void showWinScreen(String winnerName, int player1Score, int player2Score) {
        // Remove the old WinPanel if it exists
        for (Component comp : mainPanel.getComponents()) {
            if (comp instanceof WinPanel) {
                mainPanel.remove(comp);
                break;
            }
        }
        // Create a new WinPanel with the latest results
        WinPanel winPanel = new WinPanel(winnerName, player1Score, player2Score, this);
        mainPanel.add(winPanel, "winScreen");
        mainPanel.revalidate();
        mainPanel.repaint();
        showScreen("winScreen");
    }

    public void nextLevel(){
        if (currentLevel < maxLevel) {
            currentLevel++;
            
            // Removes the old LevelPanel from the CardLayout
            mainPanel.remove(levelPanel);

            // Creates a new LevelPanel for the next level
            levelPanel = new LevelPanel(this, currentLevel);
            mainPanel.add(levelPanel, "gameScreen");

            // Shows the new game screen
            mainPanel.revalidate();
            mainPanel.repaint();
            showScreen("gameScreen");
        
    } else {
            // If it's the last level, go back to the main menu
            goToMenu();
        }
    }
    
    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

}

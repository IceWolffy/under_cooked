package Game;

import java.awt.CardLayout;

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
    private PauseScreen pauseScreen;
    private Countdown countdown;

    private boolean paused = false;

    public GameManager(){
        layout = new CardLayout();
        mainPanel = new JPanel(layout);

        countdown = new Countdown(60); // 60 seconds countdown

        levelPanel = new LevelPanel(this); // Pass GameManager to LevelPanel
        pauseScreen = new PauseScreen(this);
        mainMenu = new MainMenu(this);

        mainPanel.add(mainMenu, "menuScreen");
        mainPanel.add(levelPanel, "gameScreen");
        mainPanel.add(pauseScreen, "pauseScreen");

        JFrame frame = new JFrame("Undercooked");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);

        showScreen("menuScreen");
    }
    
    public void showScreen(String screenName) {
        layout.show(mainPanel, screenName);
        if (screenName.equals("gameScreen")) {
        levelPanel.requestFocusInWindow();
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
            countdown.resume();
            showScreen("gameScreen");
            levelPanel.requestFocusInWindow();
            paused = false;
        }
    }

    public void restartGame() {
        countdown.reset(60);
        layout.show(mainPanel, "gameScreen");
        countdown.start();
        levelPanel.requestFocusInWindow(); // Ensure key input works 
    }

    public void goToMenu() {
        countdown.stop();
        layout.show(mainPanel, "menuScreen");
    }

    public boolean isPaused() {
        return paused;
    }
    

}

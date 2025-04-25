package Game;

import java.awt.CardLayout;

import javax.smartcardio.Card;
import javax.swing.*;

public class GameManager {
	

    private JFrame frame;
    
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
        countdown.stop();
        layout.show(mainPanel, "Pause");
    }

    public void resumeGame() {
        countdown.resume();
        layout.show(mainPanel, "gameScreen");
    }
    

}

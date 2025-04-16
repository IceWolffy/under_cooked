package Game;

import javax.swing.*;

import Entity.Player;
import utils.LoadSave;

import java.awt.*;

public class LevelPanel extends JPanel {

    private LevelHandler levelHandler;
    private Player player;
	private Player player2;
	
    

    public LevelPanel() {
    	LoadSave.getLevelData();
    	KeyHandler keyH = new KeyHandler(true);   // Player 1 uses WASD controls
		KeyHandler keyH2 = new KeyHandler(false); // Player 2 uses Arrow controls

		player = new Player(keyH, 1, LoadSave.player1X, LoadSave.player1Y, LoadSave.getLevelData());
		player2 = new Player(keyH2, 2, LoadSave.player2X, LoadSave.player2Y, LoadSave.getLevelData());
		
		addKeyListener(keyH);
		addKeyListener(keyH2);
        setPreferredSize(new Dimension(GameManager.GAME_WIDTH, GameManager.GAME_HEIGHT));
        setBackground(new Color(252, 244, 163));
        setFocusable(true);
        requestFocusInWindow();

        levelHandler = new LevelHandler();

        Thread gameThread = new Thread(() -> {
            while (true) {
                levelHandler.update(); // If needed later
                player.update();
                player2.update();
                repaint();
                try {
                    Thread.sleep(16); // ~60 FPS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        levelHandler.draw(g); // Draws the level using the LevelHandler
        player.draw(g); // Draws player
		player2.draw(g);
    }
}

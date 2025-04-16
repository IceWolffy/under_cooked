package Game;

import javax.swing.JPanel;
import static Game.GameManager.GAME_HEIGHT;
import static Game.GameManager.GAME_WIDTH;
import java.awt.*;

import Constants.Constants;
import Entity.Player;

@SuppressWarnings("serial")
public class Level1 extends JPanel {
	private Player player;
	private Player player2;
	

	public Level1() {
		setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT)); // Set the size of the panel to the screen size
																									// the panel
		setBackground(new Color(252, 244, 163));

		KeyHandler keyH = new KeyHandler(true);   // Player 1 uses WASD controls
		KeyHandler keyH2 = new KeyHandler(false); // Player 2 uses Arrow controls

		player = new Player(keyH, 1);
		player2 = new Player(keyH2, 2);
		
		addKeyListener(keyH);
		addKeyListener(keyH2);
		
		setFocusable(true);
		requestFocusInWindow();
		// Player logic
		Thread gameThread = new Thread(() -> {
			while (true) {
				player.update(); // Move the player
				player2.update(); // Move the player 2
				repaint(); // Redraw the screen
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

		// Draw the ground
		g.setColor(new Color(128, 128, 128)); // Brown color for the ground
		g.fillRect(0, 920, Constants.SCREEN_WIDTH, 250); // Draw the ground at the bottom
																						// of the panel

		// Draw platforms
		g.setColor(new Color(36, 36, 36));
		g.fillRect(470, 800, 1000, 25); // Platform middle
		g.fillRect(0, 600, 450, 25); // Platform left
		g.fillRect(1500, 600, 450, 25); // Platform right

		// Ending platform
		g.setColor(new Color(204, 119, 34));
		g.fillRect(700, 425, 500, 25);

		// Decorations
		// Level banner
		g.setColor(new Color(195, 176, 145));
		g.fillRoundRect(50, 50, 200, 50, 20, 20); // Draw a rounded rectangle for the level banner

		g.setColor(new Color(0, 0, 0)); // Set the color for the text
		g.setFont(new Font("Arial", Font.PLAIN, 40)); // Set the font for the level name
		g.drawString("Level 1", 70, 87); // Displays the level name

		player.draw(g); // Draws player
		player2.draw(g);
	}

}

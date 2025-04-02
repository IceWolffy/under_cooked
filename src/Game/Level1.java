 package Game;

import javax.swing.JPanel;
import java.awt.*;

import Constants.Constants;
import Entity.Player;
import Game.KeyHandler;

public class Level1 extends JPanel{
	private Player player;

    public Level1() {
        setPreferredSize(new Dimension(Constants.SCREEN_SIZE.width, Constants.SCREEN_SIZE.height)); // Set the size of the panel
        setBackground(new Color(252, 244,163));
        
        KeyHandler keyH = new KeyHandler();
        player = new Player(keyH);

        addKeyListener(keyH);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        // Draw the ground
        g.setColor(new Color(128, 128, 128)); // Brown color for the ground
        g.fillRect(0, 980, Constants.SCREEN_SIZE.width, Constants.SCREEN_SIZE.height); // Draw the ground at the bottom of the panel

        //Draw platforms
        g.setColor(new Color(36,36,36));
        g.fillRect(470, 800, 1000, 25); //Platform middle
        g.fillRect(0, 600, 450, 25); //Platform left
        g.fillRect(1500, 600, 450, 25); //Platform right

        //Ending platform
        g.setColor(new Color(204, 119, 34));
        g.fillRect(700, 425, 500, 25);

        //Decorations
        //Level banner
        g.setColor(new Color(195,176,145));
        g.fillRoundRect(50, 50, 200, 50, 20, 20); // Draw a rounded rectangle for the level banner

        g.setColor(new Color(0, 0, 0)); // Set the color for the text
        g.setFont(new Font("Arial", Font.PLAIN, 40)); // Set the font for the level name
        g.drawString("Level 1", 70,87); //Dispalys the level name
        
   	 	player.draw(g); //Draws player
    }

    
    

}

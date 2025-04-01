package Game;

import java.security.Key;
import javax.swing.JPanel;
import java.awt.*;

public class Level1 extends JPanel{

    public Level1() {
        setPreferredSize(new Dimension(1000, 800)); // Set the size of the panel
        setBackground(new Color(252, 244,163));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        // Draw the ground
        g.setColor(new Color(128, 128, 128)); // Brown color for the ground
        g.fillRect(0, 700, 1000, 100); // Draw the ground at the bottom of the panel

        //Draw platforms
        g.setColor(new Color(36,36,36));
        g.fillRect(75, 525, 350, 35); //Platform 1
        g.fillRect(600, 350, 350, 35); //Platform 2

        //Ending platform
        g.setColor(new Color(204, 119, 34));
        g.fillRect(150, 225, 200, 35);

    }


    

}

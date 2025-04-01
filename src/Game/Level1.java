package Game;

import java.security.Key;
import javax.swing.JPanel;
import java.awt.*;

public class Level1 extends JPanel{

    public Level1() {
        setPreferredSize(getPreferredSize());
        setBackground(new Color(135, 206, 235));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        // Draw the ground
        g.setColor(new Color(139, 69, 19)); // Brown color for the ground
        g.fillRect(0, getHeight() - 50, getWidth(), 50); // Draw the ground at the bottom of the panel

    }


    

}

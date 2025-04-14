package Game;

import javax.swing.*;

public class GameManager {
	

    private JFrame frame;
    

    public GameManager(){
        frame = new JFrame("UnderCooked"); // Initialize the frame FIRST
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        // Initialize the main menu panel
        frame.setContentPane(new MainMenu());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    

}

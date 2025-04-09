package Game;

import javax.swing.*;

public class GameManager {
	

    private JFrame frame;
    

    public GameManager(){
        frame = new JFrame("UnderCooked"); // Initialize the frame FIRST
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        // Initialize Level1 AFTER frame is created
        Level1 level1 = new Level1();
        frame.setContentPane(level1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    

}

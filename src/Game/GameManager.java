package Game;

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

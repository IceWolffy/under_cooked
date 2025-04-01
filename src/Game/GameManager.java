package Game;

import javax.swing.*;

public class GameManager {

    private JFrame frame;

    public GameManager(){
        frame = new JFrame("UnderCooked");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        startLevel1();
    }

}

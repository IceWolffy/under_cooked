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

    public void startLevel1(){
        Level1 level1 = new Level1();
        frame.setContentPane(level1);
        frame.pack();
        frame.setVisible(true);
    }

}

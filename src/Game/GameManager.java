package Game;

import java.awt.*;
import javax.swing.*;

public class GameManager {
	

    private JFrame frame;
    

    public GameManager(){
        frame = new JFrame("UnderCooked");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);

        startLevel1();
        enterFullscreen();
    }

    public void startLevel1(){
        Level1 level1 = new Level1();
        frame.getContentPane().removeAll();
        frame.add(level1);
        frame.revalidate();
        frame.repaint();
        frame.requestFocusInWindow();

}

private void enterFullscreen(){
    GraphicsDevice gd = frame.getGraphicsConfiguration().getDevice();
    if (gd.isFullScreenSupported()) {
       gd.setFullScreenWindow(frame);
    } else {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
    }
}
}

package Constants;

import java.awt.*;

public class Constants {
    //Screen size constants
    private static final Dimension screenSIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public final static int SCREEN_WIDTH = screenSIZE.width; // Width of the screen
    public final static int SCREEN_HEIGHT = screenSIZE.height; // Height of the screen
    public final static int GROUND_WIDTH = SCREEN_WIDTH; // Width of the ground

    // Physics variables
    public final static int gravity = 1;
    public final static int jumpForce = -20;
    public final static int groundLevel = 800 - 32; // Bottom of screen - player height

    //Key codes



    //Player



}

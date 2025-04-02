package Constants;

import java.awt.*;

public class Constants {
    //Screen size constants
    public final static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public final static int SCREEN_WIDTH = SCREEN_SIZE.width; // Width of the screen
    public final static int GROUND_HEIGHT = SCREEN_SIZE.height/3; // Height of the ground

    // Physics variables
    public boolean isJumping = false;
    public int velocityY = 0;
    public final int gravity = 1;
    public final int jumpForce = -15;
    public final int groundLevel = 800 - 32; // Bottom of screen - player height

    //Key codes



    //Player



}

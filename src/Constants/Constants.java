package Constants;

import java.awt.*;

public class Constants {
    //Screen size constants
    final static int originalTileSize = 16; // 16x16 tile size
    final static int scale = 3; // Scale factor for the game
    public final static int tileSize = originalTileSize * scale; // 48x48 tile size
    
    //screen size resolution
    public final static int maxScreenCol = 16; // Width of the screen
    public final static int maxScreenRow = 9; // Height of the screen
    public final static int SCREEN_WIDTH = tileSize * maxScreenCol; // 768 pixels
    public final static int SCREEN_HEIGHT = tileSize * maxScreenRow; // 432 pixels

    // Physics variables
    public final static int gravity = 1;
    public final static int jumpForce = -20;
    public final static int groundLevel = 800 - 32; // Bottom of screen - player height

    //Key codes



    //Player



}

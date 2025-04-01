package Entity;

import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

public class Player extends Entity{
	
	KeyHandler keyH;
	
	  public Player(KeyHandler keyH) {
	        this.keyH = keyH;
	        this.x = 100;  // Starting position
	        this.y = 100;
	        this.speed = 4; // Movement speed
	    }

}

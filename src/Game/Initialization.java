package Game;

import javax.swing.SwingUtilities;

public class Initialization {
	
    public static void main(String[] args) {
    	
     SwingUtilities.invokeLater(() -> {
            new GameManager(); // Runs on the EDT
        });

}
} 
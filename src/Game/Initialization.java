package Game;

import javax.swing.SwingUtilities;

public class Initialization {
	
	// You have to run the game twice. We'll fix this soon
    public static void main(String[] args) {
    	// Preload sounds before launching the GUI
        SoundEffects.preload("/sounds/gameStart.wav");
        SoundEffects.preload("/sounds/Mainmenu.wav");
        SoundEffects.preload("/sounds/Playermovement.wav");
        SoundEffects.preload("/sounds/PlayerJump.wav");
        SoundEffects.preload("/sounds/vegtableCollection.wav");
     SwingUtilities.invokeLater(() -> {
            new GameManager(); // Runs on the EDT
        });

}
} 
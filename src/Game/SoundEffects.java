package Game;

import javax.sound.sampled.*;

public class SoundEffects {
    public static void play(String soundFile) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                SoundEffects.class.getResourceAsStream(soundFile));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error playing sound: " + soundFile);
            e.printStackTrace();
        }
    }
}

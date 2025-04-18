/*package Game;

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
}*/
package Game;

import javax.sound.sampled.*;

public class SoundEffects {
    private static Clip currentClip;

    public static void play(String soundFile) {
        stop(); // Stop any currently playing sound
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                SoundEffects.class.getResourceAsStream(soundFile));
            currentClip = AudioSystem.getClip();
            currentClip.open(audioInputStream);
            currentClip.start();
        } catch (Exception e) {
            System.out.println("Error playing sound: " + soundFile);
            e.printStackTrace();
        }
    }

    public static void loop(String soundFile) {
        stop(); // Stop any currently playing sound
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                SoundEffects.class.getResourceAsStream(soundFile));
            currentClip = AudioSystem.getClip();
            currentClip.open(audioInputStream);
            currentClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Error looping sound: " + soundFile);
            e.printStackTrace();
        }
    }

    public static void stop() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
        }
    }
}

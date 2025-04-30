package Game;

import javax.sound.sampled.*;
import java.util.HashMap;
import java.io.InputStream;

public class SoundEffects {

    private static Clip currentClip;  //preload and cache to prevent game & sound lag
    public static final HashMap<String, Clip> soundCache = new HashMap<>();

    public static void preload(String soundFile) {
        if (soundCache.containsKey(soundFile)) return;

        try {
            InputStream audioSrc = SoundEffects.class.getResourceAsStream(soundFile);
            AudioInputStream ais = AudioSystem.getAudioInputStream(audioSrc);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            soundCache.put(soundFile, clip);
        } catch (Exception e) {
            System.out.println("Error preloading sound: " + soundFile);
            e.printStackTrace();
        }
    }

    public static void play(String soundFile) {
        preload(soundFile);

        if (soundFile.contains("Playermovement")) {
            // Special handling for movment sound: create new clip for each walk sound
            try {
                InputStream audioSrc = SoundEffects.class.getResourceAsStream(soundFile);
                AudioInputStream ais = AudioSystem.getAudioInputStream(audioSrc);
                Clip walkClip = AudioSystem.getClip();
                walkClip.open(ais);
                walkClip.start();
            } catch (Exception e) {
                System.out.println("Error playing walk sound: " + soundFile);
                e.printStackTrace();
            }
            return;
        }

        Clip clip = soundCache.get(soundFile);
        if (clip == null) return;

        // Restart from beginning every time
        clip.setFramePosition(0);
        clip.start();
    }

    public static void loop(String soundFile) {
        stop(); // Stop any running loop
        preload(soundFile);
        currentClip = soundCache.get(soundFile);
        if (currentClip == null) return;

        currentClip.setFramePosition(0);
        currentClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void stop() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.setFramePosition(0);
        }
    }
}


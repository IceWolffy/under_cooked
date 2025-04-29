package utils;

import javax.sound.sampled.*;

public class MusicPlayer {
    private static Clip clip;

    public static void play(String filepath){
        stop(); // Stop any currently playing music
        
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(MusicPlayer.class.getResource(filepath));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop music
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}

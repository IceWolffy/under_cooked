package utils;

import javax.sound.sampled.*;

public class MusicPlayer {
    private static Clip clip;

    public static void play(String filepath){
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
}

package Tests;

import org.junit.jupiter.api.Test;

import Game.SoundEffects;

import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SoundEffectsTest {

    @Test
    void testSoundFileExists() {
        // Specify the path of your .mp4 file
        String soundFile = "/sounds/testJump.mp4";

        // Try to load the resource as an InputStream
        InputStream audioSrc = SoundEffects.class.getResourceAsStream(soundFile);

        // Assert that the InputStream is not null, indicating the file was found
        assertNotNull(audioSrc, "The sound file should exist and be found.");
    }
}

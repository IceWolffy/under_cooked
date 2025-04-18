package Tests;

import static org.junit.Assert.*;
import org.junit.*;

import Game.Countdown;


public class CountdownTest {
    private Countdown countdown;

    @Before
    public void setUp() {
        countdown = new Countdown(10); // Initialize with a 10-second countdown

    }

    @Test
    public void testInitialState() {
        assertEquals("Initial seconds should be 10", 10, countdown.getSecondsRemaining());
        assertFalse("Timer should not be running initially", countdown.isRunning());
    }

    

}

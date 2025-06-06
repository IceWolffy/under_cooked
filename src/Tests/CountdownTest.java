package Tests;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.jupiter.api.Test;

import Game.Countdown;


public class CountdownTest {
    private Countdown countdown;

    @Before
    public void setUp() {
        countdown = new Countdown(10); // Initialize with a 10-second countdown

    }

    @Test
    public void testInitialState() { // This test will check if the initial state of the countdown is correct
        assertEquals("Initial seconds should be 10", 10, countdown.getSecondsRemaining());
        assertFalse("Timer should not be running initially", countdown.isRunning());
    }

    @Test
    public void testStart() throws InterruptedException { // This test will check if the countdown starts correctly
        countdown.start();
        assertTrue("Timer should be running after start", countdown.isRunning());

        Thread.sleep(1100); // Wait a little over 1 second

        int secondsAfterTick = countdown.getSecondsRemaining();
        assertTrue("Seconds should decrease after starting", secondsAfterTick < 10);

        countdown.reset(10); // Reset it so other tests don't break
    }

    @Test
    public void testReset() { // This test will check if the reset method works correctly
        countdown.start();
        countdown.reset(20);

        assertFalse("Timer should not be running after reset", countdown.isRunning());
        assertEquals("Seconds should reset to 20", 20, countdown.getSecondsRemaining());
    }

    @Test
    public void testCountdownEndsCorrectly() throws InterruptedException { // This test will check if the countdown ends correctly
        countdown = new Countdown(2); // Only 2 seconds to make test fast
        countdown.start();

        Thread.sleep(2500); // Wait enough time for it to hit 0

        assertEquals("Timer should reach 0", 0, countdown.getSecondsRemaining());
        assertFalse("Timer should stop running when it reaches 0", countdown.isRunning());
    }

}

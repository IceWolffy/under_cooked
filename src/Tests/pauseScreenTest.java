package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.GameManager;
import Game.pauseScreen;

import java.awt.Component;
import java.awt.Image;
import java.lang.reflect.Field;
import javax.swing.*;

public class pauseScreenTest {

    private GameManager gameManager;
    private pauseScreen pauseScreen;
    
    // Test implementation of GameManager for verification
    private static class TestGameManager extends GameManager {
        private boolean menuCalled = false;
        private boolean resumeCalled = false;
        private boolean restartCalled = false;
        
        @Override
        public void goToMenu() {
            menuCalled = true;
        }
        
        @Override
        public void resumeGame() {
            resumeCalled = true;
        }
        
        @Override
        public void restartGame() {
            restartCalled = true;
        }
        
        public boolean isMenuCalled() {
            return menuCalled;
        }
        
        public boolean isResumeCalled() {
            return resumeCalled;
        }
        
        public boolean isRestartCalled() {
            return restartCalled;
        }
    }

    @BeforeEach
    void setUp() {
        // Create a test implementation of GameManager
        gameManager = new TestGameManager();
        
        // Initialize the pauseScreen with our test implementation
        pauseScreen = new pauseScreen(gameManager);
    }

    @Test
    void testMenuButtonTriggersGoToMenu() {
        // Find the menu button
        JButton menuButton = findButtonByPosition(321, 300, 280, 220);
        assertNotNull(menuButton, "Menu button should not be null");
        
        // Simulate a click on the menu button
        menuButton.doClick();
        
        // Verify that goToMenu was called on the GameManager
        assertTrue(((TestGameManager)gameManager).isMenuCalled(), "goToMenu should be called");
    }

    @Test
    void testResumeButtonTriggersResumeGame() {
        // Find the resume button
        JButton resumeButton = findButtonByPosition(700, 300, 270, 220);
        assertNotNull(resumeButton, "Resume button should not be null");
        
        // Simulate a click on the resume button
        resumeButton.doClick();
        
        // Verify that resumeGame was called on the GameManager
        assertTrue(((TestGameManager)gameManager).isResumeCalled(), "resumeGame should be called");
    }

    @Test
    void testRestartButtonTriggersRestartGame() {
        // Find the restart button
        JButton restartButton = findButtonByPosition(1070, 300, 270, 220);
        assertNotNull(restartButton, "Restart button should not be null");
        
        // Simulate a click on the restart button
        restartButton.doClick();
        
        // Verify that restartGame was called on the GameManager
        assertTrue(((TestGameManager)gameManager).isRestartCalled(), "restartGame should be called");
    }
    
    @Test
    void testSwitchBackground() {
        // Check that the starting background index is 0
        int initialIndex = getBackgroundIndex();
        assertEquals(0, initialIndex, "Initial background index should be 0");
        
        // Call switchBackground and check that the index increments
        pauseScreen.switchBackground();
        int newIndex = getBackgroundIndex();
        assertEquals(1, newIndex, "Background index should increment to 1");
        
        // Call again to test the cycling behavior
        pauseScreen.switchBackground();
        newIndex = getBackgroundIndex();
        assertEquals(2, newIndex, "Background index should increment to 2");
        
        // Call again to test cycling back to 0
        pauseScreen.switchBackground();
        newIndex = getBackgroundIndex();
        assertEquals(0, newIndex, "Background index should cycle back to 0");
    }
    
    @Test
    void testBackgroundsAreLoaded() {
        // Verify backgrounds array is loaded
        Image[] backgrounds = getBackgrounds();
        assertNotNull(backgrounds, "Backgrounds array should not be null");
        assertEquals(3, backgrounds.length, "There should be 3 background images");
        
        // Check that all backgrounds were loaded
        for (Image background : backgrounds) {
            assertNotNull(background, "Each background should not be null");
        }
    }
    
    // Helper method to get the current background index using reflection
    private int getBackgroundIndex() {
        try {
            Field field = pauseScreen.getClass().getDeclaredField("currentBackgroundIndex");
            field.setAccessible(true);
            return (int) field.get(pauseScreen);
        } catch (Exception e) {
            fail("Could not access currentBackgroundIndex field: " + e.getMessage());
            return -1;
        }
    }
    
    // Helper method to get backgrounds array using reflection
    private Image[] getBackgrounds() {
        try {
            Field field = pauseScreen.getClass().getDeclaredField("backgrounds");
            field.setAccessible(true);
            return (Image[]) field.get(pauseScreen);
        } catch (Exception e) {
            fail("Could not access backgrounds field: " + e.getMessage());
            return null;
        }
    }

    // Helper method to find a button by its position
    private JButton findButtonByPosition(int x, int y, int width, int height) {
        for (Component comp : pauseScreen.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if (button.getBounds().x == x && 
                    button.getBounds().y == y &&
                    button.getBounds().width == width &&
                    button.getBounds().height == height) {
                    return button;
                }
            }
        }
        return null;
    }
}
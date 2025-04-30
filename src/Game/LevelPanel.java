package Game;

import javax.swing.*;

import Entity.Player;
import Entity.SpecialCoins;
import Entity.DropOffPoint;
import utils.LevelData;
import utils.LoadSave;
import Entity.Ingredient;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class LevelPanel extends JPanel {
    private GameManager gameManager; // Reference to GameManager

    private LevelHandler levelHandler;
    private LevelData levelData;
    private int levelNumber;

    private Player player;
    private Player player2;
    private DropOffPoint dropOffPoint;

    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private Random rand = new Random();

    
    // Points awarded per ingredient delivered
    private final int POINTS_PER_INGREDIENT = 10;
    private Countdown count = new Countdown(60); // 60 seconds countdown

    private private SpecialCoins specialCoin; // Special coin object
    private long specialSpawnTime = System.currentTimeMillis() + 10_000; // 10s after game start
    private String activeMessage = "special Coin spawned!";
    private long messageEndTime = 0;


    public LevelPanel(GameManager gameManager, int levelNumber) {
        this.levelNumber = levelNumber;
        this.gameManager = gameManager; // Initialize GameManager reference

        //Stop any previous music and starts a new one
        SoundEffects.stop(); // Stop all sounds
        SoundEffects.loop("/sounds/Level_" + levelNumber + ".wav");

        // Load level data
        levelData = LoadSave.getLevelData(levelNumber); // Get level data from LoadSave
        if (levelData == null) {
            System.err.println("Level data not found for level: " + levelNumber);
            return;
        }
        
        KeyHandler keyH = new KeyHandler(true, gameManager);   // Player 1 uses WASD controls
        KeyHandler keyH2 = new KeyHandler(false, gameManager); // Player 2 uses Arrow controls

        player = new Player(keyH, 1, LoadSave.player1X, LoadSave.player1Y, LoadSave.getLevelData(levelNumber));
        player2 = new Player(keyH2, 2, LoadSave.player2X, LoadSave.player2Y, LoadSave.getLevelData(levelNumber));
        
        // Create a drop-off point (cooking station) in the center of the map
        dropOffPoint = new DropOffPoint(GameManager.GAME_WIDTH / 2 - 65, GameManager.GAME_HEIGHT / 2 + 256);
        
        addKeyListener(keyH);
        addKeyListener(keyH2);
        setPreferredSize(new Dimension(GameManager.GAME_WIDTH, GameManager.GAME_HEIGHT));
        setBackground(new Color(252, 244, 163));
        setFocusable(true);

        levelHandler = new LevelHandler(levelNumber);

        // Spawn 5 ingredients randomly at start
        for (int i = 0; i < 5; i++) {
            ingredients.add(createRandomIngredient());
        }

        Thread gameThread = new Thread(() -> {
            while (true) {
                levelHandler.update(); // If needed later
                player.update();
                player2.update();

                // Check collisions with ingredients
                for (int i = 0; i < ingredients.size(); i++) {
                    Ingredient ing = ingredients.get(i);
                    if (!ing.collected) {
                        // Player 1 collecting ingredients
                        if (player.getBounds().intersects(ing.getBounds()) && !player.getInventory().isFull()) {
                            SoundEffects.play("/sounds/vegtableCollection.wav");  // Ingredient collected sound
                            ing.collected = true;
                            player.getInventory().addIngredient(ing);
                            
                            // Replace with a new ingredient after a delay
                            final int index = i;
                            new Thread(() -> {
                                try {
                                    Thread.sleep(3000); // 3-second delay before respawning
                                    ingredients.set(index, createRandomIngredient());
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }).start();
                        }
                        
                        // Player 2 collecting ingredients
                        if (player2.getBounds().intersects(ing.getBounds()) && !player2.getInventory().isFull()) {
                            SoundEffects.play("/sounds/vegtableCollection.wav");  // Ingredient collected sound
                            ing.collected = true;
                            player2.getInventory().addIngredient(ing);
                            
                            // Replace with a new ingredient after a delay
                            final int index = i;
                            new Thread(() -> {
                                try {
                                    Thread.sleep(3000); // 3-second delay before respawning
                                    ingredients.set(index, createRandomIngredient());
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }).start();
                        }
                    }
                }
                
                // Check collisions with drop-off point
                if (player.getBounds().intersects(dropOffPoint.getBounds()) && player.getInventory().getItemCount() > 0) {
                    // Add points based on number of ingredients delivered
                    int itemCount = player.getInventory().getItemCount();
                    player.getInventory().addScore(itemCount * POINTS_PER_INGREDIENT);
                    player.getInventory().clearInventory();
                }
                
                if (player2.getBounds().intersects(dropOffPoint.getBounds()) && player2.getInventory().getItemCount() > 0) {
                    // Add points based on number of ingredients delivered
                    int itemCount = player2.getInventory().getItemCount();
                    player2.getInventory().addScore(itemCount * POINTS_PER_INGREDIENT);
                    player2.getInventory().clearInventory();
                }

                // Check if countdown is finished
                if (count.isFinished()) {
                    // Determine winner based on score
                    int player1Score = player.getInventory().getScore();
                    int player2Score = player2.getInventory().getScore();

                    String winnerName;
                    if (player1Score > player2Score) {
                        winnerName = "Player 1";
                    } else if (player2Score > player1Score) {
                        winnerName = "Player 2";
                    } else {
                        winnerName = "It's a Tie!";
                    }

                    // Switch to win panel
                    SwingUtilities.invokeLater(() -> {
                        gameManager.showWinScreen(winnerName, player1Score, player2Score);
                    });

                    break; // exit the game loop
                }

                // Spawn special coin after delay
                    if (specialCoin == null && System.currentTimeMillis() >= specialSpawnTime) {
                        specialCoin = createRandomSpecialIngredient();
                    }

                    // Handle special coin collision
                    if (specialCoin != null && !specialCoin.collected) {
                        if (player.getBounds().intersects(specialCoin.getBounds())) {
                            applySpecialEffect(player, 1);
                        } else if (player2.getBounds().intersects(specialCoin.getBounds())) {
                            applySpecialEffect(player2, 2);
                        }
                    }

                    // Clear the message after 3 seconds
                    if (!activeMessage.isEmpty() && System.currentTimeMillis() >= messageEndTime) {
                        activeMessage = "";
                    }


                repaint();
                try {
                    Thread.sleep(16); // ~60 FPS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameThread.start(); 

        //start the countdown
        count.start();
    }

    public void resetLevel() {
        // 1. Reset player positions
        player.setStartX(LoadSave.player1X);
        player.setStartY(LoadSave.player1Y);
        player2.setStartX(LoadSave.player2X);
        player2.setStartY(LoadSave.player2Y);
    
        // 2. Reset player inventories (if you want to clear what they're carrying)
        player.getInventory().clearInventory();
        player2.getInventory().clearInventory();
    
        // 3. Reset scores (if you want to reset scores on restart)
        player.getInventory().resetScore();
        player2.getInventory().resetScore();
    
        // 4. Clear and respawn ingredients
        ingredients.clear();
        for (int i = 0; i < 5; i++) {
            ingredients.add(createRandomIngredient());
        }
    
        // 6. Repaint the panel to reflect the reset state
        repaint();
    }

    private Ingredient createRandomIngredient() {
        int padding = 50;
        int maxX = GameManager.GAME_WIDTH - Ingredient.SIZE - padding;
        int maxY = GameManager.GAME_HEIGHT - 300; // avoid placing inside bottom ground
        int x = rand.nextInt(maxX - padding) + padding;
        int y = rand.nextInt(maxY - padding) + padding;
        return new Ingredient(x, y);
    }

    private SpecialCoins createRandomSpecialIngredient() {
        int padding = 50;
        int x = rand.nextInt(GameManager.GAME_WIDTH - 100 - padding) + padding;
        int y = rand.nextInt(GameManager.GAME_HEIGHT - 300 - padding) + padding;
        return new SpecialCoins(x, y);
    }

    private void applySpecialEffect(Player target, int playerNum) {
        boolean speedUp = SpecialCoins.randomEffect();
        double multiplier = speedUp ? 2.0 : 0.5;
    
        target.setSpeedMultiplier(multiplier);
        activeMessage = "Player " + playerNum + (speedUp ? " has gained speed potion!" : " has been slowed!");
        messageEndTime = System.currentTimeMillis() + 3000;
    
        // Reset speed after 7 seconds
        new Thread(() -> {
            try {
                Thread.sleep(7000);
                target.setSpeedMultiplier(1.0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    
        specialCoin.collected = true;
        specialCoin = null;
        specialSpawnTime = System.currentTimeMillis() + 10_000;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        levelHandler.draw(g); // Draws the level using the LevelHandler
        
        // Draw drop-off point
        dropOffPoint.draw(g);
         
        // Draw ingredients
        for (Ingredient ing : ingredients) {
            ing.draw(g);
        }

        player.draw(g); // Draws player
        player2.draw(g);
        
        // Draw game instructions
        g.setColor(new Color(255, 250, 160));
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Collect ingredients and deliver them to the oven!", 
                     GameManager.GAME_WIDTH / 2 - 345, 110);
        
        //draw timer
        count.draw(g);
    }
}
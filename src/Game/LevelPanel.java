package Game;

import javax.swing.*;

import Entity.Player;
import Entity.SpecialCoins;
import Entity.DropOffPoint;
import utils.LoadSave;
import Entity.Ingredient;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class LevelPanel extends JPanel {


    private LevelHandler levelHandler;
    private Player player;
    private Player player2;
    private DropOffPoint dropOffPoint;

    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private Random rand = new Random();

    private SpecialCoins mysteryCoin = null;
    private long gameStartTime;
    private long nextMysteryCoinSpawnTime;

    private String popupMessage = null;
    private Color popupColor = Color.BLACK;
    private long messageEndTime = 0;



    
    // Points awarded per ingredient delivered
    private final int POINTS_PER_INGREDIENT = 10;
    private Countdown count = new Countdown(60); // 60 seconds countdown

    public LevelPanel() {
        LoadSave.getLevelData();
        KeyHandler keyH = new KeyHandler(true);   // Player 1 uses WASD controls
        KeyHandler keyH2 = new KeyHandler(false); // Player 2 uses Arrow controls

        player = new Player(keyH, 1, LoadSave.player1X, LoadSave.player1Y, LoadSave.getLevelData());
        player2 = new Player(keyH2, 2, LoadSave.player2X, LoadSave.player2Y, LoadSave.getLevelData());
        
        // Create a drop-off point (cooking station) in the center of the map
        dropOffPoint = new DropOffPoint(GameManager.GAME_WIDTH / 2 - 32, GameManager.GAME_HEIGHT / 2 + 256);
        
        addKeyListener(keyH);
        addKeyListener(keyH2);
        setPreferredSize(new Dimension(GameManager.GAME_WIDTH, GameManager.GAME_HEIGHT));
        setBackground(new Color(252, 244, 163));
        setFocusable(true);
        requestFocusInWindow();

        levelHandler = new LevelHandler();

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

                // Player 1 touches mystery coin
                if (mysteryCoin != null && !mysteryCoin.isCollected() && player.getBounds().intersects(mysteryCoin.getBounds())) {
                    handleMysteryCoin(player, "Player 1");
                }

                // Player 2 touches mystery coin
                if (mysteryCoin != null && !mysteryCoin.isCollected() && player2.getBounds().intersects(mysteryCoin.getBounds())) {
                    handleMysteryCoin(player2, "Player 2");
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
                        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                        topFrame.setContentPane(new WinPanel(winnerName));
                        topFrame.revalidate();
                        topFrame.repaint();
                    });

                    break; // exit the game loop
                }

                long now = System.currentTimeMillis();

                // Spawn new mystery coin every 10 seconds
                if (mysteryCoin == null && now >= nextMysteryCoinSpawnTime) {
                    mysteryCoin = createMysteryCoin();
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

        gameStartTime = System.currentTimeMillis();
        nextMysteryCoinSpawnTime = gameStartTime + 10_000; // 10 seconds after game start

    
    }


    private Ingredient createRandomIngredient() {
        int padding = 50;
        int maxX = GameManager.GAME_WIDTH - Ingredient.SIZE - padding;
        int maxY = GameManager.GAME_HEIGHT - 300; // avoid placing inside bottom ground
        int x = rand.nextInt(maxX - padding) + padding;
        int y = rand.nextInt(maxY - padding) + padding;
        return new Ingredient(x, y);
    }

    private SpecialCoins createMysteryCoin() {
        int padding = 50;
        int maxX = GameManager.GAME_WIDTH - 32 - padding;
        int maxY = GameManager.GAME_HEIGHT - 300;
        int x = rand.nextInt(maxX - padding) + padding;
        int y = rand.nextInt(maxY - padding) + padding;
    
        return new SpecialCoins(x, y);
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
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Collect ingredients and deliver them to the orange drop-off point!", 
                     GameManager.GAME_WIDTH / 2 - 325, 20);
        
        //draw timer
        count.draw(g);

        if (popupMessage != null && System.currentTimeMillis() < messageEndTime) {
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.setColor(popupColor);
            g.drawString(popupMessage, GameManager.GAME_WIDTH / 2 - 200, 100);
        }
        

    }

    private void handleMysteryCoin(Player touchedPlayer, String playerName) {
        mysteryCoin.collect(); // Mark as collected
        nextMysteryCoinSpawnTime = System.currentTimeMillis() + 10_000;
    
        boolean speedUp = rand.nextBoolean(); // Random effect
    
        if (speedUp) {
            touchedPlayer.setSpeedMultiplier(2.0); // You’ll create this method in Player.java
            showTemporaryMessage(playerName + " has gained speed poison!", Color.GREEN);
        } else {
            touchedPlayer.setSpeedMultiplier(0.5);
            showTemporaryMessage(playerName + " has been slowed!", Color.RED);
        }
    
        // Reset speed after 7 seconds
        new Thread(() -> {
            try {
                Thread.sleep(7000);
                touchedPlayer.setSpeedMultiplier(1.0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    
        // Remove coin after 1 frame
        mysteryCoin = null;
    }

    private void showTemporaryMessage(String message, Color color) {
        popupMessage = message;
        popupColor = color;
        messageEndTime = System.currentTimeMillis() + 3000; // Show for 3 seconds
    }
    
    
}
package Game;

import javax.swing.*;

import Entity.Player;
import Entity.DropOffPoint;
import utils.LoadSave;
import Entity.Ingredient;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class LevelPanel extends JPanel {

    private GameManager gameManager = new GameManager(); // Reference to GameManager
    private LevelHandler levelHandler;
    private Player player;
    private Player player2;
    private DropOffPoint dropOffPoint;

    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private Random rand = new Random();

    
    // Points awarded per ingredient delivered
    private final int POINTS_PER_INGREDIENT = 10;
    private Countdown count = new Countdown(60); // 60 seconds countdown

    public LevelPanel() {
        LoadSave.getLevelData();
        KeyHandler keyH = new KeyHandler(true, gameManager);   // Player 1 uses WASD controls
        KeyHandler keyH2 = new KeyHandler(false, gameManager); // Player 2 uses Arrow controls

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

    private Ingredient createRandomIngredient() {
        int padding = 50;
        int maxX = GameManager.GAME_WIDTH - Ingredient.SIZE - padding;
        int maxY = GameManager.GAME_HEIGHT - 300; // avoid placing inside bottom ground
        int x = rand.nextInt(maxX - padding) + padding;
        int y = rand.nextInt(maxY - padding) + padding;
        return new Ingredient(x, y);
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
    }
}
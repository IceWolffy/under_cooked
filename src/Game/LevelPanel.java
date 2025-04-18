package Game;

import javax.swing.*;

import Entity.Player;
import utils.LoadSave;
import Entity.Ingredient;  //for ingredients

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class LevelPanel extends JPanel {


    private LevelHandler levelHandler;
    private Player player;
	private Player player2;

    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private Random rand = new Random();
	
    private Countdown countdown = new Countdown(120); // 120 seconds countdown

    public LevelPanel() {
    	LoadSave.getLevelData();
    	KeyHandler keyH = new KeyHandler(true);   // Player 1 uses WASD controls
		KeyHandler keyH2 = new KeyHandler(false); // Player 2 uses Arrow controls

		player = new Player(keyH, 1, LoadSave.player1X, LoadSave.player1Y, LoadSave.getLevelData());
		player2 = new Player(keyH2, 2, LoadSave.player2X, LoadSave.player2Y, LoadSave.getLevelData());
		
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

        /** 
        Thread gameThread = new Thread(() -> {
            while (true) {
                levelHandler.update(); // If needed later
                player.update();
                player2.update();

                // Check collisions with ingredients and respawn
                for (int i = 0; i < ingredients.size(); i++) {
                    Ingredient ing = ingredients.get(i);
                    if (!ing.collected && player.getBounds().intersects(ing.getBounds())) {
                        ingredients.set(i, createRandomIngredient());
                    }
                    if (!ing.collected && player2.getBounds().intersects(ing.getBounds())) {
                        ingredients.set(i, createRandomIngredient());
                    }
                }

                repaint();
                try {
                    Thread.sleep(16); // ~60 FPS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameThread.start(); */
        Timer gameTimer = new Timer(16, e -> {
            levelHandler.update(); // If needed later
            player.update();
            player2.update();

            // Check collisions with ingredients and respawn
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ing = ingredients.get(i);
            if (!ing.collected && player.getBounds().intersects(ing.getBounds())) {
                ingredients.set(i, createRandomIngredient());
            }
            if (!ing.collected && player2.getBounds().intersects(ing.getBounds())) {
                ingredients.set(i, createRandomIngredient());
            }
        }

        repaint();
        });

        //start the countdown
        countdown.start();
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
        
         // Draw ingredients
         for (Ingredient ing : ingredients) {
            ing.draw(g);
        }

        player.draw(g); // Draws player
		player2.draw(g);

        countdown.draw(g); // Draw the countdown timer 
    }
}

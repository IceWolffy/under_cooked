package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Ingredient> ingredients;
    private int score;
    private int playerId;
    private int capacity = 3; // Maximum number of ingredients player can carry

    public Inventory(int playerId) {
        this.playerId = playerId;
        this.ingredients = new ArrayList<>();
        this.score = 0;
    }

    public boolean addIngredient(Ingredient ingredient) {
        // Check if inventory is full
        if (ingredients.size() >= capacity) {
            return false;
        }
        
        ingredients.add(ingredient);
        return true;
    }

    public void clearInventory() {
        ingredients.clear();
    }

    public void addScore(int points) {
        score += points;
    }

    public int getScore() {
        return score;
    }

    public int getItemCount() {
        return ingredients.size();
    }

    public boolean isFull() {
        return ingredients.size() >= capacity;
    }

    // Draw the inventory on screen
    public void draw(Graphics g, int x, int y) {
        // Draw inventory background
        g.setColor(new Color(230, 230, 230, 200));
        g.fillRect(x, y, 150, 50);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 150, 50);
        
        // Draw player label and score
        g.setColor(playerId == 1 ? Color.BLUE : Color.RED);
        g.drawString("Player " + playerId + " - Score: " + score, x + 10, y + 20);
        
       
    }
}
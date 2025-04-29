package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class WinPanel extends JPanel {
    private String winnerName;
    private BufferedImage bgImage;
    private GameManager gameManager; 
    private int player1Score;
    private int player2Score;


    private JLabel scoreLabel;
    private JLabel winnerLabel;
    private JButton backButton;

    public WinPanel(String winnerName, int player1score, int player2Score, GameManager gameManager) {
        this.winnerName = winnerName;
        this.gameManager = gameManager;
        this.player1Score = player1Score;
        this.player2Score = player2Score; 

        setLayout(null); // no layout manager

        try {
            bgImage = ImageIO.read(new File("res" + File.separator + "winpanel" + File.separator + "winpanel2.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Winner Label
        /*JLabel winnerLabel = new JLabel(winnerName + " Wins!", SwingConstants.CENTER);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 40));
        winnerLabel.setForeground(Color.BLACK);
        winnerLabel.setBounds(550, 150, 300, 50); 
        add(winnerLabel);*/
        
        // Score Label (left box)
        scoreLabel = new JLabel("P1: " + player1Score + "   P2: " + player2Score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setBounds(150, 250, 300, 100); // Match left yellow box position
        add(scoreLabel);

        // Winner Label (right box)
        winnerLabel = new JLabel(winnerName + " Wins!", SwingConstants.CENTER);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        winnerLabel.setForeground(Color.BLACK);
        winnerLabel.setBounds(920, 250, 300, 100); // Match right yellow box position
        add(winnerLabel);

        JButton backButton = new JButton("Back to Menu");
        //backButton.setBounds(600, 500, 200, 50);
        backButton.setBounds(630, 300, 200, 50);
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setBackground(new Color(222, 206, 59));
        add(backButton);

        backButton.addActionListener(e -> {
            SoundEffects.stop();
            gameManager.goToMenu(); 
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }

     
    }
}

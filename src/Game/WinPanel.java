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

    private JLabel scoreTitleLabel;
    private JLabel scoreLabel;
    private JLabel winnerLabel;
    private JButton backButton;

    public WinPanel(String winnerName, int player1score, int player2Score, GameManager gameManager) {
        this.winnerName = winnerName;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        this.gameManager = gameManager; 

        setLayout(null); // no layout manager

        try {
            bgImage = ImageIO.read(new File("res" + File.separator + "winpanel" + File.separator + "winpanel2.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // "Players Score:" Title
        scoreTitleLabel = new JLabel("Players Score:", SwingConstants.CENTER);
        scoreTitleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        scoreTitleLabel.setForeground(Color.BLACK);
        scoreTitleLabel.setBounds(150, 200, 300, 50); // Above the yellow box
        add(scoreTitleLabel);

        //Play Win Sound Effect
        SoundEffects.play("/sounds/CoundDownTimesUp.wav");
        
        // Score Label (left box)
        scoreLabel = new JLabel("P1: " + player1Score + "   P2: " + player2Score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 26));
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setBounds(190, 250, 300, 50); // Match left yellow box position
        add(scoreLabel);

        // Winner Label (right box)
        winnerLabel = new JLabel(winnerName + " Wins!", SwingConstants.CENTER);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        winnerLabel.setForeground(Color.BLACK);
        winnerLabel.setBounds(1200, 250, 250, 50); // Match right yellow box position
        add(winnerLabel);

        //back to menu button
        JButton backButton = new JButton("Back to Menu");
        backButton.setBounds(690, 360, 300, 50);
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setBackground(new Color(222, 206, 59));
        add(backButton);

        backButton.addActionListener(e -> {
            SoundEffects.stop();
            gameManager.goToMenu(); 
        });

        this.gameManager = gameManager;

    }

    


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }

     
    }
}

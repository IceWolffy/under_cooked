package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class WinPanel extends JPanel {
    private String winnerName;
    private BufferedImage bgImage;
    private GameManager gameManager; // <-- 🆕 ADD this!

    public WinPanel(String winnerName, GameManager gameManager) {
        this.winnerName = winnerName;
        this.gameManager = gameManager; // <-- 🆕 ASSIGN IT

        setLayout(null); // no layout manager

        try {
            bgImage = ImageIO.read(new File("res" + File.separator + "winpanel" + File.separator + "winpanel2.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton backButton = new JButton("Back to Menu");
        backButton.setBounds(600, 500, 200, 50);
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setBackground(new Color(222, 206, 59));
        add(backButton);

        backButton.addActionListener(e -> {
            SoundEffects.stop();
            gameManager.goToMenu(); // <-- 🆕 CLEAN WAY TO RETURN TO MENU
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString(winnerName + " Wins!", 500, 300);
    }
}

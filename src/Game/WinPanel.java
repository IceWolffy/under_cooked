package Game;

import javax.swing.*;
import java.awt.*;

public class WinPanel extends JPanel {

    private String winner;

    public WinPanel(String winner) {
        this.winner = winner;

        setBackground(Color.BLACK);
        setLayout(null);

        SoundEffects.stop(); // stop any background music
        SoundEffects.play("/sounds/win.wav"); //  play win music

        // Label
        JLabel label = new JLabel(winner + " Wins!", SwingConstants.CENTER);
        label.setForeground(Color.YELLOW);
        label.setFont(new Font("Arial", Font.BOLD, 48));
        label.setBounds(300, 200, 600, 100);
        add(label);

        // Return to main menu button
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setFont(new Font("Arial", Font.PLAIN, 24));
        backButton.setBounds(400, 350, 300, 60);
        add(backButton);

        backButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.setContentPane(new MainMenu());
            topFrame.revalidate();
            topFrame.repaint();
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}

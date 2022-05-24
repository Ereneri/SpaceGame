package Main;

import javax.swing.JFrame;
import java.awt.*;

public class Main {
    
    public static void main(String[] args) {

        // Create a new JFrame container
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setTitle("SpaceGame");

        // Sets boundarys for frame
        frame.setLayout(new BorderLayout());
        frame.setSize(768, 768);
        frame.setBounds(0,0,768,768);
        
        // Create a new GamePanel
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        
        // Spawns metals
        gamePanel.spawnMetal();
        
        // spawns asteroids
        gamePanel.spawnAsteroids();

        // Start the game thread for timing the frame rate
        gamePanel.startGameThread();
        
        // Make the frame sized to the game panel along with accepting input
        frame.pack();

        // Make the frame visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

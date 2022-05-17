package Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class Main {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setResizable(false);
        frame.setTitle("SpaceGame");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setBounds(0,0,600,600);

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.pack();

        gamePanel.startGameThread();
    }
}

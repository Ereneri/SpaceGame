package Main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {
    final int originalTitleSize = 16; // 16x16
    final int scale = 3;

    final int tileSize = originalTitleSize * scale; // == 48x48
    final int mapWidth = 16;
    final int mapHeight = 16;
    final int screenWidth = mapWidth * tileSize;
    final int screenHeight = mapHeight * tileSize;

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null) {
            // updates frame with info
            update();
            // repaints frame based on new updated info
            repaint();
        }
    }

    public void update() {
        
    }

    // Draw things on JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(100, 100, tileSize, tileSize);
        g2.dispose();
        update();
    }

}

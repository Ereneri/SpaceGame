package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import rocketship.rocketship;

public class GamePanel extends JPanel implements Runnable {
    // Basic Screen Vars
    final int originalTitleSize = 16; // 16x16
    final int scale = 3;

    public final int tileSize = originalTitleSize * scale; // == 48x48
    final int mapWidth = 16;
    final int mapHeight = 16;
    // Size of Screen
    final int screenWidth = mapWidth * tileSize;
    final int screenHeight = mapHeight * tileSize;

    // FPS 
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    rocketship ship = new rocketship(this, keyH);

    // Default Location
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 8;

    // Panel constructor
    public GamePanel() {
        // this.setBounds(0, 0, 768, 768);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.setLayout(null);
    }

    // Start Game Thread
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Game Loop
    @Override
    public void run() {

        // Sleep method vars
        double drawInterval = 1000000000 / FPS; // .01666 seconds 
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        // Loop for thread
        while(gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                // Update
                update();
                // Draw
                repaint();
                delta--;
            }
        }
        this.requestFocusInWindow();
    }

    // Updates frame with key input
    public void update() {
        ship.update();
    }

    // Draw things on JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        ship.draw(g2);
        g2.dispose();
    }

}

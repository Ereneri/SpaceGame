package Main;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {
    // Basic Screen Vars
    final int originalTitleSize = 16; // 16x16
    final int scale = 3;

    final int tileSize = originalTitleSize * scale; // == 48x48
    final int mapWidth = 16;
    final int mapHeight = 16;
    // Size of Screen
    final int screenWidth = mapWidth * tileSize;
    final int screenHeight = mapHeight * tileSize;

    // FPS 
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

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
        this.requestFocus();
        // this.setLayout(null);
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
        double nextDrawTime = System.nanoTime() + drawInterval;

        // Loop for thread
        while(gameThread != null) {

            // Debugging
            System.out.println("you released key code: " + keyH.upPressed + keyH.downPressed + keyH.leftPressed + keyH.rightPressed);

            // updates frame with info
            update();
            // repaints frame based on new updated info
            repaint();

            // Sleeps thread
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                // Prins error
                e.printStackTrace();
            }
        }
    }

    // Updates frame with key input
    public void update() {
        
        if (keyH.upPressed == true) {
            playerY -= playerSpeed;
        } else if (keyH.downPressed == true) {
            playerY += playerSpeed;
        } else if (keyH.leftPressed == true) {
            playerX -= playerSpeed;
        } else if (keyH.rightPressed == true) {
            playerX += playerSpeed;
        }
    }

    // Draw things on JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }

}

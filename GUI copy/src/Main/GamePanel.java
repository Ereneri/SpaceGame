package Main;

import javax.swing.*;
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

    int FPS = 60;

    KeyHandler kh = new KeyHandler();
    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 8;

    public GamePanel() {
        this.setBounds(0, 0, 768, 768);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
        this.setLayout(null);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // .01666 seconds 
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {

            // updates frame with info
            update();
            // repaints frame based on new updated info
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        
        if (kh.upPressed == true) {
            playerY -= playerSpeed;
        } else if (kh.downPressed == true) {
            playerY += playerSpeed;
        } else if (kh.leftPressed == true) {
            playerX -= playerSpeed;
        } else if (kh.rightPressed == true) {
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

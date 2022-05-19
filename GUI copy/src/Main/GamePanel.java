package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import object.metal;
import rocketship.bullet;
import rocketship.rocketship;

public class GamePanel extends JPanel implements Runnable {
    // Basic Screen Vars and scale factor
    final int originalTitleSize = 16; // 16x16
    final int scale = 3;

    // Map sizing vars
    public final int tileSize = originalTitleSize * scale; // == 48x48
    final int mapWidth = 16;
    final int mapHeight = 16;

    // Size of Screen
    final int screenWidth = mapWidth * tileSize;
    final int screenHeight = mapHeight * tileSize;

    // FPS for Thread
    int FPS = 60;

    // SYSTEM VARS
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    
    // Object and Rocketship Vars
    rocketship ship = new rocketship(this, keyH);
    public ArrayList<bullet> bullets = new ArrayList<bullet>(); // arraylist of bullets
    
    // Metal Object
    public metal obj[] = new metal[10];

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

        // Delta method vars
        double drawInterval = 1000000000 / FPS; // .01666 seconds 
        double delta = 0;
        
        long lastTime = System.nanoTime();
        long currentTime;

        // Loop for thread
        while(gameThread != null) {

            // get current time and set delta to the difference between current time and last time divided by the frame rate
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            // then set of time to current
            lastTime = currentTime;

            // if delta is greater than 1, then update
            if (delta >= 1) {
                // Update
                update();
                // Draw
                repaint();
                delta--;
            }
        }
        // needed for input to work \/
        this.requestFocusInWindow();
    }

    // Updates frame with key input
    public void update() {
        ship.update();
        
        // needed for bullet rendering
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
        }
    }

    // Draw things on JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        ship.draw(g2);
        g2.dispose();
    }

}

package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import collision.*;
import asteroids.*;
import javax.swing.JPanel;
import asteroids.*;
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
    public final int screenWidth = mapWidth * tileSize;
    public final int screenHeight = mapHeight * tileSize;

    // FPS for Thread
    int FPS = 60;
    double delta = 0;

    // SYSTEM VARS
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    boolean running = true;
    
    // Object and Rocketship Vars
    rocketship ship = new rocketship(this, keyH);
    
    // Metal Object
    public metal obj[] = new metal[10];
    public AssetSetter aSetter = new AssetSetter(this);
    
    // asteroid stuff
    public int numAsteroids = 15;
    public Asteroid asts[] = new Asteroid[numAsteroids];
    public asteroidSetter asteroidSetter = new asteroidSetter(this);

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
    
    // spawns metal
    public void spawnMetal() {
    	aSetter.setGold();
    	aSetter.setSilver();
    	aSetter.setIron();

    }
    
    // adds the asteroids
    public void spawnAsteroids() {
    	asteroidSetter.addAsteroids();

    }

    // Start Game Thread
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pause(boolean paused) {
        running = paused;
    }

    public boolean isPaused() {
        return running;
    }

    // Game Loop
    @Override
    public void run() {

        // Delta method vars
        double drawInterval = 1000000000 / FPS; // .01666 seconds 
        delta = 0;
        
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
    }

    // Draw things on JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        for (int i = 0; i < obj.length; i++) {
        	if(obj[i]!= null) {
        		obj[i].draw(g2, this);
        	}
        }
        
        for(int i = 0; i<asts.length; i++) {
        	asts[i].draw(g2);
        }

        ship.draw(g2);
        g2.dispose();
    }

}

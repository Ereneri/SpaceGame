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

import Main.GamePanel.objRocket;
import asteroids.*;
import object.metal;
import rocketship.bullet;
import rocketship.rocketship;
import rocketship.rocketship.bulletsClass;

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
    public Thread gameThread;
    
    // Object and Rocketship Vars
    rocketship ship = new rocketship(this, keyH);
    
    // Metal Object
    public class objRocket{
    	public static metal obj[] = new metal[10];
    }
    public AssetSetter aSetter = new AssetSetter(this);
    
    // asteroid stuff
    public class ast{
    	public static int numAsteroids = 10;
        public static Asteroid asts[] = new Asteroid[numAsteroids];
    }
    public asteroidSetter asteroidSetter = new asteroidSetter(this);

    // Default Location
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 8;

    // Panel constructor
    public GamePanel() {
        // this.setBounds(0, 0, 768, 768);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
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
        
        for (int i = 0; i < objRocket.obj.length; i++) {
        	if(objRocket.obj[i]!= null) {
        		objRocket.obj[i].draw(g2, this);
        	}
        }
        
        for(int i = 0; i<ast.asts.length; i++) {
        	if(ast.asts[i]!= null) {
        		ast.asts[i].astTick();
            	ast.asts[i].draw(g2);
        	}
        }
        
        for (int ibull = 0; ibull < bulletsClass.bullets.size(); ibull++) {
        	for(int i = 0; i<ast.asts.length; i++) {
        		if(ast.asts[i].getCAst().touches(bulletsClass.bullets.get(ibull).getBulletC())) {
        			bulletsClass.bullets.remove(ibull);
        			System.out.println("touch");
            	}
        	}
        }

        ship.draw(g2);
        g2.dispose();
    }

}

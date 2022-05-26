package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

import asteroids.Asteroid;
import asteroids.asteroidSetter;
import object.metal;
import rocketship.rocketship;
import rocketship.rocketship.bulletArray;
import rocketship.rocketship.bullets;


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
    BufferedImage background;

    // FPS for Thread
    int FPS = 60;
    double delta = 0;

    // SYSTEM VARS
    public UI ui = new UI(this);
    KeyHandler keyH = new KeyHandler(this);
    public Thread gameThread;

    Boolean DEBUG = false;
    
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
        public static ArrayList<Asteroid> asts = new ArrayList<Asteroid>();
        public static ArrayList<Long> astTime = new ArrayList<Long>();
    }
    public asteroidSetter asteroidSetter = new asteroidSetter(this);

    // Default Location
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 8;

    // Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;
    public final int storeState = 4;
    public final int scoreBoardState = 5;
    
    // Misc
    Sound sound = new Sound();

    // Panel constructor
    public GamePanel() {
        // this.setBounds(0, 0, 768, 768);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.setLayout(null);
        
        //sets the backgound image to something...
        try {
            background = ImageIO.read(getClass().getResource("/Main/bg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // spawns metal
    public void spawnMetal() {
    	aSetter.setGold();
    	aSetter.setSilver();
    	aSetter.setIron();
    	aSetter.setWrench();
    }
    
    
    // adds the asteroids
    public void spawnAsteroids() {
    	asteroidSetter.addAsteroids();
    }

    // Start Game Thread
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        this.playMusic(0);
        
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

        if (gameState == playState) {
            ship.update();
            
            
            //checks if there are any collectibles left. If all are gone it spawns in more
            int objCount = 0;
            for (int i = 0; i < objRocket.obj.length; i++) {
                if(objRocket.obj[i]!= null) {
                	objCount ++;
                }
            }
            if(objCount == 0) {
            	this.spawnMetal();
            }
            
            // adds in new asteroid(s) if there are too little of them
            int astCount = 0;
            for (int i = 0; i < ast.asts.size(); i++) {
                if(ast.asts.get(i)!= null) {
                	astCount ++;
                }
            }
            for(int i = 0; i<ast.astTime.size(); i++) {
            	if(astCount < ast.numAsteroids && ast.astTime.get(i) + 3000 < System.currentTimeMillis()) {
                	ast.asts.add(new Asteroid((int)(Math.random()*650+56), (int)(Math.random()), (int)(Math.random()*4+1), (int)(Math.random()*4+1), this));
                	ast.astTime.remove(i);
                	i--;
                }
            }
        }
    }

    // Draw things on JPanel
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        //draws the background image
        g2.drawImage(background, 0, 0, null);

        // render powerful buffs
        if (ship.angel) g2.drawImage(ui.angel, tileSize/3, tileSize, tileSize, tileSize, null);
        if (ship.speedBoost) g2.drawImage(ui.speed, tileSize/3, tileSize*2, tileSize, tileSize, null);

        if (DEBUG == true) {
            gameState = playState;
        }
        
        // title screen
        if (gameState != playState) {
            ui.draw(g2);
        } else {
            for (int i = 0; i < objRocket.obj.length; i++) {
                if(objRocket.obj[i]!= null) {
                    objRocket.obj[i].draw(g2, this);
                }
            }
            
            // updates asteroids position per frame
            for(int i = 0; i<ast.asts.size(); i++) {
                if(ast.asts.get(i)!= null) {
                	ast.asts.get(i).draw(g2, this);
                    ast.asts.get(i).astTick();
                }
            }
            
            // checks if any bullets are touching any asteroids
            for (int indexbull = 0; indexbull < bulletArray.bullets.size(); indexbull++) {
                for(int i = 0; i<ast.asts.size(); i++) {
                	if(ast.asts.get(i).getCAst().touches(bulletArray.bullets.get(indexbull).getBulletC())) {
                		bullets.removeBullet(bulletArray.bullets.get(indexbull));
                		ast.astTime.add(System.currentTimeMillis());
                		ast.asts.remove(i);
                		i--;
                		System.out.println("bullet touch asteroid");
                		playSE(5);
                	}
                }
            }
            ship.draw(g2);
            
            //sets the font a certain way 
            g2.setFont(ui.bossBattle);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
            g2.setColor(Color.white);
            
            //draws the score
            g2.drawString("$" + ship.getScore(), tileSize/3, tileSize);
            
            //draws the health
            g2.drawString("Health: " + ship.getHp() + "%", tileSize/3*36, tileSize);
            
    
        }
        g2.dispose();
        
    }
    
    //plays the music
    public void playMusic(int i) {
    	
    	//sets the misic to the screne were on
    	sound.setFile(i);
    	
    	//plays and loops the music
    	sound.play();
    	sound.loop();
    }
    
    //stops the music
    public void stopMusic() {
    	
    	sound.stop();
    }
    
    public void flush() {
    	
    	sound.flush();
    }
    
    //plays individual sounds
    public void playSE(int i ) {
    	
    	//sets the misic to the screne were on
    	sound.setFile(i);
    	
    	//plays the sound
    	sound.play();
    }

    public int getScore() {
        return ship.score;
    }

    public void reset() {
        ship.setDefaultValues();
    }

    public void newGame() {
        asteroidSetter.clearAst();
        aSetter.clear();
        spawnAsteroids();
        spawnMetal();
    }

    public void setAngel(boolean b) {
        ship.angel = b;
    }

    public void addBooster() {
        ship.speedBoost = true;
        ship.speed = ship.speed+2;
    }

    public boolean getAngel() {
        return ship.angel;
    }

    public boolean getSpeedBoost() {
        return ship.speedBoost;
    }

    public int getHealth() {
        return ship.hp;
    }

}

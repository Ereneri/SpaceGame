package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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

    public class exps {
        public static ArrayList<Explosion> expsList = new ArrayList<Explosion>();
    }

    // explosion stuff
    BufferedImage e1, e2, e3, e4, e5, e6, e7;

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
    public final int optionsState = 6;
    public final int helpState = 7;
    
    // Misc
    Sound sound = new Sound();
    public boolean hit = false;
    public long hitTime = 0;
    public boolean boosted = false;
    public long boosttime = 0;
    public boolean soundOption = true;
    public boolean musicOption = true;
    
    //buying helth
    public int hKeyCount = 0;


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

        // load images into memory
        try {
            e1 = ImageIO.read(getClass().getResourceAsStream("/Explosion/E1.png"));
            e2 = ImageIO.read(getClass().getResourceAsStream("/Explosion/E2.png"));
            e3 = ImageIO.read(getClass().getResourceAsStream("/Explosion/E3.png"));
            e4 = ImageIO.read(getClass().getResourceAsStream("/Explosion/E4.png"));
            e5 = ImageIO.read(getClass().getResourceAsStream("/Explosion/E5.png"));
            e6 = ImageIO.read(getClass().getResourceAsStream("/Explosion/E6.png"));
            e7 = ImageIO.read(getClass().getResourceAsStream("/Explosion/E7.png"));
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
        this.playMusic(1);
        
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

            // array list that measures the amount of time the asteroid has been gone for
            for(int i = 0; i<ast.astTime.size(); i++) {
            	if(astCount < ast.numAsteroids && ast.astTime.get(i) + 4000 < System.currentTimeMillis()) {
                	ast.asts.add(new Asteroid((int)(Math.random()*650+56), (int)(Math.random()), (int)(Math.random()*4+1), (int)(Math.random()*4+1), this));
                	ast.astTime.remove(i);
                	i--;
                }
            }

            // shows -25% text for only .5 seconds
            if (hit) {
                if (System.currentTimeMillis() - hitTime > 500) {
                    hit = false;
                }
            }

            // shows +25% text for only .5 seconds
            if (boosted) {
                if (System.currentTimeMillis()-boosttime > 500) {
                    boosted = false;
                }
            }
            
            // checks if bullets are out of bounds
            for(int i = 0; i < bulletArray.bullets.size(); i++) {
            	if(bulletArray.bullets.get(i).getX() < -5 || bulletArray.bullets.get(i).getX() > 775 || bulletArray.bullets.get(i).getY() < -5 || bulletArray.bullets.get(i).getY() > 775) {
            		bulletArray.bullets.remove(i);
            		i--;
//            		System.out.println("delete bullet");
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

        // skips title screen if debug is on
        if (DEBUG == true) {
            gameState = playState;
        }
        
        // title screen
        if (gameState != playState) {
            ui.draw(g2);
        } else {
        	
        	//draws the collectibles
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
                for(int i = 0; i < ast.asts.size(); i++) {
                	if(ast.asts.get(i).getCAst().touches(bulletArray.bullets.get(indexbull).getBulletC())) {
                        // bullet removal
                		bullets.removeBullet(bulletArray.bullets.get(indexbull));
                        indexbull--;

                        // creates explosion object and adds to array
                        Explosion exp = new Explosion(ast.asts.get(i).getX(), ast.asts.get(i).getY());
                        exps.expsList.add(exp);

                        // hides and removes asteroid
                        ast.asts.get(i).hideAst();
                        ast.astTime.add(System.currentTimeMillis());
                        ast.asts.remove(i);
                        i--;

//                		System.out.println("bullet touch asteroid");
                		playSE(5);
                	}
                }
            }

            // renders exp animation
            for (int i = 0; i < exps.expsList.size(); i++) {
                if (exps.expsList.get(i).exploded == 14) {
                        // removal code
                        exps.expsList.remove(i);
                        i--;
                } else {
                    // loads image
                    BufferedImage expImg = null;
                    if (exps.expsList.get(i).exploded/2 == 1) {
                        expImg = e1;
                    } else if (exps.expsList.get(i).exploded/2 == 2) {
                        expImg = e2;
                    } else if (exps.expsList.get(i).exploded/2 == 3) {
                        expImg = e3;
                    } else if (exps.expsList.get(i).exploded/2 == 4) {
                        expImg = e4;
                    } else if (exps.expsList.get(i).exploded/2 == 5) {
                        expImg = e5;
                    } else if (exps.expsList.get(i).exploded/2 == 6) {
                        expImg = e6;
                    } else if (exps.expsList.get(i).exploded/2 == 7) {
                        expImg = e7;
                    } 
                    g2.drawImage(expImg, exps.expsList.get(i).getX(), exps.expsList.get(i).getY(), tileSize+8, tileSize+8, null);
                    exps.expsList.get(i).exploded++;
                }
            }
            
            //draws the ship
            ship.draw(g2);
            
            //checks if the key h is being pressed and if so buys a helth shot
            if(keyH.hKeyPressed == true /*&& score >= 200 && hp < 200*/) {
            	if(hKeyCount == 5) {
                	boosted = true;
                	boosttime = System.currentTimeMillis();
                	ship.hp += 25;
                	ship.score -= 200;
                	hKeyCount = 0;
                	playSE(9);
            	}else {
            		hKeyCount++ ;
            	}
            }
            
            //sets the font a certain way 
            g2.setFont(ui.bossBattle);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
            g2.setColor(Color.white);
            
            //draws the score
            g2.drawString("$" + ship.getScore(), tileSize/3, tileSize);
            
            //draws the health
            g2.drawString("Health: " + ship.getHp() + "%", tileSize/3*36, tileSize);

            // draws the -25% text
            if (hit) {
                g2.setColor(Color.red);
                g2.drawString("-25%", tileSize/3*42, tileSize*2);
            }

            // draws the +25% text
            if (boosted) {
                g2.setColor(Color.green);
                g2.drawString("+25%", tileSize/3*42, tileSize*2);
            }
        }
        g2.dispose();
        
    }
    
    //plays the music
    public void playMusic(int i) {
    	//checks if music is enabled
    	if(musicOption) {
        	//sets the misic to the screne were on
        	sound.setFile(i);
        	
        	//plays and loops the music
        	sound.play();
        	sound.loop();
    	}
    }
    
    //stops the music
    public void stopMusic() {
    	
    	sound.stop();
    }
    
    //plays individual sounds
    public void playSE(int i ) {
    	//checks if sound is enabled
    	if(soundOption) {
    		//sets the misic to the screne were on
        	sound.setFile(i);
        	
        	//plays the sound
        	sound.play();
    	}
    }

    //gets the score
    public int getScore() {
        return ship.score;
    }

    //restest all the ships values
    public void reset() {
        ship.setDefaultValues();
    }

    //starts a new game
    public void newGame() {
        asteroidSetter.clearAst();
        aSetter.clear();
        spawnAsteroids();
        spawnMetal();
    }

    //sets the angle powerup to true or false
    public void setAngel(boolean b) {
        ship.angel = b;
    }

    //adds the speed boost to the ship
    public void addBooster() {
        ship.speedBoost = true;
        ship.speed = ship.speed+2;
    }

    //gets the angle powerup value
    public boolean getAngel() {
        return ship.angel;
    }

    //gets the speed powerup value
    public boolean getSpeedBoost() {
        return ship.speedBoost;
    }

    //gets the ships health
    public int getHealth() {
        return ship.hp;
    }

    //toggles the sound on and off
    public void setSound(boolean sound) {
        this.soundOption = sound;
    }
}

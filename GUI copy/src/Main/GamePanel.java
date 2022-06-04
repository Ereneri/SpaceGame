package Main;

import java.awt.AlphaComposite;
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
    scoreboard sb = new scoreboard();
    public Thread gameThread;

    Boolean DEBUG = false;
    
    // Object and Rocketship Vars
    rocketship ship = new rocketship(this, keyH, sb);
    
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
    public final int saveState = 8;
    public final int leaderboardState = 9;
    public boolean nameBuilder = true;
    
    // Music
    Music music = new Music();
    Sound sound = new Sound();
    public boolean soundOption = true;
    public boolean musicOption = true;
    public ArrayList<Long> soundTime = new ArrayList<Long>();
    
    //Misc
    public boolean hit = false;
    public long hitTime = 0;
    public boolean boosted = false;
    public long boosttime = 0;
    public boolean paused = false;
    
    //buying helth
    public int hKeyCount = 0;
    public long buyTime = 0;
    public boolean buy = false;
    
    //displaying helth stuff
    double oneScale = ((double)23 / 4);
    double hpBarVal;
    double shieldBarVal;


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
        try{
			//waits for this current thread to die before beginning execution
			gameThread.join();
		//most exceptions are contained in java.lang
		}catch(InterruptedException e){
			e.printStackTrace();
		}
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

            // array list that measures the amount of time the asteroid has been gone for
            for(int i = 0; i<ast.astTime.size(); i++) {
            	if(astCount < ast.numAsteroids && ast.astTime.get(i) + 4000 < System.currentTimeMillis()) {
                	ast.asts.add(new Asteroid((int)(Math.random()*650+56), (int)(Math.random()), (int)(Math.random()*4+1), (int)(Math.random()*4+1), this));
                	ast.astTime.remove(i);
                	i--;
                }
            }
            
            // array list that measures the amount of time a sound has been present for
            for(int i = 0; i < soundTime.size(); i++) {
            	if(soundTime.get(i) + 3000 < System.currentTimeMillis()) {
            		soundTime.remove(i);
            		sound.clips.get(i).close();
            		sound.clips.remove(i);
            		i--;
            	}
            }

            // shows -25% text for only .5 seconds
            if (hit) {
                if (System.currentTimeMillis() - hitTime > 500) {
                    hit = false;
                }
            }
            
         // shows - $200 text for only .5 seconds
            if(buy) {
            	if (System.currentTimeMillis() - buyTime > 500) {
                    buy = false;
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
            if(bulletArray.bullets.size() > 0 || bulletArray.bullets == null) {
            	for (int indexbull = 0; indexbull < bulletArray.bullets.size(); indexbull++) {
                    for(int i = 0; i < ast.asts.size(); i++) {
                        if(bulletArray.bullets.size() > 0 || bulletArray.bullets == null) {
                            if(ast.asts.get(i).getCAst().touches(bulletArray.bullets.get(indexbull).getBulletC())) {
                                // bullet removal
                                bullets.removeBullet(bulletArray.bullets.get(indexbull));
                                indexbull = 0;

                                // creates explosion object and adds to array
                                Explosion exp = new Explosion(ast.asts.get(i).getX(), ast.asts.get(i).getY());
                                exps.expsList.add(exp);

                                // hides and removes asteroid
                                ast.asts.get(i).hideAst();
                                ast.astTime.add(System.currentTimeMillis());
                                ast.asts.remove(i);
                                i--;

    //                    		System.out.println("bullet touch asteroid");
                                playSE(5);
                            }
                        }
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
            if(hKeyCount == 1 && ship.score >= 200 && ship.hp < 200) {
            		
            		//stuff for displaying text at the top
                	boosted = true;
                	boosttime = System.currentTimeMillis();
                	buy = true;
                	buyTime = System.currentTimeMillis();
                	
                	//changes ships stats
                	ship.hp += 25;
                	ship.score -= 200;
                	hKeyCount = 0;
                	playSE(9);
            }else {
            	hKeyCount = 0;
            }
            
            //sets the font a certain way 
            g2.setFont(ui.bossBattle);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
            g2.setColor(Color.yellow);
            
            //draws the score
            g2.drawString("$" + ship.getScore(), tileSize/3, tileSize);
            
            //draws the health
            
            hpBarVal = oneScale * (ship.hp/4);
            
            //sets the opasity of the bars to %65
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.65));
            
            //draws the frame of the normal ship helth
            g2.setColor(Color.white);
            g2.fillRect(tileSize/3*37-1, tileSize/2-1, tileSize*3+7, tileSize/2+2-5);
            
            //if the ship also has a shield
            if(ship.hp > 100) {
            	//writes the hp value
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
                g2.setColor(Color.white);
                g2.drawString("100%", tileSize/3*32, tileSize-5);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.65));
            	
            	//draws the red hp bar
                g2.setColor(Color.red);
                g2.fillRect(tileSize/3*37, tileSize/2, tileSize*3+5, tileSize/2-5);
                
                //sets the shield HP bar value
            	shieldBarVal = oneScale * ((ship.hp-100)/4);
            	
            	//writes the shield value
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
                g2.setColor(Color.white);
                g2.drawString((ship.hp - 100) + "%", tileSize/3*32, tileSize+25-5);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.65));
            	
            	//draws the frame of the shield helth
                g2.setColor(Color.white);
                g2.fillRect(tileSize/3*37-1, tileSize/2-1+25, tileSize*3+7, tileSize/2+2-5);
                
                //draws the blue shield bar
                g2.setColor(new Color(0, 215, 215));
                g2.fillRect(tileSize/3*37, tileSize/2+25, (int)shieldBarVal+6, tileSize/2-5);
                
            }else {
            	//writes the hp value
            	if(ship.hp == 100) {
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
                    g2.setColor(Color.white);
                    g2.drawString(ship.hp + "%", tileSize/3*32, tileSize-5);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.65));
            	}else {
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
                    g2.setColor(Color.white);
                    g2.drawString(ship.hp + "%", tileSize/3*33, tileSize-5);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.65));
            	}
                
            	//draws the red hp bar without the shield bar
                g2.setColor(Color.red);
                g2.fillRect(tileSize/3*37, tileSize/2, (int)hpBarVal+6, tileSize/2-5);
            
            }
            //resets the opasity of the text
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));

            // draws the -25% text
            if (hit) {
            	if(ship.hp > 100) {
                    g2.setColor(Color.red);
                    g2.drawString("-25%", tileSize/3*42, tileSize*2+20-5);
            	}else {
                    g2.setColor(Color.red);
                    g2.drawString("-25%", tileSize/3*42, tileSize*2-5);
            	}
            }

            // draws the +25% text
            if (boosted) {
            	if(ship.hp > 100 && ship.hp < 200) {
                    g2.setColor(Color.green);
                    g2.drawString("+25%", tileSize/3*42, tileSize*2+20-5);
            	}else {
                    g2.setColor(Color.green);
                    g2.drawString("+25%", tileSize/3*42, tileSize*2-5);
            	}
            }
            
            // draws the - $200 text
            if(buy) {
            	g2.setColor(Color.red);
            	g2.drawString("- $" + 200, tileSize*2+15, tileSize);
            }
        }
        g2.dispose();
        
    }
    
    //plays the music
    public void playMusic(int i) {
    	//checks if music is enabled
    	if(musicOption) {
        	//sets the misic to the screne were on
        	music.setFile(i);
        	
        	//plays and loops the music
        	music.play();
        	music.loop();
    	}
    }
    
    //stops the music
    public void stopMusic() {
    	
    	music.stop();
    }
    
    //stops the music
    public void stopSE() {
    	
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
        	soundTime.add(System.currentTimeMillis());
        	
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

    public String getName() {
        return ship.name;
    }

    public void saveScore() {
        System.out.println("addScore is called"); 
        int Sscore = (int)getScore();
        sb.addScore(getName(), Sscore);
    }

    public ArrayList<score> getScores() {
        return sb.getScores();
    }

    public int getScore(score s) {
        return sb.getScore(s);
    }

    public String getName(score s) {
        return sb.getName(s);
    }

    public void updateScores() {
        sb.updateScores();
    }
}

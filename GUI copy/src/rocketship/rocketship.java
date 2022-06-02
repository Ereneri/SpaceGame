package rocketship;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import javax.imageio.ImageIO;
import collision.*;
// import object.OBJ_Bullet;
import Main.*;
import Main.GamePanel.ast;
import Main.GamePanel.exps;
import Main.GamePanel.objRocket;

public class rocketship {

    // basic rocketship vars
    public String direction;
    public int x, y;
    public int speed;
    public int score;
    public int hp = 100;
    public String name = "";
    
    // Store stuff
    public boolean angel = false;
    public boolean speedBoost = false;
    
    //bullets
    public class bullets{
    	public bullet bullet;
    	
    	public void addBullet(bullet block) {
    		bulletArray.bullets.add(block);
        }

        public static void removeBullet(bullet block) {
        	bulletArray.bullets.remove(block);
        }
    }
    
    //ships collision
    public Collision shipC;
    
    //walls
    public class walls{
    	public static Collision wallCUp;
        public static Collision wallCDown;
        public static Collision wallCLeft;
        public static Collision wallCRight;
    }

    // initlize the game panel and keyhabndler
    GamePanel gp;
    KeyHandler keyH;

    // Vars for sprite image
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, shield;

    // Bullet Stuff
    public class bulletArray{
    	public static ArrayList<bullet> bullets = new ArrayList<bullet>(); // arraylist of bullets
    }
    
    //shield sound stuff
    public class shieldSound{
        public static boolean hasPlayedShieldSound = false;
    }

    public void removeLetter() {
        name = name.substring(0, name.length() - 1);
    }

    public void addLetter(String letter) {
        name += letter;
    }
    
    // updates the bullets position
    public void tick(Graphics2D g2) {
        for (int i = 0; i < bulletArray.bullets.size(); i++) {
        	bulletArray.bullets.get(i).tick(g2);
        }
    }


    // constructor
    public rocketship(GamePanel gp) {
        this.gp = gp;
    }

    // contructor (DEFAULT)
    public rocketship(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.score = 0;
        setDefaultValues();
        getRocketImage();
        shipC = new Collision(x, y, gp.tileSize-11, gp.tileSize-13);
        walls.wallCUp = new Collision(0, 0, 768, 5);
        walls.wallCDown = new Collision(0, 763, 768, 5);
        walls.wallCLeft = new Collision(0, 0, 5, 768);
        walls.wallCRight = new Collision(763, 0, 5, 768);
    }
    
    public int getXShip() {
    	return x;
    }

    public void setXShip(int x) {
    	this.x = x;
    }

    public int getYShip() {
    	return y;
    }

    public void setYShip(int y) {
    	this.y = y;
    }

    
    // Resets the rocketship to default values
    public void setDefaultValues() {
        x = gp.screenWidth/2;
        y = gp.screenHeight/2;
        speed = 4;
        direction = "up";
        score = 0;
        hp = 100;
    }

    // Get the image into memory
    public void getRocketImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/ship/up.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/ship/upRight.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/ship/down.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/ship/downLeft.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/ship/left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/ship/upLeft.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/ship/right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/ship/downRight.png"));
            shield = ImageIO.read(getClass().getResourceAsStream("/ship/shield.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // updates the frame depending on key input
    public void update() {
        // below is for 4 directional movement
        if (keyH.upPressed == true) {
            y -= speed;
            direction = "up";
        }
        if (keyH.downPressed == true) {
            y += speed;
            direction = "down";
        }
        if (keyH.leftPressed == true) {
            x -= speed;
            direction = "left";
        }
        if (keyH.rightPressed == true) {
            x += speed;
            direction = "right";
        }
        // Everything below is for diagonal movement
        if (keyH.upPressed && keyH.rightPressed) {
            direction = "upRight";
        }
        if (keyH.upPressed && keyH.leftPressed) {
            direction = "upLeft";
        }
        if (keyH.downPressed && keyH.rightPressed) {
            direction = "downRight";
        }
        if (keyH.downPressed && keyH.leftPressed) {
            direction = "downLeft";
        }
        // creates a new bullet object if the space bar is pressed
        if (keyH.shotKeyPressed == true) {
            if (bulletArray.bullets.size() == 0 || bulletArray.bullets.get(bulletArray.bullets.size()-1).getTime() + 200 < System.currentTimeMillis()) {
            	bulletArray.bullets.add(new bullet(this.x+16, this.y+16, direction));
            	gp.playSE(4);
            }
        }
    }

    // score getter
    public String getScore() {
        return ""+score;
    }
    
    //hp getter
    public String getHp() {
        return ""+hp;
    }

    // takes the direction and draws the correct sprite image
    public void draw(Graphics2D g2) {
    	
        // render bullets
        for (int i = 0; i < bulletArray.bullets.size(); i++) {
        	bulletArray.bullets.get(i).tick(g2);
        	bulletArray.bullets.get(i).getBulletC().setXCol((int)bulletArray.bullets.get(i).getX());
        	bulletArray.bullets.get(i).getBulletC().setYCol((int)bulletArray.bullets.get(i).getY());
        	bulletArray.bullets.get(i).draw(g2);
        }

        BufferedImage image = null;
        switch(direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        // 8 direction movement
            case "upRight":
                image = up2;
                break;
            case "downRight":
                image = right2;
                break;
            case "upLeft":
                image = left2;
                break;
            case "downLeft":
                image = down2;
                break;
            } 

        // draw the ship with using the global x and y coordinates along with scaling from the gamepanel
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        if(hp > 100) {
        	//changes the opasity to 50%
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.5));
            
            //draws the shield
            g2.drawImage(shield, x-10, y-10, gp.tileSize+20, gp.tileSize+20, null);
            
            //checks if the shield sound has been palyed
            if(!shieldSound.hasPlayedShieldSound) {
            	gp.playSE(12);
            	shieldSound.hasPlayedShieldSound = true;
            }
            
            //resets the graphics opasity to 1
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
        }

 
        //moves collision with ship
        g2.setColor(Color.gray);
        shipC.setXCol(x+5);
        shipC.setYCol(y+9);
        
//         displays the ships collision box
//      shipC.render(g2);
        
//         renders walls
//      walls.wallCUp.render(g2);
//      walls.wallCDown.render(g2);
//      walls.wallCLeft.render(g2);
//      walls.wallCRight.render(g2);
        
        
        // teleportation if you touch a wall
        if(shipC.touches(walls.wallCUp)) {
        	y = 762-gp.tileSize;
        	shipC.setYCol(y);
        	g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        	gp.playSE(14);
        }else if(shipC.touches(walls.wallCDown)) {
        	y = 6;
        	shipC.setYCol(y);
        	g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        	gp.playSE(14);
        } else if(shipC.touches(walls.wallCLeft)) {
        	x = 762-gp.tileSize;
        	shipC.setXCol(x);
        	g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        	gp.playSE(14);
        }else if(shipC.touches(walls.wallCRight)) {
        	x = 6;
        	shipC.setXCol(x);
        	g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        	gp.playSE(14);
        }
        
        
        // picking up the collectibles and adding health
        for (int i = 0; i < objRocket.obj.length; i++) {
        	if(objRocket.obj[i]!= null) {
        		objRocket.obj[i].getCObj().setXCol(objRocket.obj[i].getWorldX()+8);
        		objRocket.obj[i].getCObj().setYCol(objRocket.obj[i].getWorldY()+5);
        		
        		//checks if the ship is touching any of the collectibles
        		if(shipC.touches(objRocket.obj[i].getCObj())) {
        			
        			//adds to the score the collectibles value
                	score += objRocket.obj[i].getValue();
                	
                	//checks if you are collecting the wrench
                    if (objRocket.obj[i] == objRocket.obj[9]) {
                    	
                    	//checks if your at max health
                    	if(hp == 200) {
                    		score += 100;
                    		
                    	//if not then add more health
                    	}else {
                            hp = objRocket.obj[i].getHP() + hp;
                            gp.boosted = true;
                            gp.boosttime = System.currentTimeMillis();
                    	}
                    }
                    // deletes the collectible
                	objRocket.obj[i] = null;
                	
                	//plays the respected sound
                	if(i == 9 && hp != 200) {
                		gp.playSE(9);
                	}else {
                		gp.playSE(8);
                	}
                 }
        		
        		//drwas the collectibles collision
//              g2.drawRect(objRocket.obj[i].getWorldX()+8, objRocket.obj[i].getWorldY()+5, 28, 28);
            }
        }
        
        // touches asteroids
       for(int i = 0; i<ast.asts.size(); i++) {
       	if(ast.asts.get(i)!= null) {
       		if(shipC.touches(ast.asts.get(i).getCAst()) && hp>25) {
       			//checks if the hp is 125 and if so plays the shield break sound
                if(hp == 125) {
                	gp.playSE(13);
                	shieldSound.hasPlayedShieldSound = false;
                }
                
                //subtracts 25 because of the hit
       			hp -= 25;

                // creates explosion object and adds to array
                Explosion exp = new Explosion(ast.asts.get(i).getX(), ast.asts.get(i).getY());
                exps.expsList.add(exp);

                // hides and removes asteroid
                ast.asts.get(i).hideAst();
                ast.astTime.add(System.currentTimeMillis());
                ast.asts.remove(i);
                i--;
                	
                //plays the explosion sound
        		gp.playSE(5);
        		
        		//if the ship touches an asteroid and its health is at 25
       		}else if(shipC.touches(ast.asts.get(i).getCAst()) && hp==25) {
       			
       			//if you have the angle powerup
                if (angel) {
                    hp += 25;
                    angel = false;
                    gp.playSE(9);
                    
                // if you dont have the angle powerup
                } else {
                    hp = 0;
                    gp.playSE(5);
                    gp.playSE(6);
                    gp.gameState = gp.gameOverState;
                }
       		}
       	}
       }
    }  
}


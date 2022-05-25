package rocketship;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.*;
import javax.imageio.ImageIO;
import collision.*;
import Main.*;
import Main.GamePanel.ast;
import Main.GamePanel.objRocket;
import Main.KeyHandler;
// import object.OBJ_Bullet;

public class rocketship {

    // basic rocketship vars
    public String direction;
    public int x, y;
    public int speed;
    public int score;
    public int hp = 100;
    public class bullets{
    	public bullet bullet;
    	
    	public void addBullet(bullet block) {
    		bulletArray.bullets.add(block);
        }

        public static void removeBullet(bullet block) {
        	bulletArray.bullets.remove(block);
        }
    }
    public Collision shipC;
    
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
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    // Bullet Stuff
    public class bulletArray{
    	public static ArrayList<bullet> bullets = new ArrayList<bullet>(); // arraylist of bullets
    }
    public void tick(Graphics2D g2) {
        for (int i = 0; i < bulletArray.bullets.size(); i++) {
        	bulletArray.bullets.get(i).tick(g2);
            if (bulletArray.bullets.get(i).getX() < 0) {
            	bulletArray.bullets.remove(i);
            }
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
        x = 25;
        y = 25;
        speed = 4;
        direction = "up";
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
        // Everything below is for 8 direction movement
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
        if (keyH.shotKeyPressed == true) {
            if (bulletArray.bullets.size() == 0 || bulletArray.bullets.get(bulletArray.bullets.size()-1).getTime() + 200 < System.currentTimeMillis()) {
            	bulletArray.bullets.add(new bullet(this.x+16, this.y+16, direction));
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

        // CROP IMAGE TO MAKE SIZING CORRECT
        
        //moves collision with rocket and displays it
        g2.setColor(Color.gray);
        shipC.setXCol(x+5);
        shipC.setYCol(y+9);
        //displays the ships collision box
//        shipC.render(g2);
        
        // renders walls
//        walls.wallCUp.render(g2);
//        walls.wallCDown.render(g2);
//        walls.wallCLeft.render(g2);
//        walls.wallCRight.render(g2);
        
        
        // teleportation if you touch a wall
        if(shipC.touches(walls.wallCUp)) {
        	y = 762-gp.tileSize;
        	shipC.setYCol(y);
        	g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        }else if(shipC.touches(walls.wallCDown)) {
        	y = 6;
        	shipC.setYCol(y);
        	g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        } else if(shipC.touches(walls.wallCLeft)) {
        	x = 762-gp.tileSize;
        	shipC.setXCol(x);
        	g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        }else if(shipC.touches(walls.wallCRight)) {
        	x = 6;
        	shipC.setXCol(x);
        	g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        }
        
        
        // picking up the collectibles
        for (int i = 0; i < objRocket.obj.length; i++) {
        	if(objRocket.obj[i]!= null) {
        		objRocket.obj[i].getCObj().setXCol(objRocket.obj[i].getWorldX()+8);
        		objRocket.obj[i].getCObj().setYCol(objRocket.obj[i].getWorldY()+5);
        		if(shipC.touches(objRocket.obj[i].getCObj())) {
                	System.out.println("touch obj");
                	score = objRocket.obj[i].getValue() + score;
                	objRocket.obj[i] = null;
                	System.out.println(score);
                    }
                	//g2.drawRect(objRocket.obj[i].getWorldX()+8, objRocket.obj[i].getWorldY()+5, 28, 28);
                }
        	}
        
        // touches asteroids
       for(int i = 0; i<ast.asts.size(); i++) {
       	if(ast.asts.get(i)!= null) {
       		if(shipC.touches(ast.asts.get(i).getCAst()) && hp>25) {
       			hp -= 25;
       			ast.astTime.add(System.currentTimeMillis());
        		ast.asts.remove(i);
       		}else if(shipC.touches(ast.asts.get(i).getCAst()) && hp==25) {
       			hp = 0;
       			gp.gameThread.stop();
       		}
       	}
       }
    }  
}


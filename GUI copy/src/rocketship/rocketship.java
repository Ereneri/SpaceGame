package rocketship;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.*;
import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;
// import object.OBJ_Bullet;
import rocketship.bullet;

public class rocketship {

    // basic rocketship vars
    public String direction;
    public int x, y;
    public int speed;
    public bullet bullet;
    public Collision shipC;
    public Collision wallCUp;
    public Collision wallCDown;
    public Collision wallCLeft;
    public Collision wallCRight;

    // initlize the game panel and keyhabndler
    GamePanel gp;
    KeyHandler keyH;

    // Vars for sprite image
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    // Bullet Stuff
    public ArrayList<bullet> bullets = new ArrayList<bullet>(); // arraylist of bullets
    public void tick() {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).tick();
            if (bullets.get(i).getX() < 0) {
                bullets.remove(i);
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
        setDefaultValues();
        getRocketImage();
        shipC = new Collision(x, y, gp.tileSize, gp.tileSize);
        wallCUp = new Collision(0, 0, 768, 5);
        wallCDown = new Collision(0, 763, 768, 5);
        wallCLeft = new Collision(0, 0, 5, 768);
        wallCRight = new Collision(763, 0, 5, 768);
    }

    public void addBullet(bullet block) {
        bullets.add(block);
    }

    public void removeBullet(bullet block) {
        bullets.remove(block);
    }
    
    // Resets the rocketship to default values
    public void setDefaultValues() {
        x = 100;
        y = 100;
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
            if (bullets.size() == 0 || bullets.get(bullets.size() - 1).getTime() + 200 < System.currentTimeMillis()) {
                bullets.add(new bullet(x, y, direction));
            }
        }
    }

    // takes the direction and draws the correct sprite image
    public void draw(Graphics2D g2) {
        // render bullets
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(g2);
            bullets.get(i).tick();
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
        // vizualize the hit box for the rocketship for collision detection
//        g2.setColor(Color.red);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        // draw the image with using the global x and y coordinates along with scaling from the gamepanel
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        // CROP IMAGE TO MAKE SIZING CORRECT
        
        //moves collision with rocket and displays it
        g2.setColor(Color.gray);
        shipC.setX(x);
        shipC.setY(y);
        shipC.render(g2);
        
        // renders walls
        wallCUp.render(g2);
        wallCDown.render(g2);
        wallCLeft.render(g2);
        wallCRight.render(g2);
        
        if(shipC.touches(wallCUp) || shipC.touches(wallCDown) || shipC.touches(wallCLeft) || shipC.touches(wallCRight)) {
        	System.out.println("touches");
        }
    }
}



package rocketship;

import Main.GamePanel;
import collision.Collision;
import object.metal;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class bullet {

    // image vars
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    // basic bullet vars
    private int x;
    private int y;
    private long lastTime;
    private int speed;
    private String direction;
    public class bulletC {
    	public static Collision bulletC;
    }
    public BufferedImage bulletImage = null;

    // constructor
    public bullet(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        speed = 5;
        this.lastTime = System.currentTimeMillis();
        getBulletImage();
        bulletC.bulletC = new Collision(x+16,  y+16, 16, 16);
    }

    // gets bullets collision box
    public Collision getBulletC () {
    	return bulletC.bulletC;
    }

    // loads bullet image into memory
    public void getBulletImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/object/bullet_up.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/object/bullet_upRight.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/object/bullet_down.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/object/bullet_downLeft.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/object/bullet_left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/object/bullet_upLeft.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/object/bullet_right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/object/bullet_downRight.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // updates the frame depending on direction and speed
    public void tick(Graphics2D g2) {
        switch(this.direction) {
            case "up":
                	y -= speed;
                	bulletC.bulletC.setYCol(y);
                	g2.drawImage(bulletImage,  bulletC.bulletC.getXCol(),  bulletC.bulletC.getYCol(), 16, 16, null);
                break;
            case "down":
                	y += speed;
                	bulletC.bulletC.setYCol(y);
                	g2.drawImage(bulletImage,  bulletC.bulletC.getXCol(),  bulletC.bulletC.getYCol(), 16, 16, null);
                break;
            case "left":
                	x -= speed;
                	bulletC.bulletC.setXCol(x);
                	g2.drawImage(bulletImage,  bulletC.bulletC.getXCol(),  bulletC.bulletC.getYCol(), 16, 16, null);
                break;
            case "right":
                	x += speed;
                	bulletC.bulletC.setXCol(x);
                	g2.drawImage(bulletImage,  bulletC.bulletC.getXCol(),  bulletC.bulletC.getYCol(), 16, 16, null);
                break;
            case "upRight":
                	x += speed;
                	bulletC.bulletC.setXCol(x);
                	y -= speed;
                	bulletC.bulletC.setYCol(y);
                	g2.drawImage(bulletImage,  bulletC.bulletC.getXCol(),  bulletC.bulletC.getYCol(), 16, 16, null);
                break;
            case "upLeft":
                	x -= speed;
                	bulletC.bulletC.setXCol(x);
                	y -= speed;
                	bulletC.bulletC.setYCol(y);
                	g2.drawImage(bulletImage,  bulletC.bulletC.getXCol(),  bulletC.bulletC.getYCol(), 16, 16, null);
                break;
            case "downRight":
                	x += speed;
                	bulletC.bulletC.setXCol(x);
                	y += speed;
                	bulletC.bulletC.setYCol(y);
                	g2.drawImage(bulletImage,  bulletC.bulletC.getXCol(),  bulletC.bulletC.getYCol(), 16, 16, null);
                break;
            case "downLeft":
                    x -= speed;                	
                    bulletC.bulletC.setXCol(x);
                	y += speed;                	
                	bulletC.bulletC.setYCol(y);
                	g2.drawImage(bulletImage,  bulletC.bulletC.getXCol(),  bulletC.bulletC.getYCol(), 16, 16, null);
                break;
        }
    }

    // draws the bullet
    public void draw(Graphics g) {
        switch(direction) {
            case "up":
                bulletImage = up1;
                break;
            case "down":
                bulletImage = down1;
                break;
            case "left":
                bulletImage = left1;
                break;
            case "right":
                bulletImage = right1;
                break;
            // 8 direction movement
            case "upRight":
                bulletImage = up2;
                break;
            case "downRight":
                bulletImage = right2;   
                break;
            case "upLeft":
                bulletImage = left2;
                break;
            case "downLeft":
                bulletImage = down2;
                break;
        }
        g.drawImage(bulletImage,  x+16,  y+16, 16, 16, null);
        g.drawRect( bulletC.bulletC.getXCol(),  bulletC.bulletC.getYCol(), 16, 16);
        
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public long getTime() {
        return lastTime;
    }

}

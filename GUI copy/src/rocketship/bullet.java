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
    public class cBox {
    	public static Collision bulletC;
    }
    public BufferedImage bulletImage = null;

    // constructor
    public bullet(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        speed = 15;
        this.lastTime = System.currentTimeMillis();
        getBulletImage();
        cBox.bulletC = new Collision(x+16,  y+16, 16, 16);
    }

    // gets bullets collision box
    public Collision getBulletC () {
    	return cBox.bulletC;
    }
    public void removeBulletC () {
    	cBox.bulletC.setXCol(-50);
    	cBox.bulletC.setYCol(-50);
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
                if(cBox.bulletC.getXCol() != -50) {
                	y -= speed;
                	cBox.bulletC.setYCol(y);
                }else {
                	g2.drawImage(bulletImage,  cBox.bulletC.getXCol(),  cBox.bulletC.getYCol(), 16, 16, null);
                }
                break;
            case "down":
                if(cBox.bulletC.getXCol() != -50) {
                	y += speed;
                	cBox.bulletC.setYCol(y);
                }
                break;
            case "left":
                if(cBox.bulletC.getXCol() != -50) {
                	x -= speed;
                	cBox.bulletC.setXCol(x);
                }
                break;
            case "right":
                if(cBox.bulletC.getXCol() != -50) {
                	x += speed;
                	cBox.bulletC.setXCol(x);
                }
                break;
            case "upRight":
                if(cBox.bulletC.getXCol() != -50) {
                	x += speed;
                	cBox.bulletC.setXCol(x);
                	y -= speed;
                	cBox.bulletC.setYCol(y);
                }
                break;
            case "upLeft":
                if(cBox.bulletC.getXCol() != -50) {
                	x -= speed;
                	cBox.bulletC.setXCol(x);
                	y -= speed;
                	cBox.bulletC.setYCol(y);
                }
                break;
            case "downRight":
                if(cBox.bulletC.getXCol() != -50) {
                	x += speed;
                	cBox.bulletC.setXCol(x);
                	y += speed;
                	cBox.bulletC.setYCol(y);
                }
                break;
            case "downLeft":
                if(cBox.bulletC.getXCol() != -50) {
                    x -= speed;                	
                	cBox.bulletC.setXCol(x);
                	y += speed;                	
                	cBox.bulletC.setYCol(y);
                }
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
        g.drawRect( cBox.bulletC.getXCol(),  cBox.bulletC.getYCol(), 16, 16);
        
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

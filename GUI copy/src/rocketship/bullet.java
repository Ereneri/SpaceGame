package rocketship;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class bullet {

    // image vars
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    // basic bullet vars
    private double x;
    private double y;
    private long lastTime;
    private int speed;
    private String direction;

    // constructor
    public bullet(double x, double y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        speed = 15;
        this.lastTime = System.currentTimeMillis();
        getBulletImage();
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
    public void tick() {
        switch(this.direction) {
            case "up":
                y -= speed;
                break;
            case "down":
                y += speed;
                break;
            case "left":
                x -= speed;
                break;
            case "right":
                x += speed;
                break;
            case "upRight":
                x += speed;
                y -= speed;
                break;
            case "upLeft":
                x -= speed;
                y -= speed;
                break;
            case "downRight":
                x += speed;
                y += speed;
                break;
            case "downLeft":
                x -= speed;
                y += speed;
                break;
        }
    }

    // draws the bullet
    public void draw(Graphics g) {
        BufferedImage bulletImage = null;
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
        g.drawImage(bulletImage, (int) x+16, (int) y+16, 16, 16, null);
        
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

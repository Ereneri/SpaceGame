package rocketship;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.*;
import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;

public class rocketship {

    public String direction;
    public int x, y;
    public int speed;

    GamePanel gp;
    KeyHandler keyH;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public rocketship(GamePanel gp) {
        this.gp = gp;
    }

    public rocketship(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getRocketImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "up";
    }

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

    public void update() {
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
    }

    public void draw(Graphics2D g2) {
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
        g2.setColor(Color.red);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}



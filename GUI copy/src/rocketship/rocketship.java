package rocketship;

import java.awt.image.BufferedImage
import java.io.BufferedInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.keyListener;

public class rocketship {
    public String direction;
    public int x, y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public rocketship() {
        x = 0;
        y = 0;
        speed = 5;
        direction = "up";
    }

    public void getRocketImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("GUI copy/src/ship/up.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("GUI copy/src/ship/upRight.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("GUI copy/src/ship/down.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("GUI copy/src/ship/downLeft.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("GUI copy/src/ship/left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("GUI copy/src/ship/upLeft.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("GUI copy/src/ship/right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("GUI copy/src/ship/downRight.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyListener.upPressed == True) {
            y -= speed;
            direction = "up";
        } else if (keyListener.downPressed == True) {
            y += speed;
            direction = "down";
        } else if (keyListener.leftPressed == True) {
            x -= speed;
            direction = "left";
        } else if (keyListener.rightPressed == True) {
            x += speed;
            direction = "right";
        }
    }

    public void render(Graphics2D g2) {
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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        } 
    }
}



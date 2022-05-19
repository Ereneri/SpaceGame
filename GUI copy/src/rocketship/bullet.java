package rocketship;

import Main.GamePanel;
import object.metal;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class bullet {

    private double x;
    private double y;
    private long lastTime;
    private String direction;

    BufferedImage bulletImage;

    public bullet(double x, double y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;

        this.lastTime = System.currentTimeMillis();

        try {
            bulletImage = ImageIO.read(getClass().getResourceAsStream("/object/bullet_up.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        y -= 15;
    }

    public void draw(Graphics g) {
        g.drawImage(bulletImage, (int) x, (int) y, 16, 16, null);
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

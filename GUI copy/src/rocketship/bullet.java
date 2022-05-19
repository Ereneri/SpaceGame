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

    BufferedImage bulletImage;

    public bullet(double x, double y) {
        this.x = x;
        this.y = y;

        try {
            bulletImage = ImageIO.read(getClass().getResourceAsStream("/object/bullet_up.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        y -= 10;
    }

    public void draw(Graphics g) {
        g.drawImage(bulletImage, (int) x, (int) y, null);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

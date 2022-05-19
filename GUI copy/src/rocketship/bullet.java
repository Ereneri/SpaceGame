package rocketship;

import Main.GamePanel;
import object.metal;

public class bullet extends rocketship{

    rocketship ship;
    
    public bullet(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction) {
        this.x = worldX;
        this.y = worldY;
        this.direction = direction;
    }

    public void update() {
        switch(direction) {
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
        }
    }
}

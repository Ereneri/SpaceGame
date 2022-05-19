package object;

import rocketship.bullet;
import Main.GamePanel;

public class OBJ_Bullet extends bullet{

    GamePanel gp;
    
    public OBJ_Bullet(GamePanel gp) {
        super(gp);
        this.gp = gp;

        speed = 5;
        getImage();
        
    }

    public void getImage() {
        up1 = setup("/object/bullet_up.png",gp.tileSize,gp.tileSize);
        down1 = setup("/object/bullet_down.png",gp.tileSize,gp.tileSize);
        left1 = setup("/object/bullet_left.png",gp.tileSize,gp.tileSize);
        right1 = setup("/object/bullet_right.png",gp.tileSize,gp.tileSize);
    }


}

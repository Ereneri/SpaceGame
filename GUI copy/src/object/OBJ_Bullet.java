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
        up = setup("/object/bullet_up.png",gp.tileSize,gp.tileSize);
        down = setup("/object/bullet_down.png",gp.tileSize,gp.tileSize);
        left = setup("/object/bullet_left.png",gp.tileSize,gp.tileSize);
        right = setup("/object/bullet_right.png",gp.tileSize,gp.tileSize);
    }


}

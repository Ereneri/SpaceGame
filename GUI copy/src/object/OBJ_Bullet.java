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


}

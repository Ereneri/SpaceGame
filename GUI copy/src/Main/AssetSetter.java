package Main;

import object.Gold;
import object.Iron;
import object.Silver;
import Main.GamePanel.objRocket;
import collision.Collision;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setGold() {
		objRocket.obj[0] = new Gold();
		objRocket.obj[0].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[0].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[0].objectC = new Collision(objRocket.obj[0].worldX + 8, objRocket.obj[0].worldY + 5, 28, 28);
		objRocket.obj[7] = new Gold();
		objRocket.obj[7].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[7].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[7].objectC = new Collision(objRocket.obj[7].worldX + 8, objRocket.obj[7].worldY + 5, 28, 28);
	}
	
	public void setSilver() {
		objRocket.obj[1] = new Silver();
		objRocket.obj[1].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[1].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[1].objectC = new Collision(objRocket.obj[1].worldX + 8, objRocket.obj[1].worldY + 5, 28, 28);
		objRocket.obj[2] = new Silver();
		objRocket.obj[2].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[2].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[2].objectC = new Collision(objRocket.obj[2].worldX + 8, objRocket.obj[2].worldY + 5, 28, 28);
		objRocket.obj[8] = new Silver();
		objRocket.obj[8].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[8].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[8].objectC = new Collision(objRocket.obj[8].worldX + 8, objRocket.obj[8].worldY + 5, 28, 28);
	}
		
	public void setIron() {
		objRocket.obj[3] = new Iron();
		objRocket.obj[3].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[3].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[3].objectC = new Collision(objRocket.obj[3].worldX + 8, objRocket.obj[3].worldY + 5, 28, 28);
		objRocket.obj[4] = new Iron();
		objRocket.obj[4].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[4].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[4].objectC = new Collision(objRocket.obj[4].worldX + 8, objRocket.obj[4].worldY + 5, 28, 28);
		objRocket.obj[5] = new Iron();
		objRocket.obj[5].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[5].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[5].objectC = new Collision(objRocket.obj[5].worldX + 8, objRocket.obj[5].worldY + 5, 28, 28);
		objRocket.obj[6] = new Iron();
		objRocket.obj[6].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[6].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[6].objectC = new Collision(objRocket.obj[6].worldX + 8, objRocket.obj[6].worldY + 5, 28, 28);
	}
}

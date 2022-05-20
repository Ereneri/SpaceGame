package Main;

import object.Gold;
import object.Iron;
import object.Silver;
import object.metal;
import object.metal.objCBox;
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
	}
	
	
	public void setSilver() {
		objRocket.obj[1] = new Silver();
		objRocket.obj[1].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[1].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[2] = new Silver();
		objRocket.obj[2].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[2].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
	}
		
	public void setIron() {
		objRocket.obj[3] = new Iron();
		objRocket.obj[3].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[3].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[4] = new Iron();
		objRocket.obj[4].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[4].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[5] = new Iron();
		objRocket.obj[5].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		objRocket.obj[5].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
	}
}

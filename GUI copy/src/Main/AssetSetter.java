package Main;

import object.Gold;
import object.Iron;
import object.Silver;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setGold() {
		gp.obj[0] = new Gold();
		gp.obj[0].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		gp.obj[0].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
	}
	
	
	public void setSilver() {
		gp.obj[1] = new Silver();
		gp.obj[1].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		gp.obj[1].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
		gp.obj[2] = new Silver();
		gp.obj[2].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		gp.obj[2].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
	}
		
	public void setIron() {
		gp.obj[3] = new Iron();
		gp.obj[3].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		gp.obj[3].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
		gp.obj[4] = new Iron();
		gp.obj[4].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		gp.obj[4].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
		gp.obj[5] = new Iron();
		gp.obj[5].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		gp.obj[5].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
	}
}

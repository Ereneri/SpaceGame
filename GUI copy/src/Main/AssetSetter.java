package Main;

import object.Gold;
import object.Iron;
import object.Silver;
import object.metal;

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
	}
		
	public void setIron() {
		gp.obj[2] = new Iron();
		gp.obj[2].worldX = (int)(Math.random() * 16 + 1) * gp.tileSize;
		gp.obj[2].worldY = (int)(Math.random() * 16 + 1) * gp.tileSize;
	}
}

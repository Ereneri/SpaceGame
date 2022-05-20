package object;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import Main.GamePanel;
import collision.*;

public class metal {
	public BufferedImage image;
	public String name;
	public boolean collision = false; 
	public int worldX, worldY;
	public int value;
	public static Collision objectC;

	
	public void draw(Graphics2D g2, GamePanel gp) {
		g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
//		g2.drawRect(worldX + 8, worldY + 5, 28, 28);
	}
	
	public Collision getCObj() {
		return this.objectC;
	}
	public int getWorldX() {
		return this.worldX;
	}
public int getWorldY() {
		return this.worldY;
	}
}

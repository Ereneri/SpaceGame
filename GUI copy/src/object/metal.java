package object;

import java.awt.image.BufferedImage;

import java.awt.Graphics2D;
import Main.GamePanel;
import collision.*;

public class metal {
	public BufferedImage image;
	public String name;
	public int worldX, worldY;
	public int value;
	public int hp;
	public Collision objectC;
	
	public int getValue() {
		return value;
	}
	
	public int getHP() {
		return hp;
	}
	
	public void draw(Graphics2D g2, GamePanel gp) {
		g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
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

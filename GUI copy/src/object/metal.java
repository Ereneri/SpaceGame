package object;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import Main.GamePanel;

public class metal {
	public BufferedImage image;
	public String name;
	public boolean collision = false; 
	public int worldX, worldY;
	public int value;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
	}
}

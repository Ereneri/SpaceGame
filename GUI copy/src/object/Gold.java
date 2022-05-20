package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import collision.Collision;

public class Gold extends metal{
	
	public Gold() {
		name = "Gold";
		value = 100;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/Gold.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		objCBox.objectC = new Collision(worldX + 8, worldY + 5, 28, 28);
	}
}

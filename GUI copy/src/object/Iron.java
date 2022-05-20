package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import collision.Collision;
import object.metal.objCBox;

public class Iron extends metal{
	
	public Iron() {
		name = "Iron";
		value = 25;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/Iron.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		objCBox.objectC = new Collision(worldX + 8, worldY + 5, 28, 28);
	}
}
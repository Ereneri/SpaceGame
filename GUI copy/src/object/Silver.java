package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import collision.Collision;
import object.metal.objCBox;

public class Silver extends metal{
	
	public Silver() {
		name = "Silver";
		value = 50;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/Silver.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		objCBox.objectC = new Collision(worldX + 8, worldY + 5, 28, 28);
	}
}

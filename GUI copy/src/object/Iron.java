package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Iron extends metal{
	
	public Iron() {
		name = "Iron";
		value = 25;
		
		//Places an image on object
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/Iron.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
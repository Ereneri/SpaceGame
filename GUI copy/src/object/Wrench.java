package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Wrench extends metal{
		
	public Wrench() {
		name = "Wrench";
		value = 0;
		hp = 25;
			
		//Places an image on object
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/wrench.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Gold extends metal{
	
	public Gold() {
		name = "Gold";
		value = 100;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/Gold.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Silver extends metal{
	
	public Silver() {
		int value = 50;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/Silver.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

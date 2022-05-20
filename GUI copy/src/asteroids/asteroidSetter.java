package asteroids;

import Main.GamePanel;

public class asteroidSetter {

	GamePanel gp;
	
	public asteroidSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void addAsteroids() {
		for(int i = 0; i<gp.numAsteroids; i++) {
			gp.asts[i] = new Asteroid((int)(Math.random()*gp.screenWidth+6), (int)(Math.random()*gp.screenHeight+6), (int)(Math.random()*4+1), (int)(Math.random()*4+1));
		}
	}
}

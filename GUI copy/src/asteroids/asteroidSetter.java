package asteroids;

import Main.GamePanel;
import Main.GamePanel.ast;

public class asteroidSetter {

	GamePanel gp;
	
	public asteroidSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void addAsteroids() {
		for(int i = 0; i<ast.numAsteroids; i++) {
			ast.asts[i] = new Asteroid((int)(Math.random()*700+6), (int)(Math.random()*700+6), (int)(Math.random()*4+1), (int)(Math.random()*4+1));
		}
	}
}

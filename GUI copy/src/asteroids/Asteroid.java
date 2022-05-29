package asteroids;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import collision.*;
import rocketship.rocketship.walls;
import Main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Asteroid {
	
	public int x;
	public int y;
	BufferedImage asteroid;
	public int xVelAst;
	public int yVelAst;
	private Collision c;
	public GamePanel gp;
	public int exploded;
	public boolean hide;


	//creates an asteroid object in a certain position and with certain speeds
	public Asteroid(int x, int y, int xVel, int yVel, GamePanel gp) {
		this.gp = gp;
		this.x=x;
		this.y=y;
		this.xVelAst=xVel;
		this.yVelAst=yVel;
		this.exploded = 0;
		this.hide = false;
		try {
			asteroid = ImageIO.read(getClass().getResourceAsStream("/asteroids/ast.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		c=new Collision(x+7,y+7,gp.tileSize-14, gp.tileSize-14);
	}

	public void hideAst() {
		this.hide = true;
	}
	
	// draws the asteroids image and collision box
	public void draw(Graphics2D g, GamePanel gp) {

		if (!hide) {
			g.drawImage(this.getImageAst(), this.x, this.y, gp.tileSize, gp.tileSize, null);
			g.setColor(Color.white);
			g.drawRect(this.x+5, this.y+5, gp.tileSize/2+8, gp.tileSize/2+8);
			// c.render(g);
		}
		
		g.drawImage(this.getImageAst(), this.x, this.y, gp.tileSize, gp.tileSize, null);
//		g.setColor(Color.white);
//		g.drawRect(this.x+7, this.y+7, gp.tileSize-14, gp.tileSize-14);
		// c.render(g);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	// checks if the asteroid is touching a wall and if so makes it bounce
	public void astTick() {
		
		if(c.touches(walls.wallCRight) || c.touches(walls.wallCLeft)) {
			this.setYVelAst();
			
		}
		this.setXAst(this.getYVelAst());

	
		if(c.touches(walls.wallCUp) || c.touches(walls.wallCDown)) {
			this.setXVelAst();
			
		}
		this.setYAst(this.getXVelAst());
		c.setXCol(x+5);
		c.setYCol(y+5);
 
    }
	
	
	public Collision getCAst() {
		return c;
	}
	public void setXAst(int x) {
		this.x += x;
	}
	public void setYAst(int y) {
		this.y += y;
	}
	public int getXAst() {
		return x;
	}
	public int getYAst() {
		return y;
	}
	public int getYVelAst() {
		return yVelAst;
	}
	public int getXVelAst() {
		return xVelAst;
	}
	public void setYVelAst() {
		this.yVelAst *= -1;
	}
	public void setXVelAst() {
		this.xVelAst *= -1;
	}
	public Image getImageAst() {
		return asteroid;
	}
}

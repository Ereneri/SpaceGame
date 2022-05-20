package asteroids;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import collision.*;
import java.awt.*;
import rocketship.*;
import rocketship.rocketship.walls;
import javax.swing.ImageIcon;
import javax.swing.*;
import Main.GamePanel;

public class Asteroid {
	
	public int x;
	public int y;
	public Image asteroid;
	public int xVelAst;
	public int yVelAst;
	private Collision c;


	public Asteroid(int x, int y, int xVel, int yVel) {
		this.x=x;
		this.y=y;
		this.xVelAst=xVel;
		this.yVelAst=yVel;
		asteroid = new ImageIcon("ast.png").getImage();
		c=new Collision(x,y,41,45);
	}
	
	public void draw(Graphics2D g, GamePanel gp) {
		
		g.drawImage(asteroid, x, y, 41, 45, null);
		g.drawRect(x, y, 41, 45);
	}
	
	public void astTick() {
		
		if(c.touchesRight(walls.wallCRight) || c.touchesLeft(walls.wallCLeft)) {
			this.setYVelAst();
			
		}
		this.setXAst(this.getYVelAst());

	
		if(c.touchesUp(walls.wallCUp) || c.touchesDown(walls.wallCDown)) {
			this.setXVelAst();
			
		}
		this.setYAst(this.getXVelAst());
		c.setXCol(x);
		c.setYCol(y);
 
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

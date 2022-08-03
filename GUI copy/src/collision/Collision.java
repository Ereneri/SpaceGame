package collision;

import java.awt.*;

public class Collision{
private int x;
private int y;
private int width;
private int height;

//creates a new collision box
public Collision(int x, int y, int width, int height) {
	this.x=x;
	this.y=y;
	this.width=width;
	this.height=height;
}

public Collision getCol() {
	return this;
}
public int getXCol() {
	return x;
}

public void setXCol(int x) {
	this.x = x;
}

public int getYCol() {
	return y;
}

public void setYCol(int y) {
	this.y = y;
}

public int getWidthCol() {
	return width;
}

public void setWidthCol(int width) {
	this.width = width;
}

public int getHeightCol() {
	return height;
}

public void setHeightCol(int height) {
	this.height = height;
}


//Displays where the collision box is on the screen
public void render(Graphics2D g) {
	g.drawRect(x, y, width, height);
}


//checks if two collisions are touching
public boolean touches(Collision c) {
	int tw = this.width;
    int th = this.height;
    int rw = c.width;
    int rh = c.height;
    if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
        return false;
    }
    int tx = this.x;
    int ty = this.y;
    int rx = c.x;
    int ry = c.y;
    rw += rx;
    rh += ry;
    tw += tx;
    th += ty;
    //      overflow || intersect
    return ((rw < rx || rw > tx) &&
            (rh < ry || rh > ty) &&
            (tw < tx || tw > rx) &&
            (th < ty || th > ry));
}


}


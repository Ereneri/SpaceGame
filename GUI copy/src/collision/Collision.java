package collision;

import java.awt.Graphics;

public class Collision {
private int x;
private int y;
private int width;
private int height;

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




public void render(Graphics g) {
	g.drawRect(x, y, width, height);
}


public boolean touchesUp(Collision c) {
	int left=x;
	int right= x+width;
	int top=y;
	int bottom=y+height;
	
	int cLeft=c.getXCol();
	int cRight= c.getXCol()+c.getWidthCol()+c.getWidthCol()+5;
	int cTop=c.getYCol();
	int cBottom=c.getYCol()+c.getHeightCol()+c.getHeightCol()+5;
	
	if(right>=cLeft&&right<=cRight&&bottom>=cTop&&bottom<=cBottom) {
		return true;
		
	}
	if(right>=cLeft&&right<=cRight&&top>=cTop&&top<=cBottom) {
		return true;
	}
	if(left>=cLeft&&left<=cRight&&bottom>=cTop&&bottom<=cBottom) {
		return true;
	}
	if(left>=cLeft&&left<=cRight&&top>=cTop&&top<=cBottom) {
		return true;
	}
	return false;
}
public boolean touchesDown(Collision c) {
	int left=x;
	int right= x+width;
	int top=y;
	int bottom=y+height;
	
	int cLeft=c.getXCol();
	int cRight= c.getXCol()+c.getWidthCol()+c.getWidthCol()+5;
	int cTop=c.getYCol();
	int cBottom=c.getYCol()+c.getHeightCol()+c.getHeightCol()+5;
	
	if(right>=cLeft&&right<=cRight&&bottom>=cTop&&bottom<=cBottom) {
		return true;
		
	}
	if(right>=cLeft&&right<=cRight&&top>=cTop&&top<=cBottom) {
		return true;
	}
	if(left>=cLeft&&left<=cRight&&bottom>=cTop&&bottom<=cBottom) {
		return true;
	}
	if(left>=cLeft&&left<=cRight&&top>=cTop&&top<=cBottom) {
		return true;
	}
	return false;
}
public boolean touchesLeft(Collision c) {
	int left=x;
	int right= x+width;
	int top=y;
	int bottom=y+height;
	
	int cLeft=c.getXCol();
	int cRight= c.getXCol()+c.getWidthCol()+c.getWidthCol()+5;
	int cTop=c.getYCol();
	int cBottom=c.getYCol()+c.getHeightCol()+c.getHeightCol()+5;
	
	if(right>=cLeft&&right<=cRight&&bottom>=cTop&&bottom<=cBottom) {
		return true;
		
	}
	if(right>=cLeft&&right<=cRight&&top>=cTop&&top<=cBottom) {
		return true;
	}
	if(left>=cLeft&&left<=cRight&&bottom>=cTop&&bottom<=cBottom) {
		return true;
	}
	if(left>=cLeft&&left<=cRight&&top>=cTop&&top<=cBottom) {
		return true;
	}
	return false;
}
public boolean touchesRight(Collision c) {
	int left=x;
	int right= x+width;
	int top=y;
	int bottom=y+height;
	
	int cLeft=c.getXCol();
	int cRight= c.getXCol()+c.getWidthCol()+c.getWidthCol()+5;
	int cTop=c.getYCol();
	int cBottom=c.getYCol()+c.getHeightCol()+c.getHeightCol()+5;
	
	if(right>=cLeft&&right<=cRight&&bottom>=cTop&&bottom<=cBottom) {
		return true;
		
	}
	if(right>=cLeft&&right<=cRight&&top>=cTop&&top<=cBottom) {
		return true;
	}
	if(left>=cLeft&&left<=cRight&&bottom>=cTop&&bottom<=cBottom) {
		return true;
	}
	if(left>=cLeft&&left<=cRight&&top>=cTop&&top<=cBottom) {
		return true;
	}
	return false;
}



}

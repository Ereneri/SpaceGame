package Main;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Point;

public class DragAndDrop {

	public static void main(String[] args) {
		
		new theFrame();

	}

}


@SuppressWarnings("serial")
class theFrame extends JFrame {
	
	DragPannel dragpanel = new DragPannel();
	
	theFrame() {
		
		this.add(dragpanel);
		this.setTitle("Drag & Drop demo");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		this.setVisible(true);
		
	}
	
}

@SuppressWarnings("serial")
class DragPannel extends JPanel {
	
	ImageIcon image = new ImageIcon("rocket.png");
	final int WIDTH = image.getIconWidth();
	final int HEIGHT = image.getIconHeight();
	Point imageCorner;
	Point prevPt;
	
	DragPannel() {
		
		imageCorner = new Point(0, 0);
		ClickListener clickListener = new ClickListener();
		DragListener dragListener = new DragListener();
		this.addMouseListener(clickListener);
		this.addMouseMotionListener(dragListener);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		image.paintIcon(this, g, (int)imageCorner.getX(), (int)imageCorner.getY());
	}
	
	private class ClickListener extends MouseAdapter {
		
		public void mousePressed(MouseEvent e) {
			prevPt = e.getPoint();
		}
	}
	
	private class DragListener extends MouseMotionAdapter {
		
		public void mouseDragged(MouseEvent e) {
			Point currentPoint = e.getPoint();
			
			imageCorner.translate(
					(int)(currentPoint.getX() - prevPt.getX()),
					(int)(currentPoint.getY() - prevPt.getY())
					);
			prevPt = currentPoint;
			repaint();
		}
	}
	
}

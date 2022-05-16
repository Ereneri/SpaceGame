package Main;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class mousListener {

	public static void main(String[] args) {
		
		new listener1();

	}

}

@SuppressWarnings("serial")
class listener1 extends JFrame implements MouseListener{
	
	JLabel label;
	
	
	listener1() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setLayout(null);
		
		label = new JLabel();
		label.setBounds(0, 0, 100, 100);
		label.setBackground(Color.red);
		label.setOpaque(true);
		
		label.addMouseListener(this);
		
		this.add(label);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// invoked when the mouse button has been clicked (pressed and released) on a component.
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// invoked when the mouse button has been pressed on a component.
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// invoked when the mouse button has been released on a component.
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// invoked when the mouse enters a component.
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// invoked when the mouse exits a component.
		
		
	}
	
}

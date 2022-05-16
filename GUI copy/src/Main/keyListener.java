package Main;

import javax.swing.*;
import java.awt.event.*;


public class keyListener {

	public static void main(String[] args) {
		
		new listener();

	}

}



@SuppressWarnings("serial")
class listener extends JFrame implements KeyListener{
	
	//JFrame frame = new JFrame();
	JLabel label;
	ImageIcon rocket;
	int xVel;
	int yVel;
	
	listener(){
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setLayout(null);
		
		this.setContentPane(new JLabel(new ImageIcon("Space.png")));
		
		this.addKeyListener(this);
		
		rocket = new ImageIcon("rocket.png");
		
		
		label = new JLabel();
		label.setBounds(0, 0, 100, 100);
		label.setIcon(rocket);
		//label.setBackground(Color.red);
		//label.setOpaque(true);
		
		this.add(label);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// keyTyped = Invoked when a key is typed. Uses KeyChar, char output.
		switch(e.getKeyChar()) {
			case 'a': label.setLocation(label.getX()-10, label.getY());
				break;
			case 'w': label.setLocation(label.getX(), label.getY()-10);
				break;
			case 's': label.setLocation(label.getX(), label.getY()+10);
				break;
			case 'd': label.setLocation(label.getX()+10, label.getY());
				break;
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// keyPressed = Invoked when physical key is pressed down. Uses KeyCode, int output
		switch(e.getKeyCode()) {
			case 37: label.setLocation(label.getX()-5, label.getY());
				break;
			case 38: label.setLocation(label.getX(), label.getY()-5);
				break;
			case 39: label.setLocation(label.getX()+5, label.getY());
				break;
			case 40: label.setLocation(label.getX(), label.getY()+5);
				break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// keyReleased = called whenever a button is released 
		//System.out.println("you released key char: " + e.getKeyChar());
		System.out.println("you realesed key code: " + e.getKeyCode());
		
	}
	
}

package ClassesFromVideo;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class twoDAnimations {

	public static void main(String[] args) {
		
		new MainFrame1();

	}
}

@SuppressWarnings("serial")
class MainFrame1 extends JFrame {
	
	MainPanel1 panel;
	
	MainFrame1() {
		
		panel = new MainPanel1();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
}

@SuppressWarnings("serial")
class MainPanel1 extends JPanel implements ActionListener{
	
	final int panel_WIDTH = 500;
	final int panel_HEIGHT = 500;
	Image rocket;
	Image backgroundImage;
	Timer timer;
	int xVelosity = 2;
	int yVelosity = 1;
	int x = 0;
	int y = 0;
	
	MainPanel1() {
		
		this.setPreferredSize(new Dimension(panel_WIDTH, panel_HEIGHT));
		
		rocket = new ImageIcon("rocket.png").getImage();
		backgroundImage = new ImageIcon("Space.png").getImage();
		
		timer = new Timer(10, this);
		timer.start();
		
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.drawImage(backgroundImage, 0, 0, null);
		g2D.drawImage(rocket, x, y, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(x >= panel_WIDTH - rocket.getWidth(null) || x < 0) {
			xVelosity *= -1;
		}
		x += xVelosity;
		
		
		if(y >= panel_HEIGHT - rocket.getHeight(null) || y < 0) {
			yVelosity *= -1;
		}
		y += yVelosity;
		
		repaint();
		
	}
	
}

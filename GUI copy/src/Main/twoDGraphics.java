package Main;

import javax.swing.*;
import java.awt.*;

public class twoDGraphics {

	public static void main(String[] args) {
		
		new mainFrame();

	}

}


@SuppressWarnings("serial")
class mainFrame extends JFrame {
	
	mainPanel panel;
	
	mainFrame(){
		
		panel = new mainPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	
	}
	
}

@SuppressWarnings("serial")
class mainPanel extends JPanel {
	
	mainPanel() {
		this.setPreferredSize(new Dimension(500, 500));
	}
	
	public void paint(Graphics g) {
		
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setStroke(new BasicStroke(5));
		g2D.setPaint(Color.blue);
		
		//g2D.drawLine(0, 0, 500, 500);
		
		g2D.drawRect(0, 0, 100, 200);
		g2D.fillRect(150, 0, 100, 200);
		
		g2D.drawOval(300, 0, 100, 100);
		g2D.fillOval(300, 125, 100, 100);
		
		g2D.drawArc(0, 225, 100, 100, 0, 180);
		g2D.drawArc(0, 250, 100, 100, 180, 180);
		
		g2D.fillArc(125, 225, 100, 100, 0, 180);
		g2D.fillArc(125, 250, 100, 100, 180, 180);
		
		int[] xPoints = {250, 350, 450};
		int[] yPoints = {400, 250, 400};
		g2D.drawPolygon(xPoints, yPoints, 3);
		
		g2D.setFont(new Font("", Font.BOLD, 20));
		g2D.drawString("you are a winner!", 25, 400);
	}
	
}

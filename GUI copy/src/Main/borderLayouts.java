package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class borderLayouts {

	public static void main(String[] args) {
		
		//Layout Manager = defines the natural layout for components within a container
		
		//3 common managers
		
		//BorderLayout =   A BorderLayout places components in five areas: NORTH,SOUTH,EAST,WEST,CENTER.
		//				   All extra space is placed in the center area.
		
		myFrame frame = new myFrame(500, 500);
		frame.setVisible(false);
		frame.setLayout(new BorderLayout(10, 10));
		frame.setVisible(true);
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		
		p1.setBackground(Color.red);
		p2.setBackground(Color.green);
		p3.setBackground(Color.yellow);
		p4.setBackground(Color.magenta);
		p5.setBackground(Color.blue);
		
		p1.setPreferredSize(new Dimension(100, 100));
		p2.setPreferredSize(new Dimension(100, 100));
		p3.setPreferredSize(new Dimension(100, 100));
		p4.setPreferredSize(new Dimension(100, 100));
		p5.setPreferredSize(new Dimension(100, 100));
		
		frame.add(p1, BorderLayout.NORTH);
		frame.add(p2, BorderLayout.WEST);
		frame.add(p3, BorderLayout.EAST);
		frame.add(p4, BorderLayout.SOUTH);
		frame.add(p5, BorderLayout.CENTER);

		
		
	}

}

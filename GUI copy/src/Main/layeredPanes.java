package Main;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.Color;

public class layeredPanes {

	public static void main(String[] args) {
		
		// JLayeredPane = Swing container that provides a third 
		//				  dimension for positioning components
		//				  ex. depth, z-index
		
		JLabel l1 = new JLabel();
		l1.setOpaque(true);
		l1.setBackground(Color.lightGray);
		l1.setBounds(50, 50, 200, 200);
		
		JLabel l2 = new JLabel();
		l2.setOpaque(true);
		l2.setBackground(Color.gray);
		l2.setBounds(100, 100, 200, 200);
		
		JLabel l3 = new JLabel();
		l3.setOpaque(true);
		l3.setBackground(Color.darkGray);
		l3.setBounds(150, 150, 200, 200);
		
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 500, 500);
		layeredPane.add(l1, Integer.valueOf(0));  //the layer said label is on
		layeredPane.add(l2, Integer.valueOf(1));
		layeredPane.add(l3, Integer.valueOf(2));
		
		
		myFrame frame = new myFrame(500, 500, "null");
		frame.add(layeredPane);
		
		
		frame.setVisible(true);
		



	}

}

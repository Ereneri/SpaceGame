package Main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;


public class flowLayout {

	public static void main(String[] args) {
		
		// LayoutManager = Defines a natural layout for components within a container
		
		// FlowLayout = places components in a row, sized at their preferred size.
		//				If the horizontal space in the container is too small,
		//				the FlowLayout class uses the next available row.
		
		myFrame frame = new myFrame(500, 500);
		frame.setVisible(false);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); //first number is horizontal spacing, second is vertical
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(250, 250));
		panel.setBackground(Color.lightGray);
		panel.setLayout(new FlowLayout());
		
		frame.add(new JButton("1"));
		frame.add(new JButton("2"));
		frame.add(new JButton("3"));
		frame.add(new JButton("4"));
		frame.add(new JButton("5"));
		frame.add(new JButton("6"));
		frame.add(new JButton("7"));
		frame.add(new JButton("8"));
		frame.add(new JButton("9"));
		frame.add(new JButton("10"));
		
	//	frame.add(panel);
		
		frame.setVisible(true);

	}

}

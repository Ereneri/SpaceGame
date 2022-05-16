package Main;

import javax.swing.JButton;
import java.awt.GridLayout;

public class gridLayout {

	public static void main(String[] args) {
		
		// LayoutManager = Defines a natural layout for components within a container
		
		// GridLayout = places components in a grid of cells.
		//				Each component takes all the available space within its cell,
		//				and each cell is the same size.
		
		myFrame frame = new myFrame(500, 500);
		frame.setVisible(false);
		
		frame.setLayout(new GridLayout(3, 3, 0, 0));   //ROWS by COLS and Horizontal/Vertical space
		
		frame.add(new JButton("1"));
		frame.add(new JButton("2"));
		frame.add(new JButton("3"));
		frame.add(new JButton("4"));
		frame.add(new JButton("5"));
		frame.add(new JButton("6"));
		frame.add(new JButton("7"));
		frame.add(new JButton("8"));
		frame.add(new JButton("9"));
		
		frame.setVisible(true);


	}

}

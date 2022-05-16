package Main;

import javax.swing.JLabel;
import javax.swing.JPanel;
/*
import javax.swing.border.Border;
import javax.swing.BorderFactory;
*/
import javax.swing.ImageIcon;
//import javax.swing.JFrame;
import java.awt.Color;
/*
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
*/

public class Panels {

	public static void main(String[] args) {
		
		//JPanel = GUI component that functions as a container to hold other components
		
		ImageIcon image = new ImageIcon("robot.jpg");
		
		JLabel lable = new JLabel();
		lable.setText("Hi!");
		lable.setIcon(image);
//		lable.setVerticalAlignment(JLabel.TOP);
//		lable.setHorizontalAlignment(JLabel.RIGHT);
		lable.setBounds(0, 0, 75, 75);

		
		
		JPanel redPanel = new JPanel();
		redPanel.setBackground(Color.red);
		redPanel.setBounds(0, 0, 250, 250);
		redPanel.setLayout(null  /* new BorderLayout() */);
		
		JPanel bluePanel = new JPanel();
		bluePanel.setBackground(Color.blue);
		bluePanel.setBounds(250, 0, 250, 250);
		bluePanel.setLayout(null  /* new BorderLayout() */);
		
		JPanel greenPanel = new JPanel();
		greenPanel.setBackground(Color.green);
		greenPanel.setBounds(0, 250, 500, 250);
		greenPanel.setLayout(null  /* new BorderLayout() */);
		
		
		
		myFrame frame = new myFrame(750, 750, "Null");
		redPanel.add(lable);
		frame.add(redPanel);
		frame.add(bluePanel);
		frame.add(greenPanel);
		frame.setVisible(true);
		

	}

}

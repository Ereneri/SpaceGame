package Main;

import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
//import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;

public class Lable {

	public static void main(String[] args) {
		
		//JLabel = GUI display area for a string of text, an image, or both
		
		ImageIcon robot = new ImageIcon("robot.jpg");
		Border border = BorderFactory.createLineBorder(Color.red, 3);
		
		JLabel lable = new JLabel();  //create a new label
		lable.setText("Bob");  //set text of label
		lable.setIcon(robot);
		lable.setHorizontalTextPosition(JLabel.CENTER);  //set text LEFT, RIGHT, or CENTER of imageIcon
		lable.setVerticalTextPosition(JLabel.TOP);       //set text TOP, BOTTOM, or CENTER of imageIcon
		lable.setForeground(Color.blue);				//set font color of text
		lable.setFont(new Font("MV Boli", Font.BOLD, 20));  //set font of text
		lable.setIconTextGap(5);						//set gap of text to image
		lable.setBackground(Color.green);				//set background color
		lable.setOpaque(true);
		lable.setBorder(border);
		lable.setVerticalAlignment(JLabel.CENTER);  //set vertical position of icon+text within label
		lable.setHorizontalAlignment(JLabel.CENTER);  //set horizontal position of icon+text within label
		//lable.setBounds(100, 0, 250, 250);			//set x, y position within frame as well as dimensions
		
		
		
		myFrame frame = new myFrame();      
		//frame.setLayout(null);
		frame.add(lable);  //adds label to the frame
		frame.pack(); // automatically resize the frame to the label   NOTE: only use pack after all the components have been added

	}

}

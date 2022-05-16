package Main;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class optionPane {

	public static void main(String[] args) {
		
		// JOptionPane =  pop up a standard dialog box that prompts users 
		//				  for a value or informs then of something
		
		JOptionPane.showMessageDialog(null, "This is some useless info", "title", JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(null, "Here is more useless info", "title", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "do you want to see useless info", "title", JOptionPane.QUESTION_MESSAGE);
		JOptionPane.showMessageDialog(null, "Warning: useless info", "title", JOptionPane.WARNING_MESSAGE);
		JOptionPane.showMessageDialog(null, "Error: useless info", "title", JOptionPane.ERROR_MESSAGE);

		JOptionPane.showConfirmDialog(null, "do you like Pizza?", "Pizza", JOptionPane.YES_NO_CANCEL_OPTION);  //returns yes:0  no:1  cancel:2  x:-1
		
		JOptionPane.showInputDialog("what is your favorite food?");  //can store the answer as a string
		
		String[] responces = {"No, you're awesome!", "What are you talkng about", "Yes, I know"};
		ImageIcon image = new ImageIcon("robot.jpg");
		JOptionPane.showOptionDialog(null, 
				"you are awesome", 
				"secret message", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, 
				image, 
				responces, 
				0);

	}

}

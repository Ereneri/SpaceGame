package Main;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Font;

public class checkBox {

	public static void main(String[] args) {
		
		// JCheckBox = component that can be selected and deselected
		
		new Box();

	}

}



class Box implements ActionListener{

	JFrame frame;
	JButton button;
	JCheckBox checkBox;
	ImageIcon check;
	ImageIcon x;
	
	Box(){
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		check = new ImageIcon("checkmark.png");
		x = new ImageIcon("x.png");
		
		button = new JButton("submit");
		button.addActionListener(this);
		
		
		checkBox = new JCheckBox();
		checkBox.setText("Im not a robot");
		checkBox.setFont(new Font(null,Font.BOLD,30));
		checkBox.setIcon(x);
		checkBox.setSelectedIcon(check);
		
		frame.add(button);
		frame.add(checkBox);
		frame.pack();
		frame .setVisible(true);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
			System.out.println(checkBox.isSelected());
		}
		
	}
	
}

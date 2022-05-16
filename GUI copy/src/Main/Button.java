package Main;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Button extends Buttons implements ActionListener{
	
	private JButton button;
	private JLabel label;
	private int count = 0;

	Button() {
		
		//JButton = a button that performs an action when clicked on
		
		ImageIcon icon = new ImageIcon("robot.jpg");
		
		label = new JLabel();
		label.setText("number of clicks: " + count);
		label.setBounds(155, 115, 200, 200);
		
		
		button = new JButton();
		button.setBounds(120, 50, 200, 150);
		button.addActionListener(this);
//or do:    button.addActionListener(e -> system.out.print("click"));
//Make sure then to delete "implements ActionListener" and the "actionPerformed" method
		button.setText("Click Me!");
		//button.setFocusable(false);
		button.setIcon(icon);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setFont(new Font("Comic Sans", Font.BOLD, 15));
		button.setForeground(Color.cyan);
		button.setBackground(Color.green);
		
		
		myFrame frame = new myFrame(500, 500, "null");
		frame.add(button);
		frame.add(label);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
			System.out.println("click");
			count++;
			label.setText("number of clicks: " + count);
		}
		
	}

}

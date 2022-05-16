package Main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpeningNewWindow {

	public static void main(String[] args) {
		
		new LaunchPage();

	}

}

class LaunchPage implements ActionListener {
	
	JFrame frame = new JFrame();
	JButton button = new JButton("New Window");
	
	LaunchPage(){
		
		button.setBounds(100, 160, 200, 40);
		button.addActionListener(this);
		
		frame.add(button);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
			frame.dispose();
			new NewWindow();
		}
	}
	
}

class NewWindow {
	
	JFrame frame = new JFrame();
	JLabel label = new JLabel("Hello!");
	
	NewWindow() {
		
		label.setBounds(0, 0, 100, 50);
		label.setFont(new Font(null, Font.PLAIN, 25));
		
		frame.add(label);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}



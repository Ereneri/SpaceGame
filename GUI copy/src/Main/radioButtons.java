package Main;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class radioButtons {

	public static void main(String[] args) {
		
		// JRadioButton =  one or more buttons in a grouping in which only one my be selected per group
		
		new radioButtonFrame();

	}

}



@SuppressWarnings("serial")
class radioButtonFrame extends JFrame implements ActionListener{

	JFrame frame;
	JRadioButton o1;
	JRadioButton o2;
	JRadioButton o3;
	
	radioButtonFrame(){
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		o1 = new JRadioButton("pizza");
		o2 = new JRadioButton("corn dog");
		o3 = new JRadioButton("cake");
		
		ButtonGroup group = new ButtonGroup();
		group.add(o1);
		group.add(o2);
		group.add(o3);
		
		o1.addActionListener(this);
		o2.addActionListener(this);
		o3.addActionListener(this);
		
		frame.add(o1);
		frame.add(o2);
		frame.add(o3);
		frame.pack();
		frame .setVisible(true);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == o1) {
			System.out.println("you chose pizza");
		}else if(e.getSource() == o2) {
			System.out.println("you chose corn dog");
		}else if(e.getSource() == o3) {
			System.out.println("you chose corn dog");
		}
		
	}
	
}

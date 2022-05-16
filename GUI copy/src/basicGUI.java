//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//public class basicGUI implements ActionListener{
//	
//	private int count = 0;
//	private JLabel label;
//	private JFrame frame;
//	private JPanel panel;
//	
//	
//	public basicGUI() {
//		
//		frame = new JFrame();
//		
//		JButton button = new JButton("Click Me!");
//		button.addActionListener(this);
//		
//		label = new JLabel("Number of clicks: 0");
//		
//		panel = new JPanel();
//		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//		panel.setLayout(new GridLayout(0, 1));
//		panel.add(button);
//		panel.add(label);
//		
//		frame.add(panel, BorderLayout.CENTER);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setTitle("Our GUI");
//		frame.pack();
//		frame.setVisible(true);
//	}
//
//	public static void main(String[] args) {
//		new basicGUI();
//
//	}
//
//}

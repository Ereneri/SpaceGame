package Main;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class myFrame extends JFrame{
	
	//default constructor
	myFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //lets us close the this
		this.setSize(500, 500);      //sets the this size
		this.setVisible(true);       //sets the this to be visible
		
		ImageIcon image = new ImageIcon("robot.jpg");  //creates an image icon
		this.setIconImage(image.getImage());    //sets the this icon to image
		this.getContentPane().setBackground(new Color(150, 220, 237) /*Color.green*/);   //change color of background
	}
	
	//controllable x, y
	myFrame(int x, int y){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //lets us close the this
		this.setSize(x, y);      //sets the this size
		this.setVisible(true);       //sets the this to be visible
		
		ImageIcon image = new ImageIcon("robot.jpg");  //creates an image icon
		this.setIconImage(image.getImage());    //sets the this icon to image
	}
	
	//controllable x, y and no layout
	myFrame(int x, int y, String s){
		if(s.equals("null")) {
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //lets us close the this
			this.setLayout(null);
			this.setSize(x, y);      //sets the this size
			
			ImageIcon image = new ImageIcon("robot.jpg");  //creates an image icon
			this.setIconImage(image.getImage());    //sets the this icon to image
			this.getContentPane().setBackground(Color.white);   //change color of background
		}else {
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //lets us close the this
			this.setSize(x, y);      //sets the this size
			
			ImageIcon image = new ImageIcon("robot.jpg");  //creates an image icon
			this.setIconImage(image.getImage());    //sets the this icon to image
			this.getContentPane().setBackground(new Color(150, 220, 237) /*Color.green*/);   //change color of background
		}
		
	}

}

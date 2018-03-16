/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: GUI application Administrator first page.
 * 
 * 
 */	

package ie.lyit.code;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Admin1 extends JFrame {
	
	
	//instance fields
	
	//title
	//label
	private JLabel titleLabel = new JLabel("Simple Shopping Service");
	//font
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
	
	//panel
	private JPanel northPanel;
	
	//constructor
	public Admin1() {
		
		//north panel
		northPanel = new JPanel();
		northPanel.add(titleLabel);
		titleLabel.setFont(titleFont);
		
		//add to frame
		add(northPanel, BorderLayout.NORTH);
		
	}
	
	//draw GUI
	public static void drawAdmin1() {
		
		Admin1 a1 = new Admin1();
		a1.setTitle("Administrator");		
		//op.pack();
		a1.setSize(550, 400);
		a1.setLocationRelativeTo(null);
		a1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a1.setVisible(true);
		a1.requestFocusInWindow(true);
	}
	
	//tester
	//main
	public static void main(String[] args){
		
		drawAdmin1();
		
	}//end of main method

}

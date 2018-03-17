/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: GUI application Administrator first page.
 * 
 * 
 */	

package ie.lyit.code;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Admin1 extends JFrame {
	
	
	//instance fields
	
	//title
	//label
	private JLabel titleLabel = new JLabel("Simple Shopping Service");
	//font
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
	private Font generalFont = new Font("SanSerif", Font.BOLD, 14);
	
	//panels
	private JPanel northPanel, centerPanel;
	//blank panels
	private JPanel eastPanel, westPanel;
	
	//label
	private JLabel detailLabel;
	
	//text fields
	private JTextField usernameTf, passwordTf;
	//blank text fields
	private JLabel blanklabel1 = new JLabel("                              "
			+ "                ");
	private JLabel blanklabel2 = new JLabel("                              "
			+ "                ");
	
	//button
	private JButton loginBtn;
	
	//constructor
	public Admin1() {
		
		//north panel
		northPanel = new JPanel();
		northPanel.add(titleLabel);
		titleLabel.setFont(titleFont);
		
		//add to frame
		add(northPanel, BorderLayout.NORTH);
		
		
		//center panel
		centerPanel = new JPanel(new GridLayout(7, 1));
		//create label
		detailLabel = new JLabel("<html><u>Enter Administrator details<u/><html/>");
		//set font and alignment
		detailLabel.setFont(generalFont);
		detailLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//add to panel
		centerPanel.add(detailLabel);
		centerPanel.add(new JLabel());//blank space
		
		//add panel to frame
		add(centerPanel, BorderLayout.CENTER);
		
		//create text fields
		usernameTf = new JTextField("Enter Username");
		passwordTf = new JTextField("Enter Password");

		//add to panel
		centerPanel.add(usernameTf);
		centerPanel.add(new JLabel());//blank space
		centerPanel.add(passwordTf);
		
		
		//create login button
		loginBtn = new JButton("Login");
		
		//add to panel
		centerPanel.add(new JLabel());//blank space
		centerPanel.add(loginBtn);
		
		
		//create blank panels
		eastPanel = new JPanel();
		westPanel = new JPanel();
		
		//add blank labels to the blank panels
		eastPanel.add(blanklabel1);
		westPanel.add(blanklabel2);
		//add panels to frame
		add(eastPanel, BorderLayout.EAST);
		add(westPanel, BorderLayout.WEST);
		
		
	}
	
	
	
	//draw GUI
	public static void drawAdmin1() {
		
		Admin1 a1 = new Admin1();
		a1.setTitle("Administrator");		
		//op.pack();
		a1.setSize(500, 300);
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

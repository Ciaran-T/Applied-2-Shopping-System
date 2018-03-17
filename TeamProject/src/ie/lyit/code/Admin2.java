/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: GUI application Administrator second page.
 * 				Methods:
 * 						- Draw GUI
 * 
 * 
 */	


package ie.lyit.code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Admin2 extends JFrame {
	
	//instance fields
	//panels
	private JPanel centerPanel, northPanel;
	private AdminPanelBuilder apb1, apb2;
	//panel type
	private String add = "add";
	private String remove = "remove";
	
	//labels
	private JLabel titleLabel = new JLabel("Simple Shopping System");
	
	
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
	
	//constructor
	public Admin2() {
		
		//create north title panel
		northPanel = new JPanel();
		//create title label and add to north panel
		titleLabel.setFont(titleFont);
		northPanel.add(titleLabel);
		//set alignment and border of label
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setBorder(new LineBorder(Color.BLACK, 1));
		//add to frame
		add(northPanel, BorderLayout.NORTH);
		
		centerPanel = new JPanel(new GridLayout(2, 1, 0, 10));
		//create panels
		apb1 = new AdminPanelBuilder(add);
		apb2 = new AdminPanelBuilder(remove);
		
		//add panels to center panel
		centerPanel.add(apb1);
		centerPanel.add(apb2);
		
		//add center panel to frame
		add(centerPanel, BorderLayout.CENTER);
	}
	
	
	//draw GUI
	public static void drawAdmin2() {
		
		Admin2 a2 = new Admin2();
		a2.setTitle("Administrator");		
		//a2.pack();
		a2.setSize(550, 400);
		a2.setLocationRelativeTo(null);
		a2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a2.setVisible(true);
		a2.requestFocusInWindow(true);
	}
	
	//main
	public static void main(String[] args) {
		drawAdmin2();
	}

}

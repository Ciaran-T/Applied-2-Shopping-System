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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Admin2 extends JFrame {
	
	//instance fields
	//panels
	private JPanel centerPanel, centerTopPanel, centerBottomPanel;
	private JPanel centerTopSouthPanel, centerBottomSouthPanel;
	private JPanel northPanel, southPanel;
	private AdminPanelBuilder apb1, apb2;
	//panel type
	private String add = "add";
	private String remove = "remove";
	
	//labels
	private JLabel titleLabel = new JLabel("Simple Shopping System");
	
	//title font
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
	
	//buttons
	private JButton backBtn, deliveryScheduleBtn;
	private JButton addBtn, addAllBtn;
	private JButton removeBtn, removeAllBtn;
	
	//constructor
	public Admin2() {
		
		//north panel
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
		
		
		//center panel
		//create center panel
		centerPanel = new JPanel(new GridLayout(3, 1, 0, 10));
		//create top center panel
		centerTopPanel = new JPanel(new BorderLayout());
		//create bottom center panel
		centerBottomPanel = new JPanel(new BorderLayout());
		
		//create panel
		apb1 = new AdminPanelBuilder(add);
		//add to top center panel
		centerTopPanel.add(apb1);
		//create panel
		apb2 = new AdminPanelBuilder(remove);
		//add to bottom center panel
		centerBottomPanel.add(apb2);
		
		//add panels to center panel
		centerPanel.add(centerTopPanel);
		centerPanel.add(centerBottomPanel);
		//add center panel to frame
		add(centerPanel, BorderLayout.CENTER);
		
		//south panel
		//create south panel
		southPanel = new JPanel(new GridLayout(1, 2));
		//back button
		backBtn = new JButton("Back/Logout");
		//add button to south panel
		southPanel.add(backBtn);
		
		//delivery button
		deliveryScheduleBtn = new JButton("Go to Delivery Schedule");
		//add button to panel
		southPanel.add(deliveryScheduleBtn);
		
		//add panel to frame
		add(southPanel, BorderLayout.SOUTH);
		
		
		//create add buttons
		addBtn = new JButton("Add");
		addAllBtn = new JButton("Add All");
		//create panel
		centerTopSouthPanel = new JPanel(new GridLayout(1, 4));
		//add to panel
		centerTopPanel.add(centerTopSouthPanel, BorderLayout.SOUTH);
		//add buttons to panel
		centerTopSouthPanel.add(addBtn);
		centerTopSouthPanel.add(addAllBtn);
		
		//create remove buttons
		removeBtn = new JButton("Remove");
		removeAllBtn = new JButton("Remove All");
		//create panel
		centerBottomSouthPanel = new JPanel(new GridLayout(1, 4));
		//add to panel
		centerBottomPanel.add(centerBottomSouthPanel, BorderLayout.SOUTH);
		centerBottomSouthPanel.add(removeBtn);
		centerBottomSouthPanel.add(removeAllBtn);

		
		
	}
	
	
	//draw GUI
	public static void drawAdmin2() {
		
		Admin2 a2 = new Admin2();
		a2.setTitle("Administrator");		
		a2.pack();
		//a2.setSize(550, 400);
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

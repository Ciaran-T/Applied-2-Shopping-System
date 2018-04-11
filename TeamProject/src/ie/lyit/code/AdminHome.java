/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: GUI application Admin Home page.
 * 
 * 
 * 
 */

package ie.lyit.code;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminHome extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//instance fields
	private JPanel northPanel, centerPanel, innerCenterPanel, southPanel;
	
	//labels
	private JLabel titleLabel = new JLabel("Simple Shopping System");
	private JLabel detailLabel;
	
	//font
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
	private Font generalFont = new Font("SanSerif", Font.BOLD, 22);
	
	//button
	private JButton backBtn, exitBtn;
	private JButton productBtn, customerBtn;
	
	//constructor
	public AdminHome() {
		
		//title panel
		northPanel = new JPanel();
		
		titleLabel.setFont(titleFont);
		
		northPanel.add(titleLabel);
		
		add(northPanel, BorderLayout.NORTH);
		
		
		
		//south panel
		southPanel = new JPanel(new FlowLayout(1, 240, 10));
		
		backBtn = new JButton("Back/Logout");
		backBtn.setFont(generalFont);
		
		exitBtn = new JButton("Exit Application");
		exitBtn.setFont(generalFont);
		
		southPanel.add(backBtn);
		southPanel.add(exitBtn);
		
		add(southPanel, BorderLayout.SOUTH);
		
		
		//center panel
		centerPanel = new JPanel(new GridLayout(2, 1, 30, 30));
		
		
		detailLabel = new JLabel("What would you like to do?");
		detailLabel.setFont(generalFont);
		detailLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//inner panel
		innerCenterPanel = new JPanel(new FlowLayout(1, 240, 10));
		
		productBtn = new JButton("Maintain Products");
		productBtn.setFont(generalFont);
		customerBtn = new JButton("Maintain Customers");
		customerBtn.setFont(generalFont);
		
		innerCenterPanel.add(productBtn);
		innerCenterPanel.add(customerBtn);
		
		centerPanel.add(detailLabel);
		centerPanel.add(innerCenterPanel);
		
		add(centerPanel, BorderLayout.CENTER);
		
		
		
		
		
		
		
		
		
		//add listeners
		backBtn.addActionListener(new ActionListenerClass());
		exitBtn.addActionListener(new ActionListenerClass());
		
		productBtn.addActionListener(new ActionListenerClass());
		customerBtn.addActionListener(new ActionListenerClass());
		
		
		//disable resizing
		setResizable(false);
	}
	
	
	
	
	//inner action listener
	public class ActionListenerClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object event = e.getSource();

			//if event equals back button
			if(event == backBtn) {
				
				//open login page
				Admin1.drawAdmin1();
				
				//dispose this page
				dispose();
			}
			//if exit button
			else if(event == exitBtn) {
				
				//dispose this page
				dispose();
			}
			else if(event == productBtn) {
				
				//open admin2
				Admin2.drawAdmin2();
				
				//dispose this page
				dispose();
			}
			else if(event == customerBtn) {
				
				//open admin3
				Admin3.drawAdmin3();
				
				//dispose this page
				dispose();
			}
		}
	}
	
	
	
	
	
	//draw GUI
 	public static void drawAdminHome() {
		
		AdminHome ah = new AdminHome();
		ah.setTitle("Administrator Home");		
		//a2.pack();
		ah.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		ah.setLocationRelativeTo(null);
		ah.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ah.setVisible(true);
		ah.requestFocusInWindow(true);
	}
	
 	//main
	public static void main(String[] args) {
		
		drawAdminHome();

	}

}

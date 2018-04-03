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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminHome extends JFrame{

	//instance fields
	private JPanel northPanel, centerPanel, southPanel;
	
	//labels
	private JLabel titleLabel = new JLabel("Simple Shopping System");
	
	//font
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
	private Font generalFont = new Font("SanSerif", Font.BOLD, 22);
	
	//button
	private JButton backBtn, exitBtn;
	
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
		
		
		
		
		
		
		
		//add listeners
		backBtn.addActionListener(new ActionListenerClass());
		exitBtn.addActionListener(new ActionListenerClass());
		
		
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
				
				//dispose this page
				dispose();
				
				//open login page
				Admin1.drawAdmin1();
			}
			//if exit button
			else if(event == exitBtn) {
				
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

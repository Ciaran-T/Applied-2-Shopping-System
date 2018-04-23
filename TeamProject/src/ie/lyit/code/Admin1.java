/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: GUI application Administrator first page.
 * 				Classes:
 * 						- Anonymous inner Action Listener Class
 * 						- Inner Focus Listener Class
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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ie.lyit.data.Account;
import jdbc.DBConnector;

public class Admin1 extends JFrame {
	
	
	//instance fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//title
	//label
	private JLabel titleLabel = new JLabel("Simple Shopping Service");
	//font
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
	private Font generalFont = new Font("SanSerif", Font.BOLD, 22);
	
	//panels
	private JPanel northPanel, centerPanel, southPanel;
	//blank panels
	private JPanel eastPanel, westPanel;
	
	//label
	private JLabel detailLabel;
	
	//text fields
	private JTextField usernameTf;
	private JPasswordField passwordTf;
	//blank text fields
	private JLabel blanklabel1 = new JLabel("                              "
			+ "                                                            "
			+ "                                          ");
	private JLabel blanklabel2 = new JLabel("                              "
			+ "                                                            "
			+ "                                          ");
	
	//button
	private JButton loginBtn, backBtn, exitBtn;
	
	//constructor
	public Admin1() {
		
		//north panel
		northPanel = new JPanel();
		northPanel.add(titleLabel);
		titleLabel.setFont(titleFont);
		
		//add to frame
		add(northPanel, BorderLayout.NORTH);
		
		
		//center panel
		centerPanel = new JPanel(new GridLayout(11, 1));
		//create label
		centerPanel.add(new JLabel());//blank space
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
		usernameTf = new JTextField("Administrator");
		usernameTf.setFont(generalFont);
		usernameTf.setEditable(false);
		passwordTf = new JPasswordField("Enter Password");
		passwordTf.setFont(generalFont);

		//add to panel
		centerPanel.add(usernameTf);
		centerPanel.add(new JLabel());//blank space
		centerPanel.add(passwordTf);
		
		
		//create login button
		loginBtn = new JButton("Login");
		loginBtn.setFont(generalFont);
		
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
		
		
		//south panel
		southPanel = new JPanel(new FlowLayout(1, 240, 10));
		exitBtn = new JButton("Exit Application");
		exitBtn.setFont(generalFont);
		
		backBtn = new JButton("Back");
		backBtn.setFont(generalFont);
		
		southPanel.add(backBtn);
		southPanel.add(exitBtn);
		
		add(southPanel, BorderLayout.SOUTH);
		
		setResizable(false);
		
		//create focus listener
		MyListenerClass listener = new MyListenerClass();
		//add focus listeners to text fields
		usernameTf.addFocusListener(listener);
		passwordTf.addFocusListener(listener);
		
		backBtn.addActionListener(new ActionListenerClass());
		exitBtn.addActionListener(new ActionListenerClass());
		
		//add anonymous listener on login button
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!(usernameTf.getText().equalsIgnoreCase("Enter Username") || 
						passwordTf.getText().equalsIgnoreCase("Enter Password") ||
						usernameTf.getText().equalsIgnoreCase("") || 
						passwordTf.getText().equalsIgnoreCase(""))) {
					
					Account a = DBConnector.readAccountByName(usernameTf.getText());
					
					//if account exists
					if(!(a == null)) {
						
						//validate password
						if(a.getPassword().equals(passwordTf.getText())) {

							JOptionPane.showMessageDialog(null, "Welcome", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

							//dispose admin1 page
							dispose();
							
							//open admin2 page
							AdminHome.drawAdminHome();
						}
						//else password doesn't exist
						else if(!(a.getPassword().equals(passwordTf.getText()))) {
							JOptionPane.showMessageDialog(null, "Please enter correct password", "Wrong Password", JOptionPane.INFORMATION_MESSAGE);
							passwordTf.setText(null);
						}
					}
					//if details are wrong
					else if(a == null) {
						JOptionPane.showMessageDialog(null, "Email not found in database", "Enter correct email", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				//if no details entered
				else{
					JOptionPane.showMessageDialog(null, "Enter Administrator email and password to sign in", "Wrong details", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		
	}//end constructor
	
	
	//inner action listener
	public class ActionListenerClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object event = e.getSource();

			//if event equals back button
			if(event == backBtn) {
				
				//dispose this page
				dispose();
				
				//open home page
				HomePage.drawHome();
			}
			
			//if event equal exit button
			else if(event == exitBtn) {
				
				dispose();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	//inner FocusListener
	private class MyListenerClass implements FocusListener{


		@Override
		public void focusGained(FocusEvent f) {

			//get source of event
			Object event = f.getSource();

			/* if event equals any of the listeners
			 * 		if the text field contains the original text
			 * 			then set Text to NULL
			 * 
			 */

			if(event == usernameTf) {
				if(usernameTf.getText().equalsIgnoreCase("Enter Username")) {
					usernameTf.setText(null);
				}
			}
			else if(event == passwordTf) {
				if(passwordTf.getText().equalsIgnoreCase("Enter Password")) {
					passwordTf.setText(null);

				}

			}//end of if-else ladder

		}//end of method


		@Override
		public void focusLost(FocusEvent f){

			//get source of event
			Object event = f.getSource();

			
			/* if Focus is lost and no text was entered
			 * 		set text to the original text
			 * 
			 */
			if(event == usernameTf){
				if(usernameTf.getText() == null || usernameTf.getText().equals("")){
					usernameTf.setText("Enter Username");

				}
			}
			else if(event == passwordTf){
				if(passwordTf.getText() == null || passwordTf.getText().equals("")){
					passwordTf.setText("Enter Password");

				}
			}

		}//end of method
	}//end of inner class
		
		
	
	//draw GUI
	public static void drawAdmin1() {
		
		Admin1 a1 = new Admin1();
		a1.setTitle("Administrator");		
		//op.pack();
		a1.setSize(Toolkit.getDefaultToolkit().getScreenSize());
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

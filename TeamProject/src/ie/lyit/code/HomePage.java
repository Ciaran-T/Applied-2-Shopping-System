/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: GUI application home page.
 * 		 Methods:
 * 		 		- Draw GUI.
 * 		 
 * 		 Classes:
 * 				- Action Listener
 * 				- Focus Listener
*/


package ie.lyit.code;

import jdbc.DBConnector;
import ie.lyit.data.Account;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JFrame{
	/**
	 * Auto Generated
	 */
	private static final long serialVersionUID = 1L;

	
	//instance fields
	//frame default layout is border layout,
	//panels for each section
	//in BorderLayout
	private JPanel northPanel, eastPanel, westPanel, centerPanel;
	private JPanel southPanel;

	//default font
	private Font generalFont = new Font("SanSerif", Font.BOLD, 22);
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);

	//Text Areas
	private JTextField emailText, fNameText, lNameText, newEmail;
	private JPasswordField passwordText, newPassword;
	
	//labels
	private JLabel titleLabel, existingUser, newUser;

	//Buttons
	private JButton loginBtn, createAccBtn, adminBtn, exitBtn;

	//constructor
	public HomePage() {

		setLayout(new BorderLayout(20, 100));
		
		//Title
		//north panel
		//set title and font
		northPanel = new JPanel();
		titleLabel = new JLabel("Simple Shopping Service");
		titleLabel.setFont(titleFont);
		titleLabel.setBorder(new LineBorder(Color.BLACK, 1));
		//add label to panel
		//add panel to frame
		northPanel.add(titleLabel);
		add(northPanel, BorderLayout.NORTH);



		//sign in
		//east panel
		//set font and alignment
		existingUser = new JLabel("Existing User");
		existingUser.setFont(generalFont);
		existingUser.setHorizontalAlignment(JLabel.CENTER);

		//email field
		emailText = new JTextField("Enter Email");
		emailText.setColumns(15);
		emailText.setFont(generalFont);

		//password text
		passwordText = new JPasswordField("Enter Password");
		passwordText.setFont(generalFont);

		//login button
		loginBtn = new JButton("Login");
		loginBtn.setFont(generalFont);


		//add each component to east panel
		//new JLabel for a space between components
		eastPanel = new JPanel(new GridLayout(8, 2));
		eastPanel.add(existingUser);
		eastPanel.add(new JLabel(" "));
		eastPanel.add(emailText);
		eastPanel.add(new JLabel(" "));
		eastPanel.add(passwordText);
		eastPanel.add(new JLabel(" "));
		eastPanel.add(new JLabel(" "));
		eastPanel.add(loginBtn);
		//add east panel to frame
		//add(eastPanel, BorderLayout.EAST);

		
		
		//west panel
		//label, set font and alignment
		westPanel = new JPanel(new GridLayout(8, 0));
		newUser = new JLabel("New User");
		newUser.setFont(generalFont);
		newUser.setHorizontalAlignment(JLabel.CENTER);

		//first name field, set width
		//(which sets the width for all in the panel)
		fNameText = new JTextField("Enter First Name");
		fNameText.setColumns(15);
		fNameText.setFont(generalFont);

		//last name
		lNameText = new JTextField("Enter Last Name");
		lNameText.setFont(generalFont);

		//new user and password
		newEmail = new JTextField("Enter New Email Address");
		newEmail.setFont(generalFont);
		newPassword = new JPasswordField("Enter New Password");
		newPassword.setFont(generalFont);

		//create account button, set alignment
		createAccBtn = new JButton("Create Account");
		createAccBtn.setFont(generalFont);
		createAccBtn.setAlignmentX(CENTER_ALIGNMENT);



		//add components to panel
		//new JLabel for space between components
		westPanel.add(newUser);
		westPanel.add(new JLabel());
		westPanel.add(fNameText);
		westPanel.add(lNameText);
		westPanel.add(newEmail);
		westPanel.add(newPassword);
		westPanel.add(new JLabel());
		westPanel.add(createAccBtn);


		//add to frame
		//add(westPanel, BorderLayout.WEST);

		//disable resizing of frame
		setResizable(false);
		

		//centerPanel = new JPanel(new GridLayout(8,0));
		centerPanel = new JPanel(new FlowLayout(1, 100, 30));
		adminBtn = new JButton("Administrator");
		adminBtn.setFont(generalFont);
		
		
		//center panel, admin link
		//add blank labels to make all buttons match
//		centerPanel.add(new JLabel());
//		centerPanel.add(new JLabel());
//		centerPanel.add(new JLabel());
//		centerPanel.add(new JLabel());
//		centerPanel.add(new JLabel());
//		centerPanel.add(new JLabel());
//		centerPanel.add(new JLabel());
		
		centerPanel.add(westPanel);
		westPanel.setAlignmentX(CENTER_ALIGNMENT);
		centerPanel.add(eastPanel);
		eastPanel.setAlignmentX(BOTTOM_ALIGNMENT);
		add(centerPanel);
		
		//create new panel
		//override flow panel layout
		//1 = center, 240 = vertical gap, 10 = horizontal gap
		southPanel = new JPanel(new FlowLayout(1, 240, 10));
		
		southPanel.add(adminBtn);
		
		exitBtn = new JButton("Exit Application");
		exitBtn.setFont(generalFont);
		
		southPanel.add(exitBtn);
		
		add(southPanel, BorderLayout.SOUTH);

		
		//Listeners
		//add focus listener to text fields
		ListenerClass focusListener = new ListenerClass();
		emailText.addFocusListener(focusListener);
		passwordText.addFocusListener(focusListener);
		fNameText.addFocusListener(focusListener);
		lNameText.addFocusListener(focusListener);
		newEmail.addFocusListener(focusListener);
		newPassword.addFocusListener(focusListener);

		//Action Listeners
		//add action listens to button
		createAccBtn.addActionListener(new ActionListenerClass());
		loginBtn.addActionListener(new ActionListenerClass());
		adminBtn.addActionListener(new ActionListenerClass());
		exitBtn.addActionListener(new ActionListenerClass());

	}//end of constructor


	//inner FocusListener
	private class ListenerClass implements FocusListener{

		@Override
		public void focusGained(FocusEvent f) {

			//get source of event
			Object event = f.getSource();

			/* if event equals any of the listeners
			 * AND the text field contains the original text
			 * then set Text to NULL
			 *
			 * Otherwise do NOT set to NULL 
			 * (I found once a name was entered, and the same JTextField 
			 * was selected, the name would disapear. So i want to 
			 * retain text, if text has been entered)
			 */

			if(event == fNameText && fNameText.getText().equalsIgnoreCase("enter first name")){
				fNameText.setText(null);
			}
			else if(event == lNameText && lNameText.getText().equalsIgnoreCase("enter last name")){
				lNameText.setText(null);
			}
			else if(event == emailText && emailText.getText().equalsIgnoreCase("Enter Email")){
				emailText.setText(null);
			}
			else if(event == passwordText && passwordText.getText().equalsIgnoreCase("Enter password")){
				passwordText.setText(null);

			}
			else if(event == newEmail && newEmail.getText().equalsIgnoreCase("enter New Email Address")) {
				newEmail.setText(null);
			}
			else if(event == newPassword && newPassword.getText().equalsIgnoreCase("enter new password")) {
				newPassword.setText(null);

			}//end of if-else ladder

		}//end of method

		
		@Override
		public void focusLost(FocusEvent f){

			//get source of event
			Object event = f.getSource();

			//if Focus is lost and no text was entered
			//e.g. text equals NULL OR text equals an empty String
			//set text to the original text
			if(event == fNameText){
				if(fNameText.getText() == null || fNameText.getText().equals("")){
					fNameText.setText("Enter First name");
				}
			}
			else if(event == lNameText){
				if(lNameText.getText() == null || lNameText.getText().equals("")){
					lNameText.setText("Enter Last name");
				}
			}
			else if(event == emailText){
				if(emailText.getText() == null || emailText.getText().equals("")){
					emailText.setText("Enter Email");
				}
			}
			else if(event == passwordText){
				if(passwordText.getText() == null || passwordText.getText().equals("")){
					passwordText.setText("Enter Password");
				}
			}
			else if(event == newEmail) {
				if(newEmail.getText() == null || newEmail.getText().equals("")) {
					newEmail.setText("Enter New Email Address");
				}

			}
			else if(event == newPassword) {
				if(newPassword.getText() == null || newPassword.getText().equals("")) {
					newPassword.setText("Enter New Password");
				}
			}//end of if-else ladder
		}//end of method
	}//end of inner class

	//inner ActionListener
	private class ActionListenerClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			//check is Internet available
			boolean conn = DBConnector.checkInternet();

			/* test connection
			 * 
			 * if Internet is available
			 * 
			 * check which button event came from
			 */
			if(conn) {

				//get source of event
				Object event = e.getSource();


				/* If event equals > create account button,
				 * AND either field does not contain original
				 * text or empty string (All fields entered)
				 * write account to database
				 * 
				 * (TODO -- validate that account is NEW)
				 * */
				if(event == createAccBtn) {

					if(!(fNameText.getText().equalsIgnoreCase("Enter first name") ||
							lNameText.getText().equalsIgnoreCase("Enter last name") ||
							emailText.getText().equalsIgnoreCase("Enter new email address") ||
							passwordText.getText().equalsIgnoreCase("Enter new password"))) {

						if(!(fNameText.getText().equals("") ||
								lNameText.getText().equals("") ||
								emailText.getText().equals("") ||
								passwordText.getText().equals(""))) {
							
							//check if email is already registered in the database
							Account acc = DBConnector.readAccount(newEmail.getText());
							if(acc == null)
							{
								//create account with details taken from text fields
								Account a = new Account(fNameText.getText(), lNameText.getText(), newEmail.getText(), newPassword.getText(), 0);
								
								//pass to DB handler (write to database)
								DBConnector.writeAccount(a);
								//inform user that account has been created
								JOptionPane.showMessageDialog(null, "Your new Account has been created", "Welcome", JOptionPane.INFORMATION_MESSAGE);
								

								//Jump to Order page
								OrderPage.drawOrder(a);
								
								//dispose home page 
								dispose();
							}
							else 
							{
								//display message to user indicating that an coount already exists with that email
								JOptionPane.showMessageDialog(null, "An account with this email already exists \nin the databse, try again. ", "account already exists", JOptionPane.INFORMATION_MESSAGE);	
							
							}

						}
					}
					//else account is not created
					//dialog box to notify user
					else{

						JOptionPane.showMessageDialog(null, "Enter all Details to Create Account", "ERROR", JOptionPane.INFORMATION_MESSAGE);
					}
				}

				else if(event == loginBtn) {

					/* If event equals login button,
					 * and either of the fields are not blank,
					 * or contain original text
					 * 
					 * then make a query straight to the database,
					 * read in the account with the email supplied,
					 * assign to a account object
					 * */
					if(!(emailText.getText().equalsIgnoreCase("Enter Email") || 
							passwordText.getText().equalsIgnoreCase("Enter Password") ||
							emailText.getText().equals("")  ||
							passwordText.getText().equals(""))) {

						Account a = DBConnector.readAccount(emailText.getText());


						/* if the account object is NOT null,
						 * the account was found in the database
						 * */
						if(!(a == null)) {

							/* if the account is found in the database,
							 * make sure they entered the correct password.
							 * 
							 * if so, display dialog box and let user know
							 * 
							 * */
							if(a.getPassword().equals(passwordText.getText())) {

								JOptionPane.showMessageDialog(null, "Welcome", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

								//jump to order page
								OrderPage.drawOrder(a);
								
								//dispose home page
								dispose();

								
							}
							/* if password is wrong,
							 * let the user know and retry,
							 * clearing password field
							 * */
							else if(!(a.getPassword().equals(passwordText.getText()))) {
								JOptionPane.showMessageDialog(null, "Please enter correct password", "Wrong Password", JOptionPane.INFORMATION_MESSAGE);
								passwordText.setText(null);
							}
						}
						/* if user equals NULL
						 * then account is not in database
						 * i.e. Not an account holder.
						 * 
						 * inform user and retry.
						 */
						else if(a == null) {
							JOptionPane.showMessageDialog(null, "Email not found in database", "Create Account", JOptionPane.INFORMATION_MESSAGE);
						}
					}

					/* if all else fails,
					 * 
					 * OR
					 * 
					 * the user tries to sign in with
					 * original text or blank text
					 * 
					 * inform user and retry
					 * */
					else{
						JOptionPane.showMessageDialog(null, "Enter email and password to sign in", "Wrong details", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				//if event equals admin button
				else if(event == adminBtn) {
					
					//dispose home page
					dispose();
					
					//open admin login page
					Admin1.drawAdmin1();
				}
				//if exit button
				else if(event == exitBtn) {
					
					//dispose frame and exit
					dispose();
				}
			}
			
			//if no Internet
			//inform user
			else {
				JOptionPane.showMessageDialog(null, "Check Your Internet Connection", "NO INTERNET", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	
	//method to create window
	public static void drawHome() {
		
		HomePage hp = new HomePage();
		hp.setTitle("Create Account or Sign In");		
		//hp.pack();
		hp.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		hp.setLocationRelativeTo(null);
		hp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hp.setVisible(true);
		
		//fix to take focus off first text field
		//request focus in window instead
		hp.requestFocusInWindow(true);
		
		
	}

	//tester
	//main method
	public static void main(String[] args){

		drawHome();
	}//end of main method
}

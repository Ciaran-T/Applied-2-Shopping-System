package ie.lyit.code;

import jdbc.DBConnector;
import ie.lyit.data.Account;
import ie.lyit.code.OrderPage;
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
	private JPanel northPanel, eastPanel, westPanel;

	//default font
	private Font generalFont = new Font("SanSerif", Font.BOLD, 15);
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);

	//Text Areas
	private JTextField emailText, passwordText, fNameText, lNameText, newEmail, newPassword;

	//labels
	private JLabel titleLabel, existingUser, newUser;

	//Buttons
	private JButton loginBtn, createAccBtn;

	public HomePage() {

		
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

		//password text
		passwordText = new JTextField("Enter Password");

		//login button
		loginBtn = new JButton("Login");


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
		add(eastPanel, BorderLayout.EAST);

		
		
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

		//last name
		lNameText = new JTextField("Enter Last Name");

		//new user and password
		newEmail = new JTextField("Enter New Email Address");
		newPassword = new JTextField("Enter New Password");

		//create account button, set alignment
		createAccBtn = new JButton("Create Account");
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
		add(westPanel, BorderLayout.WEST);

		//disable resizing of frame
		setResizable(false);


		
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
	private class ActionListenerClass implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			//get source of event
			Object event = e.getSource();

			/* If event equals > create account button,
			 * AND either field does not contain original
			 * text or empty string (All fields entered)
			 * write customer to database
			 * (TODO -- validate that customer is NEW)
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

						//create customer with details taken from text fields
						Account c = new Account(fNameText.getText(), lNameText.getText(), newEmail.getText(), newPassword.getText());
						//pass to DB handler (write to database)
						DBConnector.writeCustomer(c);

						//dispose home page 
						dispose();

						//Jump to Order page
						OrderPage op = new OrderPage(c);
						op.setTitle("Order");
						//op.pack();
						op.setSize(500, 300);
						op.setLocationRelativeTo(null);
						op.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						op.setVisible(true);

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
				 * read in the customer with the email supplied,
				 * assign to a customer object
				 * */
				if(!(emailText.getText().equalsIgnoreCase("Enter Email") || 
						passwordText.getText().equalsIgnoreCase("Enter Password") ||
						emailText.getText().equals("")  ||
						passwordText.getText().equals(""))) {

					Account c = DBConnector.readCustomer(emailText.getText());

					
					/* if the customer object is NOT null,
					 * the customer was found in the database
					 * */
					if(!(c == null)) {

						/* if the customer is found in the database,
						 * make sure they entered the correct password.
						 * 
						 * if so, display dialog box and let user know
						 * 
						 * */
						if(c.getPassword().equals(passwordText.getText())) {

							JOptionPane.showMessageDialog(null, "Welcome", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

							//dispose home page
							dispose();
							
							//Jump to Order page
							OrderPage op = new OrderPage(c);
							op.setTitle("Order");
							//op.pack();
							op.setSize(500, 300);
							op.setLocationRelativeTo(null);
							op.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							op.setVisible(true);
						}
						/* if password is wrong,
						 * let the user know and retry,
						 * clearing password field
						 * */
						else if(!(c.getPassword().equals(passwordText.getText()))) {
							JOptionPane.showMessageDialog(null, "Please enter correct password", "Wrong Password", JOptionPane.INFORMATION_MESSAGE);
							passwordText.setText(null);
						}
					}
					/* if user equals NULL
					 * then customer is not in database
					 * i.e. Not a customer.
					 * 
					 * inform user and retry.
					 */
					else if(c == null) {
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
		}
	}


	//tester
	//main method
	public static void main(String[] args){
		HomePage hp = new HomePage();
		hp.setTitle("Create Account or Sign In");		
		hp.pack();
		//hp.setSize(500, 300);
		hp.setLocationRelativeTo(null);
		hp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hp.setVisible(true);
	}//end of main method
}

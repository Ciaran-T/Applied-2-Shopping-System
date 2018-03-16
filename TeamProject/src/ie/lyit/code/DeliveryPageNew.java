package ie.lyit.code;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import ie.lyit.data.Account;
import ie.lyit.data.Order;
import ie.lyit.data.Product;

public class DeliveryPageNew extends JFrame{

	private static final long serialVersionUID = 1L;
	
	//create Account and Product objects
	private Account a;
	private Order o;

	//panels
	private JPanel northPanel, centerPanel, eastPanel, westPanel, southPanel;

	//default font
	private Font generalFont = new Font("SanSerif", Font.BOLD, 15);
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);

	//labels
	private JLabel jlbCustAdress, jlbAltCustAdress, jlbHeader;

	//text fields
	private JTextField jtfAddressL1, jtfAddressL2, jtfAddressL3, jtfCity, jtfCounty,
	jtfAltAddressL1, jtfAltAddressL2, jtfAltAddressL3, jtfAltCity, jtfAltCounty;

	private JButton confirmAdressDetsBtn, backBtn;
	
	
	///TEST USER DETAILS PANEL
	private JLabel titleLabel, custDetailsLabel, shoppingCartLabel, productsLabel;
	private JTextField name, email, password, total;
	
	public DeliveryPageNew(Account a, Order o) {
		
		//assign Objects
		this.a = a;
		this.o = o;
		
		//add header to north panel
		northPanel = new JPanel();
		jlbHeader = new JLabel("Enter Delivery Details");
		jlbHeader.setFont(titleFont);
		jlbHeader.setBorder(new LineBorder(Color.BLACK, 1));
		//add label to panel
		northPanel.add(jlbHeader);
		//add panel to frame
		add(northPanel, BorderLayout.NORTH);

		//Centre Panel Home Address
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(12,1));
		centerPanel.add(jlbCustAdress = new JLabel("Enter Home Address"));
		centerPanel.add(jtfAddressL1 = new JTextField("Address Line 1", 20));
		centerPanel.add(jtfAddressL2 = new JTextField("Address Line 2", 20));
		centerPanel.add(jtfAddressL3 = new JTextField("Address Line 3", 20));
		centerPanel.add(jtfCity = new JTextField("City/Town", 20));
		centerPanel.add(jtfCounty = new JTextField("County", 20));

		centerPanel.add(jlbAltCustAdress = new JLabel("Enter Alternative Address"));
		centerPanel.add(jtfAltAddressL1 = new JTextField("Address Line 1", 20));
		centerPanel.add(jtfAltAddressL2 = new JTextField("Address Line 2", 20));
		centerPanel.add(jtfAltAddressL3 = new JTextField("Address Line 3", 20));
		centerPanel.add(jtfAltCity = new JTextField("City/Town", 20));
		centerPanel.add(jtfAltCounty = new JTextField("County", 20));
		//centerPanel.add(jtfPassword = new JTextField("Enter Password", 20));
		//add panel to frame
		add(centerPanel, BorderLayout.CENTER);
		
		//east panel
				eastPanel = new JPanel(new GridLayout(8, 1));
				//label and set alignment
				custDetailsLabel = new JLabel("Account Details");
				custDetailsLabel.setHorizontalAlignment(JLabel.CENTER);
				custDetailsLabel.setFont(generalFont);
				//add label to panel
				eastPanel.add(custDetailsLabel);
				
				//name of customer
				//set width
				//make un-editable
				name = new JTextField(a.getfName() + " " + a.getlName());
				name.setColumns(15);
				name.setEditable(false);
				//add text field to panel
				eastPanel.add(name);
				eastPanel.add(new JLabel());
				
				//email of customer
				email = new JTextField(a.getEmail());
				email.setEditable(false);
				//add to panel
				eastPanel.add(email);
				eastPanel.add(new JLabel());
			
				//password of customer
				password = new JTextField(a.getPassword());
				password.setEditable(false);
				//add to panel
				eastPanel.add(password);
				//add panel to frame
				add(eastPanel, BorderLayout.EAST);
				eastPanel.add(new JLabel());
				
				//total bill
				total = new JTextField("Total: €");
				total.setEditable(false);
//				
//				//add to panel
				eastPanel.add(total);
				add(eastPanel, BorderLayout.EAST);
		
		//southPanel Buttons
		southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1,1));
		southPanel.add(backBtn = new JButton("Go Back"), BorderLayout.EAST);
		southPanel.add(confirmAdressDetsBtn = new JButton("Confirm Address Details"), BorderLayout.WEST);
		add(southPanel, BorderLayout.SOUTH);

		//Action Listeners
		//add action listens to button
		backBtn.addActionListener(new ActionListenerClass());
		confirmAdressDetsBtn.addActionListener(new ActionListenerClass());

	
			//Listeners
			//add focus listener to text fields
			ListenerClass focusListener = new ListenerClass();
			jtfAddressL1.addFocusListener(focusListener);
			jtfAddressL2.addFocusListener(focusListener);
			jtfAddressL3.addFocusListener(focusListener);
			jtfAltAddressL1.addFocusListener(focusListener);
			jtfAltAddressL2.addFocusListener(focusListener);
			jtfAltAddressL3.addFocusListener(focusListener);
			jtfCounty.addFocusListener(focusListener);
			jtfCity.addFocusListener(focusListener);
			jtfAltCounty.addFocusListener(focusListener);
			jtfAltCity.addFocusListener(focusListener);

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

				if(event == jtfAddressL1 && jtfAddressL1.getText().equalsIgnoreCase("Address Line 1")){
					jtfAddressL1.setText(null);
				}
				else if(event == jtfAddressL2 && jtfAddressL2.getText().equalsIgnoreCase("Address Line 2")){
					jtfAddressL2.setText(null);
				}
				else if(event == jtfAddressL3 && jtfAddressL3.getText().equalsIgnoreCase("Address Line 3")){
					jtfAddressL3.setText(null);
				}
				else if(event == jtfCity && jtfCity.getText().equalsIgnoreCase("City/Town")){
					jtfCity.setText(null);
				}
				else if(event == jtfCounty && jtfCounty.getText().equalsIgnoreCase("County")){
					jtfCounty.setText(null);
				}
				else if(event == jtfAltAddressL1 && jtfAltAddressL1.getText().equalsIgnoreCase("Address Line 1")){
					jtfAltAddressL1.setText(null);

				}
				else if(event == jtfAltAddressL2 && jtfAltAddressL2.getText().equalsIgnoreCase("Address Line 2")) {
					jtfAltAddressL2.setText(null);
				}
				else if(event == jtfAltAddressL3 && jtfAltAddressL3.getText().equalsIgnoreCase("Address Line 3")) {
					jtfAltAddressL3.setText(null);
				}
				else if(event == jtfAltCity && jtfAltCity.getText().equalsIgnoreCase("City/Town")){
						jtfAltCity.setText(null);
				}
				else if(event == jtfAltCounty && jtfAltCounty.getText().equalsIgnoreCase("County")){
						jtfAltCounty.setText(null);

				}//end of if-else ladder

			}//end of method

			
			@Override
			public void focusLost(FocusEvent f){

				//get source of event
				Object event = f.getSource();

				//if Focus is lost and no text was entered
				//e.g. text equals NULL OR text equals an empty String
				//set text to the original text
				if(event == jtfAddressL1){
					if(jtfAddressL1.getText() == null || jtfAddressL1.getText().equals("")){
						jtfAddressL1.setText("Address Line 1");
					}
				}
				else if(event == jtfAddressL2){
					if(jtfAddressL2.getText() == null || jtfAddressL2.getText().equals("")){
						jtfAddressL2.setText("Address Line 2");
					}
				}
				else if(event == jtfAddressL3){
					if(jtfAddressL3.getText() == null || jtfAddressL3.getText().equals("")){
						jtfAddressL3.setText("Address Line 3");
					}
				}
				else if(event == jtfCity){
					if(jtfCity.getText() == null || jtfCity.getText().equals("")){
						jtfCity.setText("City/Town");
					}
				}
				else if(event == jtfCounty){
					if(jtfCounty.getText() == null || jtfCounty.getText().equals("")){
						jtfCounty.setText("County");
					}
				}
				else if(event == jtfAltAddressL1){
					if(jtfAltAddressL1.getText() == null || jtfAltAddressL1.getText().equals("")){
						jtfAltAddressL1.setText("Address Line 1");
					}
				}
				else if(event == jtfAltAddressL2) {
					if(jtfAltAddressL2.getText() == null || jtfAltAddressL2.getText().equals("")) {
						jtfAltAddressL2.setText("Address Line 2");
					}

				}
				else if(event == jtfAltAddressL3) {
					if(jtfAltAddressL3.getText() == null || jtfAltAddressL3.getText().equals("")) {
						jtfAltAddressL3.setText("Address Line 3");
					}
			    else if(event == jtfAltCity){
					if(jtfAltCity.getText() == null || jtfAltCity.getText().equals("")){
						jtfAltCity.setText("City/Town");
					}
				}
				else if(event == jtfAltCounty){
					if(jtfAltCounty.getText() == null || jtfAltCounty.getText().equals("")){
						jtfAltCounty.setText("County");
					}}
				}//end of if-else ladder
			}//end of method
		}//end of inner class


	//inner action listener
	public class ActionListenerClass implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			//get source of event
			Object event = e.getSource();

			/* if event equal back button
			 * dispose order page and open home page
			 * */
			if(event == backBtn) {
				dispose();
				OrderPage op = new OrderPage(new Account("Somebody","Else","elseIf@mail.ie","TestPass", 0));
				op.setTitle("Create Account or Sign In");		
				op.pack();
				//hp.setSize(500, 300);
				op.setLocationRelativeTo(null);
				op.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				op.setVisible(true);

			}
			else if(event == confirmAdressDetsBtn) {
				if(jtfAddressL1.getText() == "Address Line 1"|| jtfAddressL2.getText()== "Address Line 2"||
						jtfAddressL3.getText()== "Address Line 3"|| jtfCity.getText()== "Ciy/Town"|| jtfCounty.getText()== "County")
				{
					JOptionPane.showMessageDialog(null, "Address Not Complete");
				}
				else if(jtfAddressL1.getText() == ""|| jtfAddressL2.getText()== ""||
						jtfAddressL3.getText()== ""|| jtfCity.getText()== ""|| jtfCounty.getText()== "")
				{
					//JOptionPane.showMessageDialog(null, "Address Not Complete");
					JOptionPane.showMessageDialog(null, "Address Not Complete", "NO INTERNET", JOptionPane.INFORMATION_MESSAGE);
				}
				else 
				{
				String Address = jtfAddressL1.getText() + ", \n" + jtfAddressL2.getText() + ", \n" 
						+ jtfAddressL3.getText() + ", \n" + jtfCity.getText() + ", \n" +  jtfCounty.getText();
				System.out.println(Address);
				}
			}
		}
	}



	//tester
	//Draw method
	public static void drawDeliveryNew(Account acc, Order o) {
		DeliveryPageNew dp = new DeliveryPageNew(acc, o);
		dp.setTitle("Delivery Page");		
		dp.pack();
		//dp.setSize(500, 300);
		dp.setLocationRelativeTo(null);
		dp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dp.setVisible(true);
	}//end Draw Method
	
	//main method
	public static void main(String[] args)
	{
		drawDeliveryNew(new Account("Cathal", "Doherty", "egg", "milk", 0), new Order(new ArrayList<Product>(),100, 100, "egg"));
			
	}//end of main method



}


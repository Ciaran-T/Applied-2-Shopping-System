package ie.lyit.code;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import ie.lyit.data.Customer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class OrderPage extends JFrame {

	//panels
	private JPanel northPanel, centerPanel, eastPanel, westPanel, southPanel;
	
	//default font
	private Font generalFont = new Font("SanSerif", Font.BOLD, 15);
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
	
	//labels
	private JLabel titleLabel, custDetailsLabel, shoppingCartLabel, productsLabel;
	
	//text fields
	private JTextField name, email, password, total;
	
	//scroll pane for lists
	private JScrollPane westScrollPane, centerScrollPane;
	private JList westJlist, centerJlist;
	
	private JButton backBtn, placeOrder;
	
	public OrderPage(Customer cust) {
		
		//north panel
		northPanel = new JPanel();
		
		//Title label
		//set title, font and border
		titleLabel = new JLabel("Simple Shopping Service");
		titleLabel.setFont(titleFont);
		titleLabel.setBorder(new LineBorder(Color.BLACK, 1));
		//add label to panel
		//add panel to frame
		northPanel.add(titleLabel);
		add(northPanel, BorderLayout.NORTH);
		
		
		
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
		name = new JTextField(cust.getfName() + " " + cust.getlName());
		name.setColumns(15);
		name.setEditable(false);
		//add text field to panel
		eastPanel.add(name);
		eastPanel.add(new JLabel());
		
		//email of customer
		email = new JTextField(cust.getEmail());
		email.setEditable(false);
		//add to panel
		eastPanel.add(email);
		eastPanel.add(new JLabel());
		
		//password of customer
		password = new JTextField(cust.getPassword());
		password.setEditable(false);
		//add to panel
		eastPanel.add(password);
		//add panel to frame
		add(eastPanel, BorderLayout.EAST);
		eastPanel.add(new JLabel());
		
		//total bill
		total = new JTextField("Total: €");
		total.setEditable(false);
		
		//add to panel
		eastPanel.add(total);
		
		
		
		//south panel
		//buttons to proceed or quit
		southPanel = new JPanel(new GridLayout(1, 2));
		
		backBtn = new JButton("Back/Log Out");
		southPanel.add(backBtn);
		
		placeOrder = new JButton("Place Order");
		southPanel.add(placeOrder);
		
		add(southPanel, BorderLayout.SOUTH);
		
		
		
		//west panel
		//shopping cart
		westPanel = new JPanel(new BorderLayout());
		productsLabel = new JLabel("Products   ");
		productsLabel.setFont(generalFont);
		productsLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//add label to panel
		westPanel.add(productsLabel, BorderLayout.NORTH);
		
		//test string array (replaced with products from DB)
		String[] test = {"Apple","Orange","Orange","Orange","Orange","Orange","Orange"};
		westJlist = new JList(test);
		westScrollPane = new JScrollPane(westJlist);
		westScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		westScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//westScrollPane.setColumnHeader(shoppingCart);
		
		//add label to panel
		//panel to frame
		westPanel.add(westScrollPane, BorderLayout.CENTER);
		add(westPanel, BorderLayout.WEST);
		
		
		//center panel (set alignment + font)
		centerPanel = new JPanel(new BorderLayout());
		shoppingCartLabel = new JLabel("Shopping Cart");
		shoppingCartLabel.setHorizontalAlignment(JLabel.CENTER);
		shoppingCartLabel.setFont(generalFont);
		
		centerPanel.add(shoppingCartLabel, BorderLayout.NORTH);
		
		//create list and passing into  scroll pane
		//set vertical scroll bars
		//set horizontal scroll bars to NEVER
		centerJlist = new JList(test);
		centerScrollPane = new JScrollPane(centerJlist);
		centerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		centerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		centerPanel.add(centerScrollPane, BorderLayout.CENTER);
		
		add(centerPanel, BorderLayout.CENTER);
		
		//TODO -- create other listeners
		//
		//register listener on button
		backBtn.addActionListener(new ActionListenerClass());
	}
	
	
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
				HomePage hp = new HomePage();
				hp.setTitle("Create Account or Sign In");		
				hp.pack();
				//hp.setSize(500, 300);
				hp.setLocationRelativeTo(null);
				hp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				hp.setVisible(true);
				
			}
			else if(event == placeOrder) {
				
				
			}
		}
	}
	
	
	
	//tester
	//main method
	public static void main(String[] args){
		OrderPage op = new OrderPage(new Customer("Somebody","Else","elseIf@mail.ie","TestPass"));
		op.setTitle("Order");		
		op.pack();
		//hp.setSize(500, 300);
		op.setLocationRelativeTo(null);
		op.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		op.setVisible(true);
	}//end of main method
}

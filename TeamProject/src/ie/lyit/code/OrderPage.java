package ie.lyit.code;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import ie.lyit.data.Account;
import ie.lyit.data.Order;
import ie.lyit.data.Product;
import jdbc.DBConnector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class OrderPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//panels
	private JPanel northPanel, centerPanel, eastPanel, westPanel, southPanel;
	
	//default font
	private Font generalFont = new Font("SanSerif", Font.BOLD, 15);
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
	
	//labels
	private JLabel titleLabel, custDetailsLabel, shoppingCartLabel, productsLabel;
	
	//text fields
	private JTextField name, email, password, totalTf;
	
	//scroll pane for lists
	private JScrollPane westScrollPane, centerScrollPane;
	
	//lists
	private JList<Product> westJlist;
	private JList<Product> centerJlist;
	
	//buttons
	private JButton backBtn, placeOrder, addToCartBtn, removeBtn;
	
	//default model view for products
	private DefaultListModel<Product> listModel;
	
	//total of order
	private double total = 0;
	
	//account passed into OrderPage constructor
	//assign to global variable
	private Account a;
	
	
	//constructor
	public OrderPage(Account acc) {
		
		a = acc;
		
		
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
		name = new JTextField(acc.getfName() + " " + acc.getlName());
		name.setColumns(15);
		name.setEditable(false);
		//add text field to panel
		eastPanel.add(name);
		eastPanel.add(new JLabel());
		
		//email of customer
		email = new JTextField(acc.getEmail());
		email.setEditable(false);
		//add to panel
		eastPanel.add(email);
		eastPanel.add(new JLabel());
		
		//password of customer
		password = new JTextField(acc.getPassword());
		password.setEditable(false);
		//add to panel
		eastPanel.add(password);
		//add panel to frame
		add(eastPanel, BorderLayout.EAST);
		eastPanel.add(new JLabel());
		
		//total bill
		totalTf = new JTextField("Total: €");
		totalTf.setEditable(false);
		
		//add to panel
		eastPanel.add(totalTf);
		
		
		
		//south panel
		//buttons to proceed or quit
		southPanel = new JPanel(new GridLayout(1, 2));
		
		backBtn = new JButton("Back/Log Out");
		southPanel.add(backBtn);
		
		//button to place order
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
		
		//read products from DB and populate product array
		Product[] test = DBConnector.readProducts();
		westJlist = new JList<Product>(test);
		westScrollPane = new JScrollPane(westJlist);
		westScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//westScrollPane.setColumnHeader(shoppingCart);
		
		//add label to panel
		//panel to frame
		westPanel.add(westScrollPane, BorderLayout.CENTER);
		addToCartBtn = new JButton("Add");
		westPanel.add(addToCartBtn, BorderLayout.SOUTH);
		add(westPanel, BorderLayout.WEST);
		
		
		//center panel (set alignment + font)
		centerPanel = new JPanel(new BorderLayout());
		shoppingCartLabel = new JLabel("Shopping Cart");
		shoppingCartLabel.setHorizontalAlignment(JLabel.CENTER);
		shoppingCartLabel.setFont(generalFont);
		
		//add to panel
		centerPanel.add(shoppingCartLabel, BorderLayout.NORTH);
		
		//remove button
		removeBtn = new JButton("Remove");
		centerPanel.add(removeBtn, BorderLayout.SOUTH);
		
		//create list and passing into  scroll pane
		//set horizontal scroll bars to NEVER
		listModel = new DefaultListModel<Product>();
		
		centerJlist = new JList<Product>(listModel);
		centerScrollPane = new JScrollPane(centerJlist);
		centerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		centerPanel.add(centerScrollPane, BorderLayout.CENTER);
		
		
		add(centerPanel, BorderLayout.CENTER);
		setResizable(false);
		
		
		//listeners on buttons
		ActionListenerClass listener = new ActionListenerClass();
		backBtn.addActionListener(listener);
		addToCartBtn.addActionListener(listener);
		removeBtn.addActionListener(listener);
		placeOrder.addActionListener(listener);
	}
	
	
	//inner action listener
	public class ActionListenerClass implements ActionListener{
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			DecimalFormat df = new DecimalFormat("#0.00");
			
			//get source of event
			Object event = e.getSource();
			
			/* if event equal back button
			 * dispose order page and open home page
			 * */
			if(event == backBtn) {
				dispose();
				
				//draw new Home page using path to draw method
				ie.lyit.code.HomePage.drawHome();
				
			}
			
			/* if event equal place order button
			 * 
			 * get no. of product in list
			 * create array list to store products
			 * 
			 * connect to DB to get last order No. given out
			 * create order and write to DB
			 * 
			 * */
			else if(event == placeOrder) {
				
				int noOfProducts = listModel.getSize();
				
				ArrayList<Product> prods = new ArrayList<>();
				
				for(int i = 0; i < noOfProducts; i++) {
					prods.add(listModel.getElementAt(i));
				}
				
				int id = DBConnector.getLastOrderID() + 1;
				Order o = new Order(prods, total, id, a.getEmail());
				DBConnector.writeOrder(o, a);
			}
			
			/* if event equals add to cart button
			 * 
			 * add to model list
			 * 
			 * accumulate total (Decimal format, 2 decimal places)
			 * set total text field to display total
			 * 
			 * */
			else if(event == addToCartBtn) {
				
				Product item = westJlist.getSelectedValue();
				if(item != null) {
					listModel.addElement(item);
					total += item.getPrice();
					totalTf.setText("Total: €" + df.format(total));
				}
				
			}
			
			/* if event equals remove button
			 * 
			 * remove selected item from list,
			 * 
			 * take away from total.
			 * 
			 * */
			else if(event == removeBtn) {
				
				int item = (int)centerJlist.getSelectedIndex();
				if(item != -1) {
					Product p = listModel.remove(item);
					total -= p.getPrice();
					totalTf.setText("Total: €" + df.format(total));
					
				}
			}
		}
	}
	
	public static void drawOrder(Account acc) {
		
		OrderPage op = new OrderPage(acc);
		op.setTitle("Order");		
		//op.pack();
		op.setSize(550, 400);
		op.setLocationRelativeTo(null);
		op.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		op.setVisible(true);
		op.requestFocusInWindow(true);
	}
	
	
	//tester
	//main method
	public static void main(String[] args){
		OrderPage op = new OrderPage(new Account("Somebody","Else","elseIf@mail.ie","TestPass", 0));
		op.setTitle("Order");		
		//op.pack();
		op.setSize(550, 400);
		op.setLocationRelativeTo(null);
		op.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		op.setVisible(true);
	}//end of main method
}

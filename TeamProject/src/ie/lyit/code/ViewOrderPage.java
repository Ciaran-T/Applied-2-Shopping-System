package ie.lyit.code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import ie.lyit.data.Account;
import ie.lyit.data.Delivery;
import ie.lyit.data.Order;
import ie.lyit.data.Product;

public class ViewOrderPage extends JFrame {
	
	//instance fields
	    //labels
		private JLabel titleLabel, orderDetailsLabel, shoppingCartLabel, productsLabel;
		private JLabel custNameLabel, productPriceLabel, productTypeLabel;
		
		private JPanel northPanel, centerPanel, eastPanel, eastTopPanel, westPanel, southPanel;
		
		//text fields
		private JTextField nameTf, emailTf, productTypeTf, productPriceTf, totalTf;
		
		//buttons
		private JButton backBtn, exitBtn, goToFeedback;
		
		//lists
		private JList<Product> westJlist;
		private JList<Product> centerJlist;
		
		//default model view for products
		private DefaultListModel<Product> listModel;
		
		//scroll pane for lists
		private JScrollPane westScrollPane, centerScrollPane;
		
		//default font
		private Font generalFont = new Font("SanSerif", Font.BOLD, 15);
		private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
		private Font buttonFont = new Font("SanSerif",Font.BOLD,22);
		
		//total of order
		private double total = 0;
		
		private Account a;
		private Order o;
		private Delivery d;
		
//		private static HashMap<Product, Integer> countMap;
//		private DefaultTableModel tableModel;
		private JTable table;
//		private JScrollPane tablePane;
//		private String[] columnNames = {"Name", "Price", "Quantity"};
		
		//constructor
		public ViewOrderPage(Account a, Order o, Delivery d) {
			
			this.a = a;
			this.o = o;
			this.d = d;
			
			//get products map
		//	countMap = OrderPage.getMap();
			
			//north panel
			northPanel = new JPanel();

			//Title label
			//set title, font and border
			titleLabel = new JLabel(" View Your Order ");
			titleLabel.setFont(titleFont);
			titleLabel.setBorder(new LineBorder(Color.BLACK, 1));
			//add label to panel
			//add panel to frame
			northPanel.add(titleLabel);
			add(northPanel, BorderLayout.NORTH);
			
			//east top panel to hold title
			eastTopPanel = new JPanel(new BorderLayout());
			
			//east panel to add to eastTopPanel
			eastPanel = new JPanel(new GridLayout(7, 1));
			
			//label and set alignment
			orderDetailsLabel = new JLabel("Order Details");
			orderDetailsLabel.setHorizontalAlignment(JLabel.CENTER);
			orderDetailsLabel.setFont(buttonFont);
			
			//add label to panel
			eastPanel.add(orderDetailsLabel, BorderLayout.NORTH);
			
			//add east panel to east top panel
			eastTopPanel.add(eastPanel, BorderLayout.CENTER);
			
			//name of customer
			//set width
			//make un-editable
			nameTf = new JTextField(a.getfName() + " " + a.getlName());
			//nameTf.setColumns(15);
			nameTf.setEditable(false);
			nameTf.setColumns(30);
			nameTf.setFont(buttonFont);
			
			//name of customer
			//set width
			
			//create label and add to panel
			//set alignment				
			custNameLabel = new JLabel("<html><u> Your Name </u></html");//used to underline label
			eastPanel.add(custNameLabel);
			custNameLabel.setHorizontalAlignment(JLabel.CENTER);
			eastPanel.add(nameTf);
			custNameLabel.setFont(buttonFont);
			
			//create label and add to panel
			//set alignment
			JLabel jlbcustEmail = new JLabel("<html><u>Order ID</u></html");
			eastPanel.add(jlbcustEmail);
			jlbcustEmail.setHorizontalAlignment(JLabel.CENTER);
			jlbcustEmail.setFont(buttonFont);
			
			//product type text field
			JTextField jtfOrderID = new JTextField(""+ o.getOrderID());
			jtfOrderID.setEditable(false);
			jtfOrderID.setFont(buttonFont);
			//add to panel
			eastPanel.add(jtfOrderID);
			
			//create Delivery details label
			JLabel jlbdelDetails = new JLabel("<html><u> Delivery Details </u></html");
			eastPanel.add(jlbdelDetails);
			jlbdelDetails.setHorizontalAlignment(JLabel.CENTER);
			jlbdelDetails.setFont(buttonFont);
			
			//delivery details textarea
			JTextArea jtaDelDets = new JTextArea(d.getAllDetails());
			jtaDelDets.setEditable(false);
			jtaDelDets.setFont(buttonFont);
			//add to panel
			eastPanel.add(jtaDelDets);
			//add panel to frame
			add(eastPanel, BorderLayout.EAST);
			
			//south panel
			//buttons to proceed or quit
			southPanel = new JPanel(new FlowLayout(1, 240, 10));
			
			backBtn = new JButton("Back");
			backBtn.setFont(buttonFont);
			southPanel.add(backBtn);
			
			//button to cancel order
			goToFeedback = new JButton("Leave Feedback");
			goToFeedback.setFont(buttonFont);
			southPanel.add(goToFeedback);
			
			exitBtn = new JButton("Exit Application");
			exitBtn.setFont(buttonFont);
			southPanel.add(exitBtn);
			
			
			add(southPanel, BorderLayout.SOUTH);
			
			//center panel (set alignment + font)
			centerPanel = new JPanel(new BorderLayout());
			shoppingCartLabel = new JLabel("Your Order");
			shoppingCartLabel.setHorizontalAlignment(JLabel.CENTER);
			shoppingCartLabel.setFont(buttonFont);
			
			//add to panel
			centerPanel.add(shoppingCartLabel, BorderLayout.NORTH);
	
			
			
			//create model list
			//listModel = new DefaultListModel<Product>();
			JTextArea  orderSummery = new JTextArea("" + o.getOrderID()); 
			centerPanel.add(orderSummery);
			//create list and passing into  scroll pane
			//set horizontal scroll bars to NEVER
			//centerJlist = new JList<Product>();
			table = new JTable(OrderPage.getTableModel());
			table.getTableHeader().setFont(buttonFont);
			table.setRowHeight(30);
			table.setFont(buttonFont);
			centerScrollPane = new JScrollPane(table);
			centerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			centerPanel.add(centerScrollPane, BorderLayout.CENTER);
			
			
			add(centerPanel, BorderLayout.CENTER);
			setResizable(false);
			
			//add listeners on buttons
			ActionListenerClass listener = new ActionListenerClass();
			backBtn.addActionListener(listener);
			goToFeedback.addActionListener(listener);
			exitBtn.addActionListener(listener);
			//addToCartBtn.addActionListener(listener);;

			
			
			
			
		}
		
		//inner action listener
		public class ActionListenerClass implements ActionListener{
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//format number to two decimal places
				DecimalFormat df = new DecimalFormat("#0.00");
				
				//get source of event
				Object event = e.getSource();
				
				if(event == exitBtn) {
					dispose();
				}
				/* if event equal back button
				 * dispose order page and open home page
				 * */
				else if(event == backBtn) {
					dispose();
					
					//draw new Home page using path to draw method
					HomePage.drawHome();
					
				}
				
				/* if event equal place order button
				 * 
				 * get no. of product in list
				 * create array list to store products
				 * 
				 * connect to DB to get last order No. given out
				 * create order and write to DB
				 * 
				 * dispose order page and jump to delivery page
				 * 
				 * */
				else if(event == goToFeedback) {
					
					FeedbackPage.drawFeedbackPage();
					
				}
				
				/* if event equals add to cart button
				 * 
				 * add to model list
				 * 
				 * accumulate total (Decimal format, 2 decimal places)
				 * set total text field to display total
				 * 
				 * */
//				else if(event == addToCartBtn) {
//					
//					Product item = westJlist.getSelectedValue();
//					if(item != null) {
//						listModel.addElement(item);
//						total += item.getPrice();
//						totalTf.setText("Total: €" + df.format(total));
//						
//						//set details of product in east panel
//						productPriceTf.setText("" + item.getPrice());
//						productTypeTf.setText("" + item.getType());
//					}
//					
//				}
				
				/* if event equals remove button
				 * 
				 * remove selected item from list,
				 * 
				 * take away from total.
				 * 
				 * */
//				else if(event == cancelOrder) {
//					
//					int item = (int)centerJlist.getSelectedIndex();
//					if(item != -1) {
//						Product p = listModel.remove(item);
//						total -= p.getPrice();
//						totalTf.setText("Total: €" + df.format(total));
//						
//						productPriceTf.setText("-" + p.getPrice());
//						productTypeTf.setText("" + p.getType());
//						
//						
//					}
//				}
			}
		}
		
		
		public static void drawViewOrderPage(Account acc, Order o, Delivery d) {
			
			ViewOrderPage vop = new ViewOrderPage(acc, o, d);
			
			vop.setTitle("View Order Page");		
			//vop.pack();
			//vop.setSize(550, 400);
			vop.setLocation(0,0);
			vop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			vop.setVisible(true);
			vop.requestFocusInWindow(true);
			vop.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			
		}
		
		
		
		
		public static void main(String[] args) {
			
			ArrayList<Product> sampleProducts = new ArrayList<Product>();
			
			drawViewOrderPage(new Account("Shaun", "Haugh", "haughshaun@gmail.com", "password", 0),
							new Order(new ArrayList<Product>(sampleProducts), 100, 100, "haughshaun@gmail.com"),
							new Delivery(""));

				
		}

}

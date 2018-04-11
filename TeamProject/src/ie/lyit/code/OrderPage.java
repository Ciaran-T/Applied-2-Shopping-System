/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: GUI application order page.
 * 		 Methods:
 * 		 		- Populate model list of image icons.
 * 		 		- Draw GUI.
 * 
 * 		 Classes:
 * 				- Action Listener
 */	 


package ie.lyit.code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import ie.lyit.data.Account;
import ie.lyit.data.Order;
import ie.lyit.data.Product;
import jdbc.DBConnector;

public class OrderPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//panels
	private JPanel northPanel, centerPanel, eastPanel, eastTopPanel, westPanel, southPanel;
	
	//default font
	private Font generalFont = new Font("SanSerif", Font.BOLD, 22);
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
	
	//labels
	private JLabel titleLabel, orderDetailsLabel, shoppingCartLabel, productsLabel;
	private JLabel custNameLabel, productPriceLabel, productTypeLabel;
	
	//text fields
	private JTextField nameTf, productTypeTf, productPriceTf, totalTf;
	
	//scroll pane for lists
	private JScrollPane westScrollPane, centerScrollPane;
	
	//lists
	private JList<ImageIcon> westJlist;
	private JList<ImageIcon> centerJlist;
	
	//buttons
	private JButton backBtn, placeOrder, addToCartBtn, removeBtn, exitBtn;
	
	//default model view for products
	private DefaultListModel<ImageIcon> listModel;
	private DefaultListModel<ImageIcon> productModel;
	
	//radio button + group
	private JRadioButton perishables, dairy, fruit, meat, all;
	private ButtonGroup groupOfBtns;
	private JPanel bgPanel;
	
	//total of order
	private double total = 0;
	
	//account passed into OrderPage constructor
	//assign to global variable
	private Account a;
	
	//hash map to map image to product
	private HashMap<ImageIcon, Product> imageMap; 
	
	
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
		
		
		
		//east top panel to hold title
		eastTopPanel = new JPanel(new BorderLayout());
		
		//east panel to add to eastTopPanel
		eastPanel = new JPanel(new GridLayout(7, 1));
		
		//label and set alignment
		orderDetailsLabel = new JLabel("Order Details");
		orderDetailsLabel.setHorizontalAlignment(JLabel.CENTER);
		orderDetailsLabel.setFont(titleFont);
		
		//add label to panel
		eastTopPanel.add(orderDetailsLabel, BorderLayout.NORTH);
		
		//add east panel to east top panel
		eastTopPanel.add(eastPanel, BorderLayout.CENTER);
		
		//name of customer
		//set width
		//make un-editable
		nameTf = new JTextField(acc.getfName() + " " + acc.getlName());
		nameTf.setColumns(15);
		nameTf.setEditable(false);
		nameTf.setFont(generalFont);
		
		//create label and add to panel
		//set alignment				
		custNameLabel = new JLabel("<html><u>Customer Name</u></html");//used to underline label
		custNameLabel.setFont(generalFont);
		eastPanel.add(custNameLabel);
		custNameLabel.setHorizontalAlignment(JLabel.CENTER);
		//add text field to panel
		eastPanel.add(nameTf);
		
		//create label and add to panel
		//set alignment
		productTypeLabel = new JLabel("<html><u> Product Type </u></html");
		productTypeLabel.setFont(generalFont);
		eastPanel.add(productTypeLabel);
		productTypeLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//product type text field
		productTypeTf = new JTextField("");
		productTypeTf.setFont(generalFont);
		productTypeTf.setEditable(false);
		//add to panel
		eastPanel.add(productTypeTf);
		
		//create product price label
		productPriceLabel = new JLabel("<html><u> Product Price </u></html");
		productPriceLabel.setFont(generalFont);
		eastPanel.add(productPriceLabel);
		productPriceLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//price of product
		productPriceTf = new JTextField("");
		productPriceTf.setEditable(false);
		//add to panel
		eastPanel.add(productPriceTf);
		//add panel to frame
		add(eastTopPanel, BorderLayout.EAST);
		//eastPanel.add(new JLabel());
		
		//total bill
		totalTf = new JTextField("Total: €");
		totalTf.setFont(generalFont);
		totalTf.setEditable(false);
		
		//add to panel
		eastPanel.add(totalTf);
		
		
		
		//south panel
		//buttons to proceed or quit
		southPanel = new JPanel(new FlowLayout(1, 240, 10));
		
		//button to go back
		backBtn = new JButton("Back/Log Out");
		backBtn.setFont(generalFont);
		southPanel.add(backBtn);
		
		
		//button to place order
		placeOrder = new JButton("Place Order");
		placeOrder.setFont(generalFont);
		southPanel.add(placeOrder);
		
		//button to exit
		exitBtn = new JButton("Exit Application");
		exitBtn.setFont(generalFont);
		southPanel.add(exitBtn);
		
		add(southPanel, BorderLayout.SOUTH);
		
		
		
		//west panel
		//shopping cart
		westPanel = new JPanel(new BorderLayout());
		productsLabel = new JLabel("Products");
		productsLabel.setFont(titleFont);
		productsLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//add label to panel
		westPanel.add(productsLabel, BorderLayout.NORTH);
		
		perishables = new JRadioButton("Perishables"); 
		dairy = new JRadioButton("Dairy"); 
		fruit = new JRadioButton("Fruit"); 
		meat = new JRadioButton("Meat");
		all = new JRadioButton("All");
		
		bgPanel = new JPanel(new GridLayout(10, 1, 20, 20));
		bgPanel.add(perishables);
		bgPanel.add(dairy);
		bgPanel.add(fruit);
		bgPanel.add(meat);
		bgPanel.add(all);
		groupOfBtns = new ButtonGroup();
		
		groupOfBtns.add(perishables);
		groupOfBtns.add(dairy);
		groupOfBtns.add(fruit);
		groupOfBtns.add(meat);
		groupOfBtns.add(all);
		
		westPanel.add(bgPanel, BorderLayout.WEST);
		
		
		
		
		//read products from DB and populate product array list
		ArrayList<Product> test = DBConnector.readProducts();
		
		//populate list with images
		productModel = createProductModel(test);
		
		//pass product model list into JList
		westJlist = new JList<ImageIcon>(productModel);
		//set width of list
		westJlist.setFixedCellWidth(100);
		westScrollPane = new JScrollPane(westJlist);
		westScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//westScrollPane.setColumnHeader(shoppingCart);
		
		//add label to panel
		//panel to frame
		westPanel.add(westScrollPane, BorderLayout.CENTER);
		addToCartBtn = new JButton("Add");
		addToCartBtn.setFont(generalFont);
		westPanel.add(addToCartBtn, BorderLayout.SOUTH);
		add(westPanel, BorderLayout.WEST);
		
		
		//center panel (set alignment + font)
		centerPanel = new JPanel(new BorderLayout());
		shoppingCartLabel = new JLabel("Shopping Cart");
		shoppingCartLabel.setHorizontalAlignment(JLabel.CENTER);
		shoppingCartLabel.setFont(titleFont);
		
		//add to panel
		centerPanel.add(shoppingCartLabel, BorderLayout.NORTH);
		
		//remove button
		removeBtn = new JButton("Remove");
		removeBtn.setFont(generalFont);
		centerPanel.add(removeBtn, BorderLayout.SOUTH);
		
		//create model list
		listModel = new DefaultListModel<ImageIcon>();
		
		//create list and passing into  scroll pane
		//set horizontal scroll bars to NEVER
		centerJlist = new JList<ImageIcon>(listModel);
		
		centerScrollPane = new JScrollPane(centerJlist);
		centerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		centerPanel.add(centerScrollPane, BorderLayout.CENTER);
		
		
		add(centerPanel, BorderLayout.CENTER);
		setResizable(false);
		
		
		//add listeners on buttons
		ActionListenerClass listener = new ActionListenerClass();
		backBtn.addActionListener(listener);
		addToCartBtn.addActionListener(listener);
		removeBtn.addActionListener(listener);
		placeOrder.addActionListener(listener);
		exitBtn.addActionListener(listener);
	}
	
	
	//inner action listener
	public class ActionListenerClass implements ActionListener{
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//format number to two decimal places
			DecimalFormat df = new DecimalFormat("#0.00");
			
			//get source of event
			Object event = e.getSource();
			
			/* if event equal back button
			 * dispose order page and open home page
			 * */
			if(event == backBtn) {
				dispose();
				
				//draw new Home page using path to draw method
				HomePage.drawHome();
				
			}
			
			/* if event equal place order button
			 * 
			 * get no. of product in list
			 * create array list to store products
			 * 
			 * get product by selected image
			 * add products to array list
			 * 
			 * connect to DB to get last order No. given out
			 * create order and write to DB
			 * 
			 * dispose order page and jump to delivery page
			 * 
			 * */
			else if(event == placeOrder) {
				
				int noOfProducts = listModel.getSize();
				
				ArrayList<Product> prods = new ArrayList<>();
				
				for(int i = 0; i < noOfProducts; i++) {
					ImageIcon img = listModel.getElementAt(i);
					Product prod = imageMap.get(img);
					prods.add(prod);
					
				}
				
				int id = DBConnector.getLastOrderID() + 1;
				Order o = new Order(prods, total, id, a.getEmail());
				DBConnector.writeOrder(o, a);
				
				
				//draw delivery page
				DeliveryPageNew.drawDeliveryNew(a, o);
				
				//dispose order page
				dispose();
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
				
				ImageIcon item = westJlist.getSelectedValue();
				Product product = imageMap.get(item);
				if(item != null) {
					listModel.addElement(item);
					total += product.getPrice();
					totalTf.setText("Total: €" + df.format(total));
					
					//set details of product in east panel
					productPriceTf.setText("" + product.getPrice());
					productTypeTf.setText("" + product.getType());
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
				
				//if item equals -1, no element is selected
				if(item != -1) {
					ImageIcon icon = listModel.remove(item);
					//get product by image
					Product p = imageMap.get(icon);
					total -= p.getPrice();
					totalTf.setText("Total: €" + df.format(total));
					
					//fill fields in with details of product
					productPriceTf.setText("-" + p.getPrice());
					productTypeTf.setText("" + p.getType());
					
					
				}
			}
			
			//if exit button
			else if(event == exitBtn) {
				
				//dispose this page
				dispose();
			}
		}
	}
	
	
	/* create model list of images
	 * 
	 * initialize hash map and list model
	 * 
	 * create path to pictures
	 * create directory/file and pass in path
	 * 
	 * create array of files
	 * 
	 * for every product in the product array list
	 * 			for every file in the file array
	 * 
	 * 				compare names to find the right picture.
	 * 					once found, add product to list,
	 * 					map the image to product
	 * 						- and break out of inner for each loop
	 * 				
	 * 				catch exceptions
	 * 
	 * return list of images
	 * 
	 */
	private DefaultListModel<ImageIcon> createProductModel(ArrayList<Product> prods) {
		
		imageMap = new HashMap<>();
		ImageIcon icon;

		DefaultListModel<ImageIcon> temp = new DefaultListModel<>();
		
		String pathName = "src/pictures";
		
		File dir = new File(pathName);
		File[] files = dir.listFiles();
		
		for(Product p: prods) {
			
			for(File f: files) {
				
				if(f.getName().contains(p.getName())) {
					try {
						icon = new ImageIcon(ImageIO.read(f));
						temp.addElement(icon);
						imageMap.put(icon, p);
						break;
					} catch (IOException e) {
						System.out.println("Input/Output Error");
					}
				}
			}
		}
		
		
		return temp;
		
	}
	
	
	
	public static void drawOrder(Account acc) {
		
		OrderPage op = new OrderPage(acc);
		op.setTitle("Order");		
		//op.pack();
		op.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		op.setLocationRelativeTo(null);
		op.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		op.setVisible(true);
		op.requestFocusInWindow(true);
	}
	
	
	//tester
	//main method
	public static void main(String[] args){
		drawOrder(new Account("Somebody","Else","elseIf@mail.ie","TestPass", 0));
	}//end of main method
}

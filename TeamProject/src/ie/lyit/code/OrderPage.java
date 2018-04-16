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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import ie.lyit.data.Account;
import ie.lyit.data.Delivery;
import ie.lyit.data.Order;
import ie.lyit.data.Product;
import jdbc.DBConnector;

public class OrderPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//panels
	private JPanel northPanel, centerPanel, eastPanel, westPanel, southPanel;
	private JPanel removeBtnPanel, addBtnPanel, placeOrderPanel;
	
	//default font
	private Font generalFont = new Font("SanSerif", Font.BOLD, 22);
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
	
	//labels
	private JLabel titleLabel, orderNoLabel;
	private JLabel custNameLabel, productPriceLabel, productTypeLabel;
	
	//text fields
	private JTextField nameTf, productTypeTf, productPriceTf, totalTf;
	private JTextField orderNoTf;
	
	//scroll pane for lists
	private JScrollPane westScrollPane, centerScrollPane;
	
	//lists
	private JList<Product> westJlist;
	private JList<Product> centerJlist;
	
	//buttons
	private JButton backBtn, placeOrder, addToCartBtn, removeBtn, exitBtn;
	
	//default model view for products
	private DefaultListModel<Product> listModel;
	private DefaultListModel<Product> productModel;
	
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane tablePane;
	private String[] columnNames = {"Name", "Price", "Quantity"};
	
	//radio button + group
	private JRadioButton perishables, dairy, fruit, meat, veg, biscuits, all;
	private ButtonGroup groupOfBtns;
	private JPanel bgPanel;
	
	//total of order
	private double total = 0;
	
	//account passed into OrderPage constructor
	//assign to global variable
	private Account a;
	
	//hash map to map product to integer
	private HashMap<Product, Integer> countMap; 
	
	
	//constructor
	public OrderPage(Account acc) {
		
		//set spacing between component in frame
		setLayout(new BorderLayout(20, 10));
		
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
		eastPanel = new JPanel(new GridLayout(10, 1, 2, 2));
		
		//set titled border
		eastPanel.setBorder(BorderFactory.createTitledBorder(new TitledBorder(""), "Order Details", TitledBorder.CENTER,
				TitledBorder.TOP, titleFont));
		

		
		//name of customer
		//set width
		//make un-editable
		nameTf = new JTextField(acc.getfName() + " " + acc.getlName());
		nameTf.setColumns(15);
		nameTf.setEditable(false);
		nameTf.setFont(generalFont);
		//disable border + set alignment
		nameTf.setBorder(null);
		nameTf.setHorizontalAlignment(JTextField.CENTER);
		
		//create label and add to panel
		//set alignment + font				
		custNameLabel = new JLabel("<html><u>Customer Name</u></html");//used to underline label
		custNameLabel.setFont(generalFont);
		eastPanel.add(custNameLabel);
		custNameLabel.setHorizontalAlignment(JLabel.CENTER);
		//add text field to panel
		eastPanel.add(nameTf);
		
		//order number label
		orderNoLabel = new JLabel("<html><u>Order No.</u></html");
		orderNoLabel.setFont(generalFont);
		orderNoLabel.setHorizontalAlignment(JLabel.CENTER);
		eastPanel.add(orderNoLabel);
		
		//order number text field
		orderNoTf = new JTextField("");
		orderNoTf.setText((String.valueOf(DBConnector.getLastOrderID()+1)));
		orderNoTf.setEditable(false);
		orderNoTf.setFont(generalFont);
		orderNoTf.setHorizontalAlignment(JTextField.CENTER);
		orderNoTf.setBorder(null);
		
		eastPanel.add(orderNoTf);
		
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
		productTypeTf.setBorder(null);
		productTypeTf.setHorizontalAlignment(JTextField.CENTER);
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
		productPriceTf.setFont(generalFont);
		productPriceTf.setBorder(null);
		productPriceTf.setHorizontalAlignment(JTextField.CENTER);
		//add to panel
		eastPanel.add(productPriceTf);
		//add panel to frame
		add(eastPanel, BorderLayout.EAST);
		//eastPanel.add(new JLabel());
		
		//total bill
		totalTf = new JTextField("Total: €");
		totalTf.setFont(generalFont);
		totalTf.setEditable(false);
		totalTf.setBorder(null);
		
		//add to panel
		eastPanel.add(totalTf);
		
		//panel for place order button
		placeOrderPanel = new JPanel(new FlowLayout(1, 100, 5));
		placeOrder = new JButton("Place Order");
		placeOrder.setFont(generalFont);
		
		//add button to panel
		placeOrderPanel.add(placeOrder);
		
		//add flow panel to grid panel
		eastPanel.add(placeOrderPanel);
		
		
		//south panel
		//buttons to proceed or quit
		southPanel = new JPanel(new FlowLayout(1, 240, 10));
		
		//button to go back
		backBtn = new JButton("Back/Log Out");
		backBtn.setFont(generalFont);
		southPanel.add(backBtn);
		
		
		
		//button to exit
		exitBtn = new JButton("Exit Application");
		exitBtn.setFont(generalFont);
		southPanel.add(exitBtn);
		
		add(southPanel, BorderLayout.SOUTH);
		
		
		
		//west panel
		//shopping cart
		westPanel = new JPanel(new BorderLayout());
		
		
		//Radio buttons
		perishables = new JRadioButton("Perishables");
		perishables.setFont(generalFont);
		dairy = new JRadioButton("Dairy");
		dairy.setFont(generalFont);
		fruit = new JRadioButton("Fruit");
		fruit.setFont(generalFont);
		meat = new JRadioButton("Meat");
		meat.setFont(generalFont);
		veg = new JRadioButton("Veg");
		veg.setFont(generalFont);
		biscuits = new JRadioButton("Biscuits");
		biscuits.setFont(generalFont);
		all = new JRadioButton("All");
		all.setFont(generalFont);
		
		//add buttons to panel
		bgPanel = new JPanel(new GridLayout(10, 1, 20, 20));
		bgPanel.add(perishables);
		bgPanel.add(dairy);
		bgPanel.add(fruit);
		bgPanel.add(meat);
		bgPanel.add(veg);
		bgPanel.add(biscuits);
		bgPanel.add(all);
		
		all.setSelected(true);
		//add buttons to button group
		groupOfBtns = new ButtonGroup();
		groupOfBtns.add(perishables);
		groupOfBtns.add(dairy);
		groupOfBtns.add(fruit);
		groupOfBtns.add(meat);
		groupOfBtns.add(veg);
		groupOfBtns.add(biscuits);
		groupOfBtns.add(all);
		
		
		//add to panel
		westPanel.add(bgPanel, BorderLayout.WEST);
		
		
		westPanel.setBorder(BorderFactory.createTitledBorder(new TitledBorder(""), "Products", TitledBorder.CENTER,
				TitledBorder.TOP, titleFont));
		
		//read products from DB and populate product array list
		ArrayList<Product> test = DBConnector.readProducts();
		
		//populate list with products
		productModel = createProductModels(test);
		
		//pass product model list into JList
		westJlist = new JList<Product>(productModel);
		//set font on items in list
		westJlist.setFont(generalFont);
		//set width of list
		westJlist.setFixedCellWidth(250);
		westScrollPane = new JScrollPane(westJlist);
		westScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//add label to panel
		//panel to frame
		westPanel.add(westScrollPane, BorderLayout.CENTER);
		
		addToCartBtn = new JButton("Add");
		addToCartBtn.setFont(generalFont);
		
		addBtnPanel = new JPanel(new FlowLayout(1, 80, 10));
		addBtnPanel.add(addToCartBtn);
		
		//add blank space
		bgPanel.add(new JLabel(""));
		//add button to panel
		
		westPanel.add(addBtnPanel, BorderLayout.SOUTH);
		addBtnPanel.setAlignmentY(RIGHT_ALIGNMENT);
		
		//add panel to frame
		add(westPanel, BorderLayout.WEST);
		
		
		//center panel (set alignment + font)
		centerPanel = new JPanel(new BorderLayout());

		centerPanel.setBorder(BorderFactory.createTitledBorder(new TitledBorder(""), "Shopping Cart", TitledBorder.CENTER,
				TitledBorder.TOP, titleFont));
		
		
		//remove button
		removeBtn = new JButton("Remove");
		removeBtn.setFont(generalFont);
		
		//remove button panel
		removeBtnPanel = new JPanel(new FlowLayout(1, 80, 10));
		removeBtnPanel.add(removeBtn);
		centerPanel.add(removeBtnPanel, BorderLayout.SOUTH);
		
		//create table model
		tableModel = new DefaultTableModel(columnNames, 0);
		//create table with column headings
		table = new JTable(tableModel);
		
		tablePane = new JScrollPane(table);
		
		table.getTableHeader().setFont(generalFont);
		table.setFont(new Font("SanSerif", Font.ITALIC + Font.BOLD, 16));
		table.setRowHeight(30);
		table.setEnabled(false);
		//create model list
		//listModel = new DefaultListModel<Product>();
		
		//create list and passing into  scroll pane
		//set horizontal scroll bars to NEVER
		//centerJlist = new JList<Product>(listModel);
		//centerJlist.setFont(generalFont);
		
	//	centerScrollPane = new JScrollPane(centerJlist);
		//centerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//centerPanel.add(centerScrollPane, BorderLayout.CENTER);
		centerPanel.add(tablePane, BorderLayout.CENTER);
		
		add(centerPanel, BorderLayout.CENTER);
		setResizable(false);
		
		//initialize map
		countMap = new HashMap<>();
		
		
		//add listeners on buttons
		ActionListenerClass listener = new ActionListenerClass();
		backBtn.addActionListener(listener);
		addToCartBtn.addActionListener(listener);
		removeBtn.addActionListener(listener);
		placeOrder.addActionListener(listener);
		exitBtn.addActionListener(listener);
		perishables.addActionListener(listener);
		dairy.addActionListener(listener);
		fruit.addActionListener(listener);
		meat.addActionListener(listener);
		veg.addActionListener(listener);
		biscuits.addActionListener(listener);
		all.addActionListener(listener);
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
			
			else if(event == perishables) {
				
				//clear current model
				productModel.clear();
				
				//for every product in pList
				for(Product p: DBConnector.readProducts(perishables.getText())) {
					
					//add to current model (re-populate)
					productModel.addElement(p);
				}
				
				
				
			}
			else if(event == dairy) {

				//clear current model
				productModel.clear();
				
				//for every product in pList
				for(Product p: DBConnector.readProducts(dairy.getText())) {
					
					//add to current model (re-populate)
					productModel.addElement(p);
				}
			}
			else if(event == fruit) {
				
				//clear current model
				productModel.clear();
				
				//for every product in pList
				for(Product p: DBConnector.readProducts(fruit.getText())) {
					
					//add to current model (re-populate)
					productModel.addElement(p);
				}
			}
			
			else if(event == meat) {

				//clear current model
				productModel.clear();
				
				//for every product in pList
				for(Product p: DBConnector.readProducts(meat.getText())) {
					
					//add to current model (re-populate)
					productModel.addElement(p);
				}
			}
			else if(event == veg) {

				//clear current model
				productModel.clear();
				
				//for every product in pList
				for(Product p: DBConnector.readProducts(veg.getText())) {
					
					//add to current model (re-populate)
					productModel.addElement(p);
				}
			}
			
			
			else if(event == biscuits) {

				//clear current model
				productModel.clear();
				
				//for every product in pList
				for(Product p: DBConnector.readProducts(biscuits.getText())) {
					
					//add to current model (re-populate)
					productModel.addElement(p);
				}
			}
			
			
			else if(event == all) {
				
				//clear current model
				productModel.clear();
				
				//for every product in pList
				for(Product p: DBConnector.readProducts()) {
					
					//add to current model (re-populate)
					productModel.addElement(p);
				}
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
				
				int noOfProducts = tableModel.getRowCount();
				
				ArrayList<Product> prods = new ArrayList<>();
				
				for(int i = 0; i < noOfProducts; i++) {
					Product prod = listModel.getElementAt(i);
					//Product prod = imageMap.get(img);
					prods.add(prod);
					
				}
				
				int id = DBConnector.getLastOrderID() + 1;
				Order o = new Order(prods, total, id, a.getEmail());
				DBConnector.writeOrder(o, a);
				
				//mock delivery
				Delivery d = new Delivery("");
				
				//draw delivery page
				DeliveryPageNew.drawDeliveryNew(a, o, d);
				
				//dispose order page
				dispose();
			}
			
			/* if event equals add to cart button
			 * 
			 * map product to integer (quantity in cart)
			 * 
			 * accumulate total (Decimal format, 2 decimal places)
			 * set total text field to display total
			 * 
			 * */
			else if(event == addToCartBtn) {
				

				//swapped
				boolean removed = false;
				//get product from west list
				Product item = westJlist.getSelectedValue();
				
				
				//if a list item was selected
				if(item != null) {
					
					
					//if map doesn't contain product
					if(!countMap.containsKey(item)) {
						
						//map product to quantity - equal 1
						//(first occurrence of product)
						countMap.put(item, 1);
						
						
						//add new row to table
						//adding key name
						tableModel.addRow(new Object[] {item.getName(), 
								//adding key price
								String.valueOf(item.getPrice()), 
								//adding key value (Qty)
								String.valueOf(countMap.get(item))});
						
					}
					
					//else map already has product
					else if(countMap.containsKey(item)){
						
						//increment value (quantity)
						countMap.replace(item, countMap.get(item), countMap.get(item)+1);
						
						
						//if table model was cleared
						if(clear(tableModel)) {
							
							//for every entry in hash map
							for(Map.Entry<Product, Integer> ent: countMap.entrySet()) {

								//add row
								//adding key's (products) name
								tableModel.addRow(new Object[] {ent.getKey().getName(), 
										//adding key's price multiplied by value (Quantity)
										String.valueOf((df.format(ent.getKey().getPrice() * ent.getValue()))), 
										//adding quantity in shopping cart (value of hash map) 
										String.valueOf(ent.getValue())});
							}
						}
						
					}
					
					
					//set text fields
					total += item.getPrice();
					totalTf.setText("Total: €" + df.format(total));
					
					//set details of product in east panel
					productPriceTf.setText("€" + item.getPrice());
					productTypeTf.setText("" + item.getType());
					
					
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
				
				int item = (int)table.getSelectedColumn();
				
				//if item equals -1, no element is selected
				if(item != -1) {
					Product p = listModel.remove(item);
					//get product by image
					//Product p = imageMap.get(icon);
					total -= p.getPrice();
					totalTf.setText("Total: €" + df.format(total));
					
					//fill fields in with details of product
					productPriceTf.setText("€-" + p.getPrice());
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
	
	
	//clear table model
	private static boolean clear(DefaultTableModel tableModel) {
		
		//assign flag
		boolean removed = false;
		
		//clear table model
		//if row count more than zero
		if(tableModel.getRowCount() > 0){
			
			//for every row in table
			for(int i = tableModel.getRowCount()-1; i > -1; i--) {
				
				//remove
				tableModel.removeRow(i);
				
			}
			//flick variable
			removed = true;
			
		}
		
		return removed;
		
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
//	private DefaultListModel<ImageIcon> createProductModel(ArrayList<Product> prods) {
//
//		//imageMap = new HashMap<>();
//		ImageIcon icon;
//
//		DefaultListModel<ImageIcon> temp = new DefaultListModel<>();
//
//		String pathName = "src/pictures";
//
//		File dir = new File(pathName);
//		File[] files = dir.listFiles();
//
//		for(Product p: prods) {
//
//			for(File f: files) {
//
//				if(f.getName().contains(p.getName())) {
//					try {
//						icon = new ImageIcon(ImageIO.read(f));
//						temp.addElement(icon);
//						//imageMap.put(icon, p);
//						break;
//					} catch (IOException e) {
//						System.out.println("Input/Output Error");
//					}
//				}
//			}
//		}
//
//
//		return temp;
//
//	}
	
	
	//populate model list of products
	private DefaultListModel<Product> createProductModels(ArrayList<Product> prods){
		
		//create list to populate and pass back
		DefaultListModel<Product> temp = new DefaultListModel<Product>();
		
		//for every product in product array list
		for(Product p: prods) {
			
			//add element to model list 
			temp.addElement(p);
		}
		
		
		//return model list
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

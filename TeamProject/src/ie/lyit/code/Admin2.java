/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: GUI application Administrator second page.
 * 				Methods:
 * 						- Draw GUI
 * 
 * 				Classes:
 * 						- Inner ActionListener
 * 
 * 
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
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import ie.lyit.data.Product;
import jdbc.DBConnector;

public class Admin2 extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//instance fields
	//panels
	private JPanel westPanel, westBottomPanel;
	private JPanel northPanel, southPanel;
	private JPanel centerPanel;
	private AdminProducts apb2;
	private JPanel btnPanel;
	
	//Scroll pane, table and table data
	private JScrollPane tablePane;
	private JTable table;
	private String[] productColumnNames = {"No.", "Name", "Price", "Type", "Qty"};
	private String[][] tableData;
	private DefaultTableModel tableModel;
	
	//map product number to product 
	private HashMap<Integer, Product> map;
	private ArrayList<Product> productsList;
	
	//labels
	private JLabel titleLabel = new JLabel("Simple Shopping System");
	
	//title font
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
	
	//buttons
	private JButton backBtn, exitBtn;
	private JButton addBtn;
	private JButton editBtn, removeBtn;
	private JButton resetBtn;
	
	//constructor
	public Admin2() {
		
		//north panel
		//create north title panel
		northPanel = new JPanel();
		//create title label and add to north panel
		titleLabel.setFont(titleFont);
		northPanel.add(titleLabel);
		//set alignment and border of label
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setBorder(new LineBorder(Color.BLACK, 1));
		//add to frame
		add(northPanel, BorderLayout.NORTH);
		
		
		//center panel
		//create center panel -- main panel
		westPanel = new JPanel(new GridLayout(2, 1));
		
		//create bottom center panel
		westBottomPanel = new JPanel(new BorderLayout());
		
		//map products
		mapProducts(DBConnector.readProducts());

		//create panel
		apb2 = new AdminProducts(productsList.get(0));
		//add to bottom center panel
		westBottomPanel.add(apb2);
		updateBox();		
		//add panels to center panel
		westPanel.add(westBottomPanel);
		//add center panel to frame
		add(westPanel, BorderLayout.WEST);
		
		
		//south panel
		//create south panel
		southPanel = new JPanel(new FlowLayout(1, 240, 10));
		//back button
		backBtn = new JButton("Back");
		backBtn.setFont(new Font("SanSerif", Font.BOLD, 22));
		//add button to south panel
		southPanel.add(backBtn);
		
		//delivery button
		exitBtn = new JButton("Exit Application");
		exitBtn.setFont(new Font("SanSerif", Font.BOLD, 22));
		//add button to panel
		southPanel.add(exitBtn);
		
		//add panel to frame
		add(southPanel, BorderLayout.SOUTH);
		
		
		
		//create add button
		addBtn = new JButton("Add");
		
		//create edit button
		editBtn = new JButton("Edit");
		//create panel
		btnPanel = new JPanel(new GridLayout(1, 4, 20, 20));
		//add to panel
		westBottomPanel.add(btnPanel, BorderLayout.SOUTH);
		
		resetBtn = new JButton("Reset");
		resetBtn.setFont(apb2.getTfFont());
		btnPanel.add(resetBtn);
		btnPanel.add(editBtn);
		editBtn.setFont(apb2.getTfFont());
		btnPanel.add(addBtn);
		addBtn.setFont(apb2.getTfFont());
		removeBtn = new JButton("Remove");
		removeBtn.setFont(apb2.getTfFont());
		btnPanel.add(removeBtn);
		
		
		
		//east panel
		centerPanel = new JPanel(new GridLayout(1, 1));
		
		
		
		
		//create table with table model
		table = new JTable();
		
		updateTable();
		
		//set font on table headers
		table.getTableHeader().setFont(new Font("SanSerif", Font.BOLD, 16));
		table.setFont(apb2.getTfFont());
		table.setRowHeight(30);
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		//make table uneditable
		table.setEnabled(false);
		
		//create scroll pane, pass in table object
		tablePane = new JScrollPane(table);
		tablePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//add pane to east panel
		centerPanel.add(tablePane);

		
		
		//add east panel to frame
		add(centerPanel, BorderLayout.CENTER);

		
		//create action listener
		ActionListenerClass actionListener = new ActionListenerClass();
		//set listeners on buttons
		backBtn.addActionListener(actionListener);
		exitBtn.addActionListener(actionListener);
		addBtn.addActionListener(actionListener);
		editBtn.addActionListener(actionListener);
		removeBtn.addActionListener(actionListener);
		resetBtn.addActionListener(new BoxListener());
		
		
		//disable resizing of frame
		setResizable(false);
		
		//set listener
		apb2.setBoxListener(new BoxListener());
	}
	
	public class BoxListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			Object event = e.getSource();
			//reset button
			if(event == resetBtn) {
				
				apb2.resetFields();
				
			}
			//else its the combo box
			else{
				int id = apb2.getBoxValue();
				Product product = map.get(id);
				apb2.setData(product);
			}
		}
	}
	
	public class ActionListenerClass implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			Object event = e.getSource();
			
			if(event == backBtn) {
				
				//dispose page
				dispose();
				
				//open AdminHome page
				AdminHome.drawAdminHome();
			}
			//if exit button
			else if(event == exitBtn) {
				
				//dispose this page
				dispose();
			}
			
			//add button
			else if(event == addBtn) {
				//assume product has not been added
				boolean added = false;
				
				//get text in first panel fields
				String[] details = apb2.getAddPanelDetails();
				
				if(!(details[0].equals(null) || details[0].equals("") || 
						details[1].equals(null) || details[1].equals("") || 
						details[2].equals(null) || details[2].equals("") ||
						details[3].equals(null) || details[3].equals(""))) {

					Product p = new Product(details[0], Double.parseDouble(details[1]), (DBConnector.getLastProductID()+1), details[2], Integer.parseInt(details[3]));

					//add product to map, combo box + DB.
					map.put(p.getProductNo(), p);
					apb2.addBoxData("" + p.getProductNo());
					DBConnector.insertProduct(p);

					tableModel.addRow(new String[]{String.valueOf(p.getProductNo()), p.getName(), String.valueOf(p.getPrice()), p.getType(), String.valueOf(p.getQuantity())});
					//flick added to true
					added = true;
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Enter All Product Details", "Cannot Add Product", JOptionPane.INFORMATION_MESSAGE);
				}
				
				//if added
				if(added) {
					//reset text fields
					apb2.resetFields();
					//inform user
					JOptionPane.showMessageDialog(null, "Product has been Added", "ADDED", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			//edit button
			else if(event == editBtn) {
				//assume product has not been edited
				boolean edited = false;
				//get text in first panel fields
				String[] details = apb2.getAddPanelDetails();
				
				if(!(details[0].equals(null) || details[0].equals("") || 
						details[1].equals(null) || details[1].equals("") || 
						details[2].equals(null) || details[2].equals("") ||
						details[3].equals(null) || details[3].equals(""))) {
					
					Product product = DBConnector.readProduct(details[0]);
					
					//if product is null then there is no product in DB
					if(product != null) {

						//set price of product
						product.setPrice(Double.parseDouble(details[1]));
						product.setQuantity(Integer.parseInt(details[3]));

//						System.out.println(product.getPrice());
//						System.out.println(product.getQuantity());

						DBConnector.insertProduct(product);
						edited = true;
						
					}
					else if(product == null) {
						//inform user no product in DB
						JOptionPane.showMessageDialog(null, "Add Product to Edit Product", "Cannot Edit Product", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				//else nothing was entered
				else {
					JOptionPane.showMessageDialog(null, "Enter All Product Details", "Cannot Edit Product", JOptionPane.INFORMATION_MESSAGE);
				}
				
				//if product was successfully edited
				if(edited) {
					//reset panels
					apb2.resetFields();
					//inform user
					JOptionPane.showMessageDialog(null, "Product has been Edited", "EDITED", JOptionPane.INFORMATION_MESSAGE);
					
				}
				
			}
			
			//remove button
			else if(event == removeBtn) {
				boolean removed = false;
				
				int value = apb2.getBoxValue();
				
				removed = DBConnector.removeProduct(value);
				
				if(removed) {
					apb2.resetFields();
					map.remove(value);
					apb2.removeBoxData("" + value);
					JOptionPane.showMessageDialog(null, "Product Has Been Removed", "Product Removed", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Product Not Removed", "Cannot Remove Product", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			
		
			updateTable();
		}
		
		
	}
	
	//map number to product
	private void mapProducts(ArrayList<Product> products) {
		map = new HashMap<>();
		productsList = products;
		//for every product in products
		for(Product p: products) {
			//map the product number to product
			map.put(p.getProductNo(), p);
		}
	}
	
	//update combo box
	public void updateBox() {
		
		apb2.setBoxData(DBConnector.getProductIds());
	}
	
	//update table
	public void updateTable() {

		//create 2D array
		tableData = DBConnector.getProductsTableData();

		//create default model list with table columns and data
		tableModel = new DefaultTableModel(tableData, productColumnNames);
		table.setModel(tableModel);
	}

	
	
	//draw GUI
 	public static void drawAdmin2() {
		
		Admin2 a2 = new Admin2();
		a2.setTitle("Administrator Products");		
		//a2.pack();
		a2.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		a2.setLocationRelativeTo(null);
		a2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a2.setVisible(true);
		a2.requestFocusInWindow(true);
	}
	
	//main
	public static void main(String[] args) {
		drawAdmin2();
	}

}

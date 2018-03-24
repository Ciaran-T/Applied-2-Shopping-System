/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: GUI application, Administrator bigger panel builder.
 * 					Methods:
 * 							- Get first panel text
 * 							- Get all panel text
 * 
 * 
 */


package ie.lyit.code;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class AdminPanelBuilder extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//instance fields
	//panel
	private JPanel panel1;
	//inner panels
	private Admin2PanelBuilder apb2, apb3, apb4;
	//labels
	private JLabel nameLabel, priceLabel, typeLabel, qtyLabel;
	
	private String s = "Products to ";
	
	//constructor
	public AdminPanelBuilder(String editOrAdd) {
		
		//set layout
		this.setLayout(new GridLayout(1, 4, 5, 10));
		
		//set border
		this.setBorder(new TitledBorder(s + editOrAdd));
		
		//create panel
		panel1 = new JPanel(new GridLayout(5, 1));
		panel1.add(new JLabel());
		
		//create labels
		nameLabel = new JLabel("Enter product name: ");
		priceLabel = new JLabel("Enter product price: ");
		typeLabel = new JLabel("Enter product type: ");
		qtyLabel = new JLabel("Enter product quantity");
		
		//add to panel1
		panel1.add(nameLabel);
		panel1.add(priceLabel);
		panel1.add(typeLabel);
		panel1.add(qtyLabel);
		
		//add panel1 to this panel
		add(panel1);
		
		//create panels and add to this panel
		apb2 = new Admin2PanelBuilder();
		add(apb2);
		//apb3 = new Admin2PanelBuilder();
		//add(apb3);
		//apb4 = new Admin2PanelBuilder();
		//add(apb4);
		
		
	}
	
	//get first panel details
	public String[] getAddPanelDetails() {
		
		String[] details = new String[4];
		
		details[0] = "" + apb2.getNameText();
		details[1] = "" + apb2.getPriceText();
		details[2] = "" + apb2.getTypeText();
		details[3] = "" + apb2.getQtyText();
		
		return details;
	}
	
	
//	//get all panel details
//	public String[] getAddAllPanelDetails() {
//		
//		String[] details = new String[9];
//
//		details[0] = "" + apb2.getNameText();
//		details[1] = "" + apb2.getPriceText();
//		details[2] = "" + apb2.getTypeText();
//		
//		details[3] = "" + apb3.getNameText();
//		details[4] = "" + apb3.getPriceText();
//		details[5] = "" + apb3.getTypeText();
//		
//		details[6] = "" + apb4.getNameText();
//		details[7] = "" + apb4.getPriceText();
//		details[8] = "" + apb4.getTypeText();
//
//		return details;
//		
//	}
	
	
	//tester
	//main
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		AdminPanelBuilder apb = new AdminPanelBuilder("add");	
		jf.add(apb);
		jf.pack();
		//jf.setSize(550, 400);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);

	}//end main

}

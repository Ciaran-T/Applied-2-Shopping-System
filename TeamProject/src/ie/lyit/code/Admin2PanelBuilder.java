/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: GUI application, Administrator panel builder.
 * 				Methods:
 * 						- Get methods for each text field
 * 
 * 
 */


package ie.lyit.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;



public class Admin2PanelBuilder extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//instance fields
	private JLabel titleLabel;
	private JComboBox<String> box;
	
	private JTextField nameTf, priceTf, typeTf, qtyTf;
	//font
	private Font labelFont = new Font("SanSerif", Font.BOLD, 22);
	private Font tfFont = new Font("SanSerif", Font.PLAIN, 22);
	
	
	//constructor
	public Admin2PanelBuilder() {
		
		//set line border
		//this.setBorder(new LineBorder(Color.BLACK, 1));
		//set layout of panel
		this.setLayout(new GridLayout(5, 1, 50, 1));
		
//		//create title label
//		titleLabel = new JLabel("Product details");
//		//add font
//		titleLabel.setFont(labelFont);
//		//add to panel
//		add(titleLabel);
		box = new JComboBox<>();
		
		add(box);
		
		//set horizontal alignment of label
		//titleLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//create text fields and add to panel
		nameTf = new JTextField(10);
		add(nameTf);
		priceTf = new JTextField();
		add(priceTf);
		typeTf = new JTextField();
		add(typeTf);
		qtyTf = new JTextField();
		add(qtyTf);
		
		//set margin
		nameTf.setMargin(new Insets(2, 2, 2, 2));
		priceTf.setMargin(new Insets(2, 2, 2, 2));
		typeTf.setMargin(new Insets(2, 2, 2, 2));
		qtyTf.setMargin(new Insets(2, 2, 2, 2));
		
		nameTf.setFont(tfFont);
		priceTf.setFont(tfFont);
		typeTf.setFont(tfFont);
		qtyTf.setFont(tfFont);
	}

	//getters
	public String getNameText() {
		
		return nameTf.getText();
	}
	
	public String getPriceText() {
		
		return priceTf.getText();
	}
	
	public String getTypeText() {
		
		return typeTf.getText();
	}
	
	public String getQtyText() {
		
		return qtyTf.getText();
	}
	
	public void resetFields() {
		
		nameTf.setText("");
		priceTf.setText("");
		typeTf.setText("");
		qtyTf.setText("");
	}
	
	public void setBoxData(String[] args) {
		//iterate over array adding to combo box
		for(String s: args) {
			box.addItem(s);
		}

	}

}

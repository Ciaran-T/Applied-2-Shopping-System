/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: GUI application Admin products page.
 * 
 * 
 * 
 */


package ie.lyit.code;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import ie.lyit.data.Account;
import ie.lyit.data.Product;

public class AdminProducts extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//instance fields
	private JLabel numLbl, nameLbl, priceLbl, qtyLbl, typeLbl;
	private JComboBox<String> box;

	private JTextField nameTf, priceTf, typeTf, qtyTf;
	//font
	private Font labelFont = new Font("SanSerif", Font.BOLD, 22);
	private Font tfFont = new Font("SanSerif", Font.PLAIN, 22);
	private Font titleBorderFont = new Font("SanSerif", Font.BOLD, 25);

	private String s = "Details";

	//constructor
	public AdminProducts(Object obj) {

		String[] type = new String[6];
		if(obj instanceof Product) {
			type[0] = "Product ";
			type[1] = "Number:";
			type[2] = "Name:";
			type[3] = "Price:";
			type[4] = "Type:";
			type[5] = "Quantity:";
		}else if(obj instanceof Account){
			type[0] = "Customer ";
			type[1] = "Email:";
			type[2] = "First Name:";
			type[3] = "Last Name:";
			type[4] = "Password:";
			type[5] = "Orders:";
		}
		//set line border
		this.setBorder(BorderFactory.createTitledBorder(new TitledBorder(type[0] + s), type[0] + s,  TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, titleBorderFont));
		//set layout of panel
		this.setLayout(new GridLayout(5, 2, 10, 10));

		//create combo box
		box = new JComboBox<>();

		// create labels
		numLbl = new JLabel(type[0] + type[1]);
		numLbl.setFont(labelFont);
		numLbl.setHorizontalAlignment(JLabel.RIGHT);
		nameLbl = new JLabel(type[0] + type[2]);
		nameLbl.setFont(labelFont);
		nameLbl.setHorizontalAlignment(JLabel.RIGHT);
		typeLbl = new JLabel(type[0] + type[4]);
		typeLbl.setFont(labelFont);
		typeLbl.setHorizontalAlignment(JLabel.RIGHT);
		qtyLbl = new JLabel(type[0] + type[5]);
		qtyLbl.setFont(labelFont);
		qtyLbl.setHorizontalAlignment(JLabel.RIGHT);
		priceLbl = new JLabel(type[0] + type[3]);
		priceLbl.setFont(labelFont);
		priceLbl.setHorizontalAlignment(JLabel.RIGHT);

		// add labels 
		add(numLbl);

		//add combo box to this panel
		add(box);

		add(nameLbl);
		//create text fields and add to panel
		nameTf = new JTextField(10);
		add(nameTf);

		add(priceLbl);
		priceTf = new JTextField();
		add(priceTf);

		add(typeLbl);
		typeTf = new JTextField();
		add(typeTf);

		add(qtyLbl);
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
		box.setFont(tfFont);

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

	public Font getTfFont() {

		return tfFont;
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

	public void addBoxData(String s) {
		box.addItem(s);
	}

	public void removeBoxData(String s) {
		box.removeItem(s);
	}

	public int getBoxValue() {

		String s = (String) box.getSelectedItem();
		return Integer.parseInt(s);
	}

	public String getBoxEmail() {

		return (String)box.getSelectedItem();
	}

	public void setData(Object obj) {

		if(obj instanceof Product) {

			Product p = (Product)obj;
			nameTf.setText(p.getName());
			typeTf.setText(p.getType());
			priceTf.setText(""+ p.getPrice());
			qtyTf.setText("" + p.getQuantity());
		}
		else if(obj instanceof Account) {

			Account a = (Account) obj;
			nameTf.setText(a.getfName());
			typeTf.setText(a.getlName());
			priceTf.setText(a.getPassword());
			qtyTf.setText("" + a.getOrders());
		}
	}
	
	//get first panel details
	public String[] getAddPanelDetails() {

		String[] details = new String[4];

		details[0] = "" + nameTf.getText();
		details[1] = "" + priceTf.getText();
		details[2] = "" + typeTf.getText();
		details[3] = "" + qtyTf.getText();

		return details;
	}

	public void setBoxListener(ActionListener l) {
		box.addActionListener(l);
	}

}

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
	public AdminProducts() {

		//set line border
		this.setBorder(BorderFactory.createTitledBorder(new TitledBorder(s), s,  TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, titleBorderFont));
		//set layout of panel
		this.setLayout(new GridLayout(5, 2, 10, 10));

		//create combo box
		box = new JComboBox<>();

		// create labels
		numLbl = new JLabel("Choose number: ");
		numLbl.setFont(labelFont);
		nameLbl = new JLabel("Name: ");
		nameLbl.setFont(labelFont);
		typeLbl = new JLabel("Type: ");
		typeLbl.setFont(labelFont);
		qtyLbl = new JLabel("Quantity: ");
		qtyLbl.setFont(labelFont);
		priceLbl = new JLabel("Price: ");
		priceLbl.setFont(labelFont);

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

	public int getBoxValue() {

		String s = (String) box.getSelectedItem();
		return Integer.parseInt(s);
	}

	public void setData(Product p) {
		nameTf.setText(p.getName());
		typeTf.setText(p.getType());
		priceTf.setText(""+ p.getPrice());
		qtyTf.setText("" + p.getQuantity());
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

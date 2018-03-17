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
import java.awt.GridLayout;
import java.awt.Insets;

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
	
	private JTextField nameTf, priceTf, typeTf;
	
	
	//constructor
	public Admin2PanelBuilder() {
		
		//set line border
		//this.setBorder(new LineBorder(Color.BLACK, 1));
		//set layout of panel
		this.setLayout(new GridLayout(4, 1, 0, 1));
		
		//create title label
		titleLabel = new JLabel("Product details");
		//add to panel
		add(titleLabel);
		
		//set horizontal alignment of label
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//create text fields and add to panel
		nameTf = new JTextField("");
		add(nameTf);
		priceTf = new JTextField("");
		add(priceTf);
		typeTf = new JTextField("");
		add(typeTf);
		
		//set margin
		nameTf.setMargin(new Insets(2, 2, 2, 2));
		priceTf.setMargin(new Insets(2, 2, 2, 2));
		priceTf.setMargin(new Insets(2, 2, 2, 2));
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
	
	
	
	//tester
	//main
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		Admin2PanelBuilder apb = new Admin2PanelBuilder();	
		jf.add(apb);
		jf.pack();
		//jf.setSize(550, 400);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		

	}//end main

}

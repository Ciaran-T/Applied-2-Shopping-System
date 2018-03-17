/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: GUI application, Administrator bigger panel builder.
 * 
 * 
 */


package ie.lyit.code;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import javafx.scene.layout.Border;

public class AdminPanelBuilder extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//instance fields
	//panel
	private JPanel panel1;
	//inner panels
	Admin2PanelBuilder apb2, apb3, apb4;
	//labels
	private JLabel titleLabel;
	private JLabel nameLabel, priceLabel, typeLabel;
	
	private String s = "Products to ";
	
	//constructor
	public AdminPanelBuilder(String removeOrAdd) {
		
		//set layout
		this.setLayout(new GridLayout(1, 4, 5, 10));
		
		//set border
		this.setBorder(new TitledBorder(s + removeOrAdd));
		
		//titleLabel = new JLabel();
		
		//create panel
		panel1 = new JPanel(new GridLayout(4, 1));
		panel1.add(new JLabel());
		
		//create labels
		nameLabel = new JLabel("Enter product name: ");
		priceLabel = new JLabel("Enter product price: ");
		typeLabel = new JLabel("Enter product type");
		
		//add to panel1
		panel1.add(nameLabel);
		panel1.add(priceLabel);
		panel1.add(typeLabel);
		
		//add panel1 to panel
		add(panel1);
		
		
	}
	
	
	
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

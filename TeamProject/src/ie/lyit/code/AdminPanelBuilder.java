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

public class AdminPanelBuilder extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//instance fields
	//inner panels
	Admin2PanelBuilder apb1, apb2, apb3;
	//labels
	private JLabel titleLabel;
	private JLabel nameLabel, priceLabel, typeLabel;
	
	//constructor
	public AdminPanelBuilder() {
		
		//set layout
		this.setLayout(new GridLayout(1, 3, 5, 10));
		
		
	}
	
	
	
	//tester
	//main
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		AdminPanelBuilder apb = new AdminPanelBuilder();	
		jf.add(apb);
		jf.pack();
		//jf.setSize(550, 400);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);

	}//end main

}

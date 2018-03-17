/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: GUI application, Administrator panel builder.
 * 
 * 
 */


package ie.lyit.code;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Admin2PanelBuilder extends JPanel {
	
	//instance fields
	private JLabel titleLabel;
	
	private JTextField nameTf, priceTf, typeTf;
	
	
	//constructor
	public Admin2PanelBuilder() {
		
		//set line border
		this.setBorder(new LineBorder(Color.BLACK, 1));
		//set layout of panel
		this.setLayout(new GridLayout(4, 1));
		
		//create title labe
		titleLabel = new JLabel("Product");
		//add to panel
		add(titleLabel);
		
		//set horizontal alignment of label
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		
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

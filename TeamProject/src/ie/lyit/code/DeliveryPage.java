/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: GUI application delivery page template.
 * 
 * 
 */	


package ie.lyit.code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ie.lyit.data.Account;
import ie.lyit.data.Order;
import ie.lyit.data.Product;

public class DeliveryPage extends JFrame {
	
	//instance fields
	private JLabel titleLabel;
	
	private JPanel northPanel;
	
	//default font
	private Font generalFont = new Font("SanSerif", Font.BOLD, 15);
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
	
	private Account a;
	private Order o;
	
	//constructor
	public DeliveryPage(Account a, Order o) {
		
		this.a = a;
		this.o = o;
		
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
		
		
		
		
	}
	
	
	public static void drawDelivery(Account acc, Order o) {
		
		DeliveryPage dp = new DeliveryPage(acc, o);
		
		dp.setTitle("Delivery");		
		//op.pack();
		dp.setSize(550, 400);
		dp.setLocationRelativeTo(null);
		dp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dp.setVisible(true);
		dp.requestFocusInWindow(true);
		
	}
	
	
	
	
	public static void main(String[] args) {


	}

}

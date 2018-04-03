package ie.lyit.code;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminHome extends JFrame{

	//instance fields
	private JPanel northPanel, centerPanel;
	
	//labels
	private JLabel titleLabel = new JLabel("Simple Shopping System");
	
	//font
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
	
	
	//constructor
	public AdminHome() {
		
		northPanel = new JPanel();
		
		titleLabel.setFont(titleFont);
		
		northPanel.add(titleLabel);
		
		add(northPanel, BorderLayout.NORTH);
		
	}
	
	
	
	
	//draw GUI
 	public static void drawAdminHome() {
		
		AdminHome ah = new AdminHome();
		ah.setTitle("Administrator Home");		
		//a2.pack();
		ah.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		ah.setLocationRelativeTo(null);
		ah.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ah.setVisible(true);
		ah.requestFocusInWindow(true);
	}
	
 	//main
	public static void main(String[] args) {
		
		drawAdminHome();

	}

}

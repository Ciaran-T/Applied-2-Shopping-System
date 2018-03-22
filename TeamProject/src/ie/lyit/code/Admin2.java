/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: GUI application Administrator second page.
 * 				Methods:
 * 						- Draw GUI
 * 
 * 				Classes:
 * 						- Inner ActionListener
 * 
 * 
 */	


package ie.lyit.code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Admin2 extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//instance fields
	//panels
	private JPanel centerPanel, centerTopPanel, centerBottomPanel;
	private JPanel centerTopSouthPanel, centerBottomSouthPanel;
	private JPanel northPanel, southPanel;
	private AdminPanelBuilder apb1, apb2;
	//panel type
	private String add = "add";
	private String remove = "remove";
	private String update = "update";
	
	//labels
	private JLabel titleLabel = new JLabel("Simple Shopping System");
	
	//title font
	private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
	
	//buttons
	private JButton backBtn, deliveryScheduleBtn;
	private JButton addBtn, addAllBtn;
	private JButton removeBtn, removeAllBtn;
	
	//constructor
	public Admin2() {
		
		//north panel
		//create north title panel
		northPanel = new JPanel();
		//create title label and add to north panel
		titleLabel.setFont(titleFont);
		northPanel.add(titleLabel);
		//set alignment and border of label
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setBorder(new LineBorder(Color.BLACK, 1));
		//add to frame
		add(northPanel, BorderLayout.NORTH);
		
		
		//center panel
		//create center panel -- main panel
		centerPanel = new JPanel(new GridLayout(3, 1, 0, 10));
		//create top center panel
		centerTopPanel = new JPanel(new BorderLayout());
		//create bottom center panel
		centerBottomPanel = new JPanel(new BorderLayout());
		
		//create panel
		apb1 = new AdminPanelBuilder(add);
		//add to top center panel
		centerTopPanel.add(apb1);
		//create panel
		apb2 = new AdminPanelBuilder(remove);
		//add to bottom center panel
		centerBottomPanel.add(apb2);
		
		//add panels to center panel
		centerPanel.add(centerTopPanel);
		centerPanel.add(centerBottomPanel);
		//add center panel to frame
		add(centerPanel, BorderLayout.CENTER);
		
		//south panel
		//create south panel
		southPanel = new JPanel(new GridLayout(1, 2));
		//back button
		backBtn = new JButton("Back/Logout");
		//add button to south panel
		southPanel.add(backBtn);
		
		//delivery button
		deliveryScheduleBtn = new JButton("Go to Delivery Schedule");
		//add button to panel
		southPanel.add(deliveryScheduleBtn);
		
		//add panel to frame
		add(southPanel, BorderLayout.SOUTH);
		
		
		//create add buttons
		addBtn = new JButton("Add");
		addAllBtn = new JButton("Add All");
		//create panel
		centerTopSouthPanel = new JPanel(new GridLayout(1, 4));
		//add to panel
		centerTopPanel.add(centerTopSouthPanel, BorderLayout.SOUTH);
		//add buttons to panel
		centerTopSouthPanel.add(new JLabel());//blank for space
		centerTopSouthPanel.add(addBtn);
		centerTopSouthPanel.add(new JLabel());
		centerTopSouthPanel.add(addAllBtn);
		
		//create remove buttons
		removeBtn = new JButton("Remove");
		removeAllBtn = new JButton("Remove All");
		//create panel
		centerBottomSouthPanel = new JPanel(new GridLayout(1, 4));
		//add to panel
		centerBottomPanel.add(centerBottomSouthPanel, BorderLayout.SOUTH);
		centerBottomSouthPanel.add(new JLabel());
		centerBottomSouthPanel.add(removeBtn);
		centerBottomSouthPanel.add(new JLabel());
		centerBottomSouthPanel.add(removeAllBtn);

		
		//create action listener
		ActionListenerClass actionListener = new ActionListenerClass();
		//set listeners on buttons
		backBtn.addActionListener(actionListener);
		deliveryScheduleBtn.addActionListener(actionListener);
		addBtn.addActionListener(actionListener);
		addAllBtn.addActionListener(actionListener);
		removeBtn.addActionListener(actionListener);
		removeAllBtn.addActionListener(actionListener);
		
		
		//disable resizing of frame
		setResizable(false);
		
		
	}
	
	public class ActionListenerClass implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			Object event = e.getSource();
			
			if(event == backBtn) {
				
				//dispose page
				dispose();
				
				//open Admin1 page
				Admin1.drawAdmin1();
			}
			// TODO - Event handling
			else if(event == deliveryScheduleBtn) {
				
			}
			else if(event == addBtn) {
				
				
				//get text in first panel fields
				String[] details = apb1.getAddFirstPanelDetails();
				
				//test
				for(int i = 0; i < details.length; i++) {
					
					System.out.println(details[i]);
				}
				
			}
			else if(event == addAllBtn) {
				
				
				
			}
			else if(event == removeBtn) {
				
				
				
			}
			else if(event == removeAllBtn) {
				
				
			}
			
		}
		
	}
	

	
	//draw GUI
	public static void drawAdmin2() {
		
		Admin2 a2 = new Admin2();
		a2.setTitle("Administrator");		
		//a2.pack();
		a2.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		a2.setLocationRelativeTo(null);
		a2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a2.setVisible(true);
		a2.requestFocusInWindow(true);
	}
	
	//main
	public static void main(String[] args) {
		drawAdmin2();
	}

}

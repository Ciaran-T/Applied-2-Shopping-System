package ie.lyit.code;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import ie.lyit.data.Account;
import ie.lyit.data.Order;
import ie.lyit.data.Product;
import ie.lyit.data.Delivery;


public class DeliveryPageNew extends JFrame{

   private static final long serialVersionUID = 1L;
	
	//create Account and Product objects
   private Account a;
   private Order o;
   private Delivery delDets; // = new Delivery();

	//panels
   private JPanel northPanel, centerPanel, eastPanel, westPanel, southPanel;

	//default font
   private Font generalFont = new Font("SanSerif", Font.BOLD, 15);
   private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);

	//labels
   private JLabel jlbCustAdress, jlbOrderDetails, jlbHeader, jlbDeliveryDate, jlbSelDate;

	//text fields
   private JTextField jtfAddressL1, jtfAddressL2, jtfAddressL3, jtfCity, jtfCounty;
	
	//text Area
   private JTextArea jtaAltInstructions;

   private JButton confirmDelDetsBtn, backBtn, jBtn;
	
	
	///TEST USER DETAILS PANEL
   private JLabel titleLabel, custDetailsLabel, shoppingCartLabel, productsLabel, jlbAltInstructions, jlbDelDate;
   private JTextField name, email, password, total;
	
   public DeliveryPageNew(Account a, Order o, Delivery delDets) {
   	
   	//assign Objects
      this.a = a;
      this.o = o;
      this.delDets = delDets;
   	
   	//add header to north panel
      northPanel = new JPanel();
      jlbHeader = new JLabel("Enter Delivery Details");
      jlbHeader.setFont(titleFont);
      jlbHeader.setBorder(new LineBorder(Color.BLACK, 1));
   	//add label to panel
      northPanel.add(jlbHeader);
   	//add panel to frame
      add(northPanel, BorderLayout.NORTH);
   
   	//Centre Panel Home Address
      centerPanel = new JPanel();
      centerPanel.setLayout(new GridLayout(8,1));
      centerPanel.add(jlbCustAdress = new JLabel("Enter Home Address"));
      centerPanel.add(jtfAddressL1 = new JTextField("Address Line 1", 20));
      centerPanel.add(jtfAddressL2 = new JTextField("Address Line 2", 20));
      centerPanel.add(jtfAddressL3 = new JTextField("Address Line 3", 20));
      centerPanel.add(jtfCity = new JTextField("City/Town", 20));
      centerPanel.add(jtfCounty = new JTextField("County", 20));
   
   	// add alternative delivery instructions here
      centerPanel.add(jlbAltInstructions = new JLabel("Alternative Delivery Instructions: "));
      centerPanel.add(jtaAltInstructions = new JTextArea("Enter Alternative Delivery Instructions",5,20));
   	
   	//add panel to frame
      add(centerPanel, BorderLayout.CENTER);
   	
   	//east panel
      eastPanel = new JPanel(new GridLayout(7,1));
      //label and set alignment
      jlbOrderDetails = new JLabel("Order Details");
      jlbOrderDetails.setHorizontalAlignment(JLabel.CENTER);
      jlbOrderDetails.setFont(generalFont);
   	//add label to panel
      eastPanel.add(jlbOrderDetails);
      name = new JTextField(a.getfName() + " " + a.getlName());
      //name.setColumns(15);
      name.setEditable(false);
   
      //order
      JTextField jtfOrderEmail = new JTextField(o.getCustomerEmail());
      jtfOrderEmail.setEditable(false);
      JTextField jtfOrderID = new JTextField("" + o.getOrderID());
      jtfOrderID.setEditable(false);
      JTextField jtfOrderTotal = new JTextField(""+o.getTotal());
      jtfOrderTotal.setEditable(false);
   
      JTextArea jtaProducts = new JTextArea(""+ o.getProducts(),20,10);
      jtaProducts.setEditable(false);
   
   	//add to panel
      eastPanel.add(name);
      eastPanel.add(jtfOrderEmail);
      eastPanel.add(jtfOrderID);
      eastPanel.add(jtfOrderTotal);
      eastPanel.add(jtaProducts);
      add(eastPanel, BorderLayout.EAST);
   	
   	//southPanel Buttons
      southPanel = new JPanel();
      southPanel.setLayout(new GridLayout(1,1));
      southPanel.add(backBtn = new JButton("Go Back"), BorderLayout.EAST);
      southPanel.add(confirmDelDetsBtn = new JButton("Confirm Delivery Details"), BorderLayout.WEST);
      add(southPanel, BorderLayout.SOUTH);
   
   	//Action Listeners
   	//add action listens to button
      backBtn.addActionListener(new ActionListenerClass());
      confirmDelDetsBtn.addActionListener(new ActionListenerClass());
   
   
   		//Listeners
   		//add focus listener to text fields
      ListenerClass focusListener = new ListenerClass();
      jtfAddressL1.addFocusListener(focusListener);
      jtfAddressL2.addFocusListener(focusListener);
      jtfAddressL3.addFocusListener(focusListener);
      jtfCounty.addFocusListener(focusListener);
      jtfCity.addFocusListener(focusListener);
      jtaAltInstructions.addFocusListener(focusListener);
   		
   	//add in delivery combobox
   		//combo boxes to select a date
      westPanel = new JPanel(new FlowLayout());
   		
   		//fill box2 of current month + next month 
      Calendar cal = Calendar.getInstance();
      int currentDay = cal.get(Calendar.DAY_OF_MONTH); //current day
      int currentMonth = cal.get(Calendar.MONTH);
      int nextMonth = currentMonth;
      int year = cal.get(Calendar.YEAR);
      int nextYear = year +1;
      String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
      int nextDay = currentDay;
      int[] days = new int[20];
      String[] dates = new String[6];
     
      for(int i = 1; i < dates.length; i++)
      {
         days[i] = currentDay;
         dates[0] = "Select Date";
         dates[i] = days[i] + " " + month;
         currentDay++;
         if(currentDay > cal.getActualMaximum(Calendar.DAY_OF_MONTH))
         {
            currentDay = 1;
            if(currentDay == 1){
               cal.set(Calendar.MONTH, nextMonth + 1);
               month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());}
         }   
         if((currentDay > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) && (cal.get(Calendar.MONTH) == cal.getActualMaximum(Calendar.MONTH)))
         {
            cal.set(Calendar.YEAR, nextYear);
         }
      }
   
      // for(int i = 0; i < dates.length; i++)
   //       {
   //          System.out.println(dates[i] + " "  + year);
   //       }
      String[] times = {"Select time" ,"10:00", "12:00", "14:00", "16:00"};
   		
      final JComboBox<String> selDate = new JComboBox<String>(dates); //create combobox for date
      final JComboBox<String> selTime = new JComboBox<String>(times); //create combobox for time
      selDate.setSelectedItem(cal.get(Calendar.MONTH));
      selDate.addItemListener(
         new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
              	
               if(e.getStateChange() == ItemEvent.SELECTED)
               {
                  //jlbDelDate.setText(delDets.getDelivery());//display current selected del date
                  delDets.setDeliveryDate(selDate.getSelectedItem().toString());
               }
            }
         });
   	   
      selTime.addItemListener(
         new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
              	
               if(e.getStateChange() == ItemEvent.SELECTED)
               {
                  //jlbDelDate.setText(selDate.getSelectedItem().toString());//display current selected del date
                  delDets.setDeliveryTime(selTime.getSelectedItem().toString());
                  //jlbDelDate.setText(delDets.getDelivery());
               }
              	
              	///String dDate = jlbDelDate.getText();
              	////deliveryDate = dDate;
            }
         });
   
      selDate.setVisible(true);
      selTime.setVisible(true);   
      westPanel.add(jlbSelDate = new JLabel("Select Delivery Date: "));
      westPanel.add(selDate);
      westPanel.add(selTime);
      jlbDelDate = new JLabel("");
      westPanel.add(jlbDelDate);
      add(westPanel, BorderLayout.NORTH);
   
   }//end of constructor


		//inner FocusListener
   private class ListenerClass implements FocusListener{
   
      @Override
      	public void focusGained(FocusEvent f) {
      
      		//get source of event
         Object event = f.getSource();
      
      		/* if event equals any of the listeners
      		 * AND the text field contains the original text
      		 * then set Text to NULL
      		 *
      		 * Otherwise do NOT set to NULL 
      		 * (I found once a name was entered, and the same JTextField 
      		 * was selected, the name would disapear. So i want to 
      		 * retain text, if text has been entered)
      		 */
      
         if(event == jtfAddressL1 && jtfAddressL1.getText().equalsIgnoreCase("Address Line 1")){
            jtfAddressL1.setText(null);
         }
         else if(event == jtfAddressL2 && jtfAddressL2.getText().equalsIgnoreCase("Address Line 2")){
            jtfAddressL2.setText(null);
         }
         else if(event == jtfAddressL3 && jtfAddressL3.getText().equalsIgnoreCase("Address Line 3")){
            jtfAddressL3.setText(null);
         }
         else if(event == jtfCity && jtfCity.getText().equalsIgnoreCase("City/Town")){
            jtfCity.setText(null);
         }
         else if(event == jtfCounty && jtfCounty.getText().equalsIgnoreCase("County")){
            jtfCounty.setText(null);
         }
         else if(event == jtaAltInstructions && jtaAltInstructions.getText().equalsIgnoreCase("Enter Alternative Delivery Instructions")){
            jtaAltInstructions.setText("");
         
         }//end of if-else ladder
      
      }//end of method
   
   		
      @Override
      	public void focusLost(FocusEvent f){
      
      		//get source of event
         Object event = f.getSource();
      
      		//if Focus is lost and no text was entered
      		//e.g. text equals NULL OR text equals an empty String
      		//set text to the original text
         if(event == jtfAddressL1){
            if(jtfAddressL1.getText() == null || jtfAddressL1.getText().equals("")){
               jtfAddressL1.setText("Address Line 1");
            }
         }
         else if(event == jtfAddressL2){
            if(jtfAddressL2.getText() == null || jtfAddressL2.getText().equals("")){
               jtfAddressL2.setText("Address Line 2");
            }
         }
         else if(event == jtfAddressL3){
            if(jtfAddressL3.getText() == null || jtfAddressL3.getText().equals("")){
               jtfAddressL3.setText("Address Line 3");
            }
         }
         else if(event == jtfCity){
            if(jtfCity.getText() == null || jtfCity.getText().equals("")){
               jtfCity.setText("City/Town");
            }
         }
         else if(event == jtfCounty){
            if(jtfCounty.getText() == null || jtfCounty.getText().equals("")){
               jtfCounty.setText("County");
            }
         }
         else if(event == jtaAltInstructions){
            if(jtaAltInstructions.getText() == null || jtaAltInstructions.getText().equals("")){
               jtaAltInstructions.setText("Enter Alternative Delivery Instructions");
            }
         }//end of if-else ladder
      }//end of method
   }//end of inner class


	//inner action listener
   public class ActionListenerClass implements ActionListener{
   
      @Override
      public void actionPerformed(ActionEvent e) {
      
      	//get source of event
         Object event = e.getSource();
      
      	/* if event equal back button
      	 * dispose order page and open home page
      	 * */
         if(event == backBtn) 
         {
            dispose();
            OrderPage op = new OrderPage(new Account("Somebody","Else","elseIf@mail.ie","TestPass", 0));
            op.setTitle("Create Account or Sign In");		
            op.pack();
         	//hp.setSize(500, 300);
            op.setLocationRelativeTo(null);
            op.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            op.setVisible(true);
         
         }	
         else if(event == confirmDelDetsBtn)
         {
            if(jtfAddressL1.getText() == "Address Line 1" || jtfAddressL2.getText()== "Address Line 2" ||
            		jtfAddressL3.getText()== "Address Line 3" || jtfCity.getText()== "City/Town" || jtfCounty.getText()== "County")
            {
               JOptionPane.showMessageDialog(null, "Address Not Complete", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(jtfAddressL1.getText() == ""|| jtfAddressL2.getText()== ""||
            		jtfAddressL3.getText()== ""|| jtfCity.getText()== ""|| jtfCounty.getText()== "")
            {
               JOptionPane.showMessageDialog(null, "Address Not Complete", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(delDets.getDeliveryTime() == null || delDets.getDeliveryDate() == null)//.equalsIgnoreCase("Select time"))
            {
               JOptionPane.showMessageDialog(null, "Please Select Delivery Date and Time", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            else 
            {               
               String Address = jtfAddressL1.getText() + ", \n" + jtfAddressL2.getText() + ", \n" 
                   + jtfAddressL3.getText() + ", \n" + jtfCity.getText() + ", \n" +  jtfCounty.getText();
                //delDets.setDelivery(deliveryDetails);
               String deliveryDetails = (Address + "\n\n" + "Alternative Delivery Instructions: \n" + jtaAltInstructions.getText() +
                                  "\n\n" + "Expected Delivery date: \n" +  delDets.getDeliveryDate());
               System.out.println(deliveryDetails);
               delDets.setDeliveryDetails(deliveryDetails);
            
             
             //draw the new view order page
               ViewOrderPage.drawViewOrderPage(a, o , delDets);
            
             //dispose Delivery page
            // dispose();
            
            
            //                System.out.println(Address);
            //                System.out.println("Alternative Instructions: \n" + jtaAltInstructions.getText());
            //                System.out.println("Expected Delivery date: \n" +  delDets.getDelivery());
            //
            //String delDate = selMonth.getSelectedItem().toString() + " " + selDay.getSelectedItem().toString() + " " + year);	
            }
         	
         	
         }
      }
   }



	//tester
	//Draw method
   public static void drawDeliveryNew(Account acc, Order o, Delivery d) {
      DeliveryPageNew dp = new DeliveryPageNew(acc, o, d);
      dp.setTitle("Delivery Page");		
   	//dp.pack();
      dp.setSize(700, 400);
      dp.setLocationRelativeTo(null);
      dp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      dp.setVisible(true);
   }//end Draw Method
	
	//main method
   public static void main(String[] args)
   {
      drawDeliveryNew(new Account("Cathal", "Doherty", "egg", "milk", 0),
                      new Order(new ArrayList<Product>(),100, 100, "egg"),
                      new Delivery(""));
   		
   }//end of main method



}


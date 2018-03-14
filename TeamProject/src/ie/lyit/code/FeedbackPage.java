//Feedback page
package ie.lyit.code;

import jdbc.DBConnector;
import ie.lyit.data.Account;
import ie.lyit.code.OrderPage;
import ie.lyit.code.OrderPage.ActionListenerClass;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class FeedbackPage extends JFrame{
   //panels
   private JPanel northPanel, westPanel, southPanel;
   
   //font styles
   private Font generalFont = new Font("SanSerif", Font.BOLD, 15);
   private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
   
   //text areas
   private JTextField emailText;
   
   //text area 
   private JTextArea feedbackArea;
   
   //labels
   private JLabel titleLabel, emailLabel;
   
   //button
   private JButton submitButton;
   
   private static JFrame frame;
   
   public FeedbackPage(JFrame frame) {
      
     northPanel = new JPanel();
      titleLabel = new JLabel("Leave your Feedback");
      titleLabel.setFont(titleFont);
      titleLabel.setBorder(new LineBorder(Color.BLACK, 1));
      
      //add label to panel
      northPanel.add(titleLabel);
      add(northPanel, BorderLayout.NORTH);
      
      //east Panel
      emailLabel = new JLabel("Your email: ");
      emailLabel.setFont(generalFont);
      
      emailText = new JTextField();
      emailText.setColumns(15);
      
      
      //add to panel
      westPanel = new JPanel();//new GridLayout(1,2));
      
      westPanel.add(emailLabel);
      westPanel.add(emailText);
      //westPanel.add(feedbackArea);
      
      add(westPanel, BorderLayout.WEST);
      
      //south panel
      feedbackArea = new JTextArea("Leave your comment here",5,25);
      submitButton = new JButton("Submit");
      
      //Dimension d = new Dimension(200,20);
      
      //submitButton.setPreferredSize(d);
      //submitButton.setBounds(20,30,50,30);
      
      southPanel = new JPanel(new BorderLayout());
      southPanel.add(feedbackArea, BorderLayout.NORTH);
      southPanel.add(submitButton, BorderLayout.WEST);
      
      add(southPanel, BorderLayout.SOUTH);
      //add(submitButton, BorderLayout.WEST);
      
      //JPanel buttonPanel = new JPanel(new GridLayout(1,1));
      
      //buttonPanel.add(submitButton);
      
      //add(buttonPanel, BorderLayout.SOUTH);
      
      
      //add listener class
      //ListenerClass focusListener = new ListenerClass();
      //feedbackArea.addFocusListener(focuaListener);
      
      ActionListenerClass listener = new ActionListenerClass();
	  submitButton.addActionListener(listener);
	  
	  focusListener focusListener = new focusListener();
	  feedbackArea.addFocusListener(focusListener);
	  emailText.addFocusListener(focusListener);
      
   }
   
   public class ActionListenerClass implements ActionListener{
	   
	   @Override
	   public void actionPerformed(ActionEvent e) 
	   {
		   Object event = e.getSource();
		   
		   if(event == submitButton)
		   {
			   
			   String message = "Email: "+emailText +", your feedback has been successfully placed.";
			   //"Email: "+ emailText +" , your feedback has been received.";
			    
			   if(emailText.getText() == null || emailText.getText().equals("") || emailText.getText().equals(" "))
			   {
					JOptionPane.showMessageDialog(frame,"Incorrect email, please try again.","Incorrect email!", JOptionPane.ERROR_MESSAGE);
			   }
			   else if(feedbackArea.getText()== null || feedbackArea.getText().equals("") || feedbackArea.getText().equals(" ")|| feedbackArea.getText().equals("Leave your comment here"))
			   {
				   JOptionPane.showMessageDialog(frame,"Incorrect feedback, please try again.","Incorrect feedback!", JOptionPane.ERROR_MESSAGE);
			   }
			   else
				   JOptionPane.showMessageDialog(frame,"Your feedback has been successfully placed.","Feedback received!", JOptionPane.INFORMATION_MESSAGE);	   
		   
		   
		   }	
		   
		   
		  
	   }
   }
   
   public class focusListener implements FocusListener{
	   
	    @Override
		public void focusGained(FocusEvent f) {
	    	
	    	Object event = f.getSource();
	    	
	    	if(event == feedbackArea && feedbackArea.getText().equalsIgnoreCase("Leave your comment here")){
				feedbackArea.setText(null);
			}
	   }

		@Override
		public void focusLost(FocusEvent f) {
			
			Object event = f.getSource();
			
			if(event == feedbackArea){
				if(feedbackArea.getText() == null || feedbackArea.getText().equals("")){
					feedbackArea.setText("Leave your comment here");
				}
			}
			
			
		}
   }
   
   public static void main(String []args){
   
   
      FeedbackPage feedbPage = new FeedbackPage(frame);
      
      feedbPage.setTitle("Leave your feedback");
      feedbPage.pack();
      feedbPage.setLocation(200, 100);
      feedbPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      feedbPage.setVisible(true);

   }

   
}
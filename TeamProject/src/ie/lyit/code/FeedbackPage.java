//Feedback page
package ie.lyit.code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import ie.lyit.data.Account;
import ie.lyit.data.Delivery;
import ie.lyit.data.Order;

public class FeedbackPage extends JFrame{
   //panels
   private JPanel northPanel, westPanel, southPanel, btnPanel;
   
   //font styles
   private Font generalFont = new Font("SanSerif", Font.BOLD, 15);
   private Font titleFont = new Font("SanSerif", Font.ITALIC, 40);
   private Font textAreaFont = new Font("SanSerif", Font.ITALIC,22);
   private Font buttonFont = new Font("SanSerif",Font.BOLD,22);
    
   //text areas 
   private JTextField emailText;
   
   //text area 
   private JTextArea feedbackArea;
   
   //labels
   private JLabel titleLabel, emailLabel;
   
   //button
   private JButton submitButton, backButton, exitBtn;
   
   private static JFrame frame;
   
   private Account a;
   private Order o;
   private Delivery d;
   static String email;
   static String messageToSend;
   
   public FeedbackPage(Account a, Order o, Delivery d) {
	   
	   this.a = a;
	   this.o = o;
	   this.d = d;
      
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
      emailText.setText(a.getEmail());
      
      
      //add to panel
      westPanel = new JPanel();//new GridLayout(1,2));
      
      westPanel.add(emailLabel);
      westPanel.add(emailText);
      //westPanel.add(feedbackArea);
      
      add(westPanel, BorderLayout.WEST);
      
      //south panel
      feedbackArea = new JTextArea("Leave your comment here",20,20);
      feedbackArea.setFont(textAreaFont);
      feedbackArea.setLineWrap(true);
      feedbackArea.setWrapStyleWord(true);
      backButton = new JButton("   Back   ");
      backButton.setFont(buttonFont);
      submitButton = new JButton("  Submit  ");
      submitButton.setFont(buttonFont);
      
      //Dimension d = new Dimension(200,20);
      
      //submitButton.setPreferredSize(d);
      //submitButton.setBounds(20,30,50,30);
      exitBtn = new JButton("Exit Application");
      exitBtn.setFont(buttonFont);
      btnPanel = new JPanel(new FlowLayout(1, 240, 10));
      btnPanel.add(backButton);
      btnPanel.add(submitButton);
      btnPanel.add(exitBtn);
      
      southPanel = new JPanel(new BorderLayout());
      southPanel.add(feedbackArea, BorderLayout.NORTH);
      //southPanel.add(submitButton);  //BorderLayout.WEST
      //southPanel.add(backButton,BorderLayout.WEST);
      //southPanel.add(submitButton,BorderLayout.EAST);
      southPanel.add(btnPanel, BorderLayout.CENTER);
      
      add(southPanel, BorderLayout.SOUTH);
      //add(submitButton, BorderLayout.WEST);
      
      //JPanel buttonPanel = new JPanel(new GridLayout(1,1));
      
      //buttonPanel.add(submitButton);
      
      //add(buttonPanel, BorderLayout.SOUTH);
      
      
      //add listener class
      //ListenerClass focusListener = new ListenerClass();
      //feedbackArea.addFocusListener(focuaListener);
      
      setResizable(false);
      ActionListenerClass listener = new ActionListenerClass();
	  submitButton.addActionListener(listener);
	  backButton.addActionListener(listener);
	  exitBtn.addActionListener(listener);
	  
	  focusListener focusListener = new focusListener();
	  feedbackArea.addFocusListener(focusListener);
	  emailText.addFocusListener(focusListener);
      
   }
   
   public class ActionListenerClass implements ActionListener{
	   
	   boolean flag = false;
	   @Override
	   public void actionPerformed(ActionEvent e) 
	   {
		   Object event = e.getSource();
		   
		   if(event == exitBtn) {
			   
			   dispose();
		   }
		   else if(event == submitButton)
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
			   {
				   JOptionPane.showMessageDialog(frame,"Your feedback has been successfully placed,\nyou may be contacted via email in regards to your feedback.","Feedback received!", JOptionPane.INFORMATION_MESSAGE);
				   email = emailText.getText();
				   messageToSend = feedbackArea.getText();
				   
				   FeedbackPage.sendMail(email, messageToSend);
				   flag = true;
			   }
			   
			   if(flag) {
				   
				   dispose();
			   }
		   
			   
			   
		   }
		   
		   if(event == backButton)
		   {
			   ViewOrderPage.drawViewOrderPage(a, o, d);
			   dispose();
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
   
   public static void drawFeedbackPage(Account a, Order o, Delivery d)
   {
	   FeedbackPage fp = new FeedbackPage(a, o, d);
	   fp.setTitle("Leave your feedback");
	   //fp.pack();
	   fp.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	   fp.setLocationRelativeTo(null);
	   fp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   fp.setVisible(true);
	   
	   
	   //Properties props;
   }
   
   public static void sendMail(String e, String mTS)
   {
	   
	   try {
	   
	   String custEmail = e;
	   String message = mTS;
	   
	   //String systemEmail = "applied2shoppingsystem@gmail.com"; 
	   String host = "smtp.gmail.com";
	   String user = "sendermailapplied2@gmail.com";
	   String pass = "applied2";
	   String to = "applied2shoppingsystem@gmail.com";
	   String from = "sendermailapplied2@gmail.com";
	   String subject = "Comment/Feedback";
	   String messageText = "Customer email: "+custEmail +", \ncustomer message: " +message;
	   boolean sessionDebug = false;
	   
	   
	   Properties props = System.getProperties();
	   //Session session = Session.getDefaultInstance(props);
	   
	   props.put("mail.smtp.starttls.enable", "true");
	   props.put("mail.smtp.ssl.trust", host);
	   props.put("mail.smtp.host", host);
	   props.put("mail.smtp.port", "587");
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.required", "true");
	   
	   java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	   Session mailSession = Session.getDefaultInstance(props,null);
	   mailSession.setDebug(sessionDebug);
	   Message msg = new MimeMessage(mailSession);
	   msg.setFrom(new InternetAddress(from));
	   InternetAddress address = (new InternetAddress(to)); //commented out [] after InternetAddress
	   msg.setRecipient(Message.RecipientType.TO, address);
	   msg.setSubject(subject); msg.setSentDate(new Date());
	   msg.setText(messageText);
	   
	   Transport transport = mailSession.getTransport("smtp");
	   transport.connect(host,user,pass);
	   transport.sendMessage(msg, msg.getAllRecipients());
	   transport.close();
	   System.out.println("Message sent successfully...");
	   
	  
		      
	   }catch(Exception ex) 
	   {
		   System.out.println(ex);
	   }
   }
   
   public static void main(String []args){
   
   // drawFeedbackPage();
    
    //String MAIL = "hello@mail.com";
    //String MESSTOSEND = "Hello, team project feedback mail working";
    //FeedbackPage.sendMail(MAIL, MESSTOSEND);

   }

   
}
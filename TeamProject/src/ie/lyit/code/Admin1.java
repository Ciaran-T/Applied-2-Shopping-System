/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: GUI application Administrator first page.
 * 
 * 
 */	

package ie.lyit.code;

import javax.swing.JFrame;

public class Admin1 extends JFrame {
	
	public static void drawAdmin1() {
		
		Admin1 a1 = new Admin1();
		a1.setTitle("Administrator");		
		//op.pack();
		a1.setSize(550, 400);
		a1.setLocationRelativeTo(null);
		a1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a1.setVisible(true);
		a1.requestFocusInWindow(true);
	}
	
	//tester
	//main
	public static void main(String[] args){
		
		drawAdmin1();
		
	}//end of main method

}

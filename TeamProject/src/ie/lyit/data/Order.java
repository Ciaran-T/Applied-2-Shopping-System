/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: Order Class,
 * 		 Methods:
 * 				- Set instance variables.
 * 				- Get instance variables.
 * 				- Equals and hashCode.
 * 
 * 
 */	


package ie.lyit.data;

import ie.lyit.data.Product;
import java.util.ArrayList;



public class Order {
	
	private ArrayList<Product> products;
	private double total;
	private String customerEmail;
	private int orderID;
	
	
	public Order() {
		
		products = new ArrayList<Product>();
		total = 0.0;
		
	}
	public Order(ArrayList<Product> products, double total, int orderID, String customerEmail) {
		
		this.products = new ArrayList<Product>(products);
		this.total = total;
		this.orderID = orderID;
		this.customerEmail = customerEmail;
	}
	
	public int getOrderID() {
		return orderID;
	}
	//setters/getters
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public String getCustomerEmail() {
		return customerEmail;
	}
	
	
	//hashCode and equals methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerEmail == null) ? 0 : customerEmail.hashCode());
		result = prime * result + orderID;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customerEmail == null) {
			if (other.customerEmail != null)
				return false;
		} else if (!customerEmail.equals(other.customerEmail))
			return false;
		if (orderID != other.orderID)
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	

}

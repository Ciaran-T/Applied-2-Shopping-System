/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: Product Class,
 * 		 Methods:
 * 				- Set instance variables.
 * 				- Get instance variables.
 * 				- Equals, toString and hashCode.
 * 
 * 
 */	


package ie.lyit.data;

public class Product {
	
	//instance fields
	private int productNo;
	private double price;
	private String name;
	private String type;
	private int quantity;
	private static int nextProductNo = 1;
	
	public Product(String name, double price, int id, String type) {
		
		this.name = name;
		this.price = price;
		productNo = id;
		this.type = type;
	}
	
	public Product(double price, String name, String type, int quantity) {
		
		this.price = price;
		this.name = name;
		this.type = type;
		this.quantity = quantity;
		this.productNo = nextProductNo++;
	}
	
	//setters and getters
	public int getProductNo() {
		return productNo;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getQuantity() {
		
		return quantity;
	}
	public void setQuantity(int quantity) {
		
		this.quantity = quantity;
	}
	
	//Hash code, equals and toString
	
	@Override
	public String toString() {
		
		return "    " + name;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productNo;
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
		Product other = (Product) obj;
		if (productNo != other.productNo)
			return false;
		return true;
	}

}

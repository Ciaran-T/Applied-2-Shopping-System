package ie.lyit.data;

public class Product {
	
	//instance fields
	private int productNo;
	private double price;
	private String name;
	private String type;
	
	//setters and getters
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
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
	
	
	//Hash code, equals and toString
	
	@Override
	public String toString() {
		
		return "Product No. : " + productNo + "\nPrice : " + price + "\nName : " + name + "\nType : " + type;
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

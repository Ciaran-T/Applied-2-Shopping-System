package ie.lyit.data;


public class Account {

	private String fName, lName, email, password;
	private int orders;

	public Account(String fName, String lName, String email, String password, int orders) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.orders = orders;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getOrders() {
		return this.orders;
	}
	
	public void incrementOrders() {
		
		this.orders++;
	}

	//auto generated hash code
	//and equals methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Account other = (Account) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "FirstName=" + fName + " LastName=" + lName + " Email=" + email + " Password=" + password;
	}

}




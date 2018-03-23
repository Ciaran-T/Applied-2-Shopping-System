/* Author: Ciaran Toman
 * Class: Cloud Computing
 * DESC: Database Connector Class,
 * 		 Methods:
 * 				- Create connection to DB.
 * 				- Close connection to DB
 * 				- Read and write, to and from the Account table.
 * 				- Read from the product table.
 * 				- Read and write, to and from the Order table.
 * 				- Write to the Order_Products table.
 * 				- Get product by type.
 * 				- Get last Order number given out.
 * 				- Check if Internet is available.
 * 
 *  
 */	


package jdbc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import ie.lyit.data.Account;
import ie.lyit.data.Order;
import ie.lyit.data.Product;

public class DBConnector {

	//instance fields
	//constants
	//database URL, user name, password etc.
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_NAME = "/ShopDB";
	private static final String DB_ADDRESS = "52.214.29.36:3306";
	private static final String DB_ERROR = "?autoReconnect=true&useSSL=false";

	private static final String DB_URL = "jdbc:mysql://" + DB_ADDRESS + DB_NAME + DB_ERROR;
	private static final String USER = "remote";
	private static final String PASSWORD = "password";

	//database connections
	private static Statement stmt;
	private static Connection conn;

	/*
	 * method to create connection
	 * check Internet connectivity
	 * register driver, connect to DB
	 * catch any exceptions.
	 */
	private static void createConnection(String dbUrl, String user, String pass) {



		try {

			// Register MySQL JDBC driver
			Class.forName(JDBC_DRIVER);
			System.out.println("STEP 1 COMPLETE - Driver Registered...");

			// Get connection to DB using username and password 
			conn = DriverManager.getConnection(dbUrl, user, pass);
			System.out.println("STEP 2 COMPLETE - Connection obtained...");

			// Get statement object to execute queries
			stmt = conn.createStatement();

		}catch(SQLException e) {

			System.out.println("Problem with SQL.\n" + e.getMessage());

		}catch(Exception e) {

			System.out.println(e.getMessage());
		}

	}

	// Closes the connection
	private static void closeConnection(){   

		try{
			//if connection exists, close
			if(conn != null){
				conn.close();
				System.out.println("COMPLETE - Connection closed.");				
			}
		}
		catch (SQLException e){
			System.out.println("Could not close connection.\n" + e.getMessage());
		}
	}


	/* Account methods
	 * 
	 * take in account object
	 * create connection to DB,
	 * try execute query
	 */
	public static void writeAccount(Account acc) {


		createConnection(DB_URL, USER, PASSWORD);



		try {
			String query = insertAccount(acc.getfName(), acc.getlName(), acc.getEmail(), acc.getPassword());
			stmt.executeUpdate(query);

			System.out.println("Written Account to DB successfully");

		}catch(Exception e) {
			System.out.println("Problem with write account method ==> " + e.getMessage());
		}finally {
			closeConnection();
		}
	}

	
	/* read account from DB, 
	 * create connection
	 * 
	 * retrieve results and place into a result set object
	 * iterate over result set,
	 * create account object with row value's contained in result set
	 * 
	 * pass account back 
	 * 
	 * */
	public static Account readAccount(String email) {
		Account a = null;
		ResultSet res;

		createConnection(DB_URL, USER, PASSWORD);



		try {
			String query = queryAccount(email);
			res = stmt.executeQuery(query);

			res.next();

			a = new Account(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5));

		}catch(SQLException e) {
			System.out.println("SQL error ==>" + e.getMessage());
		}catch(Exception e) {
			System.out.println("Error ==>" + e.getMessage());
		}finally {
			closeConnection();
		}

		return a;
	}

	/*
	 * helper method
	 * make query to DB
	 * 
	 * retrieving
	 */
	private static String queryAccount(String email) {
		String query = "SELECT FirstName, LastName, Email, Password, orders FROM Accounts WHERE Email = '" + email + "';";

		return query;
	}

	/*
	 * helper method
	 * make insert query
	 * 
	 * pushing
	 */
	private static String insertAccount(String fName, String lName, String email, String pass) {
		String query = "INSERT INTO Accounts(Email, Password, FirstName, LastName, orders) " 
				+ " VALUES ('" + email + "', '" + pass + "', '" + fName + "', '" + lName + "', '" + 0 + "') "
				+ " ON DUPLICATE KEY UPDATE password='" + pass + "', firstname='" + fName + "', lastname='" + lName +"';";

		return query;
	}
	
	//update account
	private static void updateAccountOrders(Account acc) {
		
		createConnection(DB_URL, USER, PASSWORD);


		try {
			String query = queryAccOrderUpdate(acc);
			stmt.executeUpdate(query);

			System.out.println("Incremented orders in acc table successfully");

		}catch(Exception e) {
			System.out.println("Problem with update order method method ==> " + e.getMessage());
		}finally {
			closeConnection();
		}
		
	}
	
	//query Account table
	//update orders field
	private static String queryAccOrderUpdate(Account acc) {
		
		return "UPDATE Accounts SET orders = " + acc.getOrders() + " WHERE Email='" + acc.getEmail() + "';";
	}
	

	/*
	 * Queries to DB ( Order, Order_Products)
	 * */

	//write order to order and order_product table
	//update account table
	public static void writeOrder(Order o, Account acc) {

		//increment order in account
		acc.incrementOrders();

		ArrayList<Product> products = o.getProducts();
		createConnection(DB_URL, USER, PASSWORD);

		try {
			String query = insertOrder(o.getOrderID(), o.getTotal(), o.getCustomerEmail());
			stmt.executeUpdate(query);

			System.out.println("Written Order to DB successfully");

		}catch(Exception e) {
			System.out.println("Problem with write order method ==> " + e.getMessage());
		}finally {
			closeConnection();
		}

		/* Writing to OrderProducts table
		 * (OrderProducts = Table to break many to many relationship)*/
		writeOrderProducts(o.getOrderID(), products);
		
		
		//update account which increment orders of account
		//and writes the changes to table
		updateAccountOrders(acc);
		

	}

	//order methods
	/*
	 * insert into order
	 * 
	 * */
	private static String insertOrder(int orderID, double total, String email) {

		String query = "INSERT INTO Orders (OrderNo, Total, CustomerEmail) VALUES('" + orderID + "', '" 
				+ total + "', '" + email + "');";

		return query; 
	}

	
	
	// OrderProducts
	private static void writeOrderProducts(int orderID, ArrayList<Product> products) {

		createConnection(DB_URL, USER, PASSWORD);
		
		int qty = 0;
		
		//dump list
		ArrayList<Product> pr = new ArrayList<>();
		
		try {
			do {
			
				//get first product and increment qty
				Product p = products.get(0);
				boolean gone = products.remove(p);
				qty++;
				
				if(gone) {

					//loop through list to compare products
					for(int i = 0; i < products.size(); i++) {

						//if products are the same
						if(products.get(i).equals(p)) {
							qty++;
							
							//add to dump list
							pr.add(products.get(i));
							
						}
					}
				}
				
				//query DB + reset qty
				String query = insertOrderProducts(orderID, p.getProductNo(), qty);
				stmt.executeUpdate(query);
				qty = 0;
				
				//remove dump list
				products.removeAll(pr);
				
			}while(!products.isEmpty()); // while products list is NOT empty
			
			System.out.println("Written Order_Products to DB successfully");

		}catch(Exception e) {
			System.out.println("Problem with write order_products method ==> " + e.getMessage());
		}finally {
			closeConnection();
		}

	}
	

	//order_products table
	//DB query
	private static String insertOrderProducts(int orderID, int productID, int qty) {


		return "INSERT INTO order_product (OrderID, ProductID, Quantity) VALUES('" + orderID +
				"', '" + productID + "', '" + qty + "');";
	}

	/* read product from DB, 
	 * 
	 * create connection
	 * 
	 * retrieve results and place into a result set object,
	 * iterate over result set,
	 * create product object with row value's contained in result set.
	 * add to product array list
	 * 
	 * catch any exceptions,
	 * 
	 * 
	 * pass product array list back 
	 * 
	 * */
	public static ArrayList<Product> readProducts() {
		
		ArrayList<Product> products = new ArrayList<>();
		ResultSet res;

		createConnection(DB_URL, USER, PASSWORD);


		try {
			String query = queryProduct();
			res = stmt.executeQuery(query);

			while(res.next()) {				//get name, price, productNo, type and quantity
				Product pr = new Product(res.getString(1), res.getDouble(2), res.getInt(3), res.getString(4), res.getInt(5)); 
				products.add(pr);  
				
			}


		}catch(SQLException e) {
			System.out.println("SQL error ==>" + e.getMessage());
		}catch(Exception e) {
			System.out.println("Error ==>" + e.getMessage());
		}finally {
			closeConnection();
		}
		

		return products;
	}


	//get Order at end of table
	//take that number
	public static int getLastOrderID() {

		String query = "SELECT * FROM Orders ORDER BY OrderNo DESC;";
		int id = 0;
		ResultSet res;

		createConnection(DB_URL, USER, PASSWORD);



		try {
			res = stmt.executeQuery(query);

			res.next();

			id = res.getInt(1);


		}catch(SQLException e) {
			System.out.println("SQL error ==>" + e.getMessage());
		}catch(Exception e) {
			System.out.println("Error ==>" + e.getMessage());
		}finally {
			closeConnection();
		}

		return id;

	}
	
	//get Order at end of table
	//take that number
	public static int getLastProductID() {

		String query = "SELECT * FROM Products ORDER BY ProductNo DESC;";
		int id = 0;
		ResultSet res;

		createConnection(DB_URL, USER, PASSWORD);



		try {
			res = stmt.executeQuery(query);

			res.next();

			id = res.getInt(1);


		}catch(SQLException e) {
			System.out.println("SQL error ==>" + e.getMessage());
		}catch(Exception e) {
			System.out.println("Error ==>" + e.getMessage());
		}finally {
			closeConnection();
		}

		return id;

	}

	
	
	//get meat products query
	public static String queryMeat() {
		
		return "SELECT * FROM products WHERE Type='Meat';";
	}
	
	//get dairy products query
	public static String queryDiary() {
		
		return "SELECT * FROM Products WHERE Type='Diary';";
	}
	
	//get Veg products query
	public static String queryVeg() {
		
		return "SELECT * FROM Products WHERE Type='Veg';";
	}

	//get products from DB query
	private static String queryProduct() {
		String query = "SELECT Name, Price, ProductNo, Type, Qty FROM Products;" ;

		return query;
	}
	
	private static String insertProductQuery(String name, double price, int id, String type) {
		
		return "INSERT INTO Products (ProductNo, Name, Price, Type, Qty) VALUES('" + id +
				"', '" + name + "', '" + price + "', '" + type + "', '20');";
	}
	
	public static void insertProduct(Product p) {
		
		createConnection(DB_URL, USER, PASSWORD);
		
		try {
			String query = insertProductQuery(p.getName(), p.getPrice(), p.getProductNo(), p.getType());
			stmt.executeUpdate(query);

			System.out.println("Written Product to DB successfully");

		}catch(Exception e) {
			System.out.println("Problem with insert product method ==> " + e.getMessage());
		}finally {
			closeConnection();
		}
		
		
	}




	/* Method to check Internet connection
	 * 
	 * Assume there is no Internet available,
	 * Create default unconnected socket,
	 * 
	 * try
	 * 		 connect socket(passing in host name and 
	 * 					port number of out bound traffic(http/80)) i.e 'socket pair'
	 * 		 if port 80 is open and successfully connects to/recognizes google.com
	 * 			flick flag variable, i.e. Internet available.
	 * 			
	 * 
	 * if the socket is not open 
	 * 			OR
	 * google.com is not recognized i.e. Because of no Internet
	 * 		flag stays false
	 * 		catch exceptions and do nothing
	 * 
	 * close socket
	 * 
	 * return flag
	 */
	public static boolean checkInternet() {
		boolean connection = false;
		Socket socket = new Socket();

		try {

			socket.connect(new InetSocketAddress("www.google.com", 80));

			connection = true;


		} catch(UnknownHostException ue) {
		} catch (IOException e) {
		}
		finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}

		return connection;
	}


	
	//tester
	//main
	public static void main(String[] args) {
		//DBConnector db = new DBConnector();
		//Account cus = new Account("John", "Smith", "jsmith@gmail.com", "johnspass");
		//DBConnector.writeAccount(cus);
		//Customer c = db.readCustomer("ctoman@mail.ie");
		//System.out.println(c);
		boolean connection = checkInternet();

		System.out.print((connection? "Internet Connection " : "No Internet connection "));

	}
}

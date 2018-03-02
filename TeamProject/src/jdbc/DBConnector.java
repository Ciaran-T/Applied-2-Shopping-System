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

			a = new Account(res.getString(1), res.getString(2), res.getString(3), res.getString(4));

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
		String query = "SELECT FirstName, LastName, Email, Password FROM Accounts WHERE Email = '" + email + "';";

		return query;
	}

	/*
	 * helper method
	 * make insert query
	 * 
	 * pushing
	 */
	private static String insertAccount(String fName, String lName, String email, String pass) {
		String query = "INSERT INTO Accounts(Email, Password, FirstName, LastName) " 
				+ " VALUES ('" + email + "', '" + pass + "', '" + fName + "', '" + lName + "') "
				+ " ON DUPLICATE KEY UPDATE password='" + pass + "', firstname='" + fName + "', lastname='" + lName +"';";

		return query;
	}

	/*
	 * TODO -- other queries to DB ( Order_Products)
	 * */

	//*****************************************
	public static void writeOrder(Order o) {


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

		/*TODO --- writing to order but not OrderProducts
		 * (OrderProducts = Table to break many to many relationship)*/
		
		//writeOrderProducts(o.getOrderID(), products);

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

	/*
	// TODO - OrderProducts
	private static void writeOrderProducts(int orderID, ArrayList<Product> products) {

		createConnection(DB_URL, USER, PASSWORD);
		int qty = 0;
		Product p = products.get(0);
		
		//TODO -- fix quantity -- holes in arrayList
		try {
			for(int j = 1; j < products.size(); j++) {
				boolean gone = products.remove(p);
				qty++;
				if(gone) {
					for(int i = j +1; i < products.size(); i++) {

						if(products.get(i).equals(p)) {
							qty++;
							products.remove(i);
						}
					}
				}
				String query = insertOrderProducts(orderID, p.getProductNo(), qty);
				stmt.executeUpdate(query);
				qty = 0;
			}
			System.out.println("Written Order to DB successfully");

		}catch(Exception e) {
			System.out.println("Problem with write order method ==> " + e.getMessage());
		}finally {
			closeConnection();
		}

	}
	*/

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
	 * 
	 * catch any exceptions,
	 * for loop to put ArrayList products into Array products
	 * (i.e. list model takes Array not ArrayList)
	 * 
	 * pass product array back 
	 * 
	 * */
	public static Product[] readProducts() {
		ArrayList<Product> products = new ArrayList<>();
		Product[] productList;
		ResultSet res;

		createConnection(DB_URL, USER, PASSWORD);



		try {
			String query = queryProduct();
			res = stmt.executeQuery(query);

			while(res.next()) {
				Product pr = new Product(res.getString(1), res.getDouble(2), res.getInt(3)); //get name and price
				products.add(pr);
			}


		}catch(SQLException e) {
			System.out.println("SQL error ==>" + e.getMessage());
		}catch(Exception e) {
			System.out.println("Error ==>" + e.getMessage());
		}finally {
			closeConnection();
		}

		//Transfer products to String array
		int noOfProducts = products.size();
		productList = new Product[noOfProducts];

		for(int i = 0; i < noOfProducts; i++) {

			productList[i] = products.get(i);
		}

		return productList;
	}


	//get product at end of table
	//take their number + 1
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


	//get products from DB query
	private static String queryProduct() {
		String query = "SELECT Name, Price, ProductNo FROM Products;" ;

		return query;
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

package jdbc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import ie.lyit.data.Account;

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
	 * TODO -- other queries to DB (Products, Order etc)
	 * */
	
	
	//tester
	//main
	public static void main(String[] args) {
		//DBConnector db = new DBConnector();
		Account cus = new Account("John", "Smith", "jsmith@gmail.com", "johnspass");
		DBConnector.writeAccount(cus);
		//Customer c = db.readCustomer("ctoman@mail.ie");
		//System.out.println(c);
		System.out.print("Finished");

	}
}

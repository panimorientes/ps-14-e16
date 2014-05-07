package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Static clas who manage the DataBase Connection
 */
public class ConnectionManager {

	private final static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";	
	private final static String DRIVER_URL = "jdbc:mysql://localhost/sakedb";
	private final static String USER = "root";
	private final static String PASSWORD = "1234";
	
	static {		
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.err);
		}		
	}

	
	/**
	 * Return a connection to our BD
	 * @return the Connecion to our BD
	 * @throws SQLException, in case of error thows a SQLException.
	 */
	public final static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DRIVER_URL, USER, PASSWORD);
	}
	
	
}
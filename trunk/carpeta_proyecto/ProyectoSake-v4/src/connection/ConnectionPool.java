package connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool {

	private static Vector<Connection> connectionPool = new Vector<Connection>();
	static {
		while (connectionsAvailable()) {
			// Adding new connection instance until the pool is full
			try {
				connectionPool.addElement(ConnectionManager.getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static final int maxPoolSize = 30;

	private static synchronized boolean connectionsAvailable() {
		// Check if the pool size
		if (connectionPool.size() < maxPoolSize) {
			return true;
		}
		return false;
	}

	public static synchronized Connection getConnection() {

		Connection connection = null;

		// Check if there is a connection available.
		if (connectionPool.size() > 0) {
			connection = (Connection) connectionPool.firstElement();
			connectionPool.removeElementAt(0);
		}
		// Giving away the connection from the connection pool
		return connection;
	}

	public static synchronized void returnConnection(Connection connection) {
		// Adding connection from the client back to the connection pool
		connectionPool.addElement(connection);
	}

	public static synchronized void closeConnections() throws SQLException {
		for (Connection c : connectionPool) {
			c.close();
		}
	}
}

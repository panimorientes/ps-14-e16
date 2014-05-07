package connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool{

	private Vector<Connection> connectionPool = new Vector<Connection>();
	private final int maxPoolSize = 3;

	public ConnectionPool() throws SQLException{		
		connectionPool = new Vector<Connection>();
		while(connectionsAvailable()){
			//Adding new connection instance until the pool is full
			connectionPool.addElement(ConnectionManager.getConnection());
		}
	}
	
	
	private synchronized boolean connectionsAvailable(){
	  //Check if the pool size
		if(connectionPool.size() < maxPoolSize){
			return true;
		}
			return false;
	}
	
	 public synchronized Connection getConnection(){
	
		 Connection connection = null;
	
	  //Check if there is a connection available.
		 if(connectionPool.size() > 0){
			 connection = (Connection) connectionPool.firstElement();
			 connectionPool.removeElementAt(0);
		 }
	 //Giving away the connection from the connection pool
		 return connection;
	 }
	
	 public synchronized void returnConnection(Connection connection){
	  //Adding  connection from the client back to the connection pool
		 connectionPool.addElement(connection);
	 }
	 
	 public synchronized void closeConnections() throws SQLException{
		 for(Connection c: connectionPool){
			 c.close();
		 }
	 }
}
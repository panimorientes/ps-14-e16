package common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;

import connection.ConnectionPool;
import javax.swing.ListSelectionModel;

public class ListPanel extends JList<String> {
	public ListPanel(ConnectionPool myDB){
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		try{
			Vector<String> data = new Vector<String>();
			Connection c = myDB.getConnection();
			Statement st = c.createStatement();
			
			ResultSet rs = st.executeQuery(getQuery());
			
			while(rs.next()){
				data.add(rs.getString("nombreI"));
			}
			this.setListData(data);
			rs.close();
			st.close();
			myDB.returnConnection(c);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		this.setAutoscrolls(true);
		
		
	}
	
	private String getQuery(){
		String query="";
		
		query="SELECT nombreI FROM ingrediente";
		
		return query;
	}
	
	public static void main(String[] args) throws SQLException{
		JFrame f = new JFrame();
		ConnectionPool p = new ConnectionPool();
		f.getContentPane().add(new ListPanel(p));
		f.setVisible(true);
	}
	
	

}

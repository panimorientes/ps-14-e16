package homePage;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;

import connection.ConnectionManager;

public class CenterPagePanel extends JPanel{
	

	/**
	 * Method Constructor, The constructor makes a new Object who contains 9 coctailt
	 * from the DB.
	 * @param nPage, number of the page
	 * @param searchPage, my parent, usedto link
	 */
	public CenterPagePanel(int nPage,SearchResultPage searchPage){
		

		int skipedCoctails=(nPage-1)*9;
	
		this.setLayout(new GridLayout(0,3));
		 try{
			 Connection myDB = ConnectionManager.getConnection();
			 Statement st = myDB.createStatement();
	         ResultSet rs = st.executeQuery("select * from coctail limit "+skipedCoctails+",9" );
	         String img,name,descr;

	         while (rs.next()){
	           name=rs.getString("nombre");
	           rs.getDouble("precio");
	           descr=rs.getString("Descripcion");
	           img = rs.getString("PathImg");
	           this.add(new CoctailDescriptionPanel(img,name,descr,searchPage));
	          
	         }
	         rs.close();
	         st.close();
		 }
		 catch (SQLException e){
			 System.out.println("error centerPagePanel");
		 }		
	}	
}


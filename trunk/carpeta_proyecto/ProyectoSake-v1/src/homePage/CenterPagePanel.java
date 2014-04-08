package homePage;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;

public class CenterPagePanel extends JPanel{
	
	private Connection myDB;
	private SearchResultPage searchPage;
	
	public CenterPagePanel(int nPage, Connection db,SearchResultPage searchPage){
		
		this.myDB=db;
		this.searchPage=searchPage;
		
		int skipedCoctails=(nPage-1)*9;
		
		this.setLayout(new GridLayout(0,3));

			
		 try{
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


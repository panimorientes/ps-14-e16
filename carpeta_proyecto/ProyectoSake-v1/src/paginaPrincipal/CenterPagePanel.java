package paginaPrincipal;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;

public class CenterPagePanel extends JPanel{
	
	private Connection myDB;
	
	public CenterPagePanel(int nPage, Connection db){
		
		this.myDB=db;
		
		int skipedCoctails=(nPage-1)*9;
		
		this.setLayout(new GridLayout(0,3));
		
		 
		//Tratamiento de los 9 cocteles a mostrar
			//SELEC * FROM COCTAIL LIMIT skipedCoctails,9
			//Meter los 9 resultados en 9 *CoctailDescriptionPanel();
			
		 try{
			 Statement st = myDB.createStatement();
	         ResultSet rs = st.executeQuery("select * from coctail limit "+skipedCoctails+",9" );
	         String img,name,descr;
	         double price;
	         while (rs.next()){
	           name=rs.getString("nombre");
	           price = rs.getDouble("precio");
	           descr=rs.getString("Descripcion");
	           img = rs.getString("PathImg");
	           this.add(new CoctailDescriptionPanel(img,name,descr));
	          
	         }
	         rs.close();
	         st.close();
		 }
		 catch (SQLException e){
			 System.out.println("error centerPagePanel");
		 }
			
	}
	
}


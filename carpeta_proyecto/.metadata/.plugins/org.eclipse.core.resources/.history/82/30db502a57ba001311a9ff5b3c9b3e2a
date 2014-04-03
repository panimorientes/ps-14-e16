package homePage;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.ShopPagePanel;

public class SearchResultPage extends JPanel{
	
	private int pagNum;
	private Connection myDB;
	private CenterPagePanel myCenterPanel;
	private SearchFieldPanel fieldP;
	private int nCoc;
	private ShopPagePanel shopP;
	
	
	public SearchResultPage(Connection myDB){
		
		this.myDB=myDB;
		this.myCenterPanel = new CenterPagePanel(1,myDB);
		this.setLayout(new BorderLayout());
		pagNum=1;
		setNumberOfCoctails();
		this.fieldP=new SearchFieldPanel();
		this.shopP=new ShopPagePanel();
		
		this.add(myCenterPanel, BorderLayout.CENTER);
		this.add(fieldP,BorderLayout.NORTH);
		this.add(new PageNumberManager(this),BorderLayout.SOUTH);
		this.add(shopP,BorderLayout.EAST);
		
	}
	
	
		
	public int getNumberCoctails(){
		return nCoc;
	}
	
	private void setNumberOfCoctails(){
		//Consulta para saber cauntas páginas hay. 
		// nCOc=SELECT COUNT(*) FROM COCTAIL
		 try{
			 Statement st = myDB.createStatement();
		     ResultSet rs = st.executeQuery("select count(*) from coctail" );
		     
		     while (rs.next()){
		    	 nCoc=rs.getInt(1);
			          
			 }
			 rs.close();
			 st.close();
		}
		catch (SQLException e){
		System.out.println("error SearchResult getin nCOC");
		}
	}
	
	public void changePag(int n){
		this.remove(myCenterPanel);
		myCenterPanel=new CenterPagePanel(n,myDB);
		this.add(myCenterPanel);
		this.repaint();
		this.validate();
	}
	
	public int getPag(){
		return pagNum;
	}
	
}

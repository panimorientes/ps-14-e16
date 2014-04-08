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

import coctailPage.CenterCoctailPanel;

import profilePage.ProfileCenterPage;

import common.ShopPagePanel;

public class SearchResultPage extends JPanel{
	
	private int pagNum;
	private Connection myDB;
	private CenterPagePanel myCenterPanel;
	private SearchFieldPanel fieldP;
	private int nCoc;
	private ShopPagePanel shopP;
	private PageNumberManager pageNumbers;
	private CenterCoctailPanel showCoctailPage;
	private ProfileCenterPage profilePage;

	
	
	public SearchResultPage(Connection myDB){
		

		this.myDB=myDB;
		this.myCenterPanel = new CenterPagePanel(1,myDB,this);
		this.profilePage=new ProfileCenterPage(this);
		
		this.setLayout(new BorderLayout());
		pagNum=1;
		setNumberOfCoctails();
		this.fieldP=new SearchFieldPanel();
		this.shopP=new ShopPagePanel(this);
		
		this.add(myCenterPanel, BorderLayout.CENTER);
		this.add(fieldP,BorderLayout.NORTH);
		pageNumbers = (new PageNumberManager(this));
		this.add(pageNumbers,BorderLayout.SOUTH);
		this.add(shopP,BorderLayout.EAST);
		
	}
	
	
		
	public int getNumberCoctails(){
		return nCoc;
	}
	
	private void setNumberOfCoctails(){
		//Consulta para saber cauntas p�ginas hay. 
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
		myCenterPanel=new CenterPagePanel(n,myDB,this);
		this.add(myCenterPanel,BorderLayout.CENTER);
		this.repaint();
		this.validate();
	
		
	}
	
	public int getPag(){
		return pagNum;
	}
	
	public void goToCoctail(String coctail){
		this.remove(profilePage);
		this.remove(myCenterPanel);
		this.remove(pageNumbers);
		showCoctailPage=(new CenterCoctailPanel(coctail));
		this.add(showCoctailPage,BorderLayout.CENTER);
		this.validate();
		this.repaint();
	}
	
	public void goToHome(){
		this.remove(profilePage);
		if(showCoctailPage!=null){
			this.remove(showCoctailPage);
		}
		
		this.add(myCenterPanel,BorderLayout.CENTER);
		this.add(pageNumbers,BorderLayout.SOUTH);
		this.validate();
		this.repaint();
	}
	
	public void goToShop(){
		if(showCoctailPage!=null){
			this.remove(showCoctailPage);
		}
		this.remove(myCenterPanel);
		this.remove(pageNumbers);
		this.add(profilePage,BorderLayout.CENTER);
		this.validate();
		this.repaint();
	}
	
	
	
}

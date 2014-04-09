package homePage;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;
import coctailPage.CenterCoctailPanel;
import profilePage.ProfileCenterPage;
import common.ShopPagePanel;
import connection.ConnectionManager;

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

	
	
	public SearchResultPage(){

		try {
			this.myDB=ConnectionManager.getConnection();
		} 
		catch (SQLException e) {
			System.out.println("No es poisble establecer conexcion, SearchReultPage");
		}
		
		this.myCenterPanel = new CenterPagePanel(1,this);
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
	
	
	/**
	 * Return the number of coctails
	 * @return, the number of coctails
	 */
	public int getNumberCoctails(){
		return nCoc;
	}
	
	/**
	 * Sets to the private attribute nCoc the nuber total of coctails from DB
	 */
	private void setNumberOfCoctails(){
		
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
	
	/***
	 * Change the page of the coctails
	 * @param n the new page to go
	 */
	public void changePag(int n){
		this.remove(myCenterPanel);
		myCenterPanel=new CenterPagePanel(n,this);
		this.add(myCenterPanel,BorderLayout.CENTER);
		this.repaint();
		this.validate();
	}
	
	/**
	 * Return in wich number of page im
	 * @return the number of my page
	 */
	public int getPag(){
		return pagNum;
	}
	
	/**
	 * Shows in the home page the full info coctailpanel
	 * @param coctail coctail to show
	 */
	public void goToCoctail(String coctail){
		this.remove(profilePage);
		this.remove(myCenterPanel);
		this.remove(pageNumbers);
		showCoctailPage=(new CenterCoctailPanel(coctail));
		this.add(showCoctailPage,BorderLayout.CENTER);
		this.validate();
		this.repaint();
	}
	
	/**
	 * return to home page
	 */
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
	
	/**
	 * shows in the home page the full shop info
	 */
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
	
	/**
	 * add a coctail to the shop.
	 * @param name coctail to be added
	 */
	public void addToShop(String name){
		//MUST ADD A NAME CONTROL (U CANT ADD A COCATUL WITH U HAVE,
		//IF U DO THAT U ONLY INCREASE THE AMOUT BY 1
		shopP.addToShop(name,getPrice(name));
		this.validate();
		this.repaint();
	}
	
	/**
	 * get the price from the coctail with name name 
	 * Get the price from a query to the DB
	 * @param name of the coctail
	 * @return the price of the coctail
	 */
	private double getPrice(String name){
		double price=0;
		try{
			Statement st = myDB.createStatement();
		    ResultSet rs = st.executeQuery("SELECT precio from coctail where nombre='"+name+"'" );
		     
		    while (rs.next()){
		    	price=rs.getDouble("precio");
				
			}
			rs.close();
			st.close();
			return price;
			
		}
		catch (SQLException e){
			System.out.println("error SearchResult get price");
			return price;
		}
	}

}

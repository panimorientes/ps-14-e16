package coctailPage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


import connection.ConnectionManager;

public class CenterCoctailPanel extends JPanel {
	
	private Connection db;
	private String name;
	private JPanel centerInfo,allTheInfo;
	private JButton buyMe;
	
	/**
	 * Constructor method.
	 * @param name, name used to search in the DB
	 */
	public CenterCoctailPanel(String name){
		//Get the connection to he DB
		try{
			db = ConnectionManager.getConnection();
		}
		catch(SQLException e){
			System.out.println("error getConnection CenterCoctailPanel");
		}
		
		this.name=name;
		centerInfo=new JPanel();
		centerInfo.setLayout(new BorderLayout());
		allTheInfo=new JPanel();
		allTheInfo.setLayout(new BoxLayout(allTheInfo,BoxLayout.Y_AXIS));
		buyMe = new JButton("Anhadir al carrito");
		
		this.buyMe.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//add the coctail to the shop. Not implement yet.
			}
		});
		
		centerInfo.add(buyMe,BorderLayout.SOUTH);
		centerInfo.add(allTheInfo,BorderLayout.CENTER);	
		
		this.setLayout(new BorderLayout());
		JPanel titleP = new JPanel();
		JLabel nameLabel = new JLabel(this.name);
		nameLabel.setFont(new Font("Arial", Font.BOLD, 42));
		titleP.add(nameLabel);
		this.add(titleP,BorderLayout.NORTH);
		this.add(centerInfo,BorderLayout.CENTER);
		addImg();
		addDescr();		
		addIngr();
		addPrice();
	}
	
	/**
	 * add the price to the panel. Get the price from a query to the DB
	 */
	private void addPrice(){
		try{
			Statement st = db.createStatement();
		    ResultSet rs = st.executeQuery("SELECT precio from coctail where nombre='"+this.name+"'" );
		     
		    while (rs.next()){
		    	JLabel priceL = new JLabel("Precio: "+rs.getDouble("precio"));
				priceL.setFont(new Font("Arial", Font.ITALIC, 28));
				allTheInfo.add(priceL);
			}
			rs.close();
			st.close();
			
		}
		catch (SQLException e){
			System.out.println("error SearchResult get price");
			allTheInfo.add(new JLabel("Precio Desconocido"));
		}
	}
	
	/**
	 * add the coctail image to the left side of the panel.
	 * Get the path to the image from a query to the DB.
	 */
	private void addImg(){
		String img="";
		try{
			Statement st = db.createStatement();
			ResultSet rs = st.executeQuery("SELECT PathImg from coctail where nombre='"+this.name+"'" );
				     
			while (rs.next()){
				img=rs.getString("PathImg");
					          
			}
			rs.close();
			st.close();
		}
		catch (SQLException e){
			System.out.println("error SearchResult get IMG");
			img="Error consulta Img SQL";
		}
		JPanel panel = new JPanel((LayoutManager) new FlowLayout(FlowLayout.CENTER));
		
		//Only works with jpg iamges. Fix it with all images or try to resize it manually.
		if(img.contains(".jpg")){
			img=img.substring(0,img.length()-4)+"2.jpg";
			panel.add(new JLabel(new ImageIcon(img)));
		}
		else{
			panel.add(new JLabel(img));
		}
		this.add(panel,BorderLayout.WEST);
	}
	
	/**
	 * add a list of ingredients. Get the list from a query to the DB
	 */
	private void addIngr(){
		 try{
			 Statement st = db.createStatement();
		     ResultSet rs = st.executeQuery("SELECT Ingrediente_nombreI from " +
		     								"pertenece where Coctail_nombre ='"+this.name+"'" );
		     
		     JLabel ingrL = new JLabel("Lista de Ingredientes:");
		     ingrL.setFont(new Font("Arial", Font.ITALIC, 28));
		     allTheInfo.add(ingrL);
		   
		     while (rs.next()){
		    	ingrL = new JLabel("� "+rs.getString("Ingrediente_nombreI"));
				ingrL.setFont(new Font("Arial", Font.ITALIC, 22));
				allTheInfo.add(ingrL);
			          
			 }
			 rs.close();
			 st.close();
		}
		catch (SQLException e){
		System.out.println("error SearchResult get INGR");
		allTheInfo.add(new JLabel("Ingredientes Desconocidos"));
		}
		
	}
	
	/**
	 * add the desription of the coctail. 
	 * Get the description from a query to the DB 
	 */
	private void addDescr(){
		String descr ="";
		try{
			Statement st = db.createStatement();
		    ResultSet rs = st.executeQuery("SELECT Descripcion from coctail where " +
		     								"nombre='"+this.name+"'" );
		     
		    while (rs.next()){
		    	descr=""+rs.getString("Descripcion");          
			}
		    rs.close();
			st.close();
		}
		catch (SQLException e){
			System.out.println("error SearchResult get DESCCR");
			descr="Descripcion Desconocida";
		}
		 
		JTextArea descrJTA = new JTextArea(descr);
		descrJTA.setEditable(false);
		descrJTA.setBackground(null);
		descrJTA.setFocusable(false);
		descrJTA.setLineWrap(true);
		descrJTA.setWrapStyleWord(true);
		descrJTA.setFont(new Font("Arial", Font.PLAIN, 18));
		allTheInfo.add(descrJTA);
	}
	
}

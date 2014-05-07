package main;

import homePage.SearchResultPage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import profilePage.ProfileFrame;

import common.BotInfoPanel;
import common.TopInfoPanel;

import connection.ConnectionPool;

public class NinjaFrame extends JFrame {
	
	private BotInfoPanel botP;
	private TopInfoPanel topP;
	private SearchResultPage sCenterP;
	private ProfileFrame profile;
	private JButton prueba;

	/**
	 * Constructor method of the parent of my componetns
	 */
	public NinjaFrame(){
		
			Connection db;
			try {
				ConnectionPool connectionPool = new ConnectionPool();
				profile = new ProfileFrame();
				botP=new BotInfoPanel();
				sCenterP = new SearchResultPage(connectionPool);
				topP=new TopInfoPanel(this,sCenterP);

			} catch (SQLException e) {
				System.out.println("Error con la conexion");
			}
			
		
		
//		//TEST CODE TO GET A WELCOME PAGE ONLY A BUTTON
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		prueba = new JButton("ESTE BOTON ES DE PRUEBA; LA APLICACION TENDRA PANTALLA INICIAL!!");
		this.add(prueba);
		
		prueba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
			
				iniciateFrame();

			}
		});
//		
//		//END CODE TEST
		iniciateFrame();
	}
	
	/**
	 * Iiciate the JFrame.
	 */
	private void iniciateFrame(){
		
//		this.remove(prueba);
		this.setSize(1366,720);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setIconImage((new ImageIcon("coctailsImgs/logoMin.jpg").getImage()));
		this.setTitle("SOFTWARE NINJAS");
		this.setLayout(new BorderLayout());
		this.add(botP,BorderLayout.SOUTH);
		this.add(topP,BorderLayout.NORTH);
		this.add(sCenterP,BorderLayout.CENTER);
		
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();
		this.repaint();
	}
	
	public static void main(String[] args){
		
		new NinjaFrame();
		
	}
	
	/**
	 * Shows a new JFrame with the prifile info
	 */
	public void showProfile(){
		profile.setVisible(true);	
	}
	
}

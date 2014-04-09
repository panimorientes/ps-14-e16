package common;

import homePage.SearchResultPage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class ShopPagePanel extends JPanel{
	
	private JTextField myShop;
	private double total;
	private JPanel shopsPanel;
	private JButton accept; 
	private SearchResultPage searchPage;
	
	/**
	 * Method constructor
	 * @param sp, my parent. Used to visual updates.
	 */
	public ShopPagePanel(SearchResultPage sp){
		total=0;
		this.searchPage=sp;
		
		this.setLayout(new BorderLayout());
		shopsPanel=new JPanel();
		shopsPanel.setLayout(new BoxLayout(shopsPanel,BoxLayout.Y_AXIS));
		Border	blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder title = BorderFactory.createTitledBorder(blackline, "Tu Compra");
		title.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(title);
		
		myShop = new JTextField();
		JScrollPane scroll = new JScrollPane(shopsPanel);
		
		myShop.setColumns(20);
		myShop.setEditable(false);
		myShop.setBackground(null);
		myShop.setFocusable(false);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		p1.add(myShop);
		
		accept = new JButton("Comprar");
		accept.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {			
				 searchPage.goToShop();	
			}
			
		});
		
		p1.add(accept);

		this.add(scroll,BorderLayout.CENTER);
		this.add(p1,BorderLayout.SOUTH);
		this.updateTotal(0);
		
	}
	
	/**
	 * Method that updates the total price from all coctails
	 * @param addToTotal, amount to add to the total price
	 */
	public void updateTotal(double addToTotal){
		total=total+addToTotal;
		this.myShop.setText("Total de Compra: "+total);
	}
	
	/**
	 * add a new OneShopPanel to the shops list.
	 * @param name name of the coctail to add.
	 * @param price, price of the coctail
	 */
	public void addToShop(String name, double price){
		
		shopsPanel.add(new OneShopPanel(name,price,this));
		
	}
	
	/**
	 * Removes the OneSshopPanel o from shopsPanel
	 * @param p OneShopPane to be removed.
	 */
	public void removeShop(OneShopPanel p){
		shopsPanel.remove(p);
		this.validate();
		this.repaint();
	}

}

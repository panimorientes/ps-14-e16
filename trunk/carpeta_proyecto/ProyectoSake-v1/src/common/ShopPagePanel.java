package common;


import homePage.SearchResultPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class ShopPagePanel extends JPanel{
	private JTextField myShop;
	private double total;
	private JPanel shopsPanel;
	private Vector<OneShopPanel> shops;
	private JButton accept; 
	private SearchResultPage searchPage;
	
	public ShopPagePanel(SearchResultPage sp){
		
		this.searchPage=sp;
		total=0;
		
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
		for(int i=0;i<10;i++){
			addToShop("Irish"+i);
		}
	}
	
	public void updateTotal(double addToTotal){
		total=total+addToTotal;
		this.myShop.setText("Total de Compra: "+total);
	}
	
	public void addToShop(String p){
		
		shopsPanel.add(new OneShopPanel(p,12,this));
		
	}

}

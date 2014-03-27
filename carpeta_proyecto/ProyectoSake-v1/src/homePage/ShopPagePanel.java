package homePage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
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
	
	public ShopPagePanel(){
		total=10;
		
		this.setLayout(new BorderLayout());
		shopsPanel=new JPanel();
		shopsPanel.setLayout(new BoxLayout(shopsPanel,BoxLayout.Y_AXIS));
		Border	blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder title = BorderFactory.createTitledBorder(blackline, "Your Shop");
		title.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(title);
		
		myShop = new JTextField();
		JScrollPane scroll = new JScrollPane(shopsPanel);
		
		myShop.setColumns(20);
		myShop.setEditable(false);
		myShop.setBackground(null);
		myShop.setFocusable(false);

		this.add(scroll,BorderLayout.CENTER);
		this.add(myShop,BorderLayout.SOUTH);
		this.updateTotal();
		for(int i=0;i<10;i++){
			addToShop("Irish"+i);
		}
	}
	
	public void updateTotal(){
		this.myShop.setText("Total de Compra: "+total);
	}
	
	public void addToShop(String p){
		
		shopsPanel.add(new OneShopPanel(p,12));
		
	}

}

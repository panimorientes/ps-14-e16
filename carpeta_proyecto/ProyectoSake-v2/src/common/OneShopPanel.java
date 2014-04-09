package common;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OneShopPanel extends JPanel {
	
	private double totalPrice;
	private JLabel priceLabel;
	private JSpinner amount;
	private ShopPagePanel allShops;

	/**
	 * Constructor Method
	 * @param name, name of the coctail
	 * @param price, price of the coctail
	 * @param allShops, my parent, used to delete and update
	 */
	public OneShopPanel(String name, final double price, 
						final ShopPagePanel allShops){
		
		this.allShops=allShops;
		totalPrice=price;
		this.allShops.updateTotal(totalPrice);
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		SpinnerNumberModel model = new SpinnerNumberModel(1,0,null,1);
		amount = new JSpinner(model);
		
		amount.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				double oldTotal = totalPrice;
				totalPrice = price*(int)amount.getValue();
				priceLabel.setText("Total: "+totalPrice);
				allShops.updateTotal(totalPrice-oldTotal);
			}
		});
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel nameP = new JPanel();
		JPanel priceP = new JPanel();
		JPanel amountP = new JPanel();
		JPanel deleteP = new JPanel();
		
		JLabel nameLabel = new JLabel(name);
		nameP.add(nameLabel);
		
		priceLabel = new JLabel("Total: "+totalPrice);
		priceP.add(priceLabel);
		
		amountP.add(amount);
		
		JLabel deleteLabel = new JLabel(new ImageIcon("coctailsImgs/deleteI.jpg"));
		deleteP.add(deleteLabel);
		
		addListener(deleteLabel);
		
		
		p1.add(nameP);
		p2.add(amountP);
		p2.add(priceP);
		p2.add(deleteP);
		this.add(p1);
		this.add(p2);
		
	}
	
	/**
	 * Method used to set the listener to the lad
	 * @param lab, JLabel that will get the new listener
	 */
	public void addListener(JLabel lab){
		lab.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				   deleteMe();   
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}

		});
	}
	
	/**
	 * Method that first update the total (discount my own total price
	 * where total price = my price * amount
	 * Then the method calls to a method from my parent who removes myself.
	 */
	private void deleteMe(){
		allShops.updateTotal(totalPrice*-1);
		allShops.removeShop(this);
	}
}

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
	

	private double price,totalPrice;
	
	private JLabel priceLabel;
	private JSpinner amount;

	
	public OneShopPanel(String name, final double price){

		this.price=price;
		this.totalPrice=price;
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		SpinnerNumberModel model = new SpinnerNumberModel(1,0,null,1);
		amount = new JSpinner(model);
		amount.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				//comprobar nuemro negativos etc
				totalPrice = price*(int)amount.getValue();
				priceLabel.setText("Total: "+totalPrice);
				
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
	
	public void addListener(JLabel lab){
		lab.addMouseListener(new MouseListener() {
			
			   public void mouseClicked(MouseEvent arg0) {
				   
				   JOptionPane.showMessageDialog(null,"ELIMINAR�");
				   
			   }

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
				
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

		});
	}

}
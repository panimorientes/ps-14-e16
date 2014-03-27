// Clase que implementa el panel inferior de nuestra aplicacion.
// Visible en cualquier estado de esta.
package common;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BotInfoPanel extends JPanel {
	
	private JLabel faq,contact;
	
	public BotInfoPanel(){
		
		this.setLayout(new GridLayout(0,2));
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(0,2));
		
		JLabel cpRight = new JLabel("SoftWare Ninja @2014");
		p1.add(cpRight);
		
		contact = new JLabel("Contact");
		faq = new JLabel("FAQ");
		p2.add(contact);
		p2.add(faq);
		
		this.add(p1);
		this.add(p2);
		
		addListeners();
			
	}
	
	/**
	 * Método privado que añade los listeners al JLabel de contacto y al JLabel de FAQ
	 */
	private void addListeners(){
		
		faq.addMouseListener(new MouseListener() {
			
			   public void mouseClicked(MouseEvent arg0) {
				   
				   JOptionPane.showMessageDialog(null,"AQUI VIENE EL FAQ, DISCUTIR COMO");
				   
			   }

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				faq.setForeground(Color.BLUE);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
				faq.setForeground(null);
				
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
		
		contact.addMouseListener(new MouseListener() {
			
			   public void mouseClicked(MouseEvent arg0) {
				   
				   JOptionPane.showMessageDialog(null,"AQUI VIENE EL CONTACTO, DISCUTIR COMO");
				   
			   }

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				contact.setForeground(Color.BLUE);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
				contact.setForeground(null);
				
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

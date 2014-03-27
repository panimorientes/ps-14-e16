package common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TopInfoPanel extends JPanel{
	
	private JLabel logo, user;
	
	
	public TopInfoPanel(){
		
		this.setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		
		logo = new JLabel(new ImageIcon("CoctailsImgs/logo.jpg"));
		p1.add(logo,BorderLayout.EAST);
		
		user = new JLabel("usuario");
		JLabel hola = new JLabel ("Hola, ");
		
		p2.add(hola);
		p2.add(user);
		
		this.add(p1,BorderLayout.WEST);
		this.add(p2,BorderLayout.EAST);
		
		addListeners();
		
		
	}
	
	/**
	 * M�todo privado que a�ade los listeners al JLabel de contacto y al JLabel de FAQ
	 */
	private void addListeners(){
		
		logo.addMouseListener(new MouseListener() {
			
			   public void mouseClicked(MouseEvent arg0) {
				   
				   JOptionPane.showMessageDialog(null,"AQUI HAREMOS ALGO?�");
				   
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
		
		user.addMouseListener(new MouseListener() {
			
			   public void mouseClicked(MouseEvent arg0) {
				   
				   JOptionPane.showMessageDialog(null,"AQUI VAMOS AL PERFIL");
				   
			   }

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				user.setForeground(Color.BLUE);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
				user.setForeground(null);
				
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


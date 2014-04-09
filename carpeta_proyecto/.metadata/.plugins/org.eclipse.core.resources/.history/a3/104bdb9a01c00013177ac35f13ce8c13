package paginaPrincipal;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchFieldPanel extends JPanel{
	
	private JTextField field;
	private JLabel search, advanced;



	public SearchFieldPanel(){
		
		field = new JTextField();
		field.setColumns(40);
		search = new JLabel(new ImageIcon("CoctailsImgs/lupa.jpg"));
		advanced = new JLabel("Busqueda avanzada");
		
		this.setLayout(new FlowLayout());
		this.add(advanced);
		this.add(field);
		this.add(search);
		
		
		addListeners();
		
	}
	/**
	 * Método privado que añade los listeners al JLabel advanced y al JLabel de search
	 */
	private void addListeners(){
		
		search.addMouseListener(new MouseListener() {
			
			   public void mouseClicked(MouseEvent arg0) {
				   
				   JOptionPane.showMessageDialog(null,"BUSCA");
				   
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
		
		advanced.addMouseListener(new MouseListener() {
			
			   public void mouseClicked(MouseEvent arg0) {
				   
				   JOptionPane.showMessageDialog(null,"BUSQUEDA AVANZADA");
				   
			   }
	
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				advanced.setForeground(Color.BLUE);
				
			}
	
			@Override
			public void mouseExited(MouseEvent arg0) {
				
				advanced.setForeground(null);
				
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
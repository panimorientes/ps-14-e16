package common;

import homePage.SearchResultPage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.NinjaFrame;

public class TopInfoPanel extends JPanel{
	
	private JLabel icon, user;
	private NinjaFrame frame;
	private SearchResultPage searchPage;
	
	/**
	 * Method constructor
	 * @param sp,my parent used to link
	 * @param searchPage, my Frame Parent, used to link
	 */
	public TopInfoPanel(NinjaFrame sp, SearchResultPage searchPage){
		
		frame=sp;
		this.searchPage=searchPage;
		this.setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		icon = new JLabel(new ImageIcon("CoctailsImgs/logo.jpg"));
		p1.add(icon,BorderLayout.EAST);
		
		user = new JLabel("usuario");
		JLabel hello = new JLabel ("Hola, ");
		
		p2.add(hello);
		p2.add(user);
		
		this.add(p1,BorderLayout.WEST);
		this.add(p2,BorderLayout.EAST);
		
		addListeners();
	
	}
	
	/**
	 * Set the listeners to the labels "logo", and "user"
	 */
	private void addListeners(){
		
		icon.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent arg0) {   
				searchPage.goToHome();	   
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
		
		user.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent arg0) {	   
				  frame.showProfile();
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
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}

		});	
	}
}

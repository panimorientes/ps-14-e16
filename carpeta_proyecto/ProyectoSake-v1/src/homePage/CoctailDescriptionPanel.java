package homePage;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class CoctailDescriptionPanel extends JPanel {
	
	private String name,descr,img;
	private JLabel imgLabel, titleLabel;
	private SearchResultPage searchPage;
	
	/**
	 * Constructor. Makes an object with some info of the coctail
	 * @param img, the image of the coctail
	 * @param name, the name of the coctail
	 * @param descr, the description of the coctail
	 * @param searchPage, my grandParent. Used to link.
	 */
	public CoctailDescriptionPanel(String img, String name, String descr, 
									SearchResultPage searchPage){
		
		this.img=img;
		this.name=name;
		this.searchPage=searchPage;

		this.descr=descr;
		if(this.descr.length()>100){
			this.descr=this.descr.substring(0,96)+" ...";
		}
		
		this.addComponents();
		this.addListeners();

	}
	
	/**
	 * Add the components of the JPanel coctail.
	 */
	private void addComponents(){
		
		JPanel imgPanel = new JPanel();
		this.add(imgPanel);
		
		this.imgLabel = new JLabel();
		imgLabel.setIcon(new ImageIcon(this.img));
		imgPanel.add(imgLabel);
		
		JPanel infoP = new JPanel();
		this.add(infoP);
		infoP.setLayout(new BorderLayout());
		
		JPanel bot = new JPanel();
		infoP.add(bot,BorderLayout.SOUTH);
		
		
		JButton btn = new JButton("Comprar");
		bot.add(btn);	
		
		JPanel center = new JPanel();
		infoP.add(center,BorderLayout.CENTER);
		
		JTextArea descr = new JTextArea(this.descr);
		descr.setEditable(false);
		descr.setBackground(null);
		descr.setFocusable(false);
		descr.setLineWrap(true);
		descr.setWrapStyleWord(true);
		descr.setColumns(17);
		
		center.add(descr);
		
		JPanel titlePanel = new JPanel();
		infoP.add(titlePanel,BorderLayout.NORTH);

		titleLabel = new JLabel(this.name);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); 
		titlePanel.add(titleLabel);
		
		btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
		
			 searchPage.addToShop(name);

		}
	});
	}
	
	/**
	 * Set the listeners to the JLabels "imgLabel" and "titleLabel"
	 * 
	 */
	private void addListeners(){
		
		titleLabel.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent arg0) {
				searchPage.goToCoctail(name);
				   
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
		
		imgLabel.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent arg0) {
				searchPage.goToCoctail(name);
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
}
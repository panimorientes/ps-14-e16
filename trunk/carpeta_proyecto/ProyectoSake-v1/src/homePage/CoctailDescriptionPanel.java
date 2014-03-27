package homePage;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class CoctailDescriptionPanel extends JPanel {
	
	private String name, descr,img;
	
	public CoctailDescriptionPanel(String img, String name, String descr){
		
		this.img=img;
		this.name=name;
		
		
		//gestion de la descripcion
		this.descr=descr;
		if(this.descr.length()>100){
			this.descr=this.descr.substring(0,96)+" ...";
		}
		
		this.addComponents();
//		this.validate();
//		this.repaint();
	}
	
	private void addComponents(){
		
		JPanel imgPanel = new JPanel();
		this.add(imgPanel);
		
		JLabel imgLabel = new JLabel();
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
		
		JPanel titleLabel = new JPanel();
		infoP.add(titleLabel,BorderLayout.NORTH);

		JLabel nameLabel = new JLabel(this.name);
		nameLabel.setFont(new Font("Arial", Font.BOLD, 18)); 
		titleLabel.add(nameLabel);
		
		btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
		
			 JOptionPane.showMessageDialog(null,"COMRPAR "+name);

		}
	});
	}
}
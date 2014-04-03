package profilePage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ProfileCenterPage extends JFrame{
	
	private ProfileInfo info;
	private JButton acept, cancel, edit, save;
	private JPanel butP;
	
	public ProfileCenterPage(){
		info = new ProfileInfo();
		butP=new JPanel();
		this.setTitle("PERFIL DE USUARIO");
		this.setIconImage((new ImageIcon("coctailsImgs/logoMin.jpg").getImage()));
		this.setLayout(new BorderLayout());
		this.add(info,BorderLayout.CENTER);
		this.setVisible(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setSize(500,700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		putButtons();
		butP.add(edit);
		butP.add(acept);
		this.add(butP,BorderLayout.SOUTH);
		
		
	}
	
	private void putButtons(){
		acept = new JButton("Aceptar");
		acept.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				hideMe();
				
			}
			
		});
		
		edit = new JButton("Editar");
		edit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				butP.remove(acept);
				butP.remove(edit);
				butP.add(save);
				butP.add(cancel);
				info.setInfoEditable();
				validateAndrepaint();
			}
			
		});
		
		cancel = new JButton("Cancelar");
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				butP.remove(save);
				butP.remove(cancel);
				butP.add(acept);
				butP.add(edit);
				info.setInfoNotEditable();
				validateAndrepaint();
				
			}
			
		});
		
		save = new JButton("Guardar");
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private void hideMe(){
		this.setVisible(false);
	}
	
	private void validateAndrepaint(){
		this.validate();
		this.repaint();
	}
	
	
	
	

}

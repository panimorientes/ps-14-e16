package profilePage;

import homePage.SearchResultPage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ProfileCenterPage extends JPanel{
	
	private ProfileInfo info;
	private JButton continueBuy, cancel, edit, save;
	private JPanel butP;
	private SearchResultPage searchPage;
	
	/**
	 * Constructor
	 * @param sp, my parent
	 */
	public ProfileCenterPage(SearchResultPage sp){
		info = new ProfileInfo();
		butP=new JPanel();
		searchPage=sp;
		this.setLayout(new BorderLayout());
		this.add(info,BorderLayout.CENTER);

		addButtons();
		butP.add(edit);
		butP.add(continueBuy);
		this.add(butP,BorderLayout.SOUTH);
	}
	
	/**
	 * create the buttons and set the listeners.
	 */
	private void addButtons(){
		continueBuy = new JButton("Continuar Comprando");
		continueBuy.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchPage.goToHome();	
			}
		});
		
		edit = new JButton("Editar");
		edit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				butP.remove(continueBuy);
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
				
				int option = JOptionPane.showOptionDialog(
						   null,
						   "Estas seguro que deseas cancelar", 
						   "Cancelar edicion",
						   JOptionPane.YES_NO_OPTION,
						   JOptionPane.QUESTION_MESSAGE,
						   null, //possible image icon
						   new Object[] { "Si","No"},
						   "Yes");
				if(option==0){
					butP.remove(save);
					butP.remove(cancel);
					butP.add(continueBuy);
					butP.add(edit);
					info.setChanges(false);
					info.setInfoNotEditable();
					validateAndrepaint();
				}
								
			}
			
		});
		
		save = new JButton("Guardar");
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int option = JOptionPane.showOptionDialog(
						   null,
						   "Estas seguro que deseas guardas los cambios", 
						   "Guardando cambios",
						   JOptionPane.YES_NO_OPTION,
						   JOptionPane.QUESTION_MESSAGE,
						   null, //icono
						   new Object[] { "Si","No"},
						   "Yes");
				
				if(option==0){
					
					butP.remove(save);
					butP.remove(cancel);
					butP.add(continueBuy);
					butP.add(edit);
					info.setChanges(true);
					info.setInfoNotEditable();
					validateAndrepaint();
					
					JOptionPane.showMessageDialog(
							   null,
							   "Nuevos Datos Guardados Correctamente");		
				}					
			}		
		});
	}
	
	/**
	 * calls to the method validate and repaint for mySelf.
	 */
	private void validateAndrepaint(){
		this.validate();
		this.repaint();
	}
	
}

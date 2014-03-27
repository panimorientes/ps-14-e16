package paginaPrincipal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PageNumberManager extends JPanel{

	private SearchResultPage mySearch;
	private int nPag;
	
	/**
	 * panel contendra las paginas disponibles y los links hacia ellas.
	 * Si solo hay un página dispoible aparecera solo página 1. y sin nignun link
	 * Si hay mas de 10 páginas disponibles aparecera  <Anterior Pagina 1 2 3 4 ... n-3 n-2 n-1 n Siguiente>, sinedo n el numero de páginas
	 * Si hay entre 1 y 10, aparecera lo mismo que en la anterior pero sin los "..."
	 */
	public PageNumberManager(SearchResultPage p){
		mySearch=p;

		int nCoc;
		nCoc=p.getNumberCoctails();
		
		
		if(nCoc%9==0){
			nCoc=nCoc-1;
		}
		
		nPag=(nCoc/9)+1;
		
		//solo hya una págin
		if(nPag == 1){
			this.add(new JLabel("<"));
			addLabel("1");
			this.add(new JLabel(">"));
		}
		
		//hay 10 o menos páginas
		else if(nPag <11 && nPag!=1){

			addLabel("<Anterior");
			
			for(int i=1; i<nPag+1;i++){

				addLabel(""+i);
			}
			

			addLabel("Siguiente>");
			
		}
		//hay mas de 10 páginas
		else{

			addLabel("<Anterior");
			for(int i=1;i<6;i++){
				addLabel(""+i);
			}
			
			for(int i=4;i>-1;i--){

				addLabel(""+(nPag-i));
			}
			

			addLabel("Siguiente>");
		}
		
	}
	
	private void addLabel(final String pagN){
		
		final JLabel lab = new JLabel(pagN);

		lab.setFont(new Font("Arial", Font.BOLD, 16)); 

		
		final int goToPag;
		int actPag=0;
		if(!(pagN.equals("<Anterior")|| pagN.equals("Siguiente>"))){
			actPag=Integer.parseInt(pagN);
		}
		
		
		if(pagN.equals("<Anterior")){
			int goTo = mySearch.getPag()-1;
			if(goTo<1){
				goTo=1;
			}
			goToPag=goTo;
			
		}
		else if(pagN.equals("Siguiente>")){
			int goTo = mySearch.getPag()+1;
			if(goTo>nPag){
				goTo=nPag;
			}
			goToPag=goTo;
		}
		else{
			goToPag=actPag;
		}
		
		
		lab.addMouseListener(new MouseListener() {
			
			   public void mouseClicked(MouseEvent arg0) {
				  
				  
				  mySearch.changePag(goToPag);
				   
			   }

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				lab.setForeground(Color.BLUE);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
				lab.setForeground(null);
				
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

		this.add(lab);
	}
	
	
}

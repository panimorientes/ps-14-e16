package homePage;

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
	 * Constructor
	 * @param p my parent.
	 */
	public PageNumberManager(SearchResultPage p){
		mySearch=p;

		int nCoc;
		nCoc=p.getNumberCoctails();
		
		
		if(nCoc%9==0){
			nCoc=nCoc-1;
		}
		
		nPag=(nCoc/9)+1;
		

		if(nPag == 1){
			this.add(new JLabel("<"));
			addLabel("1");
			this.add(new JLabel(">"));
		}
		
		else if(nPag <11 && nPag!=1){

			addLabel("<Anterior");
			
			for(int i=1; i<nPag+1;i++){

				addLabel(""+i);
			}

			addLabel("Siguiente>");		
		}

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
	
	/**
	 * add one JLabel to this and addthe listenets to this JLabel.
	 * @param pagN, name of the JLabel
	 */
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
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}

		});

		this.add(lab);
	}
	
}

package homePage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PageNumberManager extends JPanel {

	private static final String RIGHT_STRING = ">";
	private static final String LEFT_STRING = "<";
	private static final String BACK_STRING = "Anterior";
	private static final String NEXT_STRING = "Siguiente";
	private static final int MAX_PAGES_NUMBER = 11;
	private SearchResultPage mySearch;
	private int nPag;

	/**
	 * Constructor
	 * 
	 * @param p
	 *            my parent.
	 */
	public PageNumberManager(SearchResultPage p) {
		mySearch = p;

		int nCoc;
		nCoc = p.getNumberCoctails();

		if (nCoc % CenterPagePanel.COCTAILS_PER_PAGE == 0) {
			nCoc = nCoc - 1;
		}

		nPag = (nCoc / CenterPagePanel.COCTAILS_PER_PAGE) + 1;

		if (nPag == 1) {
			this.add(new JLabel(LEFT_STRING));
			addLabel("1");
			this.add(new JLabel(RIGHT_STRING));
		}

		else if (nPag < MAX_PAGES_NUMBER && nPag != 1) {

			addLabel(LEFT_STRING+BACK_STRING);

			for (int i = 1; i < nPag + 1; i++) {

				addLabel(String.valueOf(i));
			}

			addLabel(NEXT_STRING+RIGHT_STRING);
		}

		else {
			addLabel(LEFT_STRING+BACK_STRING);
			for (int i = 1; i < 6; i++) {
				addLabel(String.valueOf(i));
			}

			for (int i = 4; i > -1; i--) {

				addLabel(String.valueOf(nPag - i));
			}

			addLabel(NEXT_STRING+RIGHT_STRING);
		}
	}

	/**
	 * add one JLabel to this and addthe listenets to this JLabel.
	 * 
	 * @param pagN
	 *            , name of the JLabel
	 */
	private void addLabel(final String pagN) {

		final JLabel lab = new JLabel(pagN);

		lab.setFont(new Font("Arial", Font.BOLD, 16));

		final int goToPag;
		int actPag = 0;
		if (!(pagN.equals(LEFT_STRING+BACK_STRING) || pagN.equals(NEXT_STRING+RIGHT_STRING))) {
			actPag = Integer.parseInt(pagN);
		}

		if (pagN.equals(LEFT_STRING+BACK_STRING)) {
			int goTo = mySearch.getPag() - 1;
			if (goTo < 1) {
				goTo = 1;
			}
			goToPag = goTo;
		} else if (pagN.equals(NEXT_STRING+RIGHT_STRING)) {
			int goTo = mySearch.getPag() + 1;
			if (goTo > nPag) {
				goTo = nPag;
			}
			goToPag = goTo;
		} else {
			goToPag = actPag;
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
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});

		this.add(lab);
	}

}

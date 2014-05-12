package homePage;

import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JPanel;

import connection.DataBaseNames;
import connection.DataManager;

public class CenterPagePanel extends JPanel {

	public static final int COCTAILS_PER_PAGE = 9;
	private static String SPACE = " ";

	/**
	 * Method Constructor, The constructor makes a new Object who contains 9
	 * coctailt from the DB.
	 * 
	 * @param myDB
	 * @param nPage
	 *            , number of the page
	 * @param searchPage
	 *            , my parent, usedto link
	 */
	public CenterPagePanel(int nPage, SearchResultPage searchPage, String query) {
		int skipedCoctails = (nPage - 1) * COCTAILS_PER_PAGE;
		query = query + DataManager.LIMIT + skipedCoctails
				+ DataManager.DATA_SEPARATOR + COCTAILS_PER_PAGE;
		String whatToSelect = DataBaseNames.COCTAIL_COLUM_NAME + SPACE
				+ DataBaseNames.COCTAIL_COLUM_PRICE + SPACE
				+ DataBaseNames.COCTAIL_COLUM_DESCR + SPACE
				+ DataBaseNames.COCTAIL_COLUM_PATH_IMG;
		Vector<String> data = DataManager.getData(query,
				DataManager.getColumns(whatToSelect));

		String img, name, descr;

		this.setLayout(new GridLayout(0, 3));
		for (int j = 0; j < data.size(); j = j + 4) {
			name = data.get(j);
			descr = data.get(j + 2);
			img = data.get(j + 3);
			this.add(new CoctailDescriptionPanel(img, name, descr, searchPage));
		}

	}
}

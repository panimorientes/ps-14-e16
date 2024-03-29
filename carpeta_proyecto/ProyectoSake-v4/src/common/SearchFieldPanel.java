package common;

import homePage.SearchResultPage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import properties.PropertiesManager;

import main.NinjaFrame;

import connection.DataBaseNames;
import connection.DataManager;

public class SearchFieldPanel extends JPanel {

	private JTextField field;
	private JLabel search, advanced, price, ingredient;
	private SearchResultPage searchPage;
	private RangeSlider priceSlider;
	private JComboBox<String> ingredientCB;
	private boolean showingAdv = false;

	/**
	 * Constructor.
	 */
	public SearchFieldPanel(SearchResultPage panel) {

		searchPage = panel;
		field = new JTextField();
		field.setColumns(40);
		search = new JLabel(new ImageIcon(
				PropertiesManager
						.getProperties(PropertiesManager.SFP_SEARCH_IMG_PATH)));
		advanced = new JLabel(
				PropertiesManager
						.getProperties(PropertiesManager.SFP_ADVANCED_LABEL));
		price = new JLabel(
				PropertiesManager.getProperties(PropertiesManager.PRICE_LABEL));
		ingredient = new JLabel(
				PropertiesManager
						.getProperties(PropertiesManager.SFP_INGREDIENT_LABEL));
		initSlider();
		initComboBox();

		this.setLayout(new FlowLayout());
		this.add(advanced);
		this.add(field);
		this.add(search);

		addListeners();

	}

	/**
	 * Iniciate the comboBox
	 */
	private void initComboBox() {
		String query = DataManager.getQuery(DataManager.SELECT,
				DataBaseNames.INGREDIENT_COLUM_NAME,
				DataBaseNames.INGREDIENT_TABLE);
		Vector<String> ingredients = connection.DataManager.getData(query,
				DataManager.getColumns(DataBaseNames.INGREDIENT_COLUM_NAME));
		ingredients.insertElementAt(
				connection.DataManager.NO_INGREDIENT_SELECTED, 0);
		ingredientCB = new JComboBox<String>(ingredients);
	}

	/**
	 * Iniciate the Slider
	 */
	private void initSlider() {

		priceSlider = new RangeSlider(0, 10);
		priceSlider.setMajorTickSpacing(10);
		priceSlider.setMinorTickSpacing(1);
		priceSlider.setPaintTicks(true);
		priceSlider.setPaintLabels(true);
		priceSlider.setValue(2);
		priceSlider.setUpperValue(7);

	}

	/**
	 * Add the listeners to the JLabel "search" and "advanced"
	 */
	private void addListeners() {

		search.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				String query = "";
				if (!advanced.isVisible()) {

					String condition = DataManager.LIKE
							+ DataManager.NAME_LIKE_IN + field.getText()
							+ DataManager.NAME_LIKE_OUT;
					query = DataManager.getQueryWhere(DataManager.SELECT,
							DataManager.SELECT_ALL,
							DataBaseNames.COCTAIL_TABLE, condition);
				} else {
					query = getQuery();
				}
				if (searchPage.isProfileVisible()) {
					searchPage.goToHome();
				}
				searchPage.updateQuery(query);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(NinjaFrame.handCursor);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				setCursor(NinjaFrame.defaultCursor);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});

		advanced.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				if (!showingAdv) {
					showAdvanced();
					showingAdv = true;
				} else {
					removeAdvanced();
					showingAdv = false;
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(NinjaFrame.handCursor);
				advanced.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				setCursor(NinjaFrame.defaultCursor);
				advanced.setForeground(null);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});

	}

	/**
	 * Show the advanced search
	 */
	private void showAdvanced() {
		advanced.setText(PropertiesManager
				.getProperties(PropertiesManager.SFP_NON_ADVANCED_LABEL));

		this.add(ingredient);
		this.add(ingredientCB);
		this.add(price);
		this.add(priceSlider);
		this.validate();
		this.repaint();
	}

	/**
	 * Hide the advanced search
	 */
	private void removeAdvanced() {
		advanced.setText(PropertiesManager
				.getProperties(PropertiesManager.SFP_ADVANCED_LABEL));

		this.remove(ingredient);
		this.remove(ingredientCB);
		this.remove(price);
		this.remove(priceSlider);
		this.validate();
		this.repaint();
	}

	/**
	 * gets the price query
	 * 
	 * @return the query to get price
	 */
	private String getPriceQuery() {
		String priceQuery;
		priceQuery = DataBaseNames.COCTAIL_COLUM_PRICE
				+ DataManager.DATA_BIGGER + DataManager.DATA_EQUAL
				+ priceSlider.getValue();
		priceQuery = priceQuery + DataManager.AND;
		priceQuery = priceQuery + DataBaseNames.COCTAIL_COLUM_PRICE
				+ DataManager.DATA_SMALLER + DataManager.DATA_EQUAL
				+ priceSlider.getUpperValue();
		return priceQuery;

	}

	/**
	 * get the query
	 * 
	 * @return query to the bd
	 */
	private String getQuery() {
		String query = "", selectedIngredient;
		selectedIngredient = (String) ingredientCB.getSelectedItem();

		if (!selectedIngredient.equals(DataManager.NO_INGREDIENT_SELECTED)) {

			String whatToSelect = DataBaseNames.COCTAIL_COLUM_NAME
					+ DataManager.DATA_SEPARATOR
					+ DataBaseNames.COCTAIL_COLUM_PRICE
					+ DataManager.DATA_SEPARATOR
					+ DataBaseNames.COCTAIL_COLUM_DESCR
					+ DataManager.DATA_SEPARATOR
					+ DataBaseNames.COCTAIL_COLUM_PATH_IMG;
			String fromWhere = DataBaseNames.COCTAIL_TABLE
					+ DataManager.DATA_SEPARATOR + DataBaseNames.PERTAIN_TABLE;
			String condition = DataBaseNames.COCTAIL_COLUM_NAME
					+ DataManager.DATA_EQUAL
					+ DataBaseNames.PERTAIN_COLUM_COCTAIL + DataManager.AND
					+ DataBaseNames.COCTAIL_COLUM_NAME + DataManager.LIKE
					+ DataManager.NAME_LIKE_IN + field.getText()
					+ DataManager.NAME_LIKE_OUT + DataManager.AND
					+ DataBaseNames.PERTAIN_COLUM_INGREDIENT
					+ DataManager.DATA_EQUAL + DataManager.EQUAL_IN_OUT
					+ ingredientCB.getSelectedItem() + DataManager.EQUAL_IN_OUT
					+ DataManager.AND + getPriceQuery();
			query = DataManager.getQueryWhere(DataManager.SELECT_DIS,
					whatToSelect, fromWhere, condition);

		} else {

			String condition = DataBaseNames.COCTAIL_COLUM_NAME
					+ DataManager.LIKE + DataManager.NAME_LIKE_IN
					+ field.getText() + DataManager.NAME_LIKE_OUT
					+ DataManager.AND + getPriceQuery();
			query = DataManager.getQueryWhere(DataManager.SELECT,
					DataManager.SELECT_ALL, DataBaseNames.COCTAIL_TABLE,
					condition);
		}

		return query;
	}
}
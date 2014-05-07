package common;

import homePage.SearchResultPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class ShopPagePanel extends JPanel {

	private static final String TOTAL_TEXT = "Total de Compra: ";
	private static final String ACCEPT_BUTTON_NAME = "Comprar";
	private static final String TITLE_BORDER = "Tu Compra";
	private JTextField myShop;
	private double total;
	private JPanel shopsPanel;
	private JButton accept;
	private SearchResultPage searchPage;
	private Vector<OneShopPanel> oneShopVector;

	/**
	 * Method constructor
	 * 
	 * @param sp
	 *            , my parent. Used to visual updates.
	 */
	public ShopPagePanel(SearchResultPage sp) {
		total = 0;
		this.searchPage = sp;
		oneShopVector = new Vector<OneShopPanel>();
		this.setLayout(new BorderLayout());
		shopsPanel = new JPanel();
		shopsPanel.setLayout(new BoxLayout(shopsPanel, BoxLayout.Y_AXIS));
		shopsPanel.setAlignmentY(TOP_ALIGNMENT);

		Border blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder title = BorderFactory.createTitledBorder(blackline,
				TITLE_BORDER);
		title.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(title);

		myShop = new JTextField();
		JScrollPane scroll = new JScrollPane(shopsPanel);

		myShop.setColumns(20);
		myShop.setEditable(false);
		myShop.setBackground(null);
		myShop.setFocusable(false);

		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p1.add(myShop);

		accept = new JButton(ACCEPT_BUTTON_NAME);
		accept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchPage.goToShop();
			}

		});

		p1.add(accept);

		this.add(scroll, BorderLayout.CENTER);
		this.add(p1, BorderLayout.SOUTH);
		this.updateTotal(0);

	}

	/**
	 * Method that updates the total price from all coctails
	 * 
	 * @param addToTotal
	 *            , amount to add to the total price
	 */
	public void updateTotal(double addToTotal) {
		total = total + addToTotal;
		this.myShop.setText(TOTAL_TEXT + total);
	}

	/**
	 * add a new OneShopPanel if it does not exist to the shops list. if the
	 * OneShopPanel already eistm increase by one the amount.
	 * 
	 * @param name
	 *            name of the coctail to add.
	 * @param price
	 *            , price of the coctail
	 */
	public void addToShop(String name, double price) {

		int i = 0;
		boolean find = false;
		while (i < oneShopVector.size() && !find) {
			if (name.equals(oneShopVector.get(i).getName())) {
				find = true;
			} else {
				i++;
			}
		}
		// no existe
		if (!find) {
			OneShopPanel shop = new OneShopPanel(name, price, this);
			oneShopVector.addElement(shop);
			shopsPanel.add(shop);
		}
		// exite
		else {
			oneShopVector.get(i).updateAmount();
		}

	}

	/**
	 * Removes the OneSshopPanel o from shopsPanel
	 * 
	 * @param p
	 *            OneShopPane to be removed.
	 */
	public void removeShop(OneShopPanel p) {
		shopsPanel.remove(p);
		oneShopVector.removeElement(p);
		this.validate();
		this.repaint();
	}

}

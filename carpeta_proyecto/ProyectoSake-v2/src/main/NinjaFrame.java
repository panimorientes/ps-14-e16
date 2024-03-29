package main;

import homePage.SearchResultPage;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import profilePage.DataProfileManager;
import profilePage.ProfileFrame;

import common.BotInfoPanel;
import common.TopInfoPanel;

public class NinjaFrame extends JFrame {

	private static final int SIZE_Y = 720;
	private static final int SIZE_X = 1366;
	private static final String TITLE_LABEL = "SOFTWARE NINJAS";
	private static final String LOGO_PATH = "coctailsImgs/logoMin.jpg";
	private BotInfoPanel botP;
	private TopInfoPanel topP;
	private SearchResultPage sCenterP;
	private ProfileFrame profile;

	/**
	 * Constructor method of the parent of my componetns
	 */
	public NinjaFrame() {

		profile = new ProfileFrame();
		botP = new BotInfoPanel();
		sCenterP = new SearchResultPage();
		topP = new TopInfoPanel(this, sCenterP);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		iniciateFrame();
	}

	/**
	 * Iiciate the JFrame.
	 */
	private void iniciateFrame() {

		// this.remove(prueba);
		this.setSize(SIZE_X, SIZE_Y);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setIconImage((new ImageIcon(LOGO_PATH).getImage()));
		this.setTitle(TITLE_LABEL);
		this.setLayout(new BorderLayout());
		this.add(botP, BorderLayout.SOUTH);
		this.add(topP, BorderLayout.NORTH);
		this.add(sCenterP, BorderLayout.CENTER);

		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();
		this.repaint();
	}

	public static void start() {
		new NinjaFrame();
	}

	public static void main(String[] args) {
		if (DataProfileManager.isInstalled()) {
			start();
		} else {
			new ProfileFrame();
		}

	}

	/**
	 * Shows a new JFrame with the prifile info
	 */
	public void showProfile() {
		profile.setVisible(true);
	}

}

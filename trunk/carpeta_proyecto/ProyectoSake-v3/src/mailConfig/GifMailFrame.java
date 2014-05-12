package mailConfig;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import properties.PropertiesManager;

public class GifMailFrame extends JFrame {

	private static final int FRAME_X_SIZE = 400;
	private static final int FRAME_Y_SIZE = 125;
	private static final String GIF_PATH = "coctailsImgs/gifMail.gif";

	public GifMailFrame() {
		this.setLayout(new FlowLayout());
		JPanel gifP = new JPanel();

		ImageIcon myImgIcon = new ImageIcon(GIF_PATH);
		JLabel imageLbl = new JLabel(myImgIcon);
		gifP.add(imageLbl, BorderLayout.CENTER);

		myImgIcon.setImageObserver(imageLbl);

		this.add(gifP);
		this.setTitle(PropertiesManager
				.getProperties(PropertiesManager.GMF_TITLE_FRAME));
		this.add(new JLabel(PropertiesManager
				.getProperties(PropertiesManager.GMF_TEXT)));

		this.setSize(FRAME_X_SIZE, FRAME_Y_SIZE);
		this.setResizable(false);
		this.setIconImage((new ImageIcon(PropertiesManager
				.getProperties(PropertiesManager.IMG_COMPANY_LITTLE_PATH))
				.getImage()));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.validate();
		this.repaint();
	}

}

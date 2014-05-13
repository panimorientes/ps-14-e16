package common;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import mailConfig.MailFrame;
import main.NinjaFrame;
import properties.PropertiesManager;
import faqFrame.FaqFrame;

public class BotInfoPanel extends JPanel {

	private JLabel faq, contact;

	/**
	 * Constructor Method
	 */
	public BotInfoPanel() {

		this.setLayout(new GridLayout(0, 2));
		JLabel cpRight = new JLabel(
				PropertiesManager
						.getProperties(PropertiesManager.COPY_RIGHT_LABEL));
		this.add(cpRight);

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(0, 2));

		contact = new JLabel(
				PropertiesManager
						.getProperties(PropertiesManager.BIP_CONTACT_LABEL));
		faq = new JLabel(
				PropertiesManager
						.getProperties(PropertiesManager.BIP_FAQ_LABEL));
		p1.add(contact);
		p1.add(faq);

		this.add(p1);
		addListeners();
	}

	/**
	 * Set the listeners to the JLabels "conctact" and "FAQ"
	 */
	private void addListeners() {

		faq.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				FaqFrame.setFrameVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(NinjaFrame.handCursor);
				faq.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				setCursor(NinjaFrame.defaultCursor);
				faq.setForeground(null);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});

		contact.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {

				MailFrame.showFrame(true);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(NinjaFrame.handCursor);
				contact.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				setCursor(NinjaFrame.defaultCursor);
				contact.setForeground(null);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});
	}
}

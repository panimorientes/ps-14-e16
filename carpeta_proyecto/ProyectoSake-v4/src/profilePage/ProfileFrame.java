package profilePage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import properties.PropertiesManager;

import main.NinjaFrame;

public class ProfileFrame extends JFrame {

	private static final int SIZE_Y = 700;
	private static final int SIZE_X = 500;

	private ProfileInfo info;
	private JButton acept, cancel, edit, save;
	private JPanel butP;

	/**
	 * Constructor
	 */
	public ProfileFrame() {
		info = new ProfileInfo();
		butP = new JPanel();
		this.setTitle(PropertiesManager
				.getProperties(PropertiesManager.PF_TITLE_FRAME));
		this.setIconImage((new ImageIcon(PropertiesManager
				.getProperties(PropertiesManager.IMG_COMPANY_LITTLE_PATH))
				.getImage()));
		this.setLayout(new BorderLayout());
		this.add(info, BorderLayout.CENTER);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setSize(SIZE_X, SIZE_Y);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		addButtons();
		butP.add(edit);
		butP.add(acept);
		this.add(butP, BorderLayout.SOUTH);
		if (!DataProfileManager.isInstalled()) {
			this.setVisible(true);
			edit.doClick();
		}

	}

	/**
	 * create the buttons and set the listeners.
	 */
	private void addButtons() {
		acept = new JButton(
				PropertiesManager
						.getProperties(PropertiesManager.PF_BTN_ACEPT_NAME));
		addMouseListener(acept);
		acept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				hideMe();
				if (DataProfileManager.isInstalled()) {
					NinjaFrame.start();
				}
			}
		});

		edit = new JButton(
				PropertiesManager
						.getProperties(PropertiesManager.EDIT_BUTTON_NAME));
		addMouseListener(edit);
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				butP.remove(acept);
				butP.remove(edit);
				butP.add(save);
				butP.add(cancel);
				info.setInfoEditable();
				validateAndrepaint();
			}
		});

		cancel = new JButton(
				PropertiesManager
						.getProperties(PropertiesManager.CANCEL_BUTTON_NAME));
		addMouseListener(cancel);
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int option = ShowDialogs.showDialogOption(
						PropertiesManager
								.getProperties(PropertiesManager.SD_CANCEL_EDIT_TEXT),
						PropertiesManager
								.getProperties(PropertiesManager.SD_CANCEL_EDIT_TITLE));

				if (option == 0) {
					butP.remove(save);
					butP.remove(cancel);
					butP.add(acept);
					butP.add(edit);
					info.setChanges(false);
					info.setInfoNotEditable();
					validateAndrepaint();
				}

			}
		});

		save = new JButton(
				PropertiesManager
						.getProperties(PropertiesManager.SAVE_BUTTON_NAME));
		addMouseListener(save);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int option = ShowDialogs.showDialogOption(
						PropertiesManager
						.getProperties(PropertiesManager.SD_SAVE_EDIT_TEXT),
						PropertiesManager
						.getProperties(PropertiesManager.SD_SAVE_EDIT_TITLE));

				if (option == 0) {

					butP.remove(save);
					butP.remove(cancel);
					butP.add(acept);
					butP.add(edit);
					info.setChanges(true);
					info.setInfoNotEditable();
					validateAndrepaint();

					JOptionPane.showMessageDialog(null,
							PropertiesManager
									.getProperties(PropertiesManager.SD_SAVED_DATA_TEXT));
				}
			}
		});
	}

	/**
	 * Set my Visibility to false.
	 */
	private void hideMe() {
		this.setVisible(false);
	}

	/**
	 * calls to the method validate and repaint for mySelf.
	 */
	private void validateAndrepaint() {
		this.validate();
		this.repaint();
	}

	public void addMouseListener(JButton b) {
		b.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

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
	}
}

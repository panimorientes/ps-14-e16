package profilePage;

import homePage.SearchResultPage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ProfileCenterPage extends JPanel {

	private static final String SAVE_LABEL = "Guardar";
	private static final String CANCEL_EDIT_LABEL = "Cancelar edicion";
	private static final String CANCEL_LABEL = "Cancelar";
	private static final String EDIT_LABEL = "Editar";
	private static final String CONTINUE_BUY_LABEL = "Continuar Comprando";
	private ProfileInfo info;
	private JButton continueBuy, cancel, edit, save;
	private JPanel butP;
	private SearchResultPage searchPage;

	/**
	 * Constructor
	 * 
	 * @param sp
	 *            , my parent
	 */
	public ProfileCenterPage(SearchResultPage sp) {
		info = new ProfileInfo();
		butP = new JPanel();
		searchPage = sp;
		this.setLayout(new BorderLayout());
		this.add(info, BorderLayout.CENTER);

		addButtons();
		butP.add(edit);
		butP.add(continueBuy);
		this.add(butP, BorderLayout.SOUTH);
	}

	/**
	 * create the buttons and set the listeners.
	 */
	private void addButtons() {
		continueBuy = new JButton(CONTINUE_BUY_LABEL);
		continueBuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchPage.goToHome();
			}
		});

		edit = new JButton(EDIT_LABEL);
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				butP.remove(continueBuy);
				butP.remove(edit);
				butP.add(save);
				butP.add(cancel);
				info.setInfoEditable();
				validateAndrepaint();
			}
		});

		cancel = new JButton(CANCEL_LABEL);
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int option = ShowDialogs.showDialogOption(
						ShowDialogs.ARE_SURE_CANCEL_LABEL,
						ShowDialogs.CANCEL_EDIT_LABEL);
				if (option == 0) {
					butP.remove(save);
					butP.remove(cancel);
					butP.add(continueBuy);
					butP.add(edit);
					info.setChanges(false);
					info.setInfoNotEditable();
					validateAndrepaint();
				}

			}

		});

		save = new JButton(SAVE_LABEL);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int option = ShowDialogs.showDialogOption(
						ShowDialogs.ARE_SURE_SAVE_LABEL,
						ShowDialogs.SAVING_CHANGES_LABEL);

				if (option == 0) {

					butP.remove(save);
					butP.remove(cancel);
					butP.add(continueBuy);
					butP.add(edit);
					info.setChanges(true);
					info.setInfoNotEditable();
					validateAndrepaint();

					JOptionPane.showMessageDialog(null,
							ShowDialogs.SAVED_DATA_LABEL);
				}
			}
		});
	}

	/**
	 * calls to the method validate and repaint for mySelf.
	 */
	private void validateAndrepaint() {
		this.validate();
		this.repaint();
	}

}

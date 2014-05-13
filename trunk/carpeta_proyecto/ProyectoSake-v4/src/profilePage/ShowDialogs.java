package profilePage;

import javax.swing.JOptionPane;

import properties.PropertiesManager;

public class ShowDialogs {

	public static int showDialogOption(String question, String title) {
		int option = JOptionPane
				.showOptionDialog(
						null,
						question,
						title,
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						new Object[] {
								PropertiesManager
										.getProperties(PropertiesManager.SD_YES_OPTION_TEXT),
								PropertiesManager
										.getProperties(PropertiesManager.SD_NO_OPTION_TEXT) },
						PropertiesManager
								.getProperties(PropertiesManager.SD_YES_DIALOG_TEXT));

		return option;
	}

}

package profilePage;

import javax.swing.JOptionPane;

public class ShowDialogs {
	
	public static final String CANCEL_EDIT_LABEL = "Cancelar edicion";
	public static final String ARE_SURE_CANCEL_LABEL = "Estas seguro que deseas cancelar";
	public static final String ARE_SURE_SAVE_LABEL = "Estas seguro que deseas guardas los cambios";
	public static final String SAVING_CHANGES_LABEL = "Guardando cambios";
	public static final String SAVED_DATA_LABEL = "Nuevos Datos Guardados Correctamente";

	private static final String NO_OPTION_LABEL = "No";
	private static final String YES_OPTION_LABEL = "Si";
	private static final String YES_DIALOG = "Yes";
	
	public static int showDialogOption(String question, String title){
		int option = JOptionPane.showOptionDialog(null,
				question,
				title,
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, // possible image
						// icon
				new Object[] { YES_OPTION_LABEL, NO_OPTION_LABEL },
				YES_DIALOG);
		
		return option;
	}

}
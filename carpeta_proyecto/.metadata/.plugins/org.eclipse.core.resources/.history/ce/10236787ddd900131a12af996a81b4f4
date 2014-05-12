package mailConfig;

import java.awt.Desktop;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import profilePage.DataProfileManager;
import properties.PropertiesManager;

public class WorkerMail extends SwingWorker<Boolean, Void> {

	private GifMailFrame gifFrame;

	public WorkerMail(GifMailFrame gifFrame) {
		this.gifFrame = gifFrame;
	}

	@Override
	protected Boolean doInBackground() throws Exception {

		String[] dataMail = DataProfileManager.getData();
		if (SendMail.sendMail(dataMail[dataMail.length - 1],
				PdfCreator.getPath(),
				String.valueOf(PdfCreator.getDocumentNumber()))) {
			if (SendMail.sendMail(SendMail.OUR_MAIL, PdfCreator.getPath(),
					String.valueOf(PdfCreator.getDocumentNumber()))) {
				gifFrame.setVisible(false);
				JOptionPane.showMessageDialog(null, PropertiesManager
						.getProperties(PropertiesManager.SM_SEND_OK));
				JOptionPane
						.showMessageDialog(
								null,
								PropertiesManager
										.getProperties(PropertiesManager.SD_ACEPTED_PURSACHE_TEXT));
				try {
					Desktop.getDesktop().open(new File(PdfCreator.getPath()));
				} catch (Exception e) {
					e.printStackTrace();
				}

				return true;
			} else {
				gifFrame.setVisible(false);
				JOptionPane.showMessageDialog(null, PropertiesManager
						.getProperties(PropertiesManager.SM_SEND_ERROR));

				return false;
			}
		} else {
			gifFrame.setVisible(false);
			JOptionPane.showMessageDialog(null, PropertiesManager
					.getProperties(PropertiesManager.SM_SEND_ERROR));

			return false;
		}

	}

}

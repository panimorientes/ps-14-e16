package mailConfig;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import properties.PropertiesManager;

public class WorkerBodyMail extends SwingWorker<Boolean, Void> {

	private GifMailFrame gifFrame;
	private String mailF, subject, text;
	private JFrame myFrame;

	public WorkerBodyMail(GifMailFrame gifFrame, String mailF, String subject,
			String text, JFrame myFrame) {
		this.gifFrame = gifFrame;
		this.mailF = mailF;
		this.subject = subject;
		this.text = text;
		this.myFrame=myFrame;
	}

	@Override
	protected Boolean doInBackground() throws Exception {

		if (SendMail.sendBodyMail(mailF, subject, text)) {
			gifFrame.setVisible(false);
			JOptionPane.showMessageDialog(null, PropertiesManager
					.getProperties(PropertiesManager.SM_SEND_OK));
			
			myFrame.setVisible(false);
			return true;
		} else {
			gifFrame.setVisible(false);
			JOptionPane.showMessageDialog(null, PropertiesManager
					.getProperties(PropertiesManager.SM_SEND_ERROR));
			myFrame.setVisible(false);
			return false;
		}
		
	}

}

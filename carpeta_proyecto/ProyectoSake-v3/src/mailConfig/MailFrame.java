package mailConfig;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import properties.PropertiesManager;

public class MailFrame extends JFrame {

	private static JButton send, cancel;
	private static JTextField mailFrom;
	private static JTextField mailTo;
	private static JTextField subject;
	private static JTextArea body;

	private static JFrame frame = new JFrame();

	static {

		frame.setSize(500, 500);
		frame.setIconImage((new ImageIcon(PropertiesManager
				.getProperties(PropertiesManager.IMG_COMPANY_LITTLE_PATH))
				.getImage()));
		frame.setTitle(PropertiesManager
				.getProperties(PropertiesManager.MF_TITLE_FRAME));
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		mailFrom = new JTextField();
		mailFrom.setColumns(20);
		mailTo = new JTextField();
		mailTo.setText(SendMail.OUR_MAIL);
		mailTo.setEditable(false);
		subject = new JTextField();
		subject.setColumns(20);
		body = new JTextArea();
		body.setRows(40);

		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		JPanel p11 = new JPanel();
		p11.add(new JLabel(PropertiesManager
				.getProperties(PropertiesManager.MF_MAIL_TO_LABEL)));
		p11.add(mailTo);

		JPanel p12 = new JPanel();
		p12.add(new JLabel(PropertiesManager
				.getProperties(PropertiesManager.MF_MAIL_FROM_LABEL)));
		p12.add(mailFrom);

		JPanel p13 = new JPanel();
		p13.add(new JLabel(PropertiesManager
				.getProperties(PropertiesManager.MF_SUBJECT_LABEL)));
		p13.add(subject);
		p1.add(p11, BorderLayout.NORTH);
		p1.add(p12, BorderLayout.CENTER);
		p1.add(p13, BorderLayout.SOUTH);

		JPanel p2 = new JPanel();
		send = new JButton(
				PropertiesManager
						.getProperties(PropertiesManager.MF_BTN_SEND_NAME));
		cancel = new JButton(
				PropertiesManager
						.getProperties(PropertiesManager.MF_BTN_CANCEL_NAME));

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showFrame(false);
			}

		});

		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String mailF = mailFrom.getText();
				String sub = subject.getText();
				String txt = body.getText();
				
				GifMailFrame showGifWait = new GifMailFrame();
				WorkerBodyMail sendBodyMail = new WorkerBodyMail(showGifWait, mailF,sub,txt,frame);
				sendBodyMail.execute();
				
			}

		});

		JPanel p21 = new JPanel();
		p21.add(send);

		JPanel p22 = new JPanel();
		p22.add(cancel);

		p2.add(p21);
		p2.add(p22);

		frame.add(p1, BorderLayout.NORTH);
		frame.add(body, BorderLayout.CENTER);
		frame.add(p2, BorderLayout.SOUTH);

		frame.setVisible(false);
		frame.validate();
		frame.repaint();
	}

	public static void showFrame(boolean show) {
		frame.setVisible(show);
	}


}

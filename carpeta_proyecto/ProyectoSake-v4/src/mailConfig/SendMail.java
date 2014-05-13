package mailConfig;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import properties.PropertiesManager;

public class SendMail extends Object {

	private static final String TRUE = "true";
	private static final String FALSE = "false";
	private static final String MAIL_FALLBACK = "mail.smtp.socketFactory.fallback";
	private static final String JAVAX_SOCKET = "javax.net.ssl.SSLSocketFactory";
	private static final String MAIL_SOCKET_CLASS = "mail.smtp.socketFactory.class";
	private static final String MAIL_SOCKET_PORT = "mail.smtp.socketFactory.port";
	private static final String MAIL_PORT = "mail.smtp.port";
	private static final String MAIL_ENABLE = "mail.smtp.starttls.enable";
	private static final String MAIL_DEBUG = "mail.debug";
	private static final String MAIL_AUTH = "mail.smtp.auth";
	private static final String MAIL_HOST = "mail.smtp.host";
	private static final String PORT_NUM = "465";
	private static final String GMAIL_SMTP = "smtp.gmail.com";

	private static final String OUR_PASS = "sake1234";
	public static final String OUR_MAIL = "ninjaswsake@gmail.com";
	public static final String THEIR_MAIL = "coctailfactory@gmail.com";

	public static Properties getProperties() {
		Properties props = new Properties();
		props.put(MAIL_HOST, GMAIL_SMTP);
		props.put(MAIL_AUTH, TRUE);
		props.put(MAIL_DEBUG, TRUE);
		props.put(MAIL_ENABLE, TRUE);
		props.put(MAIL_PORT, PORT_NUM);
		props.put(MAIL_SOCKET_PORT, PORT_NUM);
		props.put(MAIL_SOCKET_CLASS, JAVAX_SOCKET);
		props.put(MAIL_FALLBACK, FALSE);
		props.put("mail.imap.partialfetch", "false");
		props.put("mail.mime.base64.ignoreerrors", "true");
		props.put("mail.imaps.partialfetch", "false");
		return props;
	}

	public static boolean sendMail(String mailTo, String fileNametoAttach,
			String nPedido) {

		Session mailSession = Session.getInstance(SendMail.getProperties(),
				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(OUR_MAIL, OUR_PASS);
					}
				});
		try {
			mailSession.setDebug(true); // Enable the debug mode

			Message message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(OUR_MAIL));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					mailTo));
			message.setSubject(PropertiesManager
					.getProperties(PropertiesManager.SM_MAIL_SUBJECT) + nPedido);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(PropertiesManager
					.getProperties(PropertiesManager.SM_MAIL_TEXT));

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(fileNametoAttach);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileNametoAttach);
			multipart.addBodyPart(messageBodyPart);

			// Put parts in message
			message.setContent(multipart);

			// --[ Ask the Transport class to send our mail message

			Transport.send(message);
			return true;
		} catch (Exception E) {

			E.printStackTrace();
			return false;
		}

	}

	public static boolean sendBodyMail(String mailFrom, String subject,
			String body) {

		Session mailSession = Session.getInstance(SendMail.getProperties(),
				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(OUR_MAIL, OUR_PASS);
					}
				});
		try {
			mailSession.setDebug(true); // Enable the debug mode

			Message msg = new MimeMessage(mailSession);

			// --[ Set the FROM, TO, DATE and SUBJECT fields
			msg.setFrom(new InternetAddress(mailFrom));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(OUR_MAIL));
			msg.setSentDate(new Date());
			msg.setSubject(subject);

			// --[ Create the body of the mail

			msg.setText(body);
			// --[ Ask the Transport class to send our mail message
			Transport.send(msg);

			return true;
		} catch (Exception E) {

			E.printStackTrace();
			return false;
		}

	}

}

package com.xoriant.mailing;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.itextpdf.text.Document;

public class SendUsingGmail {

	public static void sendNotification(String email, Document doc) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"librarian.xorlib@gmail.com", "testtestabc");
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("librarian.xorlib@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Important - Reminder for returning book");
			message.setText("Dear "
					+ email
					+ ", \n\nThe due date for returning the book has passed. You are obligated to return the book "
					+ " to the library. You will be liable to a certain fine for not returning the book on time!\n \nThanks. \n\nLibrarian. ");

			Multipart multipart = new MimeMultipart();

			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText("This is message body");

			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();

			String filename = "D:/gaurang/LibraryManagementSystem/pdf/test.pdf";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);

			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}

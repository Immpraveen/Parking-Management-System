
package com.kpmg.parkingreservation.resources;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class provides a utility method for sending emails using JavaMail API.
 */
public class EmailUtil {

	/**
	 * Sends an email to the specified recipient.
	 * 
	 * @param toEmail the email address of the recipient
	 * @param subject the subject line of the email
	 * @param body    the body of the email
	 * @return true if the email was sent successfully, false otherwise
	 */
	public static boolean sendEmail(String toEmail, String subject, String body) {
		try {
			// Create properties object to configure SMTP server settings
			Properties props = new Properties();
			props.put("mail.smtp.host", ConstantUtils.smtpHost); // SMTP Host
			props.put("mail.smtp.port", ConstantUtils.smtpPort); // TLS Port
			props.put("mail.smtp.auth", "true"); // enable authentication
			props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

			// Create authenticator object to provide credentials to SMTP server
			Authenticator auth = new Authenticator() {
				// Override the getPasswordAuthentication method to return credentials
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(ConstantUtils.fromEmail, ConstantUtils.password);
				}
			};

			// Create a session object using the properties and authenticator
			Session session = Session.getInstance(props, auth);
			// Create a new MimeMessage object
			MimeMessage msg = new MimeMessage(session);

			// Set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			// Set the sender address and subject
			msg.setFrom("frmintern2023@gmail.com");
			msg.setSubject(subject, "UTF-8");

			// Set the body text and sent date
			msg.setText(body, "UTF-8");
			msg.setSentDate(new Date());

			// Set the recipient address
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			// Send the message using the Transport class
			Transport.send(msg);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

/* ***************************************************************************************************
 * Project Name: Seamless Payment Form
 * Description:  This file contain to send the HTML report of final results through Email 
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 ****************************************************************************************************/

package com.nn.TestReport;

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
import org.testng.annotations.Test;

public class EmailReport {
	@Test
	public void sendReport() {
		Properties props = new Properties(); //Create object of Property file
		props.put("mail.smtp.host", "mail1.novalnetsolutions.com"); //This will set host of server- you can change based on your requirement
		props.put("mail.smtp.socketFactory.port", "465"); //Set the port of socket factory
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //Set socket factory
		props.put("mail.smtp.auth", "true"); //Set the authentication to true
		props.put("mail.smtp.port", "25"); //Set the port of SMTP server
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { //This will handle the complete authentication
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("vachanpeter@novalnetsolutions.com", "dfoerno7d");
			}
		});
		try {
			Message message = new MimeMessage(session); //Create object of MimeMessage class
			message.setFrom(new InternetAddress("vachanpeter@novalnetsolutions.com")); //Set the from address
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("vachanpeter@novalnetsolutions.com")); //Set the recipient address
			message.setSubject("Test Automation Report"); //Add the subject link
			BodyPart messageBodyPart1 = new MimeBodyPart(); //Create object to add multimedia type content
			messageBodyPart1.setText("This is message body"); //Set the body of email
			MimeBodyPart messageBodyPart2 = new MimeBodyPart(); //Create another object to add another content
			String filename = System.getProperty("user.dir") + "/src/test/resources/ExtendReport/TestReport.html"; //Mention the file which you want to send
			DataSource source = new FileDataSource(filename); //Create data source and pass the filename
			messageBodyPart2.setDataHandler(new DataHandler(source)); //Set the handler
			messageBodyPart2.setFileName(filename); //Set the file
			Multipart multipart = new MimeMultipart(); //Create object of MimeMultipart class
			multipart.addBodyPart(messageBodyPart2); //Add body part 1
			multipart.addBodyPart(messageBodyPart1); //Add body part 2
			message.setContent(multipart); //Set the content
			Transport.send(message); //Finally send the email
			System.out.println("=====Email Sent=====");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
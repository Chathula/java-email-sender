package ACTIONS;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chathula
 */
public class Mailer {
    
        private final static String HOST = "smtp.gmail.com";
        private final static String PORT = "587";
        private final static String SMTP_EMAIL = "nsbm.user@gmail.com";
        private final static String SMTP_PASSWORD = "nsbm@123";
    
        public static boolean sendEmail(String email, String message) {
          Properties properties = System.getProperties();

          properties.put("mail.smtp.auth", "true");
          properties.setProperty("mail.transport.protocol", "smtp");
          properties.put("mail.smtp.starttls.enable", "true");
          properties.setProperty("mail.smtp.port", PORT);
          properties.setProperty("mail.smtp.host", HOST);
          properties.put("mail.user", SMTP_EMAIL);
          properties.put("mail.password", SMTP_PASSWORD);

          Session session = Session.getDefaultInstance(properties);

                try {
           // Create a default MimeMessage object.
           MimeMessage msg = new MimeMessage(session);

           // Set From: header field of the header.
           msg.setFrom(new InternetAddress(SMTP_EMAIL));

           // Set To: header field of the header.
           msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

           // Set Subject: header field
           msg.setSubject("NSBM Students Results Manager");

           // Now set the actual message
           msg.setText(message);

           // Send message
           Transport.send(msg, SMTP_EMAIL, SMTP_PASSWORD);
           System.out.println("Sent message successfully....");
           return true;
        } catch (MessagingException mex) {
            return false;
        }
      }
}

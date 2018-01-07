package ACTIONS;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
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
public class Mailer implements Runnable {
    
    private final String HOST = "smtp.gmail.com";
    private final String PORT = "587";
    private final String SMTP_EMAIL = "nsbm.user@gmail.com";
    private final String SMTP_PASSWORD = "nsbm@123";

    private String email;
    private String message;
    private Properties properties = System.getProperties();
    private AtomicBoolean status;

    public Mailer(String email, String message, AtomicBoolean status) {
        this.email = email;
        this.message = message;
        this.status = status;
    }

    public void run() {
        try {
            properties.put("mail.smtp.auth", "true");
            properties.setProperty("mail.transport.protocol", "smtp");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.port", PORT);
            properties.setProperty("mail.smtp.host", HOST);
            properties.put("mail.user", SMTP_EMAIL);
            properties.put("mail.password", SMTP_PASSWORD);

            Session session = Session.getDefaultInstance(properties);
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
             status.set(true);
             System.out.println("Sent message successfully....");
        } catch (Exception e) {
            status.set(false);
        }
    }
}

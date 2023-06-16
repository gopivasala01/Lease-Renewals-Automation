package mainPackage;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailActivities {
    public static void sendEmail(String failedReason) {
        // Sender's email address
        String senderEmail = "bireports@beetlerim.com";
        String senderPassword = "Welcome@123";

        // Recipient's email address
        String recipientEmail = "santosh.p@beetlerim.com";

        // SMTP server details
        String host = "smtpout.asia.secureserver.net";
        int port = 80;

        // Create properties for the SMTP session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", String.valueOf(port));

        // Create a session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            // Set the email subject and body
            message.setSubject("Login Failed");
            String body = "Login failed: " + failedReason;
            message.setText(body);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

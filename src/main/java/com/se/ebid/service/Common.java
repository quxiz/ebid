/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.UnsupportedEncodingException;
import java.util.Date;
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


/**
 *
 * @author Nuttapong
 */
public class Common {
    
    static final int ADMIN_ID = -1;
    
    static final long getMemberID(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUser customUser = (CustomUser)auth.getPrincipal();
        return customUser.getMemberID();
    }
    
    static final String getUserID(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUser customUser = (CustomUser)auth.getPrincipal();
        return customUser.getUserID();
    }
    
    public static void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
          MimeMessage msg = new MimeMessage(session);
          //set message headers
          msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
          msg.addHeader("format", "flowed");
          msg.addHeader("Content-Transfer-Encoding", "8bit");
 
          msg.setFrom(new InternetAddress("no_reply@journaldev.com", "NoReply-JD"));
          msg.setReplyTo(InternetAddress.parse("no_reply@journaldev.com", false));
 
          msg.setSubject(subject, "UTF-8");
 
          msg.setText(body, "UTF-8");
 
 
          msg.setSentDate(new Date());
 
          msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
          System.out.println("Message is ready");
          Transport.send(msg); 
 
          System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }
 
    public static final boolean sendMail(String to, String subject, String text) {
        System.out.println("!!!!!!!!!!!!!!!!!!! test send email !!!!!!!!!!!!!!!!!!!");
        String host = "smtp.gmail.com";
        final String user = "ebid.se@gmail.com";//change accordingly  
        final String password = "ebidse2014";//change accordingly  

        //String to = "iqmathematics@gmail.com";//change accordingly  

        //Get the session object  
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
  
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });
       
        //Compose the message  
        System.out.println("!!!!!! connect success");
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //message.setSubject("javatpoint");
            message.setSubject(subject);
            //message.setText("This is simple program of sending email using JavaMail API");
            message.setText(text);
            System.out.println("!!!!! initialize message success !!!!!!!!!");
            //send the message  
            Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (Exception e) {
            return false;
            //e.printStackTrace();
        }
        return true;
    }
}

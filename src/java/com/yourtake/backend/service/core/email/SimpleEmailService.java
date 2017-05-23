/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yourtake.backend.service.core.email;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Welcome
 */
public class SimpleEmailService extends Thread{
    
    
    private String mailContent;
    private String mailSubject;
    private Object entity;
    private final String myUsername="yourtake1@gmail.com";
    private final String myPassword="am0340am0340";
    private String auth="true";
    private String starttls="true"; 
    private String host="smtp.gmail.com";
    private String port="587";
    public SimpleEmailService(String mailSubject, String mailContent,  Object entity) {
        this.mailContent = mailContent;
        this.mailSubject = mailSubject;
        this.entity = entity;
    }
    
    
    
    @Override
    public void run() {
        try
        {
                    this.authenticate( (String) entity);
               
                
        }
		
		
        catch(Exception e) 
        {
		System.err.println("Error in sending message "+e);		
		
        }

    }
    
    
    public void authenticate(String adminEmailId){
        Properties prop = new Properties();
        prop.put("mail.smtp.auth",auth);
        prop.put("mail.smtp.starttls.enable",starttls);
        prop.put("mail.smtp.host",host);
        prop.put("mail.smtp.port",port);
        
        Session session = Session.getInstance(prop, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(myUsername,myPassword); 
            }
        
        }); 
        
        try{

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myUsername));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(adminEmailId));
            message.setSubject(mailSubject);
            message.setContent(mailContent,"text/html; charset=utf-8");
            Transport.send(message);
        }
        catch(MessagingException e){
            System.out.println("Error in authenticating : "+e);
        }       
    }
}

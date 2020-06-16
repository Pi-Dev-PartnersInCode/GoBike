/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.Tache;
import java.util.Date;



/**
 *
 * @author user
 */
public class Service_mail {
   
      public Service_mail() {
    }

    /**
     * Send email using GMail SMTP server.
     *
     * @param username GMail username
     * @param password GMail password
     * @param recipientEmail TO recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws AddressException if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
    public static void Send(final String username, final String password, String recipientEmail, String title, String message)  {
        
    }

    /**
     * Send email using GMail SMTP server.
     *
     * @param username GMail username
     * @param password GMail password
     * @param recipientEmail TO recipient
     * @param ccEmail CC recipient. Can be empty if there is no CC recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws AddressException if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
    public static void Send(final String username, final String password, String recipientEmail, String ccEmail, String title, String message) {
       
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Tache t  = new Tache();
        
        String msg="Bonjour\n" +
" une nouvelle tache à été assigné à vous\n" +
" nom de la tache :" +t.getNom()+"\n" +
" date limite :"+ t.getD1()+"\n" +
" veuillez consulter votre session pour plus dinformation.";
String from= "azizsahnoun5@gmail.com";
String to= "azizsahnoun5@gmail.com";
String token="AzZ5g45aLDZeYTgjlZ2FghRG7";
        activate_token(token);
sendmail(from,to,msg);
        /*
        If set to false, the QUIT command is sent and the connection is immediately closed. If set 
        to true (the default), causes the transport to wait for the response to the QUIT command.

        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
                http://forum.java.sun.com/thread.jspa?threadID=5205249
                smtpsend.java - demo program from javamail
        */
    

       

        // -- Create a new message --
    

        // -- Set the FROM and TO fields --


        if (ccEmail.length() > 0) {
     
        }

        

     

        
    }
   
 public static void sendmail(String usr,String m, String to){
        
    }
 
  public static void activate_token(String usr){
        
    }

}
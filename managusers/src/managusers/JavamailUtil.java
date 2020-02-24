package managusers;


import edu.project.servises.ActiviteCrud;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavamailUtil {
    public static void sendMail(String recepient) throws Exception{
        System.out.println("Preparing to send:");
        Properties properties = new Properties();       
        //mAIL li yabaath
        String myAccountEmail ="asma.noury@gmail.com";
        String password ="iphonerahali";
        
        properties.put("com.hof.email.starttime","20170519094544");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.connectiontimeout","60000");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.ssl.trust","*");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.timeout","60000");
        properties.put("mail.transport.protocol","smtp");
        
        
        
        Session session = Session.getInstance(properties, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        
        Message message = prepareMessage(session,myAccountEmail,recepient);
        
        Transport.send(message);
        System.out.println("message send successfully");
    }
    
    private static Message prepareMessage(Session session, String myAccountEmail,String recepient){
        try {
            UserCRUD Act = new UserCRUD();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("La listes des activités");
            message.setText(Act.afficherUsers());
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(JavamailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managusers;

import managusers.MyConnection;
import managusers.User;
import managusers.UserCRUD;
//import managusers.PDFutil;



public class MainClass {

    public static void main(String[] args) {

        MyConnection mc = MyConnection.getInstance();

        UserCRUD userC = new UserCRUD();

        User u1 = new User("Neymar", "Junior", "00", "homme", "neymar.junior@esprit.tn", "97164827", "2020/07/02", "client");
        User u2 = new User("Frank", "Kom", "00", "homme", "frank.kom@esprit.tn", "54197842", "2020/08/12", "BikeMechanic");
        User u3 = new User("Romelu", "Lukaku", "00", "homme", "romelu.lukaku@esprit.tn", "71679438", "1990/08/05", "client");
        User u4 = new User("Anice", "Badri", "00", "homme", "anice.badri@esprit.tn", "97164825", "1995/02/15", "BikeMechanic");
        User u5 = new User("Ansu", "Fati", "00", "homme", "ansu.fati@esprit.tn", "79428617", "1999/04/17", "client");
        User u6 = new User("Robert", "Lewandowski", "00", "homme", "robert.lewandowski@esprit.tn", "97346824", "1994/04/04", "client");
        User u7 = new User("asma", "nouri", "00", "femme", "lamia.bentsamir@esprit.tn", "97614382", "1999/01/01", "client");
    

        /*
         userC.ajouterUser(u1);
         userC.ajouterUser(u2);
         userC.ajouterUser(u3);
         userC.ajouterUser(u4);
         userC.ajouterUser(u5);
         userC.ajouterUser(u6);
         userC.ajouterUser(u7);
        */ 
 /*
         userC.ModifierTelUser(u6, "79467192"); 
         userC.ModifierMdpUser(u6, "1234"); 
         */
        //userC.SupprimerUser(u8);
       // System.out.println("\nTous les inscrits: " + userC.afficherUsers());
        
        
//            PDFutil pdf = new PDFutil();
//            pdf.listUtilisateurs();
//        } catch (SQLException ex) {
//            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (DocumentException ex) {
//            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        JavamailUtil mail = new JavamailUtil();
//        mail.sendMail("asma.noury@gmail.com");
//        
//        
    }
}

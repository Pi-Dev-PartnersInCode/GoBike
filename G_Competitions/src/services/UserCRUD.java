package services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import entities.Session;
import entities.User;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;
import utils.TrayIconDemo;



public class UserCRUD {

    Connection cnn;
    Statement st;

    public UserCRUD() {
        cnn = MyConnection.getInstance().getCn();
    }

    public void ajouterUser(User u) {
        try {
            String requete = "INSERT INTO user(nom,prenom,mdp,sexe,mail,tel,role) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnn.prepareStatement(requete);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(3, u.getMdp());
            pst.setString(4, u.getSexe().toLowerCase());
            pst.setString(5, u.getMail());
            pst.setString(6, u.getTel());
            pst.setString(7, u.getRole());
            pst.executeUpdate();

            JavamailUtil mail = new JavamailUtil();
            try {
                mail.sendMail(u.getMail());
            } catch (Exception ex) {
                Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            if (SystemTray.isSupported()) {
                TrayIconDemo td = new TrayIconDemo();
                try {
                    td.displayTray();
                } catch (AWTException ex) {
                    Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.err.println("System tray not supported!");
            }

            System.out.println("User ajouté !");
        } catch (SQLException ex) {
            if (ex.getMessage().contains("Duplicata")) {
                System.out.println("User existe deja!");
            } else {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void ModifierTelUser(User u, String tell) {
        try {
            String requete = "UPDATE user SET tel=? WHERE mail=?";
            PreparedStatement pst = cnn.prepareStatement(requete);

            pst.setString(1, tell);
            pst.setString(2, u.getMail());

            pst.executeUpdate();
            System.out.println("User modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ModifierMdpUser(User u, String mdpp) {
        try {
            String requete = "UPDATE user SET mdp=? WHERE mail=?";
            PreparedStatement pst = cnn.prepareStatement(requete);

            pst.setString(1, mdpp);
            pst.setString(2, u.getMail());

            pst.executeUpdate();
            System.out.println("User modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
  /*   public void ModifierMdpUserInterface(String maill, String mdpA, String mdpN) {
        try {
            String requete = "SELECT mdp FROM user WHERE mail=?";
            PreparedStatement pst = cnn.prepareStatement(requete);
            pst.setString(1, maill);
            ResultSet rs = pst.executeQuery();

            if (rs.first() == false) {
                System.out.println("Mail non valide!");
            }
            
            if (rs.getObject(1).equals(mdpA)) {
                System.out.println("Mail valide !");
                String requetee = "UPDATE user SET mdp=? WHERE mail=?";
                PreparedStatement pstt = cnn.prepareStatement(requetee);
                pstt.setString(1, mdpN);
                pstt.setString(2, maill);
                pstt.executeUpdate();
            }

            System.out.println("User modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
     
     public void ModifierMdpUserFonction(String maill, String mdpN) {
        try {
                String requetee = "UPDATE user SET mdp=? WHERE mail=?";
                PreparedStatement pstt = cnn.prepareStatement(requetee);
                pstt.setString(1, mdpN);
                pstt.setString(2, maill);
                pstt.executeUpdate();
            
            System.out.println("User modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void SupprimerUser(User u) {
        try {
            String requete = "DELETE FROM user WHERE mail=?";
            PreparedStatement pst = cnn.prepareStatement(requete);

            pst.setString(1, u.getMail());

            pst.executeUpdate();
            System.out.println("User supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public List<User> afficherUsers() {

        ArrayList<User> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM user";
            PreparedStatement pst = cnn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));                   //Soit par label soit par indice 
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setMdp(rs.getString("mdp"));
                u.setSexe(rs.getString("sexe"));
                u.setMail(rs.getString("mail"));
                u.setTel(rs.getString("tel")); 
                u.setRole(rs.getString("role"));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    

  
    
    public boolean loginInterface(String maill, String mdp) {
        try {
            String requete = "SELECT mdp FROM user WHERE mail=?";
            PreparedStatement pst = cnn.prepareStatement(requete);
            pst.setString(1, maill);
            ResultSet rs = pst.executeQuery();

            if (rs.first() == false) {
                return false;
            }
            
            if(rs.getString(1).equals(mdp) == false || mdp.equals("") ) {
                return false;
            };
     
        } catch (SQLException ex) {
            if (ex.getMessage().contains("Can not issue")) {
                System.out.println("Mail/Mot de passe invalide(s)!");
            } else {
                System.out.println(ex.getMessage());
            }
        }
        return true;
    }

    
    public User RechercheUser(String rech) {

        try {
            String requete = "SELECT * FROM user WHERE mail=?";
            PreparedStatement pst = cnn.prepareStatement(requete);

            pst.setString(1, rech);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));                   //Soit par label soit par indice 
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setMdp(rs.getString("mdp"));
                u.setSexe(rs.getString("sexe"));
                u.setMail(rs.getString("mail"));
                u.setTel(rs.getString("tel"));
                u.setRole(rs.getString("role"));
                return u;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        User u = null;
        return u;
    }
    public User login(String email,String mdp) {

        try {
            String requete = "SELECT * FROM user WHERE mail= '"+email+"' and mdp ='"+mdp+"'";
            PreparedStatement pst = cnn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));                   //Soit par label soit par indice 
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setMdp(rs.getString("mdp"));
                u.setSexe(rs.getString("sexe"));
                u.setMail(rs.getString("mail"));
                u.setTel(rs.getString("tel"));
                u.setRole(rs.getString("role"));
                Session.CurrentUser = u;
                return u;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        User u = null;
        return u;
    }


}

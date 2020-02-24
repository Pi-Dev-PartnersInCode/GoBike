package managusers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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


public class UserCRUD {

    Connection cnn;
    Statement st;

    public UserCRUD() {
        cnn = MyConnection.getInstance().getConnection();
    }

    public void ajouterUser(User u) {
        try {
            //String requete = "INSERT INTO personne(nom,prenom) VALUES ('"+p.getNom()+"','"+p.getPrenom()+"')";
            String requete = "INSERT INTO user(nom,prenom,mdp,sexe,mail,tel,date,role) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnn.prepareStatement(requete);

            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(3, u.getMdp());
            pst.setString(4, u.getSexe());
            pst.setString(5, u.getMail());
            pst.setString(6, u.getTel());
            pst.setString(7, u.getDate());
            pst.setString(8, u.getRole());
            pst.executeUpdate();

//NotificationDebut            
            if (SystemTray.isSupported()) {
                TrayIconDemo td = new TrayIconDemo();
                try {
                    td.trayAjout();
                } catch (AWTException ex) {
                    Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.err.println("System tray not supported!");
            }
 //NotificationEnd            
            
            System.out.println("User ajouté !");
        } catch (SQLException ex) {
            if (ex.getMessage().contains("Duplicata")) {
                System.out.println("User Existe Deja");
                
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
                u.setDate(rs.getString("date"));
                u.setRole(rs.getString("role"));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }


}

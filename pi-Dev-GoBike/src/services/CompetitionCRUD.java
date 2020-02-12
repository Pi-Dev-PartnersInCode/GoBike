/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Competition;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author mneri
 */
public class CompetitionCRUD {

    Connection cnx;
    Statement st;

    public CompetitionCRUD() {
        cnx = MyConnection.getInstance().getCn();

    }

    public void ajouterCompetition(Competition c) {
        try {
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO competition (nomComp,emplacement,description,dateComp) VALUES (?,?,?,?)");
            pst.setString(1, c.getNom());
            pst.setString(2, c.getEmplacement());
            pst.setString(3, c.getDescription());
            pst.setDate(4, c.getDate());
            
            pst.executeUpdate();
            System.out.println("Competition added");
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }
    public void modifierCompetition(Competition c){
        try {
            PreparedStatement pst = cnx.prepareStatement("update competition set nomComp = ? ,emplacement = ?, description = ? dateComp = ? where idCompetition = ?");
            pst.setString(1, c.getNom());
            pst.setString(2, c.getEmplacement());
            pst.setString(3, c.getDescription());
            pst.setDate(4, c.getDate());
            
            pst.setInt(5, c.getIdCompetition());
            
            pst.executeUpdate();
            System.out.println("Competition updated");
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerCompetition(int id){
        try {
            PreparedStatement pst = cnx.prepareStatement("delete from competition where idCompetition = ?");
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("Competition deleted");
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public ArrayList<Competition> afficherCompetition(){
        ArrayList<Competition> psr = new ArrayList<>();
        try {
            String req = "SELECT * FROM competition";
            PreparedStatement pst2 = cnx.prepareStatement(req);
            ResultSet rs = pst2.executeQuery();
            while (rs.next())
            {
                Competition c = new Competition();
         
                c.setNom(rs.getString("nomComp"));
                c.setEmplacement(rs.getString("emplacement"));
                c.setDescription(rs.getString("description"));
                c.setDate(rs.getDate("dateComp"));
               
                
                psr.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        
        
        return psr;             
    }
}













/*


    public ArrayList<Competition> afficherCompetition(){
        ArrayList<Competition> psr = new ArrayList<>();
        try {
            String req = "SELECT * FROM PERSONNE";
            PreparedStatement pst2 = cn2.prepareStatement(req);
            ResultSet rs = pst2.executeQuery();
            while (rs.next())
            {
                Competition c = new Competition();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString(2));
                c.setPrenom(rs.getString("prenom"));
                psr.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        
        return psr;
        
        
    }
}
*/

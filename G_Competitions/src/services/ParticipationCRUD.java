/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Participant;
import entities.Participation;
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
public class ParticipationCRUD {

    Connection cnx;
    Statement st;

    public ParticipationCRUD() {
        cnx = MyConnection.getInstance().getCn();

    }

    public void ajouterParticipation(Participation p) {
        try {
            //String requete2 ="INSERT INTO PERSONNE (NOM,PRENOM) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO Participation VALUES (?,?,?,?,?)");
            
            pst.setInt(1, p.getIdParticipation());
            pst.setInt(2, p.getMyUser().getId());
            pst.setInt(3, p.getMyCompetition().getIdCompetition());
            pst.setInt(4, p.getRang());
            pst.setInt(5, p.getRecord());
            
            pst.executeUpdate();
            
            System.out.println("Participation added");
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }
    public void modifierParticipation(Participation p){
        try {
            PreparedStatement pst = cnx.prepareStatement("update Participation set  rang = ?,record = ? where idParticipation = ?");
            
           
            pst.setInt(1, p.getRang());
            pst.setInt(2, p.getRecord());
            
            pst.setInt(3, p.getIdParticipation());
            
            pst.executeUpdate();
            System.out.println("Participation updated");
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerParticipation(int id){
        try {
            PreparedStatement pst = cnx.prepareStatement("delete from Participation where idParticipation = ?");
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("Participation deleted");
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
      public ArrayList<Participant> afficherParticipationParCompetition(int idCompetition){
        ArrayList<Participant> psr = new ArrayList<>();
        try {
            String req = "SELECT u.nom, u.prenom ,p.idParticipation,p.rang,p.record FROM Participation p inner join user u on p.idUser = u.id where p.idComp = " 
                    + idCompetition;
            PreparedStatement pst2 = cnx.prepareStatement(req);
            ResultSet rs = pst2.executeQuery();
            while (rs.next())
            {
                Participant p = new Participant();
         
                p.setNom(rs.getString("prenom"));
                p.setPrenom(rs.getString("nom"));
                p.setRank(rs.getInt("rang"));
                p.setIdParticipation(rs.getInt("idParticipation"));
                p.setRecord(rs.getInt("record"));

               
                
                psr.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }       
        return psr;             
    }
      public ArrayList<Participant> afficherParticipants(){
        ArrayList<Participant> psr = new ArrayList<>();
        try {
            String req = "SELECT u.prenom, u.nom ,p.idParticipation,p.rang,p.record FROM Participation p inner join user u on p.idUser = u.id " ;

            PreparedStatement pst2 = cnx.prepareStatement(req);
            ResultSet rs = pst2.executeQuery();
            while (rs.next())
            {
                Participant p = new Participant();
         
                p.setNom(rs.getString("prenom"));
                p.setPrenom(rs.getString("nom"));
                p.setRank(rs.getInt("rang"));
                p.setIdParticipation(rs.getInt("idParticipation"));
                p.setRecord(rs.getInt("record"));

               
                
                psr.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }       
        return psr;             
    }
}




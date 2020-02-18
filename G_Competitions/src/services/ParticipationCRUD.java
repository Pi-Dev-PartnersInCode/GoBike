/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Competition;
import entities.Participant;
import entities.Participation;
import entities.User;
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
            pst.setInt(2, p.getMyUser().getIdUser());
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
            //String requete2 ="INSERT INTO PERSONNE (NOM,PRENOM) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement("update Participation set idUser = ?, idComp = ?, rang = ?,record = ? where idParticipation = ?");
            
            
            pst.setInt(1, p.getMyUser().getIdUser());
            pst.setInt(2, p.getMyCompetition().getIdCompetition());
            pst.setInt(3, p.getRang());
            pst.setInt(4, p.getRecord());
            
            pst.setInt(5, p.getIdParticipation());
            
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
    
     public ArrayList<Participation> afficherParticipation(){
        ArrayList<Participation> psr = new ArrayList<>();
        try {
            String req = "SELECT * from participation";
            PreparedStatement pst2 = cnx.prepareStatement(req);
            ResultSet rs = pst2.executeQuery();
            while (rs.next())
            {
                
                
                String reqU = "SELECT * from user where idUser ="+rs.getString("idUser");
                PreparedStatement pstU = cnx.prepareStatement(reqU);
                ResultSet rsU = pstU.executeQuery();
                User u = new User(rsU.getInt("idUser"),rsU.getString("firstName"),rsU.getString("lastName"),rsU.getString("email"),
                        rsU.getString("password"),rsU.getString("address"),rsU.getDate("dateOfBirth"),rsU.getLong("phoneNumber"));
                
                
                
                String reqC = "SELECT * from competition where idCompetition ="+rs.getString("idComp");
                PreparedStatement pstC = cnx.prepareStatement(reqC);
                ResultSet rsC = pstC.executeQuery();               
                Competition c = new Competition(rsC.getInt("idCompetition"),rsC.getString("nomComp"),rsC.getString("emplacement"),
                        rsC.getString("description"),rsC.getDate("dateComp"));

                
                Participation p = new Participation(rs.getInt("idParticipation"),u,c,rs.getInt("rang"),rs.getInt("record"));
                

                        
                psr.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }       
        return psr;             
    }
     
     
     
     
     public ArrayList<Participation> rechercheParticipation(String value,String searchTerm){
        ArrayList<Participation> psr = new ArrayList<>();
        try {
            String req = "SELECT * from participation where ? = ?";
            PreparedStatement pst2 = cnx.prepareStatement(req);
            pst2.setString(1,searchTerm);
            pst2.setString(2,value);
            ResultSet rs = pst2.executeQuery();
            while (rs.next())
            {
                 String reqU = "SELECT * from user where idUser ="+rs.getString("idUser");
                PreparedStatement pstU = cnx.prepareStatement(reqU);
                ResultSet rsU = pstU.executeQuery();
                User u = new User(rsU.getInt("idUser"),rsU.getString("firstName"),rsU.getString("lastName"),rsU.getString("email"),
                        rsU.getString("password"),rsU.getString("address"),rsU.getDate("dateOfBirth"),rsU.getLong("phoneNumber"));
                
                
                
                String reqC = "SELECT * from competition where idCompetition = "+ rs.getString("idComp");
                PreparedStatement pstC = cnx.prepareStatement(reqC);
                ResultSet rsC = pstC.executeQuery();               
                Competition c = new Competition(rsC.getInt("idCompetition"),rsC.getString("nomComp"),rsC.getString("emplacement"),
                        rsC.getString("description"),rsC.getDate("dateComp"));

                
                Participation p = new Participation(rs.getInt("idParticipation"),u,c,rs.getInt("rang"),rs.getInt("record"));
                

                        
                psr.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }       
        return psr;             
    }
}




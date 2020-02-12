/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Partnership;
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
public class PartnershipCRUD {

    Connection cnx;
    Statement st;

    public PartnershipCRUD() {
        cnx = MyConnection.getInstance().getCn();

    }

//    void ajouterPartnership(Partnership p) {
//        try {
//            String requete = "INSERT INTO COMPETITION  VALUES   ('marathon','7oumetna','competition de 100Km')";
//            Statement st = cnx.createStatement();
//            st.executeUpdate(requete);
//            System.out.println("Partnership ajouté");
//        } catch (SQLException ex) {
//            Logger.getLogger(ex.getMessage());
//        }
//
//    }

    public void ajouterPartnership(Partnership p) {
        try {
            //String requete2 ="INSERT INTO PERSONNE (NOM,PRENOM) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO Partnership VALUES (?,?,?,?)");
            
            pst.setInt(1, p.getIdPartnership());
            pst.setInt(2, p.getIdPartner());
            pst.setInt(3, p.getIdComp());
            pst.setString(4, p.getHelp());
            
            pst.executeUpdate();
            
            System.out.println("Partnership added");
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }
    public void modifierPartnership(Partnership p){
        try {
            //String requete2 ="INSERT INTO PERSONNE (NOM,PRENOM) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement("update Partnership  idPartner = ?, idComp = ?, help = ? where idPartnership = ?");
            
            pst.setInt(4, p.getIdPartnership());
            pst.setInt(1, p.getIdPartner());
            pst.setInt(2, p.getIdComp());
            pst.setString(3, p.getHelp());

            
            pst.executeUpdate();
            System.out.println("Partnership updated");
        } catch (SQLException ex) {
            Logger.getLogger(PartnershipCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerPartnership(int id){
        try {

            PreparedStatement pst = cnx.prepareStatement("delete from Partnership where idPartnership = ?");
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("Partnership deleted");
        } catch (SQLException ex) {
            Logger.getLogger(PartnershipCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//     public ArrayList<Partnership> afficherPartnership(){
//        ArrayList<Partnership> psr = new ArrayList<>();
//        try {
//            String req = "SELECT * FROM Partnership";
//            PreparedStatement pst2 = cnx.prepareStatement(req);
//            ResultSet rs = pst2.executeQuery();
//            while (rs.next())
//            {
//                Partnership p = new Partnership();
//         
//                p.setIdPartnership(rs.getInt("idPartnership"));
//                p.setNomP(rs.getString("nomP"));
//                p.setDescP(rs.getString("descP"));
//               
//                
//                psr.add(p);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ex.getMessage());
//        }       
//        return psr;             
//    }
}




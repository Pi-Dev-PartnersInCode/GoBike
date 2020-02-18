/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Partner;
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
public class PartnerCRUD {

    Connection cnx;
    Statement st;

    public PartnerCRUD() {
        cnx = MyConnection.getInstance().getCn();

    }

//    void ajouterPartner(Partner p) {
//        try {
//            String requete = "INSERT INTO COMPETITION  VALUES   ('marathon','7oumetna','competition de 100Km')";
//            Statement st = cnx.createStatement();
//            st.executeUpdate(requete);
//            System.out.println("Partner ajouté");
//        } catch (SQLException ex) {
//            Logger.getLogger(ex.getMessage());
//        }
//
//    }

    public void ajouterPartner(Partner p) {
        try {
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO Partner VALUES (?,?,?)");
            
            pst.setInt(1, p.getIdPartner());
            pst.setString(2, p.getNomP());
            pst.setString(3, p.getDescP());
            
            pst.executeUpdate();

            System.out.println("Partner added");
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }
    public void modifierPartner(Partner p){
        try {
            PreparedStatement pst = cnx.prepareStatement("update Partner  NomP = ?, descP = ? where idPartner = ?");
            
            pst.setInt(4, p.getIdPartner());
            pst.setString(1, p.getNomP());
            pst.setString(2, p.getDescP());
            
            pst.executeUpdate();
            System.out.println("Partner updated");
        } catch (SQLException ex) {
            Logger.getLogger(PartnerCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerPartner(int id){
        try {
            PreparedStatement pst = cnx.prepareStatement("delete from Partner where idPartner = ?");
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("Partner deleted");
        } catch (SQLException ex) {
            Logger.getLogger(PartnerCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public ArrayList<Partner> afficherPartner(){
        ArrayList<Partner> psr = new ArrayList<>();
        try {
            String req = "SELECT * FROM Partner";
            PreparedStatement pst2 = cnx.prepareStatement(req);
            ResultSet rs = pst2.executeQuery();
            while (rs.next())
            {
                Partner p = new Partner();
         
                p.setIdPartner(rs.getInt("idPartner"));
                p.setNomP(rs.getString("nomP"));
                p.setDescP(rs.getString("descP"));
               
                
                psr.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }       
        return psr;             
    }
}




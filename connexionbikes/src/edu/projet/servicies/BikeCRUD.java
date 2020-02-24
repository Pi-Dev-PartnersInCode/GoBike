/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projet.servicies;

import edu.project.utils.MyConnection;
import edu.projet.entities.Bike;
import edu.projet.entities.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sondes
 */
public class BikeCRUD {
     Connection cn;
    Statement st;

    public BikeCRUD() {
          cn = MyConnection.getInstance().getCnx();
}
        public void ajouterBike(Bike b) {
        String requete = "insert into bikes(name, traveldistance) VALUES (?,?)";
        try {
            PreparedStatement pst1 = cn.prepareStatement(requete);
            pst1.setString(1, b.getName());
            pst1.setInt(2, b.getTraveldistance());
            pst1.executeUpdate();
            System.out.println("bike added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
            public void modifierBike(Bike b){
        try {
            PreparedStatement pst = cn.prepareStatement("update bikes set  name = ? ,traveldistance = ? where idbike = ?");
           
            pst.setString(1, b.getName());
            pst.setInt(2, b.getTraveldistance());
             pst.setInt(3, b.getIdbike());
            pst.executeUpdate();
            System.out.println("bike updated");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            public void supprimerBike(int idbike) {
        try {
            PreparedStatement pst3 = cn.prepareStatement("delete from bikes where idbike = ?");
            pst3.setInt(1, idbike);
            pst3.executeUpdate();
            System.out.println("bike deleted");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        
        public List<Bike> afficherBike() {
        ArrayList<Bike> bike = new ArrayList<>();

        PreparedStatement pst;
        try {
            String requete2 = "SELECT * FROM bikes";
            pst = cn.prepareStatement(requete2);
            ResultSet rs2 = pst.executeQuery();
            while (rs2.next()) {
                Bike b = new Bike();
                b.setIdbike(rs2.getInt("idbike"));
                b.setName(rs2.getString("name"));
                b.setTraveldistance(rs2.getInt("traveldistance"));
                bike.add(b);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return bike;
        }
        
        
}

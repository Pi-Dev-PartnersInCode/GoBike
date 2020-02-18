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
public class ReservationCRUD {

    Connection cn;
    Statement st;

    public ReservationCRUD() {
        cn = MyConnection.getInstance().getCnx();

    }

    public void ajouterReservation(Reservation r) {
        String requete = "insert into reservations(date, place) VALUES (str_to_date(?,'%d %M %Y'),?)";

        PreparedStatement pst;
        try {
            pst = cn.prepareStatement(requete);
            pst.setString(1, r.getDate());
            pst.setString(2, r.getPlace());
            pst.executeUpdate();
            System.out.println("reservation added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierReservation(Reservation r) {

        try {
            PreparedStatement pst = cn.prepareStatement("update reservations set  date = ? ,place = ? WHERE idreservation = ?");
            pst.setString(1, r.getDate());
            pst.setString(2, r.getPlace());
            pst.setInt(3, r.getIdreservation());
            pst.executeUpdate();
            
            System.out.println("Reservation updated");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerReservation(int idreservation) {
        try {
            PreparedStatement pst = cn.prepareStatement("delete from reservations where idreservation = ?");
            pst.setInt(1, idreservation); 
            pst.executeUpdate();
            System.out.println("reservation deleted");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Reservation> afficherReservation() {
        ArrayList<Reservation> res = new ArrayList<>();

        PreparedStatement pst1;
        try {
            String requete1 = "SELECT * FROM reservations";
            pst1 = cn.prepareStatement(requete1);
            ResultSet rs = pst1.executeQuery();
            while (rs.next()) {
                Reservation r = new Reservation();
                r.setIdreservation(rs.getInt("idreservation"));
                r.setDate(rs.getString("date"));
                r.setPlace(rs.getString("place"));
                res.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;

    }
}

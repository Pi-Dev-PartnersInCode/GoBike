/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.MyConnection;
import entities.Reservation;
import entities.Session;
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
        cn = MyConnection.getInstance().getCn();

    }

    public void ajouterReservation(Reservation r) {
        try {
            String requete2 = "INSERT INTO  reservations(date,place,bike,idUser) VALUES (?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(requete2);
            // pst.setInt(1, r.getIdreservation());
            pst.setString(1, r.getDate());
            pst.setString(2, r.getPlace());
            pst.setInt(4, r.getIdUser());
            pst.setString(3, r.getBike());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierReservation(Reservation r, String date, String place, String bike) {

        try {
            PreparedStatement pst = cn.prepareStatement("update reservations set date=?, place=?,bike=?,idUser = ?  WHERE idreservation = ?");

            pst.setString(1, date);
            pst.setString(2, place);
            pst.setString(3, bike);
            pst.setInt(5, r.getIdreservation());
            pst.setInt(4, r.getIdUser());
            pst.executeUpdate();

            System.out.println("Reservation updated");

        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerReservation(int idreservation) {

        String req = "delete from reservations where idreservation=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = cn.prepareStatement(req);

            preparedStatement.setInt(1, idreservation);
            preparedStatement.executeUpdate();
        } catch (SQLException er) {
            System.out.println("delete erreur reservation");
        }
    }

    public List<Reservation> afficherReservation() {
        ArrayList<Reservation> res = new ArrayList<>();

        PreparedStatement pst1;
        PreparedStatement pst2;
        try {
            String requete1 = "SELECT * FROM reservations";
            pst1 = cn.prepareStatement(requete1);
            ResultSet rs = pst1.executeQuery();
            while (rs.next()) {
                Reservation r = new Reservation();
                r.setIdreservation(rs.getInt("idreservation"));
                r.setIdUser(rs.getInt("idUser"));
                r.setDate(rs.getString("date"));
                r.setPlace(rs.getString("place"));
                r.setBike(rs.getString("bike"));
//                String req2 = "select * from Client where idClient =? ";
//                pst2 = cn.prepareStatement(req2);
//                ResultSet rst = pst2.executeQuery();
//                Client c = new Client(rst.getInt("idClient"), rst.getFloat("size"), rst.getFloat("weight"));
//                r.setIdClient(c);
//                String req3 = "select * from bike where idbike =? ";
//                PreparedStatement pst3 = cn.prepareStatement(req3);
//                ResultSet rst1 = pst3.executeQuery();
//                Bike b = new Bike(rst1.getInt("idbike"), rst1.getString("name"), rst1.getInt("traveldistance"));

                //            r.setIdbike(b);
                res.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;

    }
}

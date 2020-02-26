/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Trainer;
import entities.Trainer;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mey
 */
public class TrainerCRUD {

    Connection cn2;
    Statement st;

    public TrainerCRUD() {
        cn2 = MyConnection.getInstance().getCn();
    }

    public void ajouterTrainer(Trainer t) {
        try {
            String requete2 = "INSERT INTO  Trainer VALUES (?,?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete2);
            pst.setInt(1, t.getIdTrainer());
            pst.setString(2, t.getNameT());
            pst.setString(3, t.getCinT());
            pst.setString(4, t.getSpeciality());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierTrainer(Trainer t) {

        String sql = "UPDATE Trainer SET   NameT=?,CinT=?, Speciality=?  where idTrainer=?";// + t.getIdTrainer();

        PreparedStatement ps;
        try {
            ps = cn2.prepareStatement(sql);

            ps.setString(1, t.getNameT());
            ps.setString(2, t.getCinT());
            ps.setString(3, t.getSpeciality());
            ps.setInt(4, t.getIdTrainer());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Le trainer a été modifier avec succès");
            }
        } catch (SQLException e) {
            System.out.println("Erreur ");

        }

    }

    public Trainer rechercherTrainerByID(int idT) {
        Trainer t = null;
        try {
            Statement ps = cn2.createStatement();
            Statement ps2 = cn2.createStatement();
            ResultSet res;

            res = ps.executeQuery("select t from Trainer   where idTrainer=" + idT);
            while (res.next()) {

                t = new Trainer(res.getInt("idTrainer"), res.getString("NameT"), res.getString("CinT"), res.getString("Speciality"));
                int idTrainer = res.getInt("idTrainer");
                t.setIdTrainer(idTrainer);
                t.setNameT(res.getString("NameT"));
            }
        } catch (SQLException e) {
            System.out.println("Trainer Introuvable" + e.getMessage());
        }
        return t;

    }

    public void supprimerTrainer(int t) {

        String req = "delete from Trainer where idTrainer=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = cn2.prepareStatement(req);

            preparedStatement.setInt(1, t);
            preparedStatement.executeUpdate();
        } catch (SQLException er) {
            System.out.println(er.getMessage());
        }
    }

    public List<Trainer> afficherTrainer() {
        ArrayList<Trainer> per = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM Trainer";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
                Trainer t = new Trainer();

                t.setIdTrainer(rs.getInt("idTrainer"));
                t.setNameT(rs.getString("NameT"));
                t.setCinT(rs.getString("CinT"));
                t.setSpeciality(rs.getString("Speciality"));

                per.add(t);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return per;

    }

    public ResultSet setData(int id) throws SQLException {

        ResultSet rss;

        String requete2 = "SELECT * FROM trainer where idTrainer =" + id;
        PreparedStatement pst2 = cn2.prepareStatement(requete2);
        rss = pst2.executeQuery();
        while (rss.next()) {
            return rss;
        }

        return rss;
    }

}

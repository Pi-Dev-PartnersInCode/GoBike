/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Trainer;
import entities.Workshop;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mey
 */
public class WorkshopCRUD {

    Connection cn2;
    Statement st;

    public WorkshopCRUD() {
        cn2 = MyConnection.getInstance().getCn();
    }

    public void ajouterWorkshop(Workshop w) {
        try {
            String requete2 = "INSERT INTO `workshop`(`NameW`, `DateW`, `Duration`, `AddressW`, `idTrainer`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete2);
            pst.setString(1, w.getNameW());
            pst.setString(2, w.getDateW());
            pst.setString(3, w.getDuration());
            pst.setString(4, w.getAddressW());
           pst.setInt(5, w.getMyTrainer());

            pst.executeUpdate();
            System.out.println("added");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerWorkshop(int idW) {

        String req = "delete from Workshop where idW=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = cn2.prepareStatement(req);

            preparedStatement.setInt(1, idW);
            preparedStatement.executeUpdate();
        } catch (SQLException er) {
            System.out.println("delete erreur Workshop ");
        }
    }

    public void modifierWorkshop(Workshop w) {

        String sql = "UPDATE Workshop SET   NameW=?, DateW=?, Duration=?  , AddressW=?   , idTrainer=? where idW=" + w.getIdW();

        PreparedStatement ps;
        try {
            ps = cn2.prepareStatement(sql);

            ps.setString(1, w.getNameW());
            ps.setString(2, w.getDateW());
            ps.setString(3, w.getDuration());
            ps.setString(4, w.getAddressW());
            ps.setInt(5, w.getMyTrainer());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Le workshop a été modifier avec succès");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());     
      
        }

    }

    public Workshop rechercherWorkshopByID(int idWorkshop) {
        Workshop w = null;
        try {
            Statement ps = cn2.createStatement();
            Statement ps2 = cn2.createStatement();
            ResultSet res;
            ResultSet res2;
            res = ps.executeQuery("select w.*,t.* from Workshop w inner join trainer t on w.idTrainer = t.idTrainer  where idW=" + idWorkshop);
            while (res.next()) {
               
                Trainer t = new Trainer(res.getInt("idTrainer"), res.getString("NameT"), res.getString("CinT"), res.getString("Speciality"));
                w = new Workshop(res.getString("NameW"), res.getInt("idW"), res.getString("DateW"), res.getString("Duration"), res.getString("AddressW"),  res.getInt("t.idTrainer"));
                int idW = res.getInt("idW");
                w.setIdW(idW);
                w.setNameW(res.getString("NameW"));
            }
        } catch (SQLException e) {
            System.out.println("Workshop Introuvable" + e.getMessage());
        }
        return w;

    }

   public void rechercheByName(String nomworkshop) {
           Workshop w = null;
        ObservableList<Object> data = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM workshop WHERE NameW LIKE  ?";
            PreparedStatement pre = cn2.prepareStatement(sql);
            pre.setString(1, "%"+nomworkshop+ "%");
            ResultSet res = pre.executeQuery();
            while (res.next()) {
  ObservableList row = FXCollections.observableArrayList();
				for (int i = 1; i<= res.getMetaData().getColumnCount(); i++) {
					row.add(res.getString(i));
				}
                                 data.add(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        System.out.println(data);
 
}
       
   
//
//        Workshop w = null;
//        try {
//            Statement ps = cn2.createStatement();
//            ResultSet res;
//
//            res = ps.executeQuery("select *  from workshop " );
//            while (res.next()) {
////                Trainer t = new Trainer(res.getInt("idTrainer"), res.getString("NameT"), res.getString("CinT"), res.getString("Speciality"));
////                w = new Workshop(res.getString("NameW"), res.getInt("idW"), res.getString("DateW"), res.getString("Duration"), res.getString("AddressW"), t);
//
//                String NameW = res.getString("NameW");
//                int idW = res.getInt("idW");
//                String DateW = res.getString("DateW");
//                String Duration = res.getString("Duration");
//                String AddressW = res.getString("AddressW");
//                int idTrainer = res.getInt("idTrainer");
//
//                //return new Workshop(idW, NameW, DateW, Duration, AddressW, idTrainer);
//                return NameW;
//
//            }
//        } catch (SQLException ex) {
//            System.out.println("erreur" + ex.getMessage());
//        }
//        return null;
//    }

    /**
     *
     * @return
     */
    public List<Workshop> afficherWorkshop() {

        ArrayList<Workshop> per = new ArrayList<>();
        try {
            ResultSet res;
            String requete3 = "select w.*,t.* from Workshop w  join trainer t on w.idTrainer = t.idTrainer";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
        
                Workshop w = new Workshop();
                
                w.setIdW(rs.getInt("idW"));
                w.setNameW(rs.getString("NameW"));
                w.setDateW(rs.getString("DateW"));
                w.setDuration(rs.getString("Duration"));
                w.setAddressW(rs.getString("AddressW"));
                String x = rs.getString("idTrainer");
                int i = Integer.parseInt(x);
                w.setMyTrainer(i);

                per.add(w);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return per;

    }
    public ResultSet setData( int id) throws SQLException {
                                       
		ResultSet rss;
		
		
            String requete2 = "SELECT * FROM Workshop where idW ="+id;
            PreparedStatement pst2 = cn2.prepareStatement(requete2);
             rss = pst2.executeQuery();
             while (rss.next()) {
                   return rss;
             }
           
	
                return rss;
	}
}

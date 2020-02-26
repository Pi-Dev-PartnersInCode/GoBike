/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.MemberWorkshop;

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
public class MemberWorkshopCRUD {
    
    Connection cn2;
    Statement st;
    
    public MemberWorkshopCRUD() {
        cn2 = MyConnection.getInstance().getCn();
    }
    
     public void ajouterMember(MemberWorkshop m) {
        try {
            String requete2 = "INSERT INTO `memberworkshop`(`idUser`, `idW`) VALUES (?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete2);
            pst.setInt(1, m.getMyUser());
            pst.setInt(2, m.getMyWork());
      

            pst.executeUpdate();
            System.out.println("Member added");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimerMember(MemberWorkshop m) {
        
        String req = "delete from memberworkshop where idM=?";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement = cn2.prepareStatement(req);
            
            preparedStatement.setInt(1, m.getIdM());
            preparedStatement.executeUpdate();
        } catch (SQLException er) {
            System.out.println("delete erreur Member");
        }
    }
    
    public List<MemberWorkshop> afficherMember() {
        ArrayList<MemberWorkshop> per = new ArrayList<>();
        try {
            ResultSet res;
            String requete3 = "select * from memberworkshop  inner join user u on u.id = memberworkshop.idUser inner join workshop on workshop.idW = memberworkshop.idW";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {
        
                MemberWorkshop m = new MemberWorkshop();
                
                m.setIdM(rs.getInt("idM"));
                
                String x = rs.getString("id");
                int i = Integer.parseInt(x);
                m.setMyUser(i);
                String y = rs.getString("idW");
                int j = Integer.parseInt(y);
                m.setMyWork(j);
                per.add(m);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return per;

    }}
        
        
        
      
    
    

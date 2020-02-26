/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Product;
import utils.MyConnection;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.TrayIconDemo;

public class ProductCRUD {

    public Connection cnn1;
    public Statement st;

    public ProductCRUD() {
        cnn1 = MyConnection.getInstance().getCn();
    }

    public void addProduct(Product p) {
        try {
            String requete1 = "INSERT INTO product(nomProd, prixProd, quantite, descrip, type, categorie) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnn1.prepareStatement(requete1);

            pst.setString(1, p.getNomProd());
            pst.setFloat(2, p.getPrixProd());
            pst.setInt(3, p.getQuantite());
            pst.setString(4, p.getDescrip());
            pst.setString(5, p.getType());
            pst.setString(6, p.getCategorie());

            pst.executeUpdate();

            //Notification  
            if (SystemTray.isSupported()) {
                TrayIconDemo td = new TrayIconDemo();
                try {
                    td.displayTray();
                } catch (AWTException ex) {
                    Logger.getLogger(ProductCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.err.println("System tray not supported!");
            }

            //Notification
            System.out.println("Added product");

        } catch (SQLException ex) {
            if (ex.getMessage().contains("Duplicata")) {
                System.out.println("Product exists");

            } else {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void ModifyNameProduct(Product p, int idP, String name) {
        try {
            String requete2 = "UPDATE product SET nomProd=? WHERE idProd=?";
            PreparedStatement pst = cnn1.prepareStatement(requete2);

         pst.setInt(2,idP);
            
         pst.setString(1,name);

            pst.executeUpdate();

            if (SystemTray.isSupported()) {
                TrayIconDemo td = new TrayIconDemo();
                try {
                    td.displayTray();
                } catch (AWTException ex) {
                    Logger.getLogger(ProductCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.err.println("System tray not supported!");
            }

            System.out.println("Modified name !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void removeProduct(Product p, int idP) {
        String requete3 = "DELETE FROM product WHERE idProd=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCn().prepareStatement(requete3);
            pst.setInt(1, idP);
            pst.executeUpdate();

            if (SystemTray.isSupported()) {
                TrayIconDemo td = new TrayIconDemo();
                try {
                    td.displayTray();
                } catch (AWTException ex) {
                    Logger.getLogger(ProductCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.err.println("System tray not supported!");
            }

            System.out.println("Deleted product");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Product> ShowListProd() {
        List<Product> listProduct = new ArrayList();
        try {
            String requete4 = "SELECT * FROM product";

            PreparedStatement pst = cnn1.prepareStatement(requete4);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setIdProd(rs.getInt("idProd"));
                p.setNomProd(rs.getString("nomProd"));
                p.setPrixProd(rs.getFloat("prixProd"));
                p.setQuantite(rs.getInt("quantite"));
                p.setDescrip(rs.getString("descrip"));
                p.setType(rs.getString("type"));
                p.setCategorie(rs.getString("categorie"));
                listProduct.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listProduct;
    }

    
}

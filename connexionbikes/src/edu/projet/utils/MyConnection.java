/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.project.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sondes
 */
public class MyConnection {
   public static String url ="jdbc:mysql://localhost:3306/gobike";
    public static String login ="root";
    public static String pwd ="";
    public static  MyConnection instance;
    public Connection cnx;
   private MyConnection() {
        try {
           cnx = DriverManager.getConnection(url,login,pwd);
            System.out.println("connexion établie !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   public static MyConnection getInstance(){ 
   if(instance==null){
instance=new MyConnection();
       
   }
    return instance;
}
  public Connection getCnx(){
      
  return cnx;
  }
}
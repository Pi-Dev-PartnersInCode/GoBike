/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mneri
 */
public class MyConnection {
     public String url ="jdbc:mysql://localhost:3306/esprit";
     public String login ="root";
     public String pwd ="";
     public static MyConnection instance;
     public Connection cn;

    private MyConnection()
    {
                try {
             cn = DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }
        public static MyConnection getInstance()
    {
        if (instance == null){
            instance = new MyConnection();
                    
        }return instance ;
    }

    public Connection getCn() {
        return cn;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projet.entities;

import java.sql.Date;

public class Client extends User {
    public int idClient;
    public float size;
    public int weight;
    
    public Client(int idUser, String firstName, String lastName, String e_mailadress, String password, Date dateOfBirth, long phoneNumber) {
        super(idUser, firstName, lastName, e_mailadress, password, dateOfBirth, phoneNumber);
    }
    
}

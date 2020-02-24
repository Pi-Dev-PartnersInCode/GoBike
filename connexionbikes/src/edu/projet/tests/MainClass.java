/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projet.tests;

import edu.projet.entities.Bike;
import edu.projet.entities.Reservation;
import edu.projet.servicies.BikeCRUD;
import edu.projet.servicies.ReservationCRUD;

/**
 *
 * @author Sondes
 */
public class MainClass {

    private MainClass() {

    }

    public static void main(String[] args) {

        ReservationCRUD res = new ReservationCRUD();
        Reservation r = new Reservation(2, "12 feb 2020", "tunis");
        //res.ajouterReservation(r);
        //res.modifierReservation(r);
        //res.supprimerReservation(5);

        System.out.println(res.afficherReservation());

  BikeCRUD bike = new BikeCRUD();
  Bike b = new Bike(3, "vtt", 80);
//
//        bike.ajouterBike(b);
//        bike.modifierBike(b);
//        bike.supprimerBike(2);
     System.out.println(bike.afficherBike());
    
    }
}

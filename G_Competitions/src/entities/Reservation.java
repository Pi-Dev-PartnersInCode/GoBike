/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Sondes
 */
public class Reservation {

    public int idreservation;
    private int idUser;
    private String date;
    private String place;
    private String bike;

    public Reservation(int idreservation, int idUser, String date, String place, String bike) {
        this.idreservation = idreservation;
        this.idUser = idUser;
        this.date = date;
        this.place = place;
        this.bike = bike;
    }
    public Reservation() {
        this.idreservation = 0;
        this.idUser = 0;
        this.date = " ";
        this.place = " ";
        this.bike = " ";
    }
    public Reservation( String date, String place, String bike) {
        this.date = date;
        this.place = place;
        this.bike = bike;
    }
    public Reservation( int idUser, String date, String place, String bike) {
        this.idUser = idUser;
        this.date = date;
        this.place = place;
        this.bike = bike;
    }

    public int getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(int idreservation) {
        this.idreservation = idreservation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getBike() {
        return bike;
    }

    public void setBike(String bike) {
        this.bike = bike;
    }

  

}

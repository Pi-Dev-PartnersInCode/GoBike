/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projet.entities;

import java.sql.Date;

/**
 *
 * @author Sondes
 */
public class Reservation {

    private int idreservation;
    private String date;
    private String place;

    public Reservation() {
    }

    public Reservation(int idreservation, String date, String place) {
        this.idreservation = idreservation;
        this.date = date;
        this.place = place;
    }

    public int getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(int idreservation) {
        this.idreservation = idreservation;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Reservation{" + "idreservation=" + idreservation + ", date=" + date + ", place=" + place + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (this.idreservation != other.idreservation) {
            return false;
        }
        return true;
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

    public void setString(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

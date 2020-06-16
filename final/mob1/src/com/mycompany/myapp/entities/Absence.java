/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Hajer
 */
public class Absence {
    
    private int id_absence;
    private int ide_user;
    private String date;
    private String heure;
    private int nbre;
    private String username;

    public Absence() {
    }

    public int getId_absence() {
        return id_absence;
    }

    public void setId_absence(int id_absence) {
        this.id_absence = id_absence;
    }

    public int getIde_user() {
        return ide_user;
    }

    public void setIde_user(int ide_user) {
        this.ide_user = ide_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getNbre() {
        return nbre;
    }

    public void setNbre(int nbre) {
        this.nbre = nbre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Absence{" + "id_absence=" + id_absence + ", ide_user=" + ide_user + ", date=" + date + ", heure=" + heure + ", nbre=" + nbre + ", username=" + username + '}';
    }

    public Absence(int id_absence, int ide_user, String date, String heure, int nbre, String username) {
        this.id_absence = id_absence;
        this.ide_user = ide_user;
        this.date = date;
        this.heure = heure;
        this.nbre = nbre;
        this.username = username;
    }

    public Absence(String date, String heure, int nbre, String username) {
        this.date = date;
        this.heure = heure;
        this.nbre = nbre;
        this.username = username;
    }
    
    
    
}

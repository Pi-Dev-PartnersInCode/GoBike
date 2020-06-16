/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Asus
 */
public class event {
    private int id;
    private  String nomEvent;
    private  String DateEvent;
    private  String lieu;
    private  String type;
    private  String description;


    public event() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public String getDateEvent() {
        return DateEvent;
    }

    public void setDateEvent(String DateEvent) {
        this.DateEvent = DateEvent;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public event(int id, String desc, String date1, String date2, String projet, String equipe) {
        this.id = id;
        this.nomEvent = desc;
        this.DateEvent = date1;
        this.lieu = date2;       
        this.type = projet;
        this.description = equipe;
    }
        
    
    
}

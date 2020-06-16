/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Malek
 */
public class participation {
    int id ;
    int id_event ;
    String record ;
    int ranking ;
    int id_Membre ;

    public participation(int id, int id_event, String record, int ranking, int id_Membre) {
        this.id = id;
        this.id_event = id_event;
        this.record = record;
        this.ranking = ranking;
        this.id_Membre = id_Membre;
    }

    public participation() {
    }

    public participation(int id_event, String record, int ranking, int id_Membre) {
        this.id_event = id_event;
        this.record = record;
        this.ranking = ranking;
        this.id_Membre = id_Membre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getId_Membre() {
        return id_Membre;
    }

    public void setId_Membre(int id_Membre) {
        this.id_Membre = id_Membre;
    }
    
    
}

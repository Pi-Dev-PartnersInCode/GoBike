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
public class Tache {
    private int id;
    private int id_us;
    private String nom;
    private String desc;
    private String d1;
    private String d2;
    private String prio;
    private String diff;
    private int user;
    private String prog;
    private String estim;

    public Tache() {
    }

    public Tache(int id, int id_us, String nom, String desc, String d1, String d2, String prio, String diff, int user, String prog, String estim) {
        this.id = id;
        this.id_us = id_us;
        this.nom = nom;
        this.desc = desc;
        this.d1 = d1;
        this.d2 = d2;
        this.prio = prio;
        this.diff = diff;
        this.user = user;
        this.prog = prog;
        this.estim = estim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_us() {
        return id_us;
    }

    public void setId_us(int id_us) {
        this.id_us = id_us;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getD1() {
        return d1;
    }

    public void setD1(String d1) {
        this.d1 = d1;
    }

    public String getD2() {
        return d2;
    }

    public void setD2(String d2) {
        this.d2 = d2;
    }

    public String getPrio() {
        return prio;
    }

    public void setPrio(String prio) {
        this.prio = prio;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getProg() {
        return prog;
    }

    public void setProg(String prog) {
        this.prog = prog;
    }

    public String getEstim() {
        return estim;
    }

    public void setEstim(String estim) {
        this.estim = estim;
    }

    @Override
    public String toString() {
        return "Tache{" + "id=" + id + ", id_us=" + id_us + ", nom=" + nom + ", desc=" + desc + ", d1=" + d1 + ", d2=" + d2 + ", prio=" + prio + ", diff=" + diff + ", user=" + user + ", prog=" + prog + ", estim=" + estim + '}';
    }
    
    
    
}

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
public class UserSt {
    private int id;
    private String desc;
    private int ids;
    private int nbr;

    public UserSt(int id, String desc, int ids, int nbr) {
        this.id = id;
        this.desc = desc;
        this.ids = ids;
        this.nbr = nbr;
    }

    public UserSt() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    @Override
    public String toString() {
        return "UserSt{" + "id=" + id + ", desc=" + desc + ", ids=" + ids + ", nbr=" + nbr + '}';
    }
    
    
    
    
    
}

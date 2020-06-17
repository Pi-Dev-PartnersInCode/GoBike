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
public class UserSt_bp {
 private int   id;
 private String desc ;

    public UserSt_bp(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public UserSt_bp() {
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

    @Override
    public String toString() {
        return "UserSt_bp{" + "id=" + id + ", desc=" + desc + '}';
    }
 
 
 
 
    
}

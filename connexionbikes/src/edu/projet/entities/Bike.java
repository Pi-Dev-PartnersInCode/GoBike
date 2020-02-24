/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projet.entities;

/**
 *
 * @author Sondes
 */
public class Bike {
    private int idbike;
    private String name;
    private int traveldistance ;

    public Bike() {
    }

    public Bike(int idbike, String name, int traveldistance) {
        this.idbike = idbike;
        this.name = name;
        this.traveldistance = traveldistance;
    }

    public int getIdbike() {
        return idbike;
    }

    public void setIdbike(int idbike) {
        this.idbike = idbike;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTraveldistance() {
        return traveldistance;
    }

    public void setTraveldistance(int traveldistance) {
        this.traveldistance = traveldistance;
    }

    @Override
    public String toString() {
        return "Bike{" + "idbike=" + idbike + ", name=" + name + ", traveldistance=" + traveldistance + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Bike other = (Bike) obj;
        if (this.idbike != other.idbike) {
            return false;
        }
        return true;
    }
    
}

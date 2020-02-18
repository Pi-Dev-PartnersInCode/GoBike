/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author mneri
 */
public class Partner {
    private int idPartner;
    private String nomP;
    private String descP;

    public Partner(int idPartner, String nomP, String descP) {
        this.idPartner = idPartner;
        this.nomP = nomP;
        this.descP = descP;
    }
    public Partner() {
        this.idPartner = 0;
        this.nomP = " ";
        this.descP = " ";
    }

    public int getIdPartner() {
        return idPartner;
    }

    public void setIdPartner(int idPartner) {
        this.idPartner = idPartner;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public String getDescP() {
        return descP;
    }

    public void setDescP(String descP) {
        this.descP = descP;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.idPartner;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Partner other = (Partner) obj;
        if (this.idPartner != other.idPartner) {
            return false;
        }
        return true;
    }
    
    
    
}

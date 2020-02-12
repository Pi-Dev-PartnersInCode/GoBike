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
public class Participation {
    private int idParticipation;
    private int idUser;
    private int idComp;
    private int rang;
    private int record;

    public Participation(int idParticipation, int idUser, int idComp, int rang, int record) {
        this.idParticipation = idParticipation;
        this.idUser = idUser;
        this.idComp = idComp;
        this.rang = rang;
        this.record = record;
    }

    public int getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(int idParticipation) {
        this.idParticipation = idParticipation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdComp() {
        return idComp;
    }

    public void setIdComp(int idComp) {
        this.idComp = idComp;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idParticipation;
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
        final Participation other = (Participation) obj;
        if (this.idParticipation != other.idParticipation) {
            return false;
        }
        return true;
    }

    
}

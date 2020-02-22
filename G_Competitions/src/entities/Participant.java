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
public class Participant {
    
    int idParticipation;
    private int rank;
    private String nom;
    private String prenom;
    private int record;

    public Participant(int idParticipation, int rank, String nom, String prenom, int record) {
        this.idParticipation = idParticipation;
        this.rank = rank;
        this.nom = nom;
        this.prenom = prenom;
        this.record = record;
    }



    public Participant() {
        this.idParticipation =0;
        this.rank = 0;
        this.nom = " ";
        this.prenom = " ";
        this.record = 0;
    }

    public int getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(int idParticipation) {
        this.idParticipation = idParticipation;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "Participant{" + "idParticipation=" + idParticipation + ", rank=" + rank + ", nom=" + nom + ", prenom=" + prenom + ", record=" + record + '}';
    }



    
    
}

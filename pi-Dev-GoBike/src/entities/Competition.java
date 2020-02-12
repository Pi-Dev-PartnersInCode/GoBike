/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import java.sql.Date;


/**
 *
 * @author mneri
 */
public class Competition {
    
    private int idCompetition;
    private String nom;
    private String description;
    private String emplacement;
    private Date dateComp;

    public Competition(int idCompetition, String nom, String description, String emplacement, Date date) {
        this.idCompetition = idCompetition;
        this.nom = nom;
        this.description = description;
        this.emplacement = emplacement;
        this.dateComp = date;
    }
    public Competition() {
        this.idCompetition = 0;
        this.nom = " ";
        this.description = " ";
        this.emplacement = " ";
    }

    
    
    public int getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(int idCompetition) {
        this.idCompetition = idCompetition;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public Date getDate() {
        return dateComp;
    }

    public void setDate(Date date) {
        this.dateComp = date;
    }

    @Override
    public String toString() {
        return "Competition{" + "idCompetition=" + idCompetition + ", nom=" + nom + ", description=" + description + ", emplacement=" + emplacement + ", dateComp=" + dateComp + "}\n";
    }

  
   
    
}

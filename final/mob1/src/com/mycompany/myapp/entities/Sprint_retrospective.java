/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Hajer
 */
public class Sprint_retrospective {
    
    private int id;
    private int id_sprint_retrospective;
    private int ide_sprint;
    private int ide_equipe;
    private int ide_projet;
    private String description_TODO;
    private String Date_sprint_retrospective;
    private String nom_equipe;
    private String nom_projet;
    private String description;
    

    public Sprint_retrospective(int id_sprint_retrospective, int ide_sprint, int ide_equipe, int ide_projet, String description_TODO, String Date_sprint_retrospective, String nom_equipe, String nom_projet, String descritpion) {
        this.id_sprint_retrospective = id_sprint_retrospective;
        this.ide_sprint = ide_sprint;
        this.ide_equipe = ide_equipe;
        this.ide_projet = ide_projet;
        this.description_TODO = description_TODO;
        this.Date_sprint_retrospective = Date_sprint_retrospective;
        this.nom_equipe = nom_equipe;
        this.nom_projet = nom_projet;
        this.description = descritpion;
    }

    public Sprint_retrospective(String description_TODO, String Date_sprint_retrospective, String nom_equipe, String nom_projet, String description) {
        this.description_TODO = description_TODO;
        this.Date_sprint_retrospective = Date_sprint_retrospective;
        this.nom_equipe = nom_equipe;
        this.nom_projet = nom_projet;
        this.description = description;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
    public int getId_sprint_retrospective() {
        return id_sprint_retrospective;
    }

    public String getNom_equipe() {
        return nom_equipe;
    }

    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }

    public String getNom_projet() {
        return nom_projet;
    }

    public void setNom_projet(String nom_projet) {
        this.nom_projet = nom_projet;
    }

    public String getDescritpion() {
        return description;
    }

    public void setDescritpion(String descritpion) {
        this.description = descritpion;
    }

    
    public void setId_sprint_retrospective(int id_sprint_retrospective) {
        this.id_sprint_retrospective = id_sprint_retrospective;
    }

    public int getIde_sprint() {
        return ide_sprint;
    }

    public void setIde_sprint(int ide_sprint) {
        this.ide_sprint = ide_sprint;
    }

    public int getIde_equipe() {
        return ide_equipe;
    }

    public void setIde_equipe(int ide_equipe) {
        this.ide_equipe = ide_equipe;
    }

    public int getIde_projet() {
        return ide_projet;
    }

    public void setIde_projet(int ide_projet) {
        this.ide_projet = ide_projet;
    }

    public String getDescription_TODO() {
        return description_TODO;
    }

    public void setDescription_TODO(String description_TODO) {
        this.description_TODO = description_TODO;
    }

    public String getDate_sprint_retrospective() {
        return Date_sprint_retrospective;
    }

    public void setDate_sprint_retrospective(String Date_sprint_retrospective) {
        this.Date_sprint_retrospective = Date_sprint_retrospective;
    }

    @Override
    public String toString() {
        return "Sprint_retrospective{" + "id_sprint_retrospective=" + id_sprint_retrospective + ", ide_sprint=" + ide_sprint + ", ide_equipe=" + ide_equipe + ", ide_projet=" + ide_projet + ", description_TODO=" + description_TODO + ", Date_sprint_retrospective=" + Date_sprint_retrospective + ", nom_equipe=" + nom_equipe + ", nom_projet=" + nom_projet + ", descritpion=" + description + '}';
    }

   

    public Sprint_retrospective() {
    }

    public Sprint_retrospective(int ide_sprint, int ide_equipe, int ide_projet, String description_TODO, String Date_sprint_retrospective, String nom_equipe, String nom_projet, String descritpion) {
        this.ide_sprint = ide_sprint;
        this.ide_equipe = ide_equipe;
        this.ide_projet = ide_projet;
        this.description_TODO = description_TODO;
        this.Date_sprint_retrospective = Date_sprint_retrospective;
        this.nom_equipe = nom_equipe;
        this.nom_projet = nom_projet;
        this.description = descritpion;
    }
    
    
  
    
}

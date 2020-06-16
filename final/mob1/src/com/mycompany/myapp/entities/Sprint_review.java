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
public class Sprint_review {
    
    private int id_sprint_review;
    private int ide_sprint;
    private int ide_equipe;
    private int ide_projet;
    private int ide_product_owner;
    private String remarque_review_equipe;
    private String remarque_review_product_owner;
        private String Date_sprint_review;

    private String nom_equipe;
    private String nom_projet;
    private String description;
    private String username;

    public Sprint_review() {
    }

    public Sprint_review(String remarque_review_equipe, String remarque_review_product_owner, String Date_sprint_review, String nom_equipe, String nom_projet, String description, String username) {
        this.remarque_review_equipe = remarque_review_equipe;
        this.remarque_review_product_owner = remarque_review_product_owner;
        this.Date_sprint_review = Date_sprint_review;
        this.nom_equipe = nom_equipe;
        this.nom_projet = nom_projet;
        this.description = description;
        this.username = username;
    }

    public String getDate_sprint_review() {
        return Date_sprint_review;
    }

    public void setDate_sprint_review(String Date_sprint_review) {
        this.Date_sprint_review = Date_sprint_review;
    }
    
    

    public int getId_sprint_review() {
        return id_sprint_review;
    }

    public void setId_sprint_review(int id_sprint_review) {
        this.id_sprint_review = id_sprint_review;
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

    public int getIde_product_owner() {
        return ide_product_owner;
    }

    public void setIde_product_owner(int ide_product_owner) {
        this.ide_product_owner = ide_product_owner;
    }

    public String getRemarque_review_equipe() {
        return remarque_review_equipe;
    }

    public void setRemarque_review_equipe(String remarque_review_equipe) {
        this.remarque_review_equipe = remarque_review_equipe;
    }

    public String getRemarque_review_product_owner() {
        return remarque_review_product_owner;
    }

    public void setRemarque_review_product_owner(String remarque_review_product_owner) {
        this.remarque_review_product_owner = remarque_review_product_owner;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Sprint_review{" + "id_sprint_review=" + id_sprint_review + ", ide_sprint=" + ide_sprint + ", ide_equipe=" + ide_equipe + ", ide_projet=" + ide_projet + ", ide_product_owner=" + ide_product_owner + ", remarque_review_equipe=" + remarque_review_equipe + ", remarque_review_product_owner=" + remarque_review_product_owner + ", Date_sprint_review=" + Date_sprint_review + ", nom_equipe=" + nom_equipe + ", nom_projet=" + nom_projet + ", description=" + description + ", username=" + username + '}';
    }

    
    
    
}

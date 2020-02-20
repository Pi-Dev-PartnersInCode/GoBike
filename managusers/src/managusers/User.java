package managusers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Objects;

/**
 *
 * @author FERTANI Razi
 */
public class User {

    private int id;
    private String nom;
    private String prenom;
    private String mdp;
    private String sexe;
    private String mail;
    private String tel;
    private String date;
    private String role;

    public User() {
    }

    public User(String nom, String prenom, String mdp, String sexe, String mail, String tel, String date, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.sexe = sexe;
        this.mail = mail;
        this.tel = tel;
        this.date = date;
        this.role = role;
    }

    public User(int id, String nom, String prenom, String mdp, String sexe, String mail, String tel, String date, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.sexe = sexe;
        this.mail = mail;
        this.tel = tel;
        this.date = date;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public String getSexe() {
        return sexe;
    }

    public String getMail() {
        return mail;
    }

    public String getTel() {
        return tel;
    }

    public String getDate() {
        return date;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mdp=" + mdp + ", sexe=" + sexe + ", mail=" + mail + ", tel=" + tel + ", date=" + date + ", role=" + role + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.nom);
        hash = 71 * hash + Objects.hashCode(this.prenom);
        hash = 71 * hash + Objects.hashCode(this.mdp);
        hash = 71 * hash + Objects.hashCode(this.sexe);
        hash = 71 * hash + Objects.hashCode(this.mail);
        hash = 71 * hash + Objects.hashCode(this.tel);
        hash = 71 * hash + Objects.hashCode(this.date);
        hash = 71 * hash + Objects.hashCode(this.role);
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.mdp, other.mdp)) {
            return false;
        }
        if (!Objects.equals(this.sexe, other.sexe)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.tel, other.tel)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return true;
    }

}

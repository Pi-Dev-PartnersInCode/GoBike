 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

public class Product {

    private int idProd;
    private String nomProd;
    private float prixProd;
    private int quantite;
    private String descrip;
    private String type;
    private String categorie;

    public Product() {
    }

    public Product(int idProd, String nomProd, float prixProd, int quantite, String descrip, String type, String categorie) {
        this.idProd = idProd;
        this.nomProd = nomProd;
        this.prixProd = prixProd;
        this.quantite = quantite;
        this.descrip = descrip;
        this.type = type;
        this.categorie = categorie;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public float getPrixProd() {
        return prixProd;
    }

    public void setPrixProd(float prixProd) {
        this.prixProd = prixProd;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }


    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.idProd;
        hash = 73 * hash + Objects.hashCode(this.nomProd);
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
        final Product other = (Product) obj;
        if (this.idProd != other.idProd) {
            return false;
        }
        if (!Objects.equals(this.nomProd, other.nomProd)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "idProd=" + idProd + ", nomProd=" + nomProd + ", prixProd=" + prixProd + ", quantite=" + quantite + ", descrip=" + descrip + ", type=" + type + ", categorie=" + categorie + '}';
    }

    
    
     }

    
    
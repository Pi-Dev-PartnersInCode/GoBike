/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projet.entities;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import java.util.Objects;

public class User {
    public int idUser;
    public String firstName;
    public String lastName;
    public String e_mailadress;
    public String password;
    public Date dateOfBirth;
    public long phoneNumber;

    public User(int idUser, String firstName, String lastName, String e_mailadress, String password, Date dateOfBirth, long phoneNumber) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.e_mailadress = e_mailadress;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getE_mailadress() {
        return e_mailadress;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setE_mailadress(String e_mailadress) {
        this.e_mailadress = e_mailadress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.idUser;
        hash = 73 * hash + Objects.hashCode(this.firstName);
        hash = 73 * hash + Objects.hashCode(this.lastName);
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
        final User other = (User) obj;
        if (this.idUser != other.idUser) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", firstName=" + firstName + ", lastName=" + lastName + ", e_mailadress=" 
        + e_mailadress + ", password=" + password + ", dateOfBirth=" + dateOfBirth + ", phoneNumber=" + phoneNumber + '}';
    }
    
    
    
}

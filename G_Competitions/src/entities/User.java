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
public class User {
    private int idUser;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private Date DateOfBirth;
    private long numtel;

    public User(int idUser, String firstName, String lastName, String email, String password, String address, Date DateOfBirth, long numtel) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.DateOfBirth = DateOfBirth;
        this.numtel = numtel;
    }
    public User() {
        this.idUser = 0;
        this.firstName = " ";
        this.lastName = " ";
        this.email = " ";
        this.password = " ";
        this.address = " ";
        this.DateOfBirth = null;
        this.numtel = 0;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public long getNumtel() {
        return numtel;
    }

    public void setNumtel(long numtel) {
        this.numtel = numtel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idUser;
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
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", address=" + address + ", DateOfBirth=" + DateOfBirth + ", numtel=" + numtel + '}';
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Mey
 */
public class Workshop {

    private String NameW;
    private int idW;
    private String DateW;
    private String Duration;
    private String AddressW;

    private int  myTrainer;

    public Workshop(String NameW, int idW, String DateW, String Duration, String AddressW, int myTrainer) {
        this.NameW = NameW;
        this.idW = idW;
        this.DateW = DateW;
        this.Duration = Duration;
        this.AddressW = AddressW;
        this.myTrainer = myTrainer;
        
    }
    

    public Workshop() {

    }

    public Workshop(String NameW, String DateW, String Duration, String AddressW, int idTrainer) {
        this.NameW = NameW;
        this.DateW = DateW;
        this.Duration = Duration;
        this.AddressW = AddressW;
        this.myTrainer = idTrainer;
    }





    public String getNameW() {
        return NameW;
    }

    public void setNameW(String NameW) {
        this.NameW = NameW;
    }

    public int getIdW() {
        return idW;
    }

    public void setIdW(int idW) {
        this.idW = idW;
    }

    public String getDateW() {
        return DateW;
    }

    public void setDateW(String DateW) {
        this.DateW = DateW;
    }

    public String getDuration() {
        return Duration;
    }

    
    public void setDuration(String Duration) {
        this.Duration = Duration;
    }

    public String getAddressW() {
        return AddressW;
    }

    public void setAddressW(String AddressW) {
        this.AddressW = AddressW;
    }

    public int getMyTrainer() {
        return myTrainer;
    }

    public void setMyTrainer(int myTrainer) {
        this.myTrainer = myTrainer;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idW;
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
        final Workshop other = (Workshop) obj;
        return this.idW == other.idW;
    }

    @Override
    public String toString() {
        return  "NameW=" + NameW + ", " + idW + ", DateW=" + DateW + ", Duration=" + Duration + ", AddressW=" + AddressW + ", idTrainer=" + myTrainer+ "\n";
    }



}

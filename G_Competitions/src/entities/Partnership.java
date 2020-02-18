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
public class Partnership {
  
    private int idPartnership;
    private int idPartner;
    private int idComp;
    private String help;

    public Partnership(int idPartnership, int idPartner, int idComp, String help) {
        this.idPartnership = idPartnership;
        this.idPartner = idPartner;
        this.idComp = idComp;
        this.help = help;
    }

    public int getIdPartnership() {
        return idPartnership;
    }

    public void setIdPartnership(int idPartnership) {
        this.idPartnership = idPartnership;
    }

    public int getIdPartner() {
        return idPartner;
    }

    public void setIdPartner(int idPartner) {
        this.idPartner = idPartner;
    }

    public int getIdComp() {
        return idComp;
    }

    public void setIdComp(int idComp) {
        this.idComp = idComp;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.idPartnership;
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
        final Partnership other = (Partnership) obj;
        if (this.idPartnership != other.idPartnership) {
            return false;
        }
        return true;
    }
    
    
}

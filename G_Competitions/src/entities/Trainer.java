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
public class Trainer {
    int idTrainer;
    private String NameT;
    private String CinT;
    private String Speciality;

    public Trainer() {
    }
    
    /**
     *
     * @param idTrainer
     * @param NameT
     * @param CinT
     * @param Speciality
     */
    public Trainer(int idTrainer, String NameT, String CinT, String Speciality) {
        this.idTrainer = idTrainer;
        this.NameT = NameT;
        this.CinT = CinT;
        this.Speciality = Speciality;
    }

    public int getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(int idTrainer) {
        this.idTrainer = idTrainer;
    }

    public String getNameT() {
        return NameT;
    }

    public void setNameT(String NameT) {
        this.NameT = NameT;
    }

    public String getCinT() {
        return CinT;
    }

    public void setCinT(String CinT) {
        this.CinT = CinT;
    }

    public String getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(String Speciality) {
        this.Speciality = Speciality;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.idTrainer;
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
        final Trainer other = (Trainer) obj;
        return this.idTrainer == other.idTrainer;
    }

    @Override
    public String toString() {
        return  idTrainer + ", "+ NameT + "," + CinT + ", " + Speciality ;
    }
    
    
    
    
}

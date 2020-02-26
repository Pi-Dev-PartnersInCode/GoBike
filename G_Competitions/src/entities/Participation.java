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
public class Participation {
    private int idParticipation;
    private User myUser = new User();
    private Competition myCompetition = new Competition();
    private int rang;
    private int record;

    public Participation(int idParticipation, int myUser, int myCompetition, int rang, int record) {
        this.idParticipation = idParticipation;
        this.myUser.setId(myUser);
        this.myCompetition.setIdCompetition(myCompetition);
        this.rang = rang;
        this.record = record;
    }
    public Participation() {
        this.idParticipation = 0;
        this.myUser = null;
        this.myCompetition = null;
        this.rang = 0;
        this.record = 0;
    }

    public int getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(int idParticipation) {
        this.idParticipation = idParticipation;
    }

    public User getMyUser() {
        return myUser;
    }

    public void setMyUser(User myUser) {
        this.myUser = myUser;
    }

    public Competition getMyCompetition() {
        return myCompetition;
    }

    public void setMyCompetition(Competition myCompetition) {
        this.myCompetition = myCompetition;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "Participation{" + "idParticipation=" + idParticipation + ", myUser=" + myUser + ", myCompetition=" + myCompetition + ", rang=" + rang + ", record=" + record + '}';
    }

    
    
}

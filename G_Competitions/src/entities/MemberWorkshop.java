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
public class MemberWorkshop {

    int idM;

    private int myUser;

    private int myWork;

    public MemberWorkshop(int idM, int myUser, int myWork) {
        this.idM = idM;
        this.myUser = myUser;
        this.myWork = myWork;
    }
        public MemberWorkshop( int myUser, int myWork) {
  
        this.myUser = myUser;
        this.myWork = myWork;
    }
//     public Member(int idM, int idUser, int idW) {
//        this.idM = idM;
//        this.myUser = idUser;
//        this.myWork = idW;
//    }

    public int getIdM() {
        return idM;
    }

    public void setIdM(int idM) {
        this.idM = idM;
    }

    public int getMyUser() {
        return myUser;
    }

    public void setMyUser(int myUser) {
        this.myUser = myUser;
    }

    public int getMyWork() {
        return myWork;
    }

    public void setMyWork(int myWork) {
        this.myWork = myWork;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.idM;
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
        final MemberWorkshop other = (MemberWorkshop) obj;
        if (this.idM != other.idM) {
            return false;
        }
        return true;
    }

    public MemberWorkshop() {
    }

    @Override
    public String toString() {
        return "Member{" + "idM=" + idM + ", myUser=" + myUser + ", myWork=" + myWork + '}';
    }

}
    
    
    
    


  
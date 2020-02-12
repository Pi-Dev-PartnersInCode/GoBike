/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import utils.TrayIconDemo;
import entities.Participation;
import java.awt.AWTException;
import java.awt.SystemTray;
import services.CompetitionCRUD;
import services.ParticipationCRUD;




/**
 *
 * @author mneri
 */
public class MainClass {

  public static void main(String[] args) throws AWTException {

    
    CompetitionCRUD cc = new CompetitionCRUD();
    Participation p = new Participation(0,1,7,2,231);
    ParticipationCRUD pp = new ParticipationCRUD();  
    //pp.ajouterParticipation(p);
    //System.out.println(pp.afficherParticipationParCompetition(8));
      
      
      
      if (SystemTray.isSupported()) {
            TrayIconDemo td = new TrayIconDemo();
            td.displayTray();
        } else {
            System.err.println("System tray not supported!");
        }
   
//    Competition c = new Competition(0,"marathon","100Km race","ba7thena",Date.valueOf(LocalDate.now()));
//    Competition c1 = new Competition(3,"marathono","120Km race","ba7thehom",Date.valueOf(LocalDate.now()));
//    cc.ajouterCompetition(c);
//    cc.modifierCompetition(c1);
    //cc.supprimerCompetition(6);
//      System.out.println(cc.afficherCompetition());
   
}
    
}

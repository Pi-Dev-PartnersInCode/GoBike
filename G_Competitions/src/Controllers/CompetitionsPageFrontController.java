/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Competition;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import services.CompetitionCRUD;

/**
 * FXML Controller class
 *
 * @author mneri
 */
public class CompetitionsPageFrontController implements Initializable {

    @FXML
    private FlowPane FlowContainer;
    
    static ObservableList<Competition> obs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Afficher();
    }    
    public void Afficher()  {
  
        CompetitionCRUD srv = new CompetitionCRUD();
        
        nodeCompetitionController.i = 0;
        ArrayList<Competition> annonces = srv.afficherCompetition();
        obs = FXCollections.observableArrayList(annonces);
        //obslsorted = FXCollections.observableArrayList((ArrayList) annonceService.trierParDate())
        //prixdesc = FXCollections.observableArrayList((ArrayList) annonceService.trierParPrixDESC());
        Node[] nodes = new Node[obs.size()];
        for (int i = 0; i < nodes.length; i++) {
            
            try {
                nodes[i] = FXMLLoader.load(getClass().getResource("/GUI/nodeCompetition.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(CompetitionsPageFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
            FlowContainer.getChildren().add(nodes[i]);
        }
}
}
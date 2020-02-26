/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mneri
 */
public class DashboardPageController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private AnchorPane Container;

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
    }    


    @FXML
    private void CompetitionClicked(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CompetitionsPage.fxml"));
            Parent root = loader.load();
            Container.getChildren().setAll(root);

            
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    @FXML
    private void participationClicked(MouseEvent event) {
               try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ParticipationPage.fxml"));
            Parent root = loader.load();
            Container.getChildren().setAll(root);

            
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    @FXML
    private void homeClicked(MouseEvent event) {
    }

    @FXML
    private void usersClicked(MouseEvent event) {
              try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/admininterface.fxml"));
            Parent root = loader.load();
            Container.getChildren().setAll(root);

            
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    @FXML
    private void ReservationsClicked(MouseEvent event) {
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ReservationPage.fxml"));
            Parent root = loader.load();
            Container.getChildren().setAll(root);

            
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    @FXML
    private void ProductClicked(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ProductPage.fxml"));
            Parent root = loader.load();
            Container.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }


    }

    @FXML
    private void WorkshopsCilcked(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/WorkshopPage.fxml"));
            Parent root = loader.load();
            Container.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    @FXML
    private void TrainerClicked(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/TrainerPage.fxml"));
            Parent root = loader.load();
            Container.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    
}

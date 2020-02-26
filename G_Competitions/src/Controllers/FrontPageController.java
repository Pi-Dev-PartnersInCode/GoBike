/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Session;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class FrontPageController implements Initializable {

    public static User ConnectedUser;
    @FXML
    private Label nameLbl;
    @FXML
    private AnchorPane Container;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameLbl.setText(Session.CurrentUser.getNom() + " " + Session.CurrentUser.getPrenom());
    }

    @FXML
    private void homePageClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/HomePageFront.fxml"));
            Parent root = loader.load();
            Container.getChildren().setAll(root);

        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    @FXML
    private void competitionPageClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CompetitionsPageFront.fxml"));
            Parent root = loader.load();
            Container.getChildren().setAll(root);

        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    @FXML
    private void reservationsPageClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ReservationPage.fxml"));
            Parent root = loader.load();
            Container.getChildren().setAll(root);

        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    @FXML
    private void storePageClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ProductPageFront.fxml"));
            Parent root = loader.load();
            Container.getChildren().setAll(root);

        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }

    }

    @FXML
    private void blogPageClicked(ActionEvent event) {
    }

    @FXML
    private void usersAccountClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/listuser.fxml"));
            Parent root = loader.load();
            Container.getChildren().setAll(root);

        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    @FXML
    private void TrainerClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/TrainerFrontPage.fxml"));
            Parent root = loader.load();
            Container.getChildren().setAll(root);

        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    @FXML
    private void workshopPageClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/WorkshopFrontPage.fxml"));
            Parent root = loader.load();
            Container.getChildren().setAll(root);

        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }
}

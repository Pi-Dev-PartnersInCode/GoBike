/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mneri
 */
public class LoginPageController implements Initializable {

    @FXML
    private Label usernameLbl;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginPress(ActionEvent event) {
                        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/DashboardPage.fxml"));
            Parent root = loader.load();
            rootPane.getChildren().setAll(root);
            rootPane.getScene().getWindow().sizeToScene();
            rootPane.getScene().getWindow().setX(310);
            rootPane.getScene().getWindow().setY(90);
            
            

            
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
        

    }
    
}

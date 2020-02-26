/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Session;
import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asma
 */
public class ListuserController implements Initializable {

    public static User ConnectedUser;
    @FXML
    private Label resPrenom;
    @FXML
    private Label resSexe;
    @FXML
    private Label resMail;
    @FXML
    private Label resTel;
    @FXML
    private Label resMdp;
    @FXML
    private Label ResNom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         resPrenom.setText(Session.CurrentUser.getPrenom());
         ResNom.setText(Session.CurrentUser.getNom());
         resSexe.setText(Session.CurrentUser.getSexe());
         resMail.setText(Session.CurrentUser.getMail());
         resTel.setText(Session.CurrentUser.getTel());
         resMdp.setText(Session.CurrentUser.getMdp());
         

    }    

   
    
   
    
}

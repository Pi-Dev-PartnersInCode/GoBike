/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managusers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author afifa
 */
public class LoginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Label outputlogin;
    @FXML
    private Label mdpoublie;
    @FXML
    private Button login1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void authentification(ActionEvent event) {
    }

    @FXML
    private void reset(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Reset.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();

    }

    @FXML
    private void inscription(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    } 

    }
    


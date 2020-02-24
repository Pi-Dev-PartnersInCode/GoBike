/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managusers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class RegisterController implements Initializable {

    @FXML
    private Label outputregister;
    @FXML
    private Button retour;
    @FXML
    private Label outputlogin;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField confpassword;
    @FXML
    private ComboBox<?> role;
    @FXML
    private Button inscription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void inscription(ActionEvent event) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.UserCRUD;

public class SignupController implements Initializable {

    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtmail;
    @FXML
    private TextField txttel;
    @FXML
    private PasswordField txtmdp;
    @FXML
    private Button txtcancel;
    @FXML
    private TextField txtnom;
    @FXML
    private Button btnajouter;
    @FXML
    private Label outputlogin;
    @FXML
    private ComboBox<String> txtsexe2;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtsexe2.getItems().addAll("male", "female");
        // TODO
    }

    @FXML
    private void ajouter(ActionEvent event) {
        String nomP = txtnom.getText();
        String prenomP = txtprenom.getText();
        String mdpP = txtmdp.getText();
        String sexeP = txtsexe2.getValue();
        String mailP = txtmail.getText();
        String telP = txttel.getText();
        String roleP = "client";

        UserCRUD userC = new UserCRUD();
        User u = new User(nomP, prenomP, mdpP, sexeP, mailP, telP, roleP);

        if (!nomP.isEmpty() && !prenomP.isEmpty() && mdpP.length() > 3 && (sexeP.toLowerCase().equals("male")
                || sexeP.toLowerCase().equals("female")) && mailP.contains("@") && telP.length() == 8) {
            try {
                userC.ajouterUser(u);
                userC.login(mailP, mdpP);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FrontPage.fxml"));
                Parent root = loader.load();
                rootPane.getChildren().setAll(root);
                rootPane.getScene().getWindow().sizeToScene();

            } catch (IOException ex) {
                Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("bheeeeeeeeeeeeeem");
        }
    }

    @FXML
    public void cancel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/login.fxml"));
            Parent root = loader.load();
            rootPane.getChildren().setAll(root);
            rootPane.getScene().getWindow().sizeToScene();

        } catch (IOException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

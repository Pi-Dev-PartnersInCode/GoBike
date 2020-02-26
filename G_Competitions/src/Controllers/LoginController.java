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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.UserCRUD;

/**
 * FXML Controller class
 *
 * @author asma
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtmail;
    @FXML
    private PasswordField txtpassword;
    @FXML
    private Button btnsignin;
    private Button txtsignup;
    @FXML
    private Label outputlogin;
    @FXML
    private Label lblclose;
    @FXML
    private Button btnsignup;
    @FXML
    private Label txtoublie;
    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onlogin(ActionEvent event) {

        String mailP = txtmail.getText();
        String mdpP = txtpassword.getText();

        UserCRUD userC = new UserCRUD();
        User u = new User();

        if (userC.loginInterface(mailP, mdpP) == false) {
            JOptionPane.showMessageDialog(null, "Mail/mot de passe incorrect(s)!");
        }

//        System.out.println(userC.loginInterface(mailP, mdpP));
//        System.out.println(userC.RechercheUser(mailP).getRole().equals("client"));
        if (userC.loginInterface(mailP, mdpP) && userC.RechercheUser(mailP).getRole().equals("client")) {

            try {
                System.out.println("Vous êtes connecté !");
                userC.login(mailP, mdpP);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FrontPage.fxml"));
                Parent root = loader.load();
                rootPane.getChildren().setAll(root);
                rootPane.getScene().getWindow().sizeToScene();

            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (userC.loginInterface(mailP, mdpP) && userC.RechercheUser(mailP).getRole().equals("admin")) {

            System.out.println("Vous êtes connecté !");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/DashboardPage.fxml"));
                Parent root = loader.load();
                rootPane.getChildren().setAll(root);
                rootPane.getScene().getWindow().sizeToScene();

            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void oublie(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/reset.fxml"));
            Parent root = loader.load();
            rootPane.getChildren().setAll(root);
            rootPane.getScene().getWindow().sizeToScene();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void signup(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/signup.fxml"));
            Parent root = loader.load();
            rootPane.getChildren().setAll(root);
            rootPane.getScene().getWindow().sizeToScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exit(MouseEvent event) {
    }
}

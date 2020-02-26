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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import services.JavamailUtil1;
import services.UserCRUD;

/**
 * FXML Controller class
 *
 * @author asma
 */
public class ResetController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField mailrecover;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void valider(ActionEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void send(ActionEvent event) {

        try {
            String str = mailrecover.getText();
            JavamailUtil1 mail = new JavamailUtil1();
            String x = mail.sendMail(str);
            UserCRUD userC = new UserCRUD();
            userC.ModifierMdpUserFonction(str, x);
            JOptionPane.showMessageDialog(null, "Password sent to your e-mail !");

        } catch (Exception ex) {

            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/login.fxml"));
            Parent root = loader.load();
            rootPane.getChildren().setAll(root);
            rootPane.getScene().getWindow().sizeToScene();
        } catch (IOException ex) {
            Logger.getLogger(ResetController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

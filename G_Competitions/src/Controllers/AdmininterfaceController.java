/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import entities.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.UserCRUD;

/**
 * FXML Controller class
 *
 * @author asma
 */
public class AdmininterfaceController implements Initializable {

    @FXML
    private TableColumn<User, String> txtname;
    @FXML
    private TableColumn<User, String> txtlastname;
    @FXML
    private TableColumn<User, String> txtpassword;
    @FXML
    private TableColumn<User, String> txtsexe;
    @FXML
    private TableColumn<User, String> txtemail;
    @FXML
    private TableColumn<User, String> txtnumber;
    @FXML
    private TableColumn<User, String> txtrole;
    @FXML
    private TableColumn<User, Integer> txtid;
    @FXML
    private TableView<User> tableUser;
    @FXML
    private TextField searchBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
     txtid.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        txtname.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
       txtlastname.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        txtpassword.setCellValueFactory(new PropertyValueFactory<User, String>("mdp"));
       txtsexe.setCellValueFactory(new PropertyValueFactory<User, String>("sexe"));
        txtemail.setCellValueFactory(new PropertyValueFactory<User, String>("mail"));
        txtnumber.setCellValueFactory(new PropertyValueFactory<User, String>("tel"));
        txtrole.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        UserCRUD uc = new UserCRUD();
        refreshTable(uc.afficherUsers());

    }

  
    
     private void refreshTable(List<User> a) {
        ObservableList<User> obs;
        obs = FXCollections.observableArrayList();
        obs.addAll(a);
        tableUser.setItems(obs);
        FilteredList<User> filteredData = new FilteredList<>(obs, b -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(aff -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (aff.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        });
       SortedList<User> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableUser.comparatorProperty());
        tableUser.setItems(sortedData);
//        System.out.println(obs);

    }
     
     
     
}

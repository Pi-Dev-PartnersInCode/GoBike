/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Competition;
import entities.Participation;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.CompetitionCRUD;
import services.ParticipationCRUD;

/**
 * FXML Controller class
 *
 * @author mneri
 */
public class ParticipationPageController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    private TableColumn<?, ?> nomComp_col;
    private TableColumn<?, ?> emplacement_col;
    private TableColumn<?, ?> dateComp_col;
    @FXML
    private TextField rang_input;
    @FXML
    private TextField record_input;
    @FXML
    private ComboBox<String> searchTermComboBox;
    @FXML
    private TextField search_input;
    @FXML
    private TableColumn<Participation,String> idParticipation_col;
    @FXML
    private TableColumn<Participation,String> nomParticipation_col;
    @FXML
    private TableColumn<Participation,String> prenom_col;
    @FXML
    private TableColumn<Participation,String> record_col;
    @FXML
    private TableColumn<Participation,String> rang_col;
    @FXML
    private TableView<Participation> tableParticipations;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchTermComboBox.getItems().addAll("idComp²","idUser","idParticipation");
        idParticipation_col.setCellValueFactory(new PropertyValueFactory<Participation,String>("idCompetition"));
        nomParticipation_col.setCellValueFactory(new PropertyValueFactory<Participation,String>("myUser.firstName"));
        prenom_col.setCellValueFactory(new PropertyValueFactory<Participation,String>("myUser.lastName"));
        record_col.setCellValueFactory(new PropertyValueFactory<Participation,String>("record"));
        rang_col.setCellValueFactory(new PropertyValueFactory<Participation,String>("rang"));
        refreshTable();
    }    
    private void refreshTable() {
        ParticipationCRUD cc = new ParticipationCRUD();
        ObservableList<Participation> obs;
        System.out.println(cc.afficherParticipation());
        obs = FXCollections.observableArrayList();
        obs.addAll(cc.afficherParticipation());
        tableParticipations.setItems(obs);
        System.out.println(obs);
    }

    @FXML
    private void CompetitonSelected(MouseEvent event) {
    }

    @FXML
    private void addClicked(ActionEvent event) {
    }

    @FXML
    private void editClicked(ActionEvent event) {
    }

    @FXML
    private void deleteClicked(ActionEvent event) {
    }
    
}

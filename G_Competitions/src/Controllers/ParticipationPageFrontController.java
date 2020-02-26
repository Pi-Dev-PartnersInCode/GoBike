/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Participant;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import services.ParticipationCRUD;

/**
 * FXML Controller class
 *
 * @author mneri
 */
public class ParticipationPageFrontController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableColumn<Participant, String> idParticipation_col;
    @FXML
    private TableColumn<Participant, String> nomParticipation_col;
    @FXML
    private TableColumn<Participant, String> prenom_col;
    @FXML
    private TableColumn<Participant, String> record_col;
    @FXML
    private TableColumn<Participant, String> rang_col;
    @FXML
    private TableView<Participant> tableParticipations;
    @FXML
    private Label compDescription;
    @FXML
    private Label compTitle;
    @FXML
    private TextField searchBar;
    @FXML
    private Label compDate;
    @FXML
    private Label compLocation;
    @FXML
    private Label idCompLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idParticipation_col.setCellValueFactory(new PropertyValueFactory<Participant, String>("idParticipation"));
        nomParticipation_col.setCellValueFactory(new PropertyValueFactory<Participant, String>("nom"));
        prenom_col.setCellValueFactory(new PropertyValueFactory<Participant, String>("prenom"));
        record_col.setCellValueFactory(new PropertyValueFactory<Participant, String>("record"));
        rang_col.setCellValueFactory(new PropertyValueFactory<Participant, String>("rank"));
        ParticipationCRUD p = new ParticipationCRUD();
        nodeCompetitionController ncc = new nodeCompetitionController();
        refreshTable(p.afficherParticipationParCompetition(ncc.cc.getIdCompetition()));
        compDescription.setText(ncc.cc.getDescription());
        compDate.setText(ncc.cc.getDateComp().toString());
        compLocation.setText(ncc.cc.getEmplacement());
        compTitle.setText(ncc.cc.getNom());
        idCompLbl.setText(Integer.toString(ncc.cc.getIdCompetition()));
    }

    void refreshTable(ArrayList<Participant> a) {
        ObservableList<Participant> obs;
        obs = FXCollections.observableArrayList();
        obs.addAll(a);
        tableParticipations.setItems(obs);
        FilteredList<Participant> filteredData = new FilteredList<>(obs, b -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(aff -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (aff.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Participant> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableParticipations.comparatorProperty());
        tableParticipations.setItems(sortedData);
//        System.out.println(obs);
    }

    @FXML
    private void returnClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FrontPage.fxml"));
            Parent root = loader.load();
            rootPane.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(ParticipationPageFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

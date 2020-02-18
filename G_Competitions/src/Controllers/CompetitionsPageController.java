/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Competition;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.CompetitionCRUD;

/**
 * FXML Controller class
 *
 * @author mneri
 */
public class CompetitionsPageController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Competition> tableCompetitions;
    @FXML
    private TableColumn<Competition, String> idComp_col;
    @FXML
    private TableColumn<Competition, String> nomComp_col;
    @FXML
    private TableColumn<Competition, String> emplacement_col;
    @FXML
    private TableColumn<Competition, Date> dateComp_col;
    @FXML
    private TableColumn<Competition, String> description_col;
    @FXML
    private TextField idComp_input;
    @FXML
    private TextField nomComp_input;
    @FXML
    private TextField emplacement_input;
    @FXML
    private TextArea description_input;
    @FXML
    private DatePicker dateComp_input;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idComp_col.setCellValueFactory(new PropertyValueFactory<Competition,String>("idCompetition"));
        nomComp_col.setCellValueFactory(new PropertyValueFactory<Competition,String>("nom"));
        emplacement_col.setCellValueFactory(new PropertyValueFactory<Competition,String>("emplacement"));
        dateComp_col.setCellValueFactory(new PropertyValueFactory<Competition,Date>("dateComp"));
        description_col.setCellValueFactory(new PropertyValueFactory<Competition,String>("description"));
        refreshTable();

    }

    private void refreshTable() {
        CompetitionCRUD cc = new CompetitionCRUD();
        ObservableList<Competition> obs;
        obs = FXCollections.observableArrayList();
        obs.addAll(cc.afficherCompetition());
        tableCompetitions.setItems(obs);
//      System.out.println(obs);
    }

    @FXML
    private void addClicked(ActionEvent event) {

        int id = Integer.parseInt(idComp_input.getText());
        String nom = nomComp_input.getText();
        String empla = emplacement_input.getText();
        String desc = description_input.getText();
        Date dateComp = java.sql.Date.valueOf(dateComp_input.getValue());

        Competition c = new Competition(id, nom, desc, empla, dateComp);
        CompetitionCRUD cc = new CompetitionCRUD();
        cc.ajouterCompetition(c);
        refreshTable();

    }

    @FXML
    private void editClicked(ActionEvent event) {

        CompetitionCRUD cc = new CompetitionCRUD();
        Competition ca = new Competition(Integer.parseInt(idComp_input.getText()), nomComp_input.getText(),
        description_input.getText(), emplacement_input.getText(),java.sql.Date.valueOf(dateComp_input.getValue()));
            
        System.out.println(ca);
        cc.modifierCompetition(ca);
        
        nomComp_input.clear();
        emplacement_input.clear();
        description_input.clear();
        dateComp_input.setValue(null);
        refreshTable();
    }

    @FXML
    private void deleteClicked(ActionEvent event) {
        Competition c = tableCompetitions.getSelectionModel().getSelectedItem();
        CompetitionCRUD cc = new CompetitionCRUD();
        cc.supprimerCompetition(c.getIdCompetition());
        refreshTable();
    }

    @FXML
    private void CompetitonSelected(MouseEvent event) {
        Competition c = tableCompetitions.getSelectionModel().getSelectedItem();
        idComp_input.setText(String.valueOf(c.getIdCompetition()));
        nomComp_input.setText(c.getNom());
        emplacement_input.setText(c.getEmplacement());
        description_input.setText(c.getDescription());
            
  
        dateComp_input.setValue(c.getDateComp().toLocalDate());
    }


}

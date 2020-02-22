/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Competition;
import entities.Participation;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import services.CompetitionCRUD;
import utils.TrayIconDemo;

/**
 * FXML Controller class
 *
 * @author mneri
 */
public class CompetitionsPageFController implements Initializable {

    @FXML
    private TableView<Competition> tableCompetitions;
    @FXML
    private TableColumn<Competition, String> nomComp_col;
    @FXML
    private TableColumn<Competition, Date> dateComp_col;
    @FXML
    private TableColumn<Competition, String> description_col;

    @FXML
    private TextField searchBar;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableColumn<Competition, String> idComp_col;
    @FXML
    private TableColumn<Competition, String> emplacement_col;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idComp_col.setCellValueFactory(new PropertyValueFactory<Competition, String>("idCompetition"));
        nomComp_col.setCellValueFactory(new PropertyValueFactory<Competition, String>("nom"));
        emplacement_col.setCellValueFactory(new PropertyValueFactory<Competition, String>("emplacement"));
        dateComp_col.setCellValueFactory(new PropertyValueFactory<Competition, Date>("dateComp"));
        description_col.setCellValueFactory(new PropertyValueFactory<Competition, String>("description"));
        CompetitionCRUD cc = new CompetitionCRUD();
        refreshTable(cc.afficherCompetition());
    }

    private void refreshTable(ArrayList<Competition> a) {
        ObservableList<Competition> obs;
        obs = FXCollections.observableArrayList();
        obs.addAll(a);
        tableCompetitions.setItems(obs);
        FilteredList<Competition> filteredData = new FilteredList<>(obs, b -> true);
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
        SortedList<Competition> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableCompetitions.comparatorProperty());
        tableCompetitions.setItems(sortedData);
//        System.out.println(obs);
    }

    @FXML
    private void addParticipation(ActionEvent event) {

        Competition selectedId = tableCompetitions.getSelectionModel().getSelectedItem();
        if (selectedId != null) {
            Participation p = new Participation(0, 1, selectedId.getIdCompetition(), 0, 0);
            if (SystemTray.isSupported()) {
                try {
                    TrayIconDemo td = new TrayIconDemo();
                    td.displayTray();
                } catch (AWTException ex) {
                    Logger.getLogger(CompetitionsPageFController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.err.println("System tray not supported!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "no competition Selected");
        }

    }




}

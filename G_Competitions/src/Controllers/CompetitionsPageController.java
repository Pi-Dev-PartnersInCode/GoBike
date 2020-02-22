/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.itextpdf.text.DocumentException;
import entities.Competition;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import services.CompetitionCRUD;
import services.ParticipationCRUD;

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
    @FXML
    private TextField searchBar;

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
    private void addClicked(ActionEvent event) {
        if (fieldsEmpty()) {
            JOptionPane.showMessageDialog(null, "Check fields !");
        } else {
            int id = Integer.parseInt(idComp_input.getText());
            String nom = nomComp_input.getText();
            String empla = emplacement_input.getText();
            String desc = description_input.getText();
            Date dateComp = java.sql.Date.valueOf(dateComp_input.getValue());

            Competition c = new Competition(id, nom, desc, empla, dateComp);
            CompetitionCRUD cc = new CompetitionCRUD();
            cc.ajouterCompetition(c);
            refreshTable(cc.afficherCompetition());
            idComp_input.setText("0");
            nomComp_input.clear();
            emplacement_input.clear();
            description_input.clear();
            dateComp_input.setValue(null);
        }

    }

    private boolean fieldsEmpty() {
        return idComp_input.getText().trim().isEmpty() || nomComp_input.getText().trim().isEmpty()
                || emplacement_input.getText().trim().isEmpty() || description_input.getText().trim().isEmpty() || "".equals(dateComp_input.getValue().toString());
    }

    @FXML
    private void editClicked(ActionEvent event) {

        if (fieldsEmpty()) {
            JOptionPane.showMessageDialog(null, "Check fields !");

        } else {
            CompetitionCRUD cc = new CompetitionCRUD();
            Competition ca = new Competition(Integer.parseInt(idComp_input.getText()), nomComp_input.getText(),
                    description_input.getText(), emplacement_input.getText(), java.sql.Date.valueOf(dateComp_input.getValue()));

            System.out.println(ca);
            cc.modifierCompetition(ca);

            idComp_input.setText("0");
            nomComp_input.clear();
            emplacement_input.clear();
            description_input.clear();
            dateComp_input.setValue(null);
            refreshTable(cc.afficherCompetition());
        }

    }

    @FXML
    private void deleteClicked(ActionEvent event) {
        Competition c = tableCompetitions.getSelectionModel().getSelectedItem();
        if (c == null){
            JOptionPane.showMessageDialog(null, "Please select a competition !");
        }else {
        int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Competition ?", "delete Competition", YES_NO_OPTION);
        CompetitionCRUD cc = new CompetitionCRUD();
        if (i == 0) {
            cc.supprimerCompetition(c.getIdCompetition());
            refreshTable(cc.afficherCompetition());
        }
        }

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

    @FXML
    private void viewParticipants(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ParticipationPage.fxml"));
            Parent root = loader.load();
            ParticipationPageController ppc = loader.getController();
            ppc.CompetitionselectedLbl.setText(tableCompetitions.getSelectionModel().getSelectedItem().getNom());
            ParticipationCRUD p = new ParticipationCRUD();
            ppc.refreshTable(p.afficherParticipationParCompetition(tableCompetitions.getSelectionModel().getSelectedItem().getIdCompetition()));

            rootPane.getChildren().setAll(root);

        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    @FXML
    private void printTopdf(ActionEvent event) {

        try {
            int i = Integer.parseInt(idComp_input.getText());
            CompetitionCRUD c = new CompetitionCRUD();
            c.FacturePdf(i);
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionsPageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(CompetitionsPageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CompetitionsPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Competition;
import entities.Participant;
import entities.Participation;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class ParticipationPageController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField rang_input;
    @FXML
    private TextField record_input;
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
    Label CompetitionselectedLbl;

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
        refreshTable(p.afficherParticipants());

    }

    void refreshTable(ArrayList<Participant> a) {
        ObservableList<Participant> obs;
        obs = FXCollections.observableArrayList();
        obs.addAll(a);
        tableParticipations.setItems(obs);
//        System.out.println(obs);
    }

    @FXML
    private void editClicked(ActionEvent event) {
        if (tableParticipations.getSelectionModel().getSelectedItem() != null){
            Participation pp = new Participation(Integer.parseInt(CompetitionselectedLbl.getText()), 0, 0, Integer.parseInt(rang_input.getText()), Integer.parseInt(record_input.getText()));
            ParticipationCRUD ppp = new ParticipationCRUD();
            ppp.modifierParticipation(pp);
            refreshTable(ppp.afficherParticipants());
        }else {
            JOptionPane.showMessageDialog(null,"Please select a participation !");
        }
        
        CompetitionselectedLbl.setText("Not Selected");
        CompetitionselectedLbl.setVisible(true);

        

    }

    @FXML
    private void deleteClicked(ActionEvent event) {
        
        int i = JOptionPane.showConfirmDialog(null , "Are you sure you want to delete this Participation ?","delete Participation",YES_NO_OPTION);
        if (i == 1){
            ParticipationCRUD ppp = new ParticipationCRUD();
            ppp.supprimerParticipation(Integer.parseInt(CompetitionselectedLbl.getText()));
        }
        CompetitionselectedLbl.setText("Not Selected");
        CompetitionselectedLbl.setVisible(true);
    }

    @FXML
    private void ParticipationSelected(MouseEvent event) {
        Participant p = tableParticipations.getSelectionModel().getSelectedItem();
        CompetitionselectedLbl.setText(Integer.toString(p.getIdParticipation()));
        CompetitionselectedLbl.setVisible(false);

    }

}

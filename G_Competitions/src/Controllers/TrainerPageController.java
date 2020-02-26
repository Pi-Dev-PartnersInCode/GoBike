/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Trainer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.TrainerCRUD;

/**
 * FXML Controller class
 *
 * @author Mey
 */
public class TrainerPageController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Trainer> tableTrainer;
    @FXML
    private TableColumn< Trainer, String> idTrai_col;
    @FXML
    private TableColumn<Trainer, String> nameTrai_col;
    @FXML
    private TableColumn<Trainer, String> cinTrai_col;
    @FXML
    private TableColumn<Trainer, String> specialityTrai_col;
    @FXML
    private TextField idTrai;
    @FXML
    private TextField nameTrai;
    @FXML
    private TextField SpecTrai;
    @FXML
    private TextField CinTrai;
    @FXML
    private AnchorPane Container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idTrai_col.setCellValueFactory(new PropertyValueFactory<Trainer, String>("idTrainer"));
        nameTrai_col.setCellValueFactory(new PropertyValueFactory<Trainer, String>("NameT"));
        cinTrai_col.setCellValueFactory(new PropertyValueFactory<Trainer, String>("CinT"));
        specialityTrai_col.setCellValueFactory(new PropertyValueFactory<Trainer, String>("Speciality"));

        refreshTable();

    }

    private void refreshTable() {
        TrainerCRUD tt = new TrainerCRUD();
        ObservableList<Trainer> obs;
        obs = FXCollections.observableArrayList();
        obs.addAll(tt.afficherTrainer());
        tableTrainer.setItems(obs);
    System.out.println(obs);
    }

    @FXML
    private void TrainerSelected(MouseEvent event) {
         Trainer c = tableTrainer.getSelectionModel().getSelectedItem();
        int x = c.getIdTrainer();
        String str2 = Integer.toString(x);
       
        
        Trainer t = tableTrainer.getSelectionModel().getSelectedItem();
        nameTrai.setText(t.getNameT());
        CinTrai.setText(t.getCinT());
        SpecTrai.setText(t.getSpeciality());
         

//        description_input.setText(c.getDescription());
//        LocalDate tempdate = java.sql.Date(c.getDateComp().getTime()).toLocalDate();
//        dateComp_input.setValue(tempdate);

    }

    @FXML
    private void addClicked(ActionEvent event) {
        TrainerCRUD tt = new TrainerCRUD();
        int idTrainer = Integer.parseInt(idTrai.getText());

        String NameT = nameTrai.getText();
        String CinT = CinTrai.getText();
        String Speciality = SpecTrai.getText();

        Trainer t = new Trainer(idTrainer, NameT, CinT, Speciality);
        tt.ajouterTrainer(t);
        refreshTable();

    }

    @FXML
    private void editClicked(ActionEvent event) {
         

        Trainer t = tableTrainer.getSelectionModel().getSelectedItem();
        TrainerCRUD tt = new TrainerCRUD();
        int id = t.getIdTrainer();

        int idTrainer = Integer.parseInt(idTrai.getText());

        String NameT = nameTrai.getText();
        String CinT = CinTrai.getText();
        String Speciality = SpecTrai.getText();

        Trainer ta = new Trainer(idTrainer, NameT, CinT, Speciality);
        tt.modifierTrainer(ta);

        nameTrai.clear();
        CinTrai.clear();
        SpecTrai.clear();

        refreshTable();
    }

//    @FXML
//    private void deleteClicked(ActionEvent event) {
//        Trainer t = tableTrainer.getSelectionModel().getSelectedItem();
//        TrainerCRUD tt = new TrainerCRUD();
//        tt.supprimerTrainer(t.getIdTrainer());
//        refreshTable();
//    }

  

    @FXML
    private void deleteClicked(ActionEvent event) {
           
         Trainer c = tableTrainer.getSelectionModel().getSelectedItem();
        TrainerCRUD cc = new TrainerCRUD();
        cc.supprimerTrainer(c.getIdTrainer());
        refreshTable();
    
    }


}
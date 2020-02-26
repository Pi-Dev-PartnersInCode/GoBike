/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Reservation;
import entities.Session;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.ReservationCRUD;
import utils.TrayIconDemo;

/**
 * FXML Controller class
 *
 * @author Sondes
 */
public class ReservationPageController implements Initializable {

    ReservationCRUD RC = new ReservationCRUD();
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableColumn<Reservation, String> idres_col;
    @FXML
    private TableColumn<Reservation, String> emplacement_col;
    @FXML
    private TableColumn<Reservation, String> dateres_col;
    @FXML
    private TableColumn<Reservation, String> idbike;
    @FXML
    private TableColumn<Reservation, String> idclient;
    @FXML
    private Label identifiantres;
    @FXML
    private TextField idres_input;
    @FXML
    private ComboBox<String> place;
    public ObservableList<String> emp = FXCollections.observableArrayList("Tunis", "Sfax", "medenine");
    @FXML
    private DatePicker dateresinput;
    @FXML
    private Label identifiantres1;
    @FXML
    private TextField idclient_input;
    @FXML
    private Label gestionreservation;
    @FXML
    private ComboBox<String> idbike_input;
    public ObservableList<String> b = FXCollections.observableArrayList("vtt", "bmx");
    @FXML
    private TableView<Reservation> tableReservation;
    public ObservableList<Reservation> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        place.setItems(emp);
        idbike_input.setItems(b);
        data.addAll(RC.afficherReservation());
        this.idres_col.setCellValueFactory(new PropertyValueFactory<>("idreservation"));
        this.emplacement_col.setCellValueFactory(new PropertyValueFactory<>("place"));
        this.dateres_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.idbike.setCellValueFactory(new PropertyValueFactory<>("bike"));
        this.idclient.setCellValueFactory(new PropertyValueFactory<>("idUser"));
//      this.idbike.setCellValueFactory(new PropertyValueFactory<>("idbike"));
        this.tableReservation.setItems(data);
        //edit
//        this.tableReservation.setEditable(true);
//        this.dateres_col.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    private void ReservationSelected(MouseEvent event) {
        Reservation R = tableReservation.getSelectionModel().getSelectedItem();
        idres_input.setText(String.valueOf(R.getIdreservation()));
        place.setValue(String.valueOf(R.getPlace()));
        idbike_input.setValue(String.valueOf(R.getBike()));
        // dateresinput.setValue(R.getDate().toLocalDate());
//        idclient_input.setText(String.valueOf(R.getClient()));
        //      idbike_input.setText(String.valueOf(R.getBike()));

    }

    @FXML
    private void addClicked(ActionEvent event) throws Exception {
        int id = Integer.parseInt(idres_input.getText());
        int idU = Integer.parseInt(idclient_input.getText());
        String emplacement = place.getValue().toString();
        String date = dateresinput.getValue().format(DateTimeFormatter.ISO_DATE);
        String bike = idbike_input.getValue().toString();
      
        Reservation r = new Reservation(id,idU, date, place.getValue(), idbike_input.getValue());
        RC.ajouterReservation(r);
//        data.clear();
//        data.addAll(RC.afficherReservation());
        tableReservation.getItems().clear();
        tableReservation.getItems().addAll(r);
        refreshTable();
               if (SystemTray.isSupported()) {
                TrayIconDemo td = new TrayIconDemo();
                try {
                    td.displayTray();
                } catch (AWTException ex) {
                    Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.err.println("System tray not supported!");
            }
//        JavamailUtil mail= new JavamailUtil();
//        mail.sendMail("sondes.maazoul1@esprit.tn");
//        
    //   jvm=new JavamailUtil("ghaithbhs095@gmail.com","Testing Code 2 example","Testing Code Body yess");
    }

    @FXML
    private void editClicked(ActionEvent event) {
        Reservation r = tableReservation.getSelectionModel().getSelectedItem();
        /*
         int idreservation = Integer.parseInt(idbike_input.getText());
         */
        String date = dateresinput.getValue().toString();
        String emplacement = place.getValue();
        String bike = idbike_input.getValue();
        ReservationCRUD CD = new ReservationCRUD();
        int id = r.getIdreservation();
        System.out.println(id);
        CD.modifierReservation(r, date, emplacement, bike);
        tableReservation.getItems().clear();
        tableReservation.getItems().addAll(r);
        refreshTable();
    }
//

    @FXML
    private void deleteClicked(ActionEvent event) {
        Reservation R = (Reservation) tableReservation.getSelectionModel().getSelectedItem();
        ReservationCRUD RC = new ReservationCRUD();
        RC.supprimerReservation(R.getIdreservation());
        refreshTable();
    }

    @FXML
    private void CompetitonSelected(MouseEvent event) {
        Reservation r = tableReservation.getSelectionModel().getSelectedItem();
        // LocalDate date1=java.sql.Date(r.getDate().getTime()).toLocalDate();
        //  dateresinput.setValue(r.getDate());

        place.setValue(r.getPlace());
        idbike_input.setValue(r.getBike());
    }

    private void refreshTable() {
        ReservationCRUD RC = new ReservationCRUD();
        ObservableList<Reservation> Obs;
        Obs = FXCollections.observableArrayList();
        Obs.addAll(RC.afficherReservation());
        tableReservation.setItems(Obs);

    }

}

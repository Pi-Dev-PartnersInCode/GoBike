/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Workshop;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.xml.ws.Holder;
import services.WorkshopCRUD;

/**
 * FXML Controller class
 *
 * @author Mey
 */
public class WorkshopPageController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Workshop> tableWorkshop;
    @FXML
    private TableColumn<Workshop, String> idWork_col;
    @FXML
    private TableColumn<Workshop, String> nameWork_col;
    @FXML
    private TableColumn<Workshop, String> addressWork_col;
    @FXML
    private TableColumn<Workshop, String> dateWork_col;
    @FXML
    private TableColumn<Workshop, String> durationWork_col;
    @FXML
    private TableColumn<Workshop, String> idTrainer_col;
    @FXML
    private TextField idWork;
    @FXML
    private TextField nameWork;
    @FXML
    private TextField addressWork;
    @FXML
    private TextField idTrai;

    @FXML
    private TextField durationWork;
    @FXML
    private DatePicker dateWork;

    public static Holder<String> holdID = new Holder<String>();

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idWork_col.setCellValueFactory(new PropertyValueFactory<Workshop, String>("idW"));
        nameWork_col.setCellValueFactory(new PropertyValueFactory<Workshop, String>("NameW"));
        addressWork_col.setCellValueFactory(new PropertyValueFactory<Workshop, String>("AddressW"));
        dateWork_col.setCellValueFactory(new PropertyValueFactory<Workshop, String>("DateW"));
        durationWork_col.setCellValueFactory(new PropertyValueFactory<Workshop, String>("Duration"));
        idTrainer_col.setCellValueFactory(new PropertyValueFactory<Workshop, String>("myTrainer"));
        refreshTable();

    }

    private void refreshTable() {
        WorkshopCRUD ww = new WorkshopCRUD();
        ObservableList<Workshop> obs;
        obs = FXCollections.observableArrayList();
        obs.addAll(ww.afficherWorkshop());
        System.out.println(ww.afficherWorkshop());
        tableWorkshop.setItems(obs);

    }

    @FXML
    private void addClicked(ActionEvent event) {

        WorkshopCRUD ww = new WorkshopCRUD();

        int myTrainer = Integer.parseInt(idTrai.getText());
        String NameW = nameWork.getText();
        String DateW = dateWork.getValue().toString();
        String AddressW = addressWork.getText();
        String Duration = durationWork.getText();

        Workshop w = new Workshop(NameW, DateW, Duration, AddressW, myTrainer);
        ww.ajouterWorkshop(w);
        refreshTable();
    }

    @FXML
    private void editClicked(ActionEvent event) {

        Workshop c = tableWorkshop.getSelectionModel().getSelectedItem();
        WorkshopCRUD cc = new WorkshopCRUD();
        int id = c.getIdW();
        java.sql.Date javaSqlDate = java.sql.Date.valueOf(dateWork.getValue());
        int idW = Integer.parseInt(idWork.getText());
        int myTrainer = Integer.parseInt(idTrai.getText());
        String NameW = nameWork.getText();
        String DateW = dateWork.getValue().toString();
        String AddressW = addressWork.getText();
        String Duration = durationWork.getText();

        Workshop ca = new Workshop(NameW, idW, DateW, Duration, AddressW, myTrainer);
        cc.modifierWorkshop(ca);
        System.out.println("test");
        refreshTable();

    }

    @FXML
    public void WorkshopSelected(MouseEvent event) {
        Workshop c = tableWorkshop.getSelectionModel().getSelectedItem();
        int x = c.getIdW();
        String str2 = Integer.toString(x);
        String str3 = Integer.toString(c.getMyTrainer());

        idWork.setText(str2);
        nameWork.setText(c.getNameW());
        addressWork.setText(c.getAddressW());
        durationWork.setText(c.getDuration());
        idTrai.setText(str3);


        if (event.getClickCount() == 2) {
            holdID.value = str2;

            System.out.println(holdID.value);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(WorkshopPageController.this.getClass().getResource("/GUI/membreWorkshop.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(WorkshopPageController.this.getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
        }
//        LocalDate tempdate = java.sql.Date(c.getDateComp().getTime()).toLocalDate();
//        dateComp_input.setValue(tempdate);
    }

    @FXML
    private void deleteClicked(ActionEvent event) {
        Workshop c = tableWorkshop.getSelectionModel().getSelectedItem();
        WorkshopCRUD cc = new WorkshopCRUD();
        cc.supprimerWorkshop(c.getIdW());
        refreshTable();
    }

}

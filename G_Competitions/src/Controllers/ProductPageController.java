/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

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
import entities.Product;
import services.ProductCRUD;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ProductPageController implements Initializable {

    ProductCRUD PC = new ProductCRUD();
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableColumn<Product, String> id_col;
    @FXML
    private TableColumn<Product, String> nom_col;
    @FXML
    private TableColumn<Product, Float> prix_col;
    @FXML
    private TableColumn<Product, String> qte_col;
    @FXML
    private TableColumn<Product, String> typ_col;
    @FXML
    private TableColumn<Product, String> catg_col;
    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField prix;
    @FXML
    private TextField qte;
    @FXML
    private TextField typ;
    @FXML
    private TextField catg;
    @FXML
    private TextField des;
    @FXML
    private TableView<Product> tableProduct;
    @FXML
    private TableColumn<Product, String> des_col;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_col.setCellValueFactory(new PropertyValueFactory<Product, String>("idProd"));//1)atributs dans textfield 2)attributd dans classe java
        nom_col.setCellValueFactory(new PropertyValueFactory<Product, String>("nomProd"));
        prix_col.setCellValueFactory(new PropertyValueFactory<Product, Float>("prixProd"));
        qte_col.setCellValueFactory(new PropertyValueFactory<Product, String>("quantite"));
        typ_col.setCellValueFactory(new PropertyValueFactory<Product, String>("Type"));
        catg_col.setCellValueFactory(new PropertyValueFactory<Product, String>("categorie"));
        des_col.setCellValueFactory(new PropertyValueFactory<Product, String>("descrip"));
        refreshTable();

    }

    private void ReservationSelected(MouseEvent event) {
        Product R = tableProduct.getSelectionModel().getSelectedItem();
        id.setText(String.valueOf(R.getIdProd()));
        nom.setText(String.valueOf(R.getNomProd()));
        //  idbike_input.setValue(String.valueOf(R.getBike()));
        // dateresinput.setValue(R.getDate().toLocalDate());
//        idclient_input.setText(String.valueOf(R.getClient()));
        //      idbike_input.setText(String.valueOf(R.getBike()));

    }

    private void refreshTable() {
        ObservableList<Product> obs;
        obs = FXCollections.observableArrayList();
        obs.addAll(PC.ShowListProd());
        tableProduct.setItems(obs);

    }

    @FXML
    private void addClicked(ActionEvent event) {

        //nomProd, prixProd, quantite, descrip, type, categorie
        int idProd = Integer.parseInt(id.getText());
        String nomProd = nom.getText();
        float prixProd = Float.parseFloat(prix.getText());
        int quantite = Integer.parseInt(qte.getText());
        String descrip = des.getText();
        String type = typ.getText();
        String categorie = catg.getText();
        Product p = new Product(idProd, nomProd, prixProd, quantite, descrip, type, categorie);
        PC.addProduct(p);
        tableProduct.getItems().clear();
        tableProduct.getItems().addAll(p);
        refreshTable();
    }

    @FXML
    private void editClicked(ActionEvent event) {
        Product p = tableProduct.getSelectionModel().getSelectedItem();
        /*
         int idreservation = Integer.parseInt(idbike_input.getText());
         */

        String nomProd = nom.getText();
        float prixProd = Float.parseFloat(prix.getText());
        int quantite = Integer.parseInt(qte.getText());
        String descrip = des.getText();
        String type = typ.getText();
        String categorie = catg.getText();

        ProductCRUD PC = new ProductCRUD();
        int id = p.getIdProd();
        System.out.println(id);
        PC.ModifyNameProduct(p, id, nomProd);
        tableProduct.getItems().clear();
        tableProduct.getItems().addAll(p);
        refreshTable();

    }

    @FXML
    private void deleteClicked(ActionEvent event) {

        Product p = (Product) tableProduct.getSelectionModel().getSelectedItem();
        // ReservationCRUD RC = new ReservationCRUD();
        //  ProductCRUD PC= new ProductCRUD();
        // PC.removeProduct(p.getIdProd());
        PC.removeProduct(p, p.getIdProd());
        refreshTable();

    }

    @FXML
    private void ajouter(MouseEvent event) {
    }

    @FXML
    private void participationsClicked(MouseEvent event) {
    }
    @FXML
    private void productSelected(MouseEvent event) {
        Product p = tableProduct.getSelectionModel().getSelectedItem();
        nom.setText(p.getNomProd());
    }

}

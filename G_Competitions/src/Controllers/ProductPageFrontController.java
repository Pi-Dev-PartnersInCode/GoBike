/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import entities.Product;
import services.ProductCRUD;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ProductPageFrontController implements Initializable {

    @FXML
    private FlowPane FlowContainer;

    static ObservableList<Product> obs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProductCRUD pcrud = new ProductCRUD();

        nodeProductController.i = 0;
        ArrayList<Product> annonces = (ArrayList<Product>) pcrud.ShowListProd();
        obs = FXCollections.observableArrayList(annonces);

        Node[] nodes = new Node[obs.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                nodes[i] = FXMLLoader.load(getClass().getResource("/gui/nodeProduct.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(ProductPageFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
            FlowContainer.getChildren().add(nodes[i]);

        }
    }

//    public void Afficher() {
//        ProductCRUD pcrud = new ProductCRUD();
//
//        nodeProductController.i = 0;
//        ArrayList<Product> annonces = (ArrayList<Product>) pcrud.ShowListProd();
//        obs = FXCollections.observableArrayList(annonces);
//
//        Node[] nodes = new Node[obs.size()];
//        for (int i = 0; i < nodes.length; i++) {
//            try {
//                nodes[i] = FXMLLoader.load(getClass().getResource("/GUI/nodeProduct.fxml"));
//            } catch (IOException ex) {
//                Logger.getLogger(ProductPageFrontController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            FlowContainer.getChildren().add(nodes[i]);
//
//        }
//        
//    }

}

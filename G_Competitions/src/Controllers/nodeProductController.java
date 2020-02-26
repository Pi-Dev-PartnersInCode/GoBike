/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class nodeProductController implements Initializable {

    @FXML
    private GridPane ProductContainer;
    @FXML
    private ImageView item_Image;
    @FXML
    private Label desc;
    @FXML
    private Label nomP;
    @FXML
    private Label prix;
    
    static int i;
    public int t;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        t = ProductPageFrontController.obs.get(i).getIdProd();

        nomP.setText(ProductPageFrontController.obs.get(i).getNomProd());
        desc.setText(ProductPageFrontController.obs.get(i).getDescrip());
        prix.setText(String.valueOf(ProductPageFrontController.obs.get(i).getPrixProd()));
        i++;
     //   prix.setText(ProductPageFrontController.obs.get(i).getPrixProd(float.class));
//prix.setCellValueFactory(new PropertyValueFactory<Product, Float>("prixProd"));
   

     

//        Image = v.getImage();
//        Image.setFitWidth(125);
//        Image.setFitHeight(125);
    }

}

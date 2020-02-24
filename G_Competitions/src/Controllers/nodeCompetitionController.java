package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import entities.Competition;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author mneri
 */
public class nodeCompetitionController implements Initializable {

    @FXML
    private GridPane ProductContainer;
    @FXML
    private ImageView item_Image;
    @FXML
    private Label compDescription;
    @FXML
    private Button item_location_button;
    @FXML
    private Label compTitle;

    static int i;
    static int t;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        compTitle.setText(CompetitionsPageFrontController.obs.get(i).getNom());
        compDescription.setText(CompetitionsPageFrontController.obs.get(i).getDescription());
        i++;
//        Image = v.getImage();
//        Image.setFitWidth(125);
//        Image.setFitHeight(125);
    }


}

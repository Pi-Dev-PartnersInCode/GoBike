package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import entities.Competition;
import entities.Participation;
import entities.Session;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import services.ParticipationCRUD;
import utils.TrayIconDemo;

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
    private Label compTitle;

    static int i;
    public int t;
    @FXML
    private Label locationLbl;
    @FXML
    private Label dateLabel;
    @FXML
    private Button viewButton;
    @FXML
    private Button participateButton;
    
    public static Competition cc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        t = CompetitionsPageFrontController.obs.get(i).getIdCompetition();
        cc = CompetitionsPageFrontController.obs.get(i);
        System.out.println(t);
        String NomComp = CompetitionsPageFrontController.obs.get(i).getNom();
        compTitle.setText(NomComp);
        String DescComp = CompetitionsPageFrontController.obs.get(i).getDescription();
        compDescription.setText(DescComp);
        compDescription.setWrapText(true);
        String LocationComp = CompetitionsPageFrontController.obs.get(i).getEmplacement();
        locationLbl.setText(LocationComp);
        DateFormat format = new SimpleDateFormat("dd MMM yyyy");
        String DateComp = format.format(CompetitionsPageFrontController.obs.get(i).getDateComp()).toString();
        dateLabel.setText(DateComp);
        i++;

        participateButton.setOnAction((event) -> {
            try {
                ParticipationCRUD P = new ParticipationCRUD();
                System.out.println(t);
                Participation a = new Participation(0, Session.CurrentUser.getId(), t, 0, 0);
                P.ajouterParticipation(a);
                if (SystemTray.isSupported()) {
                    TrayIconDemo td = new TrayIconDemo();
                    td.displayTray();
                } else {
                    System.err.println("System tray not supported!");
                }
            } catch (AWTException ex) {
                Logger.getLogger(ex.getMessage());
            }

        });

        viewButton.setOnAction((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ParticipationPageFront.fxml"));
                Parent root = loader.load();
                compTitle.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(nodeCompetitionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
//        Image = v.getImage();
//        Image.setFitWidth(125);
//        Image.setFitHeight(125);
    }

}

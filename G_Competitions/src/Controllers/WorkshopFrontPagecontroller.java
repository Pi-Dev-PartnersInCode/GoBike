/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.MemberWorkshop;
import entities.Workshop;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.MemberWorkshopCRUD;
import services.WorkshopCRUD;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Mey
 */
public class WorkshopFrontPagecontroller implements Initializable {

    Statement ste = null;

    @FXML
    private TableView tblData;
    Connection con;
    Statement st;
    private ObservableList<ObservableList> data;
    @FXML
    private Pane rootpane;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtaddress;
    @FXML
    private ComboBox<String> txtduration;
    @FXML
    private DatePicker txtdate;
    @FXML
    private Button btnSearch;
    @FXML
    private Label lblStatus;
    @FXML
    private Button print;
    @FXML
    private Button stats;
    @FXML
    private Button btnPart;

    public WorkshopFrontPagecontroller() {
        con = MyConnection.getInstance().getCn();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        stats.setOnAction((ActionEvent event) -> {
            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Staistique des Produits");
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Percent");
            // Create a BarChart
            BarChart<String,Number> barChart = new BarChart<String,Number>(xAxis, yAxis);

            // Series 1 - Data of 2014
            XYChart.Series<String,Number> dataSeries1 = new XYChart.Series<String,Number>();
            dataSeries1.setName(">20");

            dataSeries1.getData().add(new XYChart.Data<String,Number>("Workshop", countdata()));
            dataSeries1.getData().add(new XYChart.Data<String,Number>("Membre workshop", countdata2()));
            dataSeries1.getData().add(new XYChart.Data<String,Number>("Trainer", countdata3()));

            // Series 2 - Data of 2015
            XYChart.Series<String,Number> dataSeries2 = new XYChart.Series<String, Number>();
            dataSeries2.setName("<20");

            // Add Series to BarChart.
            barChart.getData().add(dataSeries1);
            barChart.getData().add(dataSeries2);

            barChart.setTitle("Workshop");

            VBox vbox = new VBox(barChart);

            Scene scene = new Scene(vbox, 400, 200);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setHeight(300);
            stage.setWidth(400);

            stage.show();
        });

        print.setOnAction((ActionEvent e) -> {
            ResultSet rs;
            try {

                String query = "Select * from Workshop";

                rs = con.createStatement().executeQuery(query);

                //Apache POI Jar Link-
                //http://a.mbbsindia.com/poi/release/bin/poi-bin-3.13-20150929.zip
                XSSFWorkbook wb = new XSSFWorkbook();//for earlier version use HSSF

                XSSFSheet sheet = wb.createSheet("Product Details");

                XSSFRow header = sheet.createRow(0);

                header.createCell(0).setCellValue("NameW");

                header.createCell(1).setCellValue("idW");

                header.createCell(2).setCellValue("DateW");

                header.createCell(3).setCellValue("Duration");

                sheet.autoSizeColumn(1);

                sheet.autoSizeColumn(2);

                sheet.setColumnWidth(3, 256 * 25);//256-character width

                sheet.setZoom(150);//scale-150%

                int index = 1;

                while (rs.next()) {

                    XSSFRow row = sheet.createRow(index);

                    row.createCell(0).setCellValue(rs.getString("NameW"));

                    row.createCell(1).setCellValue(rs.getInt("idW"));

                    row.createCell(2).setCellValue(rs.getString("DateW"));

                    row.createCell(3).setCellValue(rs.getString("Duration"));

                    index++;

                }

                try (FileOutputStream fileOut = new FileOutputStream("WorkshopDetails.xlsx") // before 2007 version xls
                        ) {
                    wb.write(fileOut);
                }

                Alert alert = new Alert(AlertType.INFORMATION);

                alert.setTitle("Information Dialog");

                alert.setHeaderText(null);

                alert.setContentText("User Details Exported in Excel Sheet.");

                alert.showAndWait();

                rs.close();

            } catch (SQLException | FileNotFoundException ex) {

                System.out.println("Error " + ex.getMessage());

            } catch (IOException ex) {

                System.out.println("Error " + ex.getMessage());

            }
        });

        fetColumnList();
        fetRowList();
        btnSearch.setOnAction((ActionEvent event) -> {
            fetRowListsearch();
        });
//        btnPart.setOnAction((ActionEvent event) -> {
//            System.out.println("test");
//        });
//        txtduration.getItems().addAll("1 Hour per week","1.30 Hour per week", "2 Hours per week");
    }

    @FXML
    private void HandleEvents(MouseEvent event) {
    }

    public void fetColumnList() {
        try {

            String SQL = "SELECT `NameW`,idW, `DateW`, `Duration`, `AddressW` , `NameT`   FROM workshop p JOIN trainer c ON p.idTrainer = c.idTrainer";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());

                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tblData.getColumns().removeAll(col);
                tblData.getColumns().addAll(col);

                System.out.println("Column [" + i + "]");
// Create a new RowFactory to handle actions
                tblData.setRowFactory((Object tv) -> {
                    TableRow<Workshop> row = new TableRow<>();
                    row.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
                        Object selectedItems = tblData.getSelectionModel().getSelectedItems().get(0);
                        String data1 = selectedItems.toString().split(",")[1].substring(1);
                        String data2 = selectedItems.toString().split(",")[0].substring(0);

                        lblStatus.setText(data2);
                        int idwww = Integer.parseInt(data1);
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setHeaderText("Particpate in Workshop ? : " + data2);
                        alert.setContentText("COMFIRM Participation");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            MemberWorkshop WW = new MemberWorkshop();
                            WW = new MemberWorkshop(5, idwww);
                            MemberWorkshopCRUD cc = new MemberWorkshopCRUD();
                            cc.ajouterMember(WW);
                        } else {
                        }

                    }
                    );

                    return row;
                });

            }

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void fetRowList() {
        data = FXCollections.observableArrayList();
        ResultSet rs;

        try {
            PreparedStatement pre = con.prepareStatement("SELECT `NameW`,idW, `DateW`, `Duration`, `AddressW` , `NameT`   FROM workshop p JOIN trainer c ON p.idTrainer = c.idTrainer");
            rs = pre.executeQuery();

            while (rs.next()) {
                //Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                //row.set(1, row.get(6));
                //System.out.println("Row [1] added " + row.get(1));
                //System.out.println("hey yo " + row.get(7));
                data.add(row);
            }
            tblData.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * *********************************************************************************
     */
    public void fetRowListsearch() {
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {

            PreparedStatement pre = con.prepareStatement("SELECT `NameW`, `DateW`, `Duration`, `AddressW` , `NameT`   FROM workshop p JOIN trainer c ON p.idTrainer = c.idTrainer  WHERE NameW LIKE  ? OR Duration LIKE ? OR AddressW LIKE ? ");
            pre.setString(1, txtname.getText());
            pre.setString(2, (String) txtduration.getValue());
            pre.setString(3, (String) txtaddress.getText());

            rs = pre.executeQuery();

            while (rs.next()) {
                //Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }

                //System.out.println("Row [1] added " + row);
                //System.out.println("hey yo " + row.get(7));
                data.add(row);
            }
            tblData.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public int countdata() {
        int count = 0;
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from workshop");
            while (rs.next()) {
                count++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText(ex.getMessage());

        }
        return count;
    }

    public int countdata2() {
        int count = 0;
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from memberworkshop");
            while (rs.next()) {
                count++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public int countdata3() {
        int count = 0;
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from trainer");
            while (rs.next()) {
                count++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

}

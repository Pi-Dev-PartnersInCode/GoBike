/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Trainer;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import static jdk.nashorn.internal.objects.Global.print;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static sun.misc.Version.print;
import static sun.misc.Version.print;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Mey
 */
public class TrainerFrontPageController implements Initializable {
 Connection con;
    Statement st;
    Statement ste = null;
    @FXML
      private TableView tblData;
    
     private ObservableList<ObservableList> data;
    @FXML
    private Pane rootpane;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtspec;
    @FXML
    private Button btnSearch;
 
    @FXML
    private Label lblStatus;
    @FXML
    private Button print;
    @FXML
    private Button stats;
  public TrainerFrontPageController() {
        con = MyConnection.getInstance().getCn();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {



 	stats.setOnAction((ActionEvent event) -> {
	CategoryAxis xAxis = new CategoryAxis();
       xAxis.setLabel("Trainers Statistic");
 
       NumberAxis yAxis = new NumberAxis();
       yAxis.setLabel("Percent");
 
       // Create a BarChart
       BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
 
       // Series 1 - Data of 2014
       XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
       dataSeries1.setName(">20");
 
       dataSeries1.getData().add(new XYChart.Data<String, Number>("Trainer", countdata()));
       dataSeries1.getData().add(new XYChart.Data<String, Number>("Workshops", countdata2()));
    
 
     
 
       // Add Series to BarChart.
       barChart.getData().add(dataSeries1);
    
 
       barChart.setTitle("Trainers");
 
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
                
                String query = "Select * from Trainer";
                
                rs = con.createStatement().executeQuery(query);
                
                
                //Apache POI Jar Link-
                
                //http://a.mbbsindia.com/poi/release/bin/poi-bin-3.13-20150929.zip
                
                XSSFWorkbook wb = new XSSFWorkbook();//for earlier version use HSSF
                
                XSSFSheet sheet = wb.createSheet("Trainer Details");
                
                XSSFRow header = sheet.createRow(0);
                
                header.createCell(0).setCellValue("idTrainer");
                
                header.createCell(1).setCellValue("NameT");
                
                header.createCell(2).setCellValue("CinT");
                
                header.createCell(3).setCellValue("Speciality");
                
                
                
                sheet.autoSizeColumn(1);
                
                sheet.autoSizeColumn(2);
                
                sheet.setColumnWidth(3, 256*25);//256-character width
                
                
                
                sheet.setZoom(150);//scale-150%
                
                
                
                
                
                int index = 1;
                
                while(rs.next()){
                    
                    XSSFRow row = sheet.createRow(index);
                    
                    row.createCell(0).setCellValue(rs.getInt("idTrainer"));
                    
                    row.createCell(1).setCellValue(rs.getString("NameT"));
                    
                    row.createCell(2).setCellValue(rs.getInt("CinT"));
                    
                    row.createCell(3).setCellValue(rs.getString("Speciality"));
                    
                    index++;
                    
                }
                
                
                
                try (FileOutputStream fileOut = new FileOutputStream("TrainerDetails.xlsx") // before 2007 version xls
                        ) {
                    wb.write(fileOut);
                }
                
                
                
                Alert alert = new Alert(AlertType.INFORMATION);
                
                alert.setTitle("Information Dialog");
                
                alert.setHeaderText(null);
                
                alert.setContentText("Trainer Details Exported in Excel Sheet.");
                
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

                
                
               }
                

    @FXML
    private void HandleEvents(MouseEvent event) {
    }
    
     /************************************************************************************/
	//only fetch columns
	public void fetColumnList() {
		try {
			String SQL = "SELECT * FROM `trainer`";
			ResultSet rs = con.createStatement().executeQuery(SQL);
			for (int i = 0; i<rs.getMetaData().getColumnCount(); i++) {
				//We are using non property style for making dynamic table
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());

				col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String> , ObservableValue<String>> () {
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
					TableRow<Trainer> row = new TableRow<>();
					row.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
						Object selectedItems = tblData.getSelectionModel().getSelectedItems().get(0);
						String data1 = selectedItems.toString().split(",")[1].substring(1);
						String data2 = selectedItems.toString().split(",")[0].substring(1);
						
					
						lblStatus.setText(data1);
					
 
					});
					return row;
				});
		
			}

		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}
	}

	/************************************************************************************/

	public void fetRowList() {
                                         data = FXCollections.observableArrayList();
		ResultSet rs;
		try {
			PreparedStatement pre = con.prepareStatement("SELECT * FROM `trainer`");
			rs = pre.executeQuery();
			while (rs.next()) {
				//Iterate Row
				ObservableList row = FXCollections.observableArrayList();
				for (int i =1 ; i<= rs.getMetaData().getColumnCount(); i++) {
					row.add(rs.getString(i));
				}
				//row.set(1, row.get(6));
				System.out.println("Row [1] added " + row);
				//System.out.println("hey yo " + row.get(7));
				data.add(row);
			}
			tblData.setItems(data);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
        
        /************************************************************************************/
    
    	public void fetRowListsearch() {
		data = FXCollections.observableArrayList();
		ResultSet rs;
		try {
                                                         
			PreparedStatement pre = con.prepareStatement("SELECT * FROM `trainer`  WHERE NameT LIKE  ? OR Speciality LIKE ? ");
			pre.setString(1, txtname.getText());
                                                              pre.setString(2,  txtspec.getText());
                                                             
                                                             
			rs = pre.executeQuery();

			while (rs.next()) {
				//Iterate Row
				ObservableList row = FXCollections.observableArrayList();
				for (int i = 1; i<= rs.getMetaData().getColumnCount(); i++) {
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
    
    

 
     

     
        public int countdata()  {
            int count = 0;
            try {
		ste = con.createStatement();
		ResultSet rs = ste.executeQuery("select * from Trainer");
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
         public int countdata2()  {
            int count = 0;
            try {
		ste = con.createStatement();
		ResultSet rs = ste.executeQuery("select * from Workshop");
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
        
     
         }



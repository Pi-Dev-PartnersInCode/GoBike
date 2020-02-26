package Controllers;

import static Controllers.WorkshopPageController.holdID;
import entities.Workshop;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import utils.MyConnection;


public class MemberWorkshopController  implements Initializable {

    @FXML
    private Pane rootpane;
    @FXML
    private Button SUPPRIMER;
    @FXML
    private TableView tblData;
    private ObservableList<ObservableList> data;
    Connection con;
    Statement st;
    private static int idm;
    
        public MemberWorkshopController() {
        con = MyConnection.getInstance().getCn();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        
        	tblData.setRowFactory((Object tv) -> {
					TableRow<Workshop> row = new TableRow<>();
					row.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
						Object selectedItems = tblData.getSelectionModel().getSelectedItems().get(0);
					
						String data2 = selectedItems.toString().split(",")[0].substring(1);
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
alert.setHeaderText("DELETE MEMBER : ");
alert.setContentText("Are you sure !");
Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    int i = Integer.parseInt(data2);

                    supprimerMember(i);
                               fetRowList();
} else {
}
});
return row;
});
fetColumnList();
fetRowList();
    }

    
     /************************************************************************************/
	//only fetch columns
	public void fetColumnList() {
		try {
			String SQL = "select m.idM , u.id , u.prenom , u.nom , u.mail from memberworkshop m  inner join user u on u.id = m.idUser inner join workshop on workshop.idW = m.idW where workshop.idw ="+Integer.parseInt(holdID.value);
			ResultSet rs = con.createStatement().executeQuery(SQL);
			for (int i = 1; i<rs.getMetaData().getColumnCount(); i++) {
				//We are using non property style for making dynamic table
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1).toUpperCase());

				col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String> , ObservableValue<String>> () {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
						return new SimpleStringProperty(param.getValue().get(j).toString());
					}
				});
				tblData.getColumns().removeAll(col);
				tblData.getColumns().addAll(col);

				System.out.println("Column [" + i + "]");
// Create a new RowFactory to handle actions
			
		
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
			PreparedStatement pre = con.prepareStatement("select m.idM , u.id , u.prenom , u.nom , u.mail from memberworkshop m  inner join user u on u.id = m.idUser inner join workshop on workshop.idW = m.idW where workshop.idw ="+Integer.parseInt(holdID.value));
			rs = pre.executeQuery();

			while (rs.next()) {
				//Iterate Row
                                              System.out.println(rs.getInt("m.idM"));
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
        
        /**
     * @param m**********************************************************************************/
    public void supprimerMember(int m) {
        
        String req = "delete from memberworkshop where idM=?";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement = con.prepareStatement(req);
            
            preparedStatement.setInt(1,m);
            preparedStatement.executeUpdate();
        } catch (SQLException er) {
            System.out.println(er.getMessage());
        }
    }

    

}


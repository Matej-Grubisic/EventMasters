package GUI.controllers;

import BE.Coordinator;
import BLL.CoordinatorLogic;
import BLL.EventEvCoLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewCoordinator implements Initializable{
    @FXML
    private Button CreateEvCo;
    @FXML
    private Button DelEvCo;
    @FXML
    private TableColumn<Coordinator, String> EvCoName;
    @FXML
    private TableColumn<Coordinator, Integer> EvCoId;
    @FXML
    private TableView<Coordinator> EvCoTable;
    @FXML
    private Button NewEventCooBTN;
    @FXML
    private Button deleteCoordinatorBTN;

    private IEController iec;

    public void setIEController(IEController iecontroller) {
        this.iec = iecontroller;
    }


    CoordinatorLogic coorLogic = new CoordinatorLogic();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EvCoName.setCellValueFactory(new PropertyValueFactory<>("Username"));
        EvCoId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        EvCoTable.setEditable(true);
        EvCoTable.getItems().addAll(coorLogic.getCoordinatorAll());
        EvCoTable.setEditable(false);

        EvCoTable.setRowFactory( tv -> {
            TableRow<Coordinator> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Coordinator rowData = row.getItem();
                    System.out.println(rowData);
                    iec.addEvCo(rowData);
                }
            });
            return row;
        });

    }

    public void ClickNewEventCooBTN(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NewCoordinator.fxml"));
        Parent root = loader.load();
        NewCoordinator createCoor = loader.getController();
        createCoor.setNewCoordinatorController(this);
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Coordinator coor1 = coorLogic.getCoordinator();

    }

    public void ClickDeleteCoordinatorBTN(ActionEvent actionEvent) throws IOException {
        EventEvCoLogic EventEvCoLogic = new EventEvCoLogic();
        System.out.println(EvCoTable.getSelectionModel().getSelectedItem().getId());
        EventEvCoLogic.delEvCo2(EvCoTable.getSelectionModel().getSelectedItem().getId());
        coorLogic.deleteCordinator(EvCoTable.getSelectionModel().getSelectedItem().getId());

        EvCoTable.setEditable(true);
        EvCoTable.getItems().remove(EvCoTable.getSelectionModel().getSelectedItem());
        EvCoTable.setEditable(false);
    }

    public void UpdateTable(Coordinator coordinator){
        EvCoTable.getItems().add(coordinator);
    }




}

package GUI.controllers;

import BE.Coordinator;
import BLL.CoordinatorLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewCoordinator implements Initializable{
    public Button CreateEvCo;
    public Button DelEvCo;
    public TableColumn<Coordinator, String> EvCoName;
    public TableColumn<Coordinator, Integer> EvCoId;
    public TableView<Coordinator> EvCoTable;
    public Button NewEventCooBTN;
    public Button deleteCoordinatorBTN;

    CoordinatorLogic coorLogic = new CoordinatorLogic();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EvCoName.setCellValueFactory(new PropertyValueFactory<>("Username"));
        EvCoId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        EvCoTable.setEditable(true);
        EvCoTable.getItems().addAll(coorLogic.getCoordinatorAll());
        EvCoTable.setEditable(false);
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
        coorLogic.deleteCordinator(EvCoTable.getSelectionModel().getSelectedItem().getId());
        EvCoTable.setEditable(true);
        EvCoTable.getItems().remove(EvCoTable.getSelectionModel().getSelectedItem());
        EvCoTable.setEditable(false);
    }

    public void UpdateTable(Coordinator coordinator){
        EvCoTable.getItems().add(coordinator);
    }




}

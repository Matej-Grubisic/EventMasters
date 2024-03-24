package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class IEController {
    @FXML
    private ImageView infoImage;
    @FXML
    private Label nameInfo;
    @FXML
    private Label locationInfo;
    @FXML
    private Label timendateInfo;
    @FXML
    private Label descInfo;

    //On click closes info FXML
    public void closeInfo(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }
    //On click open edit FXML.
    public void editEvent(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NewEvent.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
    //Deletes the event.
    public void deleteEvent(ActionEvent actionEvent) {


    }
    //Generates the ticket.
    public void genTicket(ActionEvent actionEvent) {
    }
}

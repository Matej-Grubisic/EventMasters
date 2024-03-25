package GUI.controllers;

import BE.Event;
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
    private Event selectedEvent;



    /**
     * On click closes info FXML
     */
    public void closeInfo(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }


    /**
     * Sets the passed event as a selected event.
     * @param event
     */
    public void setEvent(Event event) {
        this.selectedEvent = event;
        updateUIInfo();
    }


    /**
     * Updates labels with info corresponding to the selected event.
     */
    public void updateUIInfo() {
        if (selectedEvent != null) {
            nameInfo.setText(selectedEvent.getName());
            locationInfo.setText(selectedEvent.getLocation());
            timendateInfo.setText(selectedEvent.getTime());
            descInfo.setText(selectedEvent.getDescription());
        }
    }



    /**
     * On click open edit FXML.
     * @param actionEvent
     * @throws IOException
     */
    public void editEvent(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NewEvent.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    /**
     * Deletes the event.
     * @param actionEvent
     */
    public void deleteEvent(ActionEvent actionEvent) {


    }


    /**
     * Generates the ticket.
     * @param actionEvent
     */
    public void genTicket(ActionEvent actionEvent) {
    }
}

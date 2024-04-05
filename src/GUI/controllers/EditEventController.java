package GUI.controllers;

import BE.Event;
import BLL.EventLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;


public class EditEventController {
    @FXML
    private TextField eventName;
    @FXML
    private TextField eventLoc;
    @FXML
    private TextField eventStart;
    @FXML
    private TextField eventDescription;
    @FXML
    private Button cancelButton;
    @FXML
    private Button loadImageButton;

    @FXML
    private Button saveButton;

    private Event selectedEvent;
    private EventLogic eventLogic = new EventLogic();
    private EventMasterController eventMasterController;
    ArrayList<Image> eventImages = new ArrayList<>();

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
        loadEventData();
    }

    public void setEventMasterController(EventMasterController eventMasterController) {
        this.eventMasterController = eventMasterController;
    }

    @FXML
    private void initialize() {
        cancelButton.setOnAction(event -> closeEditEvent());
        saveButton.setOnAction(event -> saveChanges());
        loadEventData(); // Call method to load event data when FXML is loaded
    }

    private void loadEventData() {
            eventName.setText(selectedEvent.getName());
            eventLoc.setText(selectedEvent.getLocation());
            eventStart.setText(selectedEvent.getTime());
            eventDescription.setText(selectedEvent.getDescription());
    }

    @FXML
    private void saveChanges() {
            String name = eventName.getText();
            String location = eventLoc.getText();
            String time = eventStart.getText();
            String description = eventDescription.getText();

            selectedEvent.updateEvent(time, location, description, name);
            eventLogic.updateEvent(selectedEvent);
            eventMasterController.updateUIMain(eventMasterController.getNewEvents());
            closeEditEvent();

    }

    private void closeEditEvent() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    /*public void saveEvent(ActionEvent actionEvent) throws IOException {
        String time = eventStart.getText();
        String location = eventLoc.getText();
        String description = eventDescription.getText();
        String name = eventName.getText();

        Event event = new Event(time, description, location,name);
        el.updateEvent(event);
        emc.editEvent(event);

        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }*/


    //Closes the create event FXML file.
    public void cancelEvent(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }


    public void loadEventImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {

            Image image = new Image(file.toURI().toString());

            eventImages.add(image);
        }
    }
}

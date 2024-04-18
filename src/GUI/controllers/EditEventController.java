package GUI.controllers;

import BE.Event;
import BLL.EventLogic;
import BLL.Notifications;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
     private Button saveButton;

     private Event selectedEvent;
     private EventLogic eventLogic = new EventLogic();
     private IEController ieController;
     ArrayList<Image> eventImages = new ArrayList<>();

     private Notifications nt = new Notifications();

     public void setSelectedEvent(Event selectedEvent) {
         this.selectedEvent = selectedEvent;
         loadEventData();
     }

     public void setEventInfoController(IEController ieController){
         this.ieController = ieController;
     }

     @FXML
     private void initialize() {
         cancelButton.setOnAction(this::cancelEvent);
         saveButton.setOnAction(event -> {
             try {
                 saveChanges();
             } catch (SQLException e) {
                 nt.showError("Failed to save event changes: " + e.getMessage());
                 e.printStackTrace();
             }
         });
         loadEventData();
     }

     private void loadEventData() {
         if (selectedEvent != null) {
             eventName.setText(selectedEvent.getName());
             eventLoc.setText(selectedEvent.getLocation());
             eventStart.setText(selectedEvent.getTime());
             eventDescription.setText(selectedEvent.getDescription());
         }
     }
     @FXML
     private void saveChanges() throws SQLException {
         String name = eventName.getText();
         String location = eventLoc.getText();
         String time = eventStart.getText();
         String description = eventDescription.getText();
         if (fieldCheck()) {


             selectedEvent.updateEvent(time, location, description, name);
             eventLogic.updateEvent(selectedEvent);

             closeEditEvent();
             ieController.setEvent(selectedEvent);
             ieController.updateUIInfo();
             ieController.updateEvent(eventLogic.getAllEvents());
             nt.showSuccess("Successfully edited the event");
         }else{
             nt.showError("Please fill out all of the fields");
         }
         // Reload the InfoEvent.fxml to reflect the updated event
         //openInfoEventWindow();
         //ieController.reloadEventMasterFXML();
     }


     public boolean fieldCheck(){
         if (!eventStart.getText().isEmpty() && !eventLoc.getText().isEmpty()&&!eventName.getText().isEmpty()){
             return true;
         }
         return false;

     }

     private void closeEditEvent() {
         Stage stage = (Stage) cancelButton.getScene().getWindow();
         stage.close();
     }
     @FXML//Closes the create event FXML file.
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


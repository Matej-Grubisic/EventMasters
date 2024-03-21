package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class NewEventController {
    @FXML
    private TextField eventName;
    @FXML
    private TextField eventLoc;
    @FXML
    private TextField eventStart;
    @FXML
    private TextField eventDescription;
    @FXML
    private Label imageUrl;
    ArrayList<Image> eventImages = new ArrayList<>();
    //Creates event.
    public void createEvent(ActionEvent actionEvent) {
    }
    //Closes the create event FXML file.
    public void cancelEvent(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }
    //On click loades the choosen image.
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

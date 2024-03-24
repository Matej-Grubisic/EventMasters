package GUI.controllers;

import BE.Event;
import BLL.EventLogic;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EventMasterController implements Initializable {

    @FXML
    private Button LogOutBTN;
    @FXML
    private Button createEventBTN;
    @FXML
    private Button NewEventCooBTN;
    @FXML
    private ImageView imageV1,imageV2,imageV3,imageV4,imageV5,imageV6;
    @FXML
    public Label name1,name2,name3,name4,name5,name6;
    @FXML
    public Label date1,date2,date3,date4,date5,date6;
    private List<Event> events = new ArrayList<>();
    EventLogic el=new EventLogic();


    //Sets the events to the ui when fxml is loaded.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            updateUI(el.getAllEvents());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Addes newly created events to the list and put them into update method.
    public void addEvent(Event event) {
        events.add(event);

        updateUI(events);
    }

    //When clicked,sets the stage to Log in FXML.
    public void ClickLogOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LogIn.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }

    //When clicked opens new FXML for creating event.
    public void ClickCreateEvent(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NewEvent.fxml"));
        Parent root = loader.load();
        NewEventController newEventController = loader.getController();
        newEventController.setEventMasterController(this);
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    //On click of the imageview,opens INFO fxml file.
    public void clickedEvent(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/InfoEvent.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void ClickNewEventCooBTN(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NewCoordinator.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void updateUI(List<Event> events) {
        if (events != null) {
            int eventCount = events.size();
            int startIndex = Math.max(0, eventCount - 6);
            for (int i = eventCount - 1; i >= startIndex; i--) {
                Event event = events.get(i);
                final Label nameLabel;
                final Label dateLabel;
                int labelIndex = eventCount - i - 1;
                switch (labelIndex) {
                    case 0:
                        nameLabel = name1;
                        dateLabel = date1;
                        break;
                    case 1:
                        nameLabel = name2;
                        dateLabel = date2;
                        break;
                    case 2:
                        nameLabel = name3;
                        dateLabel = date3;
                        break;
                    case 3:
                        nameLabel = name4;
                        dateLabel = date4;
                        break;
                    case 4:
                        nameLabel = name5;
                        dateLabel = date5;
                        break;
                    case 5:
                        nameLabel = name6;
                        dateLabel = date6;
                        break;
                    default:
                        nameLabel = null;
                        dateLabel = null;
                        break;
                }
                if (nameLabel != null && dateLabel != null) {
                    Platform.runLater(() -> {
                        nameLabel.setText(event.getName());
                        dateLabel.setText(event.getTime());
                    });
                }
            }
        }
    }






}

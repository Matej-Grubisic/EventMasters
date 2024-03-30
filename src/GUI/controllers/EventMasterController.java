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
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class EventMasterController implements Initializable {

    public Button delteCoordinatorBTN;

    public Button EvCoBtn;
    public Button genQRCodeBTN;
    @FXML
    private Button LogOutBTN;
    @FXML
    private Button createEventBTN;
    @FXML
    private Button NewEventCooBTN;
    @FXML
    private ImageView imageV1,imageV2,imageV3,imageV4,imageV5,imageV6;
    @FXML
    private Label name1,name2,name3,name4,name5,name6;
    @FXML
    private Label date1,date2,date3,date4,date5,date6;
    private List<Event> newEvents = new ArrayList<>();
    EventLogic el=new EventLogic();
    private Event selectedEvent;



    /**
     *  Sets the events to the ui when fxml is loaded from database.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            updateUIMain(el.getAllEvents());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * Addes newly created events to the list and updates the UI with it.
     */
    public void addEvent(Event event) {
        newEvents.add(event);

        updateUIMain(newEvents);
    }

    public void removeEvent(Event event){
        newEvents.remove(event);

        updateUIMain(newEvents);
    }


    /**
     * When clicked,sets the stage to Log in FXML.
     * @param actionEvent
     * @throws IOException
     */
    public void ClickLogOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LogIn.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }


    /**
     *  When clicked opens new FXML for creating event.
     */
    public void ClickCreateEvent(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NewEvent.fxml"));
        Parent root = loader.load();
        NewEventController newEventController = loader.getController();
        newEventController.setEventMasterController(this);
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }



    /**
     * Checks what imageview was clicked adn assigns index to every one.
     * Extracts events from the database and reverses the list,since we want to access only 6 last added.
     * Gets the index of the chosen event from reversed list,therefore we get events from 0-5.
     * Lastly loads FXML file sets the event to selected one and calls update method.
     * @param mouseEvent
     * @throws IOException
     * @throws SQLException
     */
    public void clickedEvent(MouseEvent mouseEvent) throws IOException, SQLException {
        ImageView clickedImageView = (ImageView) mouseEvent.getSource();
        int index = selectTheEvent(clickedImageView);

        List<Event> eventsList=el.getAllEvents();
        Collections.reverse(eventsList);

        if (!eventsList.isEmpty() && index >= 0 && index < eventsList.size()) {
            selectedEvent = eventsList.get(index);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/InfoEvent.fxml"));
            Parent root = loader.load();

            IEController controller = loader.getController();
            controller.setEvent(selectedEvent);
            controller.setEventMasterController(this);
            controller.updateUIInfo();

            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
    }


    /**
     *
     * @param clickedImageView
     * @return
     */
    public int selectTheEvent(ImageView clickedImageView){
        int index = -1;
        if (clickedImageView == imageV1) {
            index = 0;
        } else if (clickedImageView == imageV2) {
            index = 1;
        } else if (clickedImageView == imageV3) {
            index = 2;
        } else if (clickedImageView == imageV4) {
            index = 3;
        } else if (clickedImageView == imageV5) {
            index = 4;
        } else if (clickedImageView == imageV6) {
            index = 5;
        }
        return index;
    }




    /**
     * Updates the Main ui elements such as Date and Name labels.
     *  Iterates over the events list with a limit of 6 events
     *  Finds the labels corresponding to index.
     *  Updates labels with event information.
     * @param events
     */
    public void updateUIMain(List<Event> events) {
        if (events != null) {
            Collections.reverse(events);
            for (int i = 0; i < Math.min(events.size(), 6); i++) {
                Event event = events.get(i);
                Label nameLabel;
                Label dateLabel;
                switch (i) {
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




    public void clickViewEvCo(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ViewCoordinator.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void clickGenQRCode(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/QRCode.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

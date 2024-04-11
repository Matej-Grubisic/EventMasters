package GUI.controllers;

import BE.Event;
import BLL.EventLogic;
import BLL.Notifications;
import GUI.controllers.LogInController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class EventMasterController implements Initializable {
    @FXML
    private ImageView imageV1,imageV2,imageV3,imageV4,imageV5,imageV6,imageV7,imageV8,imageV9;
    @FXML
    private Label name1,name2,name3,name4,name5,name6,name7,name8,name9;
    @FXML
    private Label date1,date2,date3,date4,date5,date6,date7,date8,date9;
    private List<Event> newEvents = new ArrayList<>();
    EventLogic el=new EventLogic();
    private Event selectedEvent;
    private List<ImageView> imageViews;
    Notifications nt=new Notifications();


    /**
     *  Sets the events to the ui when fxml is loaded from database.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            updateUIMain(el.getAllEvents());
            imageViews = new ArrayList<>();
            imageViews.add(imageV1);
            imageViews.add(imageV2);
            imageViews.add(imageV3);
            imageViews.add(imageV4);
            imageViews.add(imageV5);
            imageViews.add(imageV6);
            imageViews.add(imageV7);
            imageViews.add(imageV8);
            imageViews.add(imageV9);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * Addes newly created events to the list and updates the UI with it.
     */
    public void addEvent(Event event,ArrayList<Image> eventImages) {
        newEvents.add(event);
        updateUIMainImages(eventImages);
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
        LogInController.loggedUser = 0;
        nt.showSuccess("Successfully logged out");
    }


    /**
     *  When clicked opens new FXML for creating event.
     */
    public void ClickCreateEvent(ActionEvent actionEvent) throws IOException {
        if(LogInController.loggedUser == 1) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NewEvent.fxml"));
            Parent root = loader.load();
            NewEventController newEventController = loader.getController();
            newEventController.setEventMasterController(this);
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        else{
            showError("An Admin cannot create Events");
        }
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

        List<Event> eventsList = el.getAllEvents();
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
        } else if (clickedImageView == imageV7) {
            index = 6;
        } else if (clickedImageView == imageV8) {
            index = 7;
        } else if (clickedImageView == imageV9) {
            index = 8;
        }
        return index;
    }


    ///IMAGES DISPLAYING LOGIC.
    public void updateUIMainImages(ArrayList<Image> images) {
        if (images != null && !images.isEmpty()) {
            Collections.reverse(images);
            for (int i = 0; i < Math.min(images.size(), imageViews.size()); i++) {
                Image image = images.get(i);
                ImageView imageView = imageViews.get(i);
                System.out.println(image);
                System.out.println(imageView);
                if (imageView != null && image != null) {
                    Platform.runLater(() -> imageView.setImage(image));
                }
            }
        }
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
            for (int i = 0; i < Math.min(events.size(), 9); i++) {
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
                    case 6:
                        nameLabel = name7;
                        dateLabel = date7;
                        break;
                    case 7:
                        nameLabel = name8;
                        dateLabel = date8;
                        break;
                    case 8:
                        nameLabel = name9;
                        dateLabel = date9;
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

    /**
     * Update the UI after receiving an updated event from IEController.
     */
    public void updateEventInUI(Event updatedEvent) throws SQLException {
        // Replace the old event with the updated event in the list
        int index = newEvents.indexOf(updatedEvent);
        if (index != -1) {
            System.out.println("aaa");
            List<Event> updatedEvents = el.getAllEvents();
            updateUIMain(updatedEvents); // Update the UI with the modified event list
        }
    }


    public void clickViewEvCo(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ViewCoordinator.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void viewTickets(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TicketsView.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
   /* public void editEvent(Event event, String time, String location, String description, String name) {
        event.updateEvent(time, location, description, name);
        eventLogic.updateEvent(event);
        updateUIMain(newEvents); // Update UI after editing
    }*/
    public List<Event> getNewEvents() {
        return newEvents;
    }
    private void showError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}

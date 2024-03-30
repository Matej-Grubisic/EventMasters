package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TGController implements Initializable {


    @FXML
    private TextField customType;
    @FXML
    private ChoiceBox<String> ticketType;
    public ArrayList<String> types = new ArrayList<>();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        types.add("VIP");
        types.add("+Food");
        types.add("Free Drink");
        types.add("1st Row");
        ticketType.getItems().addAll(types);

    }

    public void generateTicket(ActionEvent actionEvent) {

    }

    public void cancelGeneration(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }

    public void addNewType(ActionEvent actionEvent) {
        String newType = customType.getText().trim();
        if (!newType.isEmpty()) {
            types.add(newType);
            ticketType.getItems().add(newType);
            customType.clear();
        }
    }
}

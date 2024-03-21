package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NewCoordinator {
    public Button CreateBTN;
    public Button CancelBTN;

    public void ClickCreateBTN(ActionEvent actionEvent) {

    }

    public void ClickCancleBTN(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }
}

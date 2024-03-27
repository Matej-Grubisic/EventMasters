package GUI.controllers;

import BLL.CoordinatorLogic;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteCoordinator {
    public Button DeleteBTN;
    public Button CancelBTN;
    public TextField IDFieald;

    CoordinatorLogic coordinatorLogic = new CoordinatorLogic();

    public void ClickDeleteBTN(ActionEvent actionEvent) {
        coordinatorLogic.deleteCordinator(Integer.parseInt(IDFieald.getText()));
        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }

    public void ClickCancelBTN(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }
}

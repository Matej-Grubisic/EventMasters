package BLL;

import javafx.scene.control.Alert;

public class Notifications {

    public void showSuccess(String successMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success message");
        alert.setHeaderText(null);
        alert.setContentText(successMessage);
        alert.showAndWait();
    }

    public void showConfirm(String confirmMessage){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm the action");
        alert.setHeaderText(null);
        alert.setContentText(confirmMessage);
        alert.showAndWait();
    }

    public void showError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}

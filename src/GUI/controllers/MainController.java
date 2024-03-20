package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button btnBig;
    @FXML
    private Button logInBTN;
    @FXML
    private ImageView imageLog;
    @FXML
    private TextField ussernameLbl;
    @FXML
    private TextField passwordLbl;
    Random rand = new Random();
    private static String username = "admin";
    private static String password = "admin1";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("file:src/background.jpg");
        imageLog.setImage(image);
    }

    public void ClickLogInBTN(ActionEvent actionEvent) throws IOException {
        String enteredUsername = ussernameLbl.getText();
        String enteredPassword = passwordLbl.getText();

        if (!enteredUsername.equals(username) || !enteredPassword.equals(password)) {
            showError("Incorrect username or password");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EventMaster.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }

    private void showError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

}

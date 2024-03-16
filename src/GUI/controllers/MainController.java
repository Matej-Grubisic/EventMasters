package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public Button btnBig;
    public Button logInBTN;
    public ImageView imageLog;
    public TextField ussernameLbl;
    public TextField passwordLbl;
    Random rand = new Random();

    public void click(ActionEvent actionEvent) {
        System.out.println("Weeeeeeeee!");
        btnBig.setLayoutX(rand.nextDouble(700));
        btnBig.setLayoutY(rand.nextDouble(450));
    }

    public void ClickLogInBTN(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EventMaster.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("file:src/background.jpg");
        imageLog.setImage(image);
    }
}

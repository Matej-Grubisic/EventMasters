package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public void ClickLogInBTN(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("file:src/background.jpg");
        imageLog.setImage(image);
    }
}

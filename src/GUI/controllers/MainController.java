package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.Random;

public class MainController {
    public Button btnBig;
    public Button logInBTN;
    Random rand = new Random();

    public void click(ActionEvent actionEvent) {
        System.out.println("Weeeeeeeee!");
        btnBig.setLayoutX(rand.nextDouble(700));
        btnBig.setLayoutY(rand.nextDouble(450));
    }

    public void ClickLogInBTN(ActionEvent actionEvent) {
    }
}

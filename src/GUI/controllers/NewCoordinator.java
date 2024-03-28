package GUI.controllers;

import BE.Coordinator;
import BLL.CoordinatorLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class NewCoordinator {
    @FXML
    private Button CreateBTN;
    @FXML
    private Button CancelBTN;
    @FXML
    private TextField CooPassword;
    @FXML
    private TextField CooUserName;

    CoordinatorLogic coordinatorLogic = new CoordinatorLogic();
    private ViewCoordinator viewcontroller;

    public void ClickCreateBTN(ActionEvent actionEvent) throws NoSuchAlgorithmException {
        String username= CooUserName.getText();
        String enteredPassword= CooPassword.getText();

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(enteredPassword.getBytes(StandardCharsets.UTF_8));

        //converts the hashed pass to hexadecimal
        enteredPassword = bytesToHex(encodedhash);

        Coordinator coordinator= new Coordinator(username, enteredPassword);
        coordinatorLogic.createCoordinator(coordinator);

        Coordinator newCoor = coordinatorLogic.getCoordinator();
        viewcontroller.UpdateTable(newCoor);

        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();


    }

    public void ClickCancleBTN(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public void setNewCoordinatorController(ViewCoordinator viewCoordinator) {
        this.viewcontroller = viewCoordinator;
    }
}

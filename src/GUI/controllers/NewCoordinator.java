package GUI.controllers;

import BE.Coordinator;
import BLL.CoordinatorLogic;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class NewCoordinator {
    public Button CreateBTN;
    public Button CancelBTN;
    public TextField CooPassword;
    public TextField CooUserName;

    CoordinatorLogic coordinatorLogic = new CoordinatorLogic();

    public void ClickCreateBTN(ActionEvent actionEvent) throws NoSuchAlgorithmException {
        String username= CooUserName.getText();
        String enteredPassword= CooPassword.getText();

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(enteredPassword.getBytes(StandardCharsets.UTF_8));

        //converts the hashed pass to hexadecimal
        enteredPassword = bytesToHex(encodedhash);

        Coordinator coordinator= new Coordinator(username, enteredPassword);
        coordinatorLogic.createCoordinator(coordinator);


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
}

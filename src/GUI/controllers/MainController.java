package GUI.controllers;

import BE.Admin;
import BE.Coordinator;
import DAL.AdminDAO;
import DAL.CoordinatorDAO;
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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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


    //When the FXML file is loaded sets background image to the one mentioned.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("file:src/background.jpg");
        imageLog.setImage(image);
    }


    public void ClickLogInBTN(ActionEvent actionEvent) throws IOException, NoSuchAlgorithmException {
        AdminDAO AdminDAO = new AdminDAO();
        Admin adminAuth = AdminDAO.getAdmin();

        CoordinatorDAO CoordinatorDAO = new CoordinatorDAO();
        Coordinator coordinatorAuth = CoordinatorDAO.getCoordinator();

        String enteredUsername = ussernameLbl.getText();
        String enteredPassword = passwordLbl.getText();

        //password gets hashed
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(enteredPassword.getBytes(StandardCharsets.UTF_8));

        //converts the hashed pass to hexadecimal
        enteredPassword = bytesToHex(encodedhash);

        //checks if everything is alright
        if (!enteredUsername.equals(adminAuth.getUsername()) || !enteredPassword.equals(adminAuth.getPassword())) {
            showError("Incorrect username or password");
            return;
        }
        //Need to be fixed!!!!
        /*else if (!enteredUsername.equals(coordinatorAuth.getUsername()) || !enteredPassword.equals(coordinatorAuth.getPassword())) {
            showError("Incorrect username or password");
            return;
        }*/
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EventMaster.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }

    //Error message to display the user in case of wrong username or password.
    private void showError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
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

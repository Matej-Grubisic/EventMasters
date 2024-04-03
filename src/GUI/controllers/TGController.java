package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class TGController implements Initializable {

    @FXML
    private TextField quantityField;
    @FXML
    private TextField emailField;
    @FXML
    private ChoiceBox<String> ticketType;
    public ArrayList<String> types = new ArrayList<>();
    private ContextMenu emailSuggestionsMenu;
    private List<String> emailDomains = new ArrayList<>();




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        types.add("VIP");
        types.add("Free Drink");
        types.add("Regular");
        ticketType.getItems().addAll(types);

        quantityField.setText("1");

        suggestEmail();

    }

    public void generateTicket(ActionEvent actionEvent) {
        if(fieldCheck()){

            //PUT GENERATE LOGIC HERE


        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Generating error");
            alert.setHeaderText(null);
            alert.setContentText("Fields shouldn't be empty or 0");
            alert.showAndWait();
        }

    }

    public void cancelGeneration(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }

    //Logic for suggesting email domain to user once he types @
    public void suggestEmail(){
        emailDomains.add("gmail.com");
        emailDomains.add("yahoo.com");
        emailDomains.add("hotmail.com");
        emailDomains.add("outlook.com");

        emailSuggestionsMenu = new ContextMenu();
        emailField.setContextMenu(emailSuggestionsMenu);

        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            int atIndex = newValue.lastIndexOf("@");
            if (atIndex >= 0) {
                String partialDomain = "";
                if (atIndex < newValue.length() - 1) {
                    partialDomain = newValue.substring(atIndex + 1);
                }

                emailSuggestionsMenu.getItems().clear();
                for (String domain : emailDomains) {
                    if (domain.startsWith(partialDomain)) {
                        MenuItem item = new MenuItem(domain);
                        item.setOnAction(event -> {
                            String prefix = newValue.substring(0, atIndex + 1);
                            emailField.setText(prefix + domain);
                            emailField.requestFocus();
                            emailField.positionCaret(emailField.getText().length());
                        });
                        emailSuggestionsMenu.getItems().add(item);
                    }
                }

                if (!emailSuggestionsMenu.getItems().isEmpty()) {
                    double x = emailField.localToScreen(0, 0).getX();
                    double y = emailField.localToScreen(0, 0).getY() + emailField.getHeight();
                    emailSuggestionsMenu.show(emailField, x, y);
                } else {
                    emailSuggestionsMenu.hide();
                }
            } else {
                emailSuggestionsMenu.hide();
            }
        });
    }

    //Checks the fields.
    public boolean fieldCheck(){
        if(!emailField.getText().isEmpty()&& !quantityField.getText().equals("0")
                && !quantityField.getText().isEmpty()
                && !ticketType.getValue().isEmpty()){
            return true;
        }
        return false;
    }





}

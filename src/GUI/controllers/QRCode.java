package GUI.controllers;

import BE.Event;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QRCode {
    public TextField eventName;
    public TextField eventLoc;
    public TextField eventStart;
    public TextField eventDescription;
    public Label imageUrl;

    ArrayList info=new ArrayList<>();


    public void createQRCode(ActionEvent actionEvent) throws WriterException, IOException {

        String time = eventStart.getText();
        String location = eventLoc.getText();
        String description = eventDescription.getText();
        String name = eventName.getText();

        Event event = new Event(time, description, location,name);

        System.out.println(event.getName());

        String data= String.valueOf(event);
        String path= "QR";

        BitMatrix matrix= new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE,500, 500);

        String fileName = "qr_code.jpg";
        Path filePath = Paths.get(path, fileName);

        MatrixToImageWriter.writeToPath(matrix, "jpg", filePath);

        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("QR Code is created");
        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        }
    }

    public void cancelQRCode(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }
}

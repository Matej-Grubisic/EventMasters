package GUI.controllers;

import java.io.*;
import BE.Coordinator;
import BE.Event;
import BE.Ticket;
import BLL.EventLogic;
import BLL.Notifications;
import BLL.TicketLogic;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.sun.java.accessibility.util.EventID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.UUID;
import com.itextpdf.io.image.*;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import static com.itextpdf.layout.properties.TextAlignment.CENTER;


public class TicketsViewController implements Initializable {


    public TableColumn<Ticket, Integer> eventId;
    @FXML
    private TableColumn<Ticket, String> uuidRow;


    @FXML
    private TableColumn<Ticket, String> emailRow;



    @FXML
    private TableColumn<Ticket, String> typeRow;


    @FXML
    private TableView <Ticket> ticketTable;
    Notifications nt=new Notifications();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TicketLogic ticketLogic = new TicketLogic();


        emailRow.setCellValueFactory(new PropertyValueFactory<>("email"));
        typeRow.setCellValueFactory(new PropertyValueFactory<>("type"));
        uuidRow.setCellValueFactory(new PropertyValueFactory<>("UUID"));
        eventId.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        ticketTable.setEditable(true);
        ticketTable.getItems().addAll(ticketLogic.getAllTicket());
        ticketTable.setEditable(false);
    }

    public void printTicket(ActionEvent actionEvent) throws IOException, WriterException {
        if (LogInController.loggedUser == 1) {
            EventLogic eventLogic = new EventLogic();
            Event event = eventLogic.getEvent(ticketTable.getSelectionModel().getSelectedItem().getEventId());
            String eventName = event.getName();
            String time = event.getTime();
            String location = event.getLocation();
            String notes = event.getDescription();
            String uuid = ticketTable.getSelectionModel().getSelectedItem().getUUID();
            String email = ticketTable.getSelectionModel().getSelectedItem().getEmail();
            String type = ticketTable.getSelectionModel().getSelectedItem().getType();

            String currDir = System.getProperty("user.dir");

            String pdfPath = currDir + "/src/TicketsPdf/Ticket" + "_" + email + "_" + eventName + ".pdf";

            PdfWriter writer = new PdfWriter(pdfPath);

            PdfDocument pdfDoc = new PdfDocument(writer);


            Document doc = new Document(pdfDoc);
            //qr code
            String data = uuid + " " + email + " " + type + " " + eventName + " " + time + " " + location + " " + notes;
            String path = "./src/Images";

            BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 250, 250);

            String fileName = "qr_code.jpg";
            Path filePath = Paths.get(path, fileName);

            MatrixToImageWriter.writeToPath(matrix, "jpg", filePath);
            ImageData imageData = ImageDataFactory.create(currDir + "/src/Images/qr_code.jpg");
            Image qr = new Image(imageData);


            //barcode
            String data1 = uuid + " " + email;
            String path1 = "./src";

            BitMatrix matrix1 = new MultiFormatWriter().encode(data1, BarcodeFormat.CODE_128, 50, 100);

            String fileName1 = "Images/barcode.jpg";
            Path filePath1 = Paths.get(path1, fileName1);

            MatrixToImageWriter.writeToPath(matrix1, "jpg", filePath1);
            ImageData imageData1 = ImageDataFactory.create(currDir + "/src/Images/barcode.jpg");
            Image barcode = new Image(imageData1);

            qr.setHorizontalAlignment(HorizontalAlignment.CENTER);
            qr.setMarginLeft(115);

            barcode.setHorizontalAlignment(HorizontalAlignment.LEFT);
            barcode.setRotationAngle(1.570796);
            barcode.setMarginTop(-475);

            Paragraph eventNameP = new Paragraph(eventName);
            eventNameP.setFontSize(35);
            eventNameP.setTextAlignment(CENTER);
            eventNameP.setMarginLeft(115);
            eventNameP.setMarginTop(125);

            Paragraph timeP = new Paragraph("Time & Date: " + time);
            timeP.setFontSize(18);
            timeP.setTextAlignment(CENTER);
            timeP.setMarginLeft(115);

            Paragraph locationP = new Paragraph("Location: " + location);
            locationP.setFontSize(18);
            locationP.setTextAlignment(CENTER);
            locationP.setMarginLeft(115);

            Paragraph notesP = new Paragraph("Notes: " + notes);
            notesP.setFontSize(18);
            notesP.setTextAlignment(CENTER);
            notesP.setMarginLeft(115);

            doc.add(eventNameP);
            doc.add(timeP);
            doc.add(locationP);
            doc.add(notesP);
            doc.add(qr);
            doc.add(barcode);

            doc.close();
            nt.showSuccess("Successfully printed the ticket");
            // Closing the document
            System.out.println("Image added successfully and PDF file created!");
        }
        else {
            showError("An Admin cannot print tickets");
        }
    }

    private void showError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }


}

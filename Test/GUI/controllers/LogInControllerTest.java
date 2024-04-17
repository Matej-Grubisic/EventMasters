package GUI.controllers;

import BE.Admin;
import com.itextpdf.io.util.IntHashtable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LogInControllerTest {


    //Testing if the Information is true
    @Test
    void clickLogInBTNTrue() {
        String enteredUsername = "Admin";
        String enteredPassword = "114663ab194edcb3f61d409883ce4ae6c3c2f9854194095a5385011d15becbef";

        Admin admin = new Admin(0, "Admin", "114663ab194edcb3f61d409883ce4ae6c3c2f9854194095a5385011d15becbef");

        String password = "114663ab194edcb3f61d409883ce4ae6c3c2f9854194095a5385011d15becbef";

        assertEquals(enteredUsername, admin.getUsername());
        assertEquals(enteredPassword, password);

    }


    //Testing if the Information is false
    @Test
    void clickLogInBTNFalse(){
        String enteredUsername = "Admin";
        String enteredPassword = "114663ab194edcbsdfkjw61d409883ce4ae6c3c2f9854194095a5385011d15becbef";

        Admin admin = new Admin(0, "Admin", "114663ab194edcb3f61d409883ce4ae6c3c2f9854194095a5385011d15becbef");

        String password = "114663ab194edcb3f61d409883ce4ae6c3c2f9854194095a5385011d15becbef";

        //showError("Incorrect username or password");
        if(enteredUsername.equals(admin.getUsername())){
            assertNotEquals(enteredPassword, password);
        }
        else{
            assertNotEquals(enteredUsername, admin.getUsername());
        }



    }

}
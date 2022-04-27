package at.jku.se.diary;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SelectFileLocationController {
    private String entryFileLocation;
    private String photoFileLocation;

    @FXML
    private Button btnSaveFileLocation;

    @FXML
    private TextField TxtEntryFileLocation;

    @FXML
    private TextField TxtPhotoFileLocation;

    @FXML
    void saveFileLocation(MouseEvent event) throws IOException {
        //save the selected Location
        entryFileLocation = TxtEntryFileLocation.getText();
        photoFileLocation = TxtPhotoFileLocation.getText();

        //switch to JournalList Page
        Scene scene = btnSaveFileLocation.getScene();
        URL url = new File("src/main/java/at/jku/se/diary/JournalList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

}

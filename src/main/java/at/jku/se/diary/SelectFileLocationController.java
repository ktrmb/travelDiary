package at.jku.se.diary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SelectFileLocationController {

    @FXML
    private Rectangle BtnOk;

    @FXML
    private ImageView btnCalendar;

    @FXML
    private ImageView btnJournalList;

    @FXML
    private ImageView btnMap;

    @FXML
    private ImageView btnNewEntry;

    @FXML
    private TextField entryLocation;

    @FXML
    private TextField photoLocation;

    @FXML
    void showCalendarPage(MouseEvent event) {

    }

    @FXML
    void showJournalList(MouseEvent event) throws IOException {
        Scene scene = BtnOk.getScene();
        URL url = new File("src/main/java/at/jku/se/diary/JournalList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    @FXML
    void showMapPage(MouseEvent event) {

    }

    @FXML
    void showNewEntryPage(MouseEvent event) throws IOException{
        Scene scene = btnNewEntry.getScene();
        URL url = new File("src/main/java/at/jku/se/diary/DiaryEntryView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

}

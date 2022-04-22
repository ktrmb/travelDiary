package at.jku.se.diary;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JournalListController {
    @FXML
    private ImageView btnCalendar;

    @FXML
    private ImageView btnJournalList;

    @FXML
    private ImageView btnMap;

    @FXML
    private ImageView btnNewEntry;

    @FXML
    private Button btnSFL;

    @FXML
    void showCalendarPage(MouseEvent event) {

    }

    @FXML
    void showMapPage(MouseEvent event) {

    }


    //Scene wechseln auf NewDiaryEntry
    @FXML
    void showNewEntryPage(MouseEvent event) throws IOException {
        Scene scene = btnNewEntry.getScene();
        URL url = new File("src/main/java/at/jku/se/diary/DiaryEntryView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

}




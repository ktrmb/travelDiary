package at.jku.se.diary;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JournalListController {

    @FXML
    private TableView<ShortDiaryEntry> TVjournalList;

    @FXML
    private TableColumn<ShortDiaryEntry, String> tvDate;

    @FXML
    private TableColumn<ShortDiaryEntry, String> tvTitle;

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

    @FXML
    void showSelectFileLocation(MouseEvent event) throws IOException{
        Scene scene = btnSFL.getScene();
        URL url = new File("src/main/java/at/jku/se/diary/SelectFileLocation.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    // Load Table - all Entry's

    @FXML
    void showTV(MouseEvent event) {
        Diary d = HelloFX.diary;
        loadTableView(d);
    }

    public void loadTableView(Diary diary) {
        tvTitle.setCellValueFactory(new PropertyValueFactory<ShortDiaryEntry, String >("title"));
        tvDate.setCellValueFactory(new PropertyValueFactory<ShortDiaryEntry, String >("date"));

        ObservableList<ShortDiaryEntry> list = FXCollections.observableArrayList(diary.getShortEntryList());
        TVjournalList.setItems(list);
    }


}




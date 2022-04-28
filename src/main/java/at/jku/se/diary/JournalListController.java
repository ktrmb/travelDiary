package at.jku.se.diary;


import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

public class JournalListController {

    public DiaryEntry selectedEntry;

    @FXML
    private TableView<DiaryEntry> TVjournalList;

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

    // Load Table - all Entry's
    public void initialize () {
        Diary diary = HelloFX.diary;

        TableColumn<DiaryEntry, String> titel = new TableColumn<DiaryEntry, String>("Titel");
        titel.setCellValueFactory(c -> new SimpleStringProperty((c.getValue().getTitle())));

        TableColumn<DiaryEntry, LocalDate> date = new TableColumn<DiaryEntry, LocalDate>("Date");
        date.setCellValueFactory(c -> new SimpleObjectProperty<LocalDate>(c.getValue().getDate()));

        TVjournalList.getColumns().addAll(titel, date);

        ObservableList<DiaryEntry> diaryE = FXCollections.observableArrayList(diary.getEntryList());
        TVjournalList.setItems(diaryE);
    }

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

    @FXML
    void showSelectedEntry(MouseEvent event) throws IOException{
        selectedEntry = TVjournalList.getSelectionModel().getSelectedItem();

        Scene scene = null;
        URL url = new File("src/main/java/at/jku/se/diary/EntryView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

}




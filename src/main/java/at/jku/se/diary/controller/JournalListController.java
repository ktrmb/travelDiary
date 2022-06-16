package at.jku.se.diary.controller;

import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.SceneSwitch;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

public class JournalListController {

    public DiaryEntry selectedEntry;

    @FXML
    private TableView<DiaryEntry> tVjournalList;
    @FXML
    private ImageView btnJournalList;
    @FXML
    private ImageView btnMap;
    @FXML
    private ImageView btnNewEntry;
    @FXML
    private Button btnSFL;
    @FXML
    private Button btnShowEntry;
    @FXML
    private TextField filterTitle;
    @FXML
    private ComboBox<String> filterCategory;
    @FXML
    private DatePicker filterDateFrom;
    @FXML
    private DatePicker filterDateTo;
    @FXML
    private ComboBox<String> filterStars;
    @FXML
    private TextField filterStructInfo;
    @FXML
    private TextField filterText;


        public void initialize() {
        Diary diary = HelloFX.diary;

        TableColumn<DiaryEntry, String> titel = new TableColumn<DiaryEntry, String>("Titel");
        titel.setCellValueFactory(c -> new SimpleStringProperty((c.getValue().getTitle())));

        TableColumn<DiaryEntry, LocalDate> date = new TableColumn<DiaryEntry, LocalDate>("Date");
        date.setCellValueFactory(c -> new SimpleObjectProperty<LocalDate>(c.getValue().getDate()));

        tVjournalList.getColumns().addAll(titel, date);

        ObservableList<DiaryEntry> diaryE = FXCollections.observableArrayList(diary.getEntryList());
        tVjournalList.setItems(diaryE);

        //FILTERN: ---------------
        //1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<DiaryEntry> titleFilterList = new FilteredList<DiaryEntry>(diaryE, p -> true);
        //2. Set the filter Predicate whenever the filter changes.
        filterTitle.textProperty().addListener((observable, oldValue, newValue) -> {
            titleFilterList.setPredicate(diaryEntry -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (diaryEntry.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true; //Filter matches title.
                }
                return false; //Filter not match.
            });
        });
        SortedList<DiaryEntry> sortedEntryList = new SortedList<>(titleFilterList);
        sortedEntryList.comparatorProperty().bind(tVjournalList.comparatorProperty());
        tVjournalList.setItems(sortedEntryList);
    }
    @FXML
    void applyFilter(MouseEvent event) {

    }

    @FXML
    void deleteFilter(MouseEvent event) {
    }


    @FXML
    void showMapPage(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("map", btnMap.getScene());
        s.switchScene();
    }

    @FXML
    void showNewEntryPage(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("newEntry", btnNewEntry.getScene());
        s.switchScene();
    }

    @FXML
    void showSelectFileLocation(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("fileLocation", btnSFL.getScene());
        s.switchScene();
    }

    @FXML
    void saveSelectedItem(MouseEvent event) throws IOException {
        selectedEntry = tVjournalList.getSelectionModel().getSelectedItem();
    }

    @FXML
    void showSelectedEntry(MouseEvent event) {
        try {
            Scene scene = btnShowEntry.getScene();
            URL url = new File("src/main/java/at/jku/se/diary/view/EntryEdit.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            EntryEditController eController = loader.getController();
            eController.setSelectedEntry(selectedEntry);

            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




package at.jku.se.diary.controller;

import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.SceneSwitch;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
    public SortedList<DiaryEntry> sortedList;
    public Diary diary = HelloFX.diary;

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
    private ComboBox<String> filterCategoryBox;
    @FXML
    private DatePicker filterDateFrom;
    @FXML
    private DatePicker filterDateTo;
    @FXML
    private ComboBox<String> filterStarsBox;
    @FXML
    private TextField filterStructInfo;
    @FXML
    private TextField filterText;


        public void initialize() {


        TableColumn<DiaryEntry, String> titel = new TableColumn<DiaryEntry, String>("Titel");
        titel.setCellValueFactory(c -> new SimpleStringProperty((c.getValue().getTitle())));

        TableColumn<DiaryEntry, LocalDate> date = new TableColumn<DiaryEntry, LocalDate>("Date");
        date.setCellValueFactory(c -> new SimpleObjectProperty<LocalDate>(c.getValue().getDate()));

        tVjournalList.getColumns().addAll(titel, date);

        ObservableList<DiaryEntry> diaryE = FXCollections.observableArrayList(diary.getEntryList());
        tVjournalList.setItems(diaryE);

        filterStarsBox.getItems().addAll("-", "1.0", "2.0", "3.0", "4.0", "5.0");
        filterCategoryBox.getItems().add("-");
        filterCategoryBox.getItems().addAll(diary.getCategories());

        //Title filtern (wird in Diary Klasse gemacht)
        SortedList<DiaryEntry> sortedEntryListTitle = new SortedList<>(diary.filterTitle(diaryE, filterTitle.textProperty()));
        sortedEntryListTitle.comparatorProperty().bind(tVjournalList.comparatorProperty());
        tVjournalList.setItems(sortedEntryListTitle);

        //Text filtern
        SortedList<DiaryEntry> sortedEntryListText = new SortedList<>(diary.filterText(sortedEntryListTitle, filterText.textProperty()));
        sortedEntryListText.comparatorProperty().bind(tVjournalList.comparatorProperty());
        tVjournalList.setItems(sortedEntryListText);


        //Struct Info Text filtern
        SortedList<DiaryEntry> sortedEntryListStructText = new SortedList<>(diary.filterStructText(sortedEntryListText, filterStructInfo.textProperty()));
        sortedEntryListStructText.comparatorProperty().bind(tVjournalList.comparatorProperty());
        tVjournalList.setItems(sortedEntryListStructText);
        sortedList = sortedEntryListStructText;

    }

    @FXML
    void filterCategory(ActionEvent event) {
        SortedList<DiaryEntry> sortedListCategory = new SortedList<>(diary.filterCategory(sortedList, filterCategoryBox.getSelectionModel().getSelectedItem()));
        sortedListCategory.comparatorProperty().bind(tVjournalList.comparatorProperty());
        tVjournalList.setItems(sortedListCategory);
    }
    @FXML
    void filterStars(ActionEvent event) {
        SortedList<DiaryEntry> sortedListStars = new SortedList<>(diary.filterStars(sortedList, filterStarsBox.getSelectionModel().getSelectedItem()));
        sortedListStars.comparatorProperty().bind(tVjournalList.comparatorProperty());
        tVjournalList.setItems(sortedListStars);
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
        SceneSwitch s = new SceneSwitch("DiaryEntryView", btnNewEntry.getScene());
        s.switchScene();
    }

    @FXML
    void showSelectFileLocation(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("SelectFileLocation", btnSFL.getScene());
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




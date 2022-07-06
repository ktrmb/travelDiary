package at.jku.se.diary.controller;

import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryDB;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.SceneSwitch;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class JournalListController {

    public DiaryEntry selectedEntry;
    public Diary diary = HelloFX.diary;
    ObservableList<DiaryEntry> diaryE;
    public DiaryDB diaryDB = HelloFX.diaryDB;
    public File diaryFile = HelloFX.diaryFile;


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
    private DatePicker filterDateFromBox;
    @FXML
    private DatePicker filterDateToBox;
    @FXML
    private ComboBox<String> filterStarsBox;
    @FXML
    private TextField filterStructInfo;
    @FXML
    private TextField filterText;
    @FXML
    private TextField applyHelpTextBox;

    public void initialize() throws JAXBException {
        filterStarsBox.getItems().addAll("Stars", "1.0", "2.0", "3.0", "4.0", "5.0");
        filterCategoryBox.getItems().add("Category");
        filterCategoryBox.getItems().addAll(diary.getCategories());


        TableColumn<DiaryEntry, String> titel = new TableColumn<DiaryEntry, String>("Titel");
        titel.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId() + " " +  c.getValue().getTitle()));

        TableColumn<DiaryEntry, LocalDate> date = new TableColumn<DiaryEntry, LocalDate>("Date");
        date.setCellValueFactory(c -> new SimpleObjectProperty<LocalDate>(c.getValue().getDate()));

        tVjournalList.getColumns().addAll(titel, date);

        diaryE = FXCollections.observableArrayList(diary.getEntryList());
        tVjournalList.setItems(diaryE);

        applyHelpTextBox.setVisible(false);
        filterTitle.setText("");
        filterText.setText("Text");
        filterDateFromBox.setValue(LocalDate.of(2022, 06, 01));
        filterDateToBox.setValue(LocalDate.of(2022, 07, 31));
        filterCategoryBox.setValue("Category");
        filterStructInfo.setText("Structured Info");
        filterStarsBox.setValue("Stars");


        FilteredList<DiaryEntry> filteredList = new FilteredList<>(diaryE);
        tVjournalList.setItems(filteredList);
        filteredList.predicateProperty().bind(Bindings.createObjectBinding(() -> diaryEntry ->
                        ((diaryEntry.getTitle().toLowerCase().contains(filterTitle.getText().toLowerCase())) || (filterTitle.getText().isEmpty()))
                                && ((applyHelpTextBox.getText().equals("")))
                                && ((diaryEntry.getDiaryText().toLowerCase().contains(filterText.getText().toLowerCase())) || (filterText.getText().isEmpty()) || filterText.getText().equals("Text"))
                                && (diaryEntry.getDate().isAfter(filterDateFromBox.getValue()) || (diaryEntry.getDate().isEqual(filterDateFromBox.getValue())))
                                && (diaryEntry.getDate().isBefore(filterDateToBox.getValue()) || diaryEntry.getDate().isEqual(filterDateToBox.getValue()))
                                && ((diary.filterCategories(diaryEntry, filterCategoryBox.getValue())) || (filterCategoryBox.getValue().equals("Category")))
                                && ((diary.filterStructInfoText(diaryEntry, filterStructInfo.getText())) || (filterStructInfo.getText().isEmpty()) || (filterStructInfo.getText().equals("Structured Info")))
                                && (diary.filterStars(diaryEntry, filterStarsBox.getValue()) || (filterStarsBox.getValue().equals("Stars"))),
                applyHelpTextBox.textProperty(),
                filterTitle.textProperty(),
                filterText.textProperty(),
                filterDateFromBox.converterProperty(),
                filterDateToBox.converterProperty(),
                filterCategoryBox.converterProperty(),
                filterStructInfo.textProperty(),
                filterStarsBox.converterProperty()
        ));
    }

    @FXML
    void filterDateFrom(ActionEvent event) {
        applyHelpTextBox.setText("");
    }

    @FXML
    void filterDateTo(ActionEvent event) {
        applyHelpTextBox.setText("");
    }

    @FXML
    void filterCategory(ActionEvent event) {
        applyHelpTextBox.setText("");
    }
    @FXML
    void filterStars(ActionEvent event) {
        applyHelpTextBox.setText("");
    }

    @FXML
    void showMapPage(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("MapView", btnMap.getScene());
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
            SceneSwitch s = new SceneSwitch("EntryEdit", btnShowEntry.getScene());
            s.switchSceneEntryEditController(selectedEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




package at.jku.se.diary.controller;

import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryEntry;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
/**
 *
 * this class loads the entry overview and the table filters
 * @author Team E
 *
 */
public class JournalListController {

    public DiaryEntry selectedEntry;
    public Diary diary = HelloFX.diary;
    ObservableList<DiaryEntry> diaryE;

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
    @FXML
    private Button btnResetFilter;

    /**
     * loads table with entries uses default Filter, respectively applies used filters from the user immediately
     */
    public void initialize() {
        filterStarsBox.getItems().addAll("Stars", "1.0", "2.0", "3.0", "4.0", "5.0");
        filterCategoryBox.getItems().add("Category");
        filterCategoryBox.getItems().addAll(diary.getCategories());

        TableColumn<DiaryEntry, String> titel = new TableColumn<>("Titel");
        titel.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId() + " " + c.getValue().getTitle()));

        TableColumn<DiaryEntry, LocalDate> date = new TableColumn<>("Date");
        date.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getDate()));

        tVjournalList.getColumns().addAll(titel, date);

        diaryE = FXCollections.observableArrayList(diary.getEntryList());
        tVjournalList.setItems(diaryE);

        applyHelpTextBox.setVisible(false);
        filterTitle.setText("");
        filterText.setText("Text");
        filterDateFromBox.setValue(LocalDate.of(2022, 1, 1));
        filterDateToBox.setValue(LocalDate.of(2022, 12, 31));
        filterCategoryBox.setValue("Category");
        filterStructInfo.setText("Structured Info");
        filterStarsBox.setValue("Stars");

        FilteredList<DiaryEntry> filteredList = new FilteredList<>(diaryE);
        tVjournalList.setItems(filteredList);
        filteredList.predicateProperty().bind(Bindings.createObjectBinding(() -> diaryEntry ->
                        ((diaryEntry.getTitle().toLowerCase().contains(filterTitle.getText().toLowerCase()))
                                || (filterTitle.getText().isEmpty()))
                                && ((applyHelpTextBox.getText().equals("")))
                                && ((diaryEntry.getDiaryText().toLowerCase().contains(filterText.getText().toLowerCase())) ||
                                (filterText.getText().isEmpty()) || filterText.getText().equals("Text"))
                                && (diaryEntry.getDate().isAfter(filterDateFromBox.getValue()) ||
                                (diaryEntry.getDate().isEqual(filterDateFromBox.getValue())))
                                && (diaryEntry.getDate().isBefore(filterDateToBox.getValue()) ||
                                diaryEntry.getDate().isEqual(filterDateToBox.getValue()))
                                && ((diary.filterCategories(diaryEntry, filterCategoryBox.getValue())) ||
                                (filterCategoryBox.getValue().equals("Category")))
                                && ((diary.filterStructInfoText(diaryEntry, filterStructInfo.getText())) ||
                                (filterStructInfo.getText().isEmpty()) || (filterStructInfo.getText().equals("Structured Info")))
                                && (diary.filterStars(diaryEntry, filterStarsBox.getValue())
                                || (filterStarsBox.getValue().equals("Stars"))),
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

    /**
     * sets all filters to default values
     * @param event button clicked to reset all filters
     */
    @FXML
    void resetFilter(ActionEvent event) {
        filterTitle.setText("");
        filterText.setText("Text");
        filterDateFromBox.setValue(LocalDate.of(2022, 1, 1));
        filterDateToBox.setValue(LocalDate.of(2022, 12, 31));
        filterCategoryBox.setValue("Category");
        filterStructInfo.setText("Structured Info");
        filterStarsBox.setValue("Stars");
    }

    @FXML
    private void filterDateFrom(ActionEvent event) {
        applyHelpTextBox.setText("");
    }
    @FXML
    private void filterDateTo(ActionEvent event) {
        applyHelpTextBox.setText("");
    }

    @FXML
    private void filterCategory(ActionEvent event) {
        applyHelpTextBox.setText("");
    }
    @FXML
    private void filterStars(ActionEvent event) {
        applyHelpTextBox.setText("");
    }

    /**
     * @param event button clicked, loads "Map" scene
     */
    @FXML
    void showMapPage(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("MapView", btnMap.getScene());
        s.switchScene();
    }
    /**
     * @param event button clicked, loads "NewEntry" scene
     */
    @FXML
    void showNewEntryPage(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("DiaryEntryView", btnNewEntry.getScene());
        s.switchScene();
    }
    /**
     * @param event button clicked, loads "SelectFileLocation" scene
     */
    @FXML
    void showSelectFileLocation(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("SelectFileLocation", btnSFL.getScene());
        s.switchScene();
    }
    /**
     * @param event saves selected table item
     */
    @FXML
    void saveSelectedItem(MouseEvent event) {
        selectedEntry = tVjournalList.getSelectionModel().getSelectedItem();
    }
    /**
     * @param event button clicked, shows selected entry
     */
    @FXML
    void showSelectedEntry(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("EntryEdit", btnShowEntry.getScene());
        s.switchSceneEntryEditController(selectedEntry);
    }
}
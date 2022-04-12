package at.jku.se.diary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.time.LocalDate;

public class DiaryEntryController {

    @FXML
    private TextField address;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonEditCategories;

    @FXML
    private MenuButton category;

    @FXML
    private DatePicker date;

    @FXML
    private TextArea diaryText;

    @FXML
    private TextArea structuredText;

    @FXML
    private TextField title;

    @FXML
    void addEntry(ActionEvent event) {
        String entryTitle = title.getText();
        LocalDate entryDate = date.getValue();
        String entryAddress = address.getText();
        String entryDiaryText = diaryText.getText();

        DiaryEntry newEntry = new DiaryEntry(entryDate, entryTitle, entryAddress, entryDiaryText);
        newEntry.outPut();

    }

}
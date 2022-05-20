package at.jku.se.diary;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.tools.Platform;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EntryViewController {

    DiaryEntry entry;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnTest;

    @FXML
    private Label txtDatePlace;

    @FXML
    private Label txtRating;

    @FXML
    private Label txtStrukt;

    @FXML
    private Label txtText;

    @FXML
    private Label txtTitel;

    //Edit und View verbinden um anzusehen und gleichzeitig editieren?

    public void setSelectedEntry (DiaryEntry entry) {
        this.entry = entry;
    }

    @FXML
    void showEntry(MouseEvent event) {
        System.out.println(entry.getTitle());
    }

    @FXML
    void deleteEntry(MouseEvent event) {

    }

    @FXML
    void editEntry(MouseEvent event) {

    }

    @FXML
    void showJournalList(MouseEvent event) throws IOException {
        Scene scene = btnBack.getScene();
        URL url = new File("src/main/java/at/jku/se/diary/JournalList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    public void initialize() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setEntryView(entry);
            }
        });
    }

    public void setEntryView (DiaryEntry entry) {
        this.txtTitel.setText(entry.getTitle());
        this.txtDatePlace.setText(entry.getDate() + " || " + entry.getAddress());
        this.txtText.setText(entry.getDiaryText());
    }
}

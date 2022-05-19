package at.jku.se.diary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class EntryViewController {

    DiaryEntry selectedEntry;

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

    public void setSelectedEntry(DiaryEntry entry) {
        selectedEntry = entry;
    }

    @FXML
    void showEntry(MouseEvent event) {
        System.out.println(selectedEntry.getTitle());
    }

    public void initialize () {
        //System.out.println(selectedEntry.getTitle());

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

}

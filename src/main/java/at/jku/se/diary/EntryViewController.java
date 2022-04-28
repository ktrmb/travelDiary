package at.jku.se.diary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.io.IOException;


public class EntryViewController extends JournalListController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

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

    @FXML //wird nicht benutzt ka?
    void deleteEntry(MouseEvent event) {

    }

    @FXML //wird nicht benutzt ka?
    void editEntry(MouseEvent event) {

    }

    @FXML //wird nicht benutzt ka?
    void showJournalList(MouseEvent event) throws IOException{
        Scene scene = null;
        URL url = new File("src/main/java/at/jku/se/diary/JournalList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    public void initialize () {
        DiaryEntry entry = super.selectedEntry;

        txtTitel.setText(entry.getTitle());
        txtDatePlace.setText(entry.getDate().toString() + " >>> " + entry.getAddress());
        txtText.setText(entry.getDiaryText());
        //StructInformation s = entry.getStructuredInfo();
        //txtRating.setText("Raiting: " + entry.getStructuredInfo().);
    }


    public void showJournalList(javafx.scene.input.MouseEvent mouseEvent) throws IOException{
        Scene scene = null;
        URL url = new File("src/main/java/at/jku/se/diary/JournalList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    public void editEntry(javafx.scene.input.MouseEvent mouseEvent) {
    }

    public void deleteEntry(javafx.scene.input.MouseEvent mouseEvent) {
    }
}

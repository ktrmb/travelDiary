package at.jku.se.diary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class EntryEditController {

    private DiaryEntry entry;
    private Diary diary = HelloFX.diary;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnShowStructuredInfo;

    @FXML
    private ImageView pic1;

    @FXML
    private ImageView pic2;

    @FXML
    private ImageView pic3;

    @FXML
    private TextField txtAdress;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtText;

    @FXML
    private TextField txtTitel;

    public void initialize() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setEntry(entry);
            }
        });
    }

    public void setSelectedEntry (DiaryEntry entry) {
        this.entry = entry;
    }

    public void setEntry (DiaryEntry entry) {
        txtTitel.setText(entry.getTitle());
        txtAdress.setText(entry.getAddress());
        txtDate.setValue(entry.getDate());
        txtText.setText(entry.getDiaryText());
    }

    @FXML
    void cancelEdit(MouseEvent event) throws IOException {
        Scene scene = btnCancel.getScene();
        URL url = new File("src/main/java/at/jku/se/diary/EntryView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    @FXML
    void saveEntry(MouseEvent event) {
        entry.setTitle(txtTitel.getText());
        entry.setAddress(txtAdress.getText());
        entry.setDate(txtDate.getValue());
        entry.setDiaryText(txtText.getText());

        try {
            URL url = new File("src/main/java/at/jku/se/diary/EntryView.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            EntryViewController eController = loader.getController();
            eController.setSelectedEntry(entry);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Entry View");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void showStructuredInfo(MouseEvent event) {

    }

}

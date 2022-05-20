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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class EntryEditController {

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

    @FXML
    void cancelEdit(MouseEvent event) throws IOException {
        Scene scene = btnCancel.getScene();
        URL url = new File("src/main/java/at/jku/se/diary/EntryView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    @FXML
    void saveEntry(MouseEvent event) {

    }

    @FXML
    void showStructuredInfo(MouseEvent event) {

    }

}

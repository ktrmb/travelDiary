package at.jku.se.diary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import javax.xml.bind.JAXBException;
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

    @FXML
    private ImageView btnDeletePic1;

    @FXML
    private ImageView btnDeletePic2;

    @FXML
    private ImageView btnDeletePic3;

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
        Image image1 = new Image("file:src/pictures/image"+entry.getId()+"_1.jpg");
        this.pic1.setImage(image1);
        Image image2 = new Image("file:src/pictures/image"+entry.getId()+"_2.jpg");
        this.pic2.setImage(image2);
        Image image3 = new Image("file:src/pictures/image"+entry.getId()+"_3.jpg");
        this.pic3.setImage(image3);
    }

    @FXML
    void deletePic1(MouseEvent event) {

    }

    @FXML
    void deletePic2(MouseEvent event) {

    }

    @FXML
    void deletePic3(MouseEvent event) {

    }


    @FXML
    void cancelEdit(MouseEvent event) throws IOException {
        Scene scene = btnCancel.getScene();
        URL url = new File("src/main/java/at/jku/se/diary/EntryView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    @FXML
    void saveEntry(MouseEvent event) throws JAXBException {
        int oldId = entry.getId();
        DiaryEntry newEntry = new DiaryEntry(oldId, txtDate.getValue(), txtTitel.getText(), txtAdress.getText(), txtText.getText(), entry.getStructuredInfo());
        //save to xml and switch page
        diary.getEntryList().remove(entry);
        diary.addNewEntry(newEntry);
        HelloFX.diaryDB.writeDiary(diary, HelloFX.diaryFile);

/*        try {
            URL url = new File("src/main/java/at/jku/se/diary/EntryView.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            EntryViewController eController = loader.getController();
            eController.setSelectedEntry(newEntry);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Entry View");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            Scene scene = btnSave.getScene();
            URL url = new File("src/main/java/at/jku/se/diary/EntryView.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            EntryViewController eController = loader.getController();
            eController.setSelectedEntry(newEntry);

            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showStructuredInfo(MouseEvent event) {

    }
}

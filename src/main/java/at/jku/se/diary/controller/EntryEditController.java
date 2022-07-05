package at.jku.se.diary.controller;

import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.EntryEdit;
import at.jku.se.diary.model.SceneSwitch;
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
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class EntryEditController {

    private DiaryEntry entry;
    private Diary diary = HelloFX.diary;
    private Stage stage;
    String file = "file:src/pictures/";
    private EntryEdit e = new EntryEdit();

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnShowStructuredInfo;

    @FXML
    public ImageView pic1;

    @FXML
    public ImageView pic2;

    @FXML
    public ImageView pic3;

    @FXML
    public TextField txtAdress;

    @FXML
    public DatePicker txtDate;

    @FXML
    public HTMLEditor txtText;

    @FXML
    public TextField txtTitel;

    public EntryEditController() throws MalformedURLException {
    }

    public void initialize() {
        System.out.println("2. jetzt wird inizialized mit setEntry");
        SwingUtilities.invokeLater(() -> setEntry());
    }

    public void setEntry() {
        System.out.println("3. jetzt wird alles gesetzt");
        txtTitel.setText(entry.getTitle());
        txtAdress.setText(entry.getAddress());
        txtDate.setValue(entry.getDate());
        txtText.setHtmlText(entry.getDiaryText());

        pic1.setImage(new Image(file + entry.getPicture1()));
        pic2.setImage(new Image(file + entry.getPicture2()));
        pic3.setImage(new Image(file + entry.getPicture3()));

        e.setEntry(entry);
    }

    public void setSelectedEntry (DiaryEntry entry) {
        System.out.println("1. jetzt wird setSelectedEntry ausgeführt");
        this.entry = entry;
    }

    @FXML
    void cancelEdit(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("JournalList", btnCancel.getScene());
        s.switchScene();
    }

    @FXML
    void saveEntry(MouseEvent event) throws IOException, JAXBException {
        int oldId = entry.getId();
        diary.getEntryList().remove(entry);

        diary.createNewEntry(oldId, txtDate.getValue(), txtTitel.getText(), txtAdress.getText(), txtText.getHtmlText(),
                pic1.getImage().getUrl(), pic2.getImage().getUrl(), pic3.getImage().getUrl() ,entry.getStructuredInfo());

        SceneSwitch s = new SceneSwitch("JournalList", btnSave.getScene());
        s.switchScene();
    }
/*    @FXML
    void saveEntry(MouseEvent event) throws IOException, JAXBException {
        int oldId = entry.getId();


        DiaryEntry newEntry = new DiaryEntry(oldId, txtDate.getValue(),
                txtTitel.getText(), txtAdress.getText(), txtText.getHtmlText(), entry.getStructuredInfo());

        if(!pic1.getImage().getUrl().contains("default")){
            newEntry.setPicture1(entry.saveImageToFile(pic1.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_1")));
        }

        if(!pic2.getImage().getUrl().contains("default")){
            newEntry.setPicture2(entry.saveImageToFile(pic2.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_2")));
        }

        if(!pic3.getImage().getUrl().contains("default")){
            newEntry.setPicture3(entry.saveImageToFile(pic3.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_3")));
        }

        diary.getEntryList().remove(entry);
        diary.addNewEntry(newEntry);
        HelloFX.diaryDB.writeDiary(diary, HelloFX.diaryFile);

        SceneSwitch s = new SceneSwitch("JournalList", btnSave.getScene());
        s.switchScene();
    }*/

    @FXML
    void deleteEntry(MouseEvent event) throws IOException, JAXBException {
        e.deleteEntry();
        SceneSwitch s = new SceneSwitch("JournalList", btnDelete.getScene());
        s.switchScene();
    }

    @FXML
    void showStructuredInfo(MouseEvent event) {
        entry.setTitle(txtTitel.getText());
        entry.setDate(txtDate.getValue());
        entry.setAddress(txtAdress.getText());
        entry.setDiaryText(txtText.getHtmlText());

        try {

            Scene scene = btnShowStructuredInfo.getScene();
            URL url = new File("src/main/java/at/jku/se/diary/view/StructInformationView.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            StructuredInfoController controller = loader.getController();
            controller.setEntryEdit(entry);
            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editPic1(MouseEvent event) {
        File selectedFile = diary.addPic(stage);
        if(selectedFile!=null){
            pic1.setImage(new Image(String.valueOf(selectedFile)));
        }
    }
    @FXML
    void editPic2(MouseEvent event) {
        File selectedFile = diary.addPic(stage);
        if(selectedFile!=null){
            pic2.setImage(new Image(String.valueOf(selectedFile)));
        }
    }
    @FXML
    void editPic3(MouseEvent event) {
        File selectedFile = diary.addPic(stage);
        if(selectedFile!=null){
            pic3.setImage(new Image(String.valueOf(selectedFile)));
        }
    }

    @FXML
    void deletePic1(MouseEvent event) {
        if(!entry.getPicture1().contains("default")){
            e.deletePic(pic1, "1");
        }
    }
    @FXML
    void deletePic2(MouseEvent event) {
        if(!entry.getPicture2().contains("default")){
            e.deletePic(pic2, "2");
        }
    }
    @FXML
    void deletePic3(MouseEvent event) {
        if(!entry.getPicture3().contains("default")){
            e.deletePic(pic3, "3");
        }
    }
    @FXML
    void enlargePic1(MouseEvent event) {
        openNewWindowWithPic(entry.getPicture1());
    }
    @FXML
    void enlargePic2(MouseEvent event) {
        openNewWindowWithPic(entry.getPicture2());
    }
    @FXML
    void enlargePic3(MouseEvent event) {
        openNewWindowWithPic(entry.getPicture3());
    }

    void openNewWindowWithPic(String picture){
        try {
            URL url = new File("src/main/java/at/jku/se/diary/view/EnlargedPicture.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            EnlargedPictureController eController = loader.getController();
            eController.setSelectedEntry(picture);

            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("enlarge Picture");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

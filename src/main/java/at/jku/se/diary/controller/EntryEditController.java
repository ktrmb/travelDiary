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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EntryEditController {

    private DiaryEntry entry;
    private Diary diary = HelloFX.diary;
    private Stage stage;
    String file = "file:src/pictures/";
    private String defaultPicPath = "file:src/pictures/defaultPic.png";
    private String defaultPicName = "defaultPic.png";
    private String pathToPic = "src/pictures/image";

    private EntryEdit e = new EntryEdit();

    @FXML
    private Button btnCancel;

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
        SwingUtilities.invokeLater(this::setEntry);
    }

    public void setEntry() {
        txtTitel.setText(entry.getTitle());
        txtAdress.setText(entry.getAddress());
        txtDate.setValue(entry.getDate());
        txtText.setHtmlText(entry.getDiaryText());

        Image i1 = new Image(file + entry.getPicture1());
        pic1.setImage(i1);
        Image i2 = new Image(file + entry.getPicture2());
        pic2.setImage(i2);
        Image i3 = new Image(file + entry.getPicture3());
        pic3.setImage(i3);

        e.setEntry(entry);
    }

    public void setSelectedEntry (DiaryEntry entry) {
        this.entry = entry;
    }

    @FXML
    void cancelEdit(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("journalList", btnCancel.getScene());
        s.switchScene();
    }

    @FXML
    void saveEntry(MouseEvent event) throws IOException, JAXBException {
        e.saveEntry();
        SceneSwitch s = new SceneSwitch("journalList", btnSave.getScene());
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
        e.editPic(pic1);
    }

    @FXML
    void editPic2(MouseEvent event) {
        e.editPic(pic2);
    }

    @FXML
    void editPic3(MouseEvent event) {
        e.editPic(pic3);
    }

    /*public File addPic(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wähle ein Bild aus");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        System.out.println(selectedFile);
        return selectedFile;
    }*/


    @FXML
    void deletePic1(MouseEvent event) {
        String fileName = pathToPic+entry.getId()+"_1.jpg";
        deletePic(fileName);
        //Default Image wird gesetzt
        pic1.setImage(new Image(defaultPicPath));
        entry.setPicture1(defaultPicName);
    }

    @FXML
    void deletePic2(MouseEvent event) {
        String fileName = pathToPic+entry.getId()+"_2.jpg";
        deletePic(fileName);
        //Default Image wird gesetzt
        pic2.setImage(new Image(defaultPicPath));
        entry.setPicture2(defaultPicName);
    }

    @FXML
    void deletePic3(MouseEvent event) {
        String fileName = pathToPic+entry.getId()+"_3.jpg";
        deletePic(fileName);
        //Default Image wird gesetzt
        pic3.setImage(new Image(defaultPicPath));
        entry.setPicture3(defaultPicName);
    }

    //Methode um Bilder aus dem Verzeichnis "pictures" zu löschen
    void deletePic(String fileName){
        File newFile = new File(fileName);
        String pathString = newFile.getAbsolutePath();
        Path path = Paths.get(pathString);
        try{
            Files.delete(path);
        }catch(IOException e){
            e.printStackTrace();
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

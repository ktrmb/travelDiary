package at.jku.se.diary.controller;

import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.EntryEdit;
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

import javax.swing.SwingUtilities;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
/**
 *
 * this class contains the functions regarding editing entries and user interface
 * @author Team E
 *
 */
public class EntryEditController {

    private DiaryEntry entry;
    private final Diary diary = HelloFX.diary;
    private final EntryEdit e = new EntryEdit();
    private Stage stage;

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

    /**
     * loads entry into interface before scene loads
     */
    public void initialize() {
        SwingUtilities.invokeLater(this::setEntry);
    }

    /**
     * loads entry into interface
     */
    public void setEntry() {
        txtTitel.setText(entry.getTitle());
        txtAdress.setText(entry.getAddress());
        txtDate.setValue(entry.getDate());
        txtText.setHtmlText(entry.getDiaryText());

        if(entry.getPicture1().contains("/") || entry.getPicture1().contains("\\")){
            Image i1 = new Image(entry.getPicture1());
            pic1.setImage(i1);
            Image i2 = new Image(entry.getPicture2());
            pic2.setImage(i2);
            Image i3 = new Image(entry.getPicture3());
            pic3.setImage(i3);
        }else{
            String file = "file:src/pictures/";
            pic1.setImage(new Image(file + entry.getPicture1()));
            pic2.setImage(new Image(file + entry.getPicture2()));
            pic3.setImage(new Image(file + entry.getPicture3()));
        }
        e.setEntry(entry);
    }

    /**
     * @param entry to be viewed/edited
     */
    public void setSelectedEntry (DiaryEntry entry) {
        this.entry = entry;
    }

    /**
     * @param event button clicked, loads "JournalList" scene
     * @throws IOException
     */
    @FXML
    void cancelEdit(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("JournalList", btnCancel.getScene());
        s.switchScene();
    }

    /**
     * @param event button clicked to save entry
     * @throws IOException
     * @throws JAXBException
     */
    @FXML
    void saveEntry(MouseEvent event) throws IOException, JAXBException {
        int oldId = entry.getId();
        diary.getEntryList().remove(entry);

        diary.createNewEntry(oldId, txtDate.getValue(), txtTitel.getText(), txtAdress.getText(), txtText.getHtmlText(),
                pic1.getImage().getUrl(), pic2.getImage().getUrl(), pic3.getImage().getUrl() ,entry.getStructuredInfo());

        SceneSwitch s = new SceneSwitch("JournalList", btnSave.getScene());
        s.switchScene();
    }

    /**
     * @param event button clicked to delete entry
     * @throws IOException
     * @throws JAXBException
     */
    @FXML
    void deleteEntry(MouseEvent event) throws IOException, JAXBException {
        e.deleteEntry();
        SceneSwitch s = new SceneSwitch("JournalList", btnDelete.getScene());
        s.switchScene();
    }

    /**
     * @param event button clicked to view structured info from entry
     * @throws IOException
     */
    @FXML
    void showStructuredInfo(MouseEvent event) throws IOException {
        entry.setTitle(txtTitel.getText());
        entry.setDate(txtDate.getValue());
        entry.setAddress(txtAdress.getText());
        entry.setDiaryText(txtText.getHtmlText());
        entry.setPicture1(pic1.getImage().getUrl());
        entry.setPicture2(pic2.getImage().getUrl());
        entry.setPicture3(pic3.getImage().getUrl());

        SceneSwitch s = new SceneSwitch("StructInformationView", btnShowStructuredInfo.getScene());
        s.switchSceneStructInfoController(entry);
    }

    /**
     * to show the selected picture in the first imageview
     * @param event button clicked to edit first picture
     */
    @FXML
    void editPic1(MouseEvent event) {
        File selectedFile = diary.addPic(stage);
        if(selectedFile!=null){
            pic1.setImage(new Image(String.valueOf(selectedFile)));
        }
    }
    /**
     * to show the selected picture in the second imageview
     * @param event button clicked to edit second picture
     */
    @FXML
    void editPic2(MouseEvent event) {
        File selectedFile = diary.addPic(stage);
        if(selectedFile!=null){
            pic2.setImage(new Image(String.valueOf(selectedFile)));
        }
    }
    /**
     * to show the selected picture in the third imageview
     * @param event button clicked to edit third picture
     */
    @FXML
    void editPic3(MouseEvent event) {
        File selectedFile = diary.addPic(stage);
        if(selectedFile!=null){
            pic3.setImage(new Image(String.valueOf(selectedFile)));
        }
    }

    /**
     * calls the method to delete the picture from the folder and sets the default picture
     * @param event button clicked to delete first picture
     */
    @FXML
    void deletePic1(MouseEvent event) {
        if(!entry.getPicture1().contains("default")){
            String fileName = "src/pictures/image" + entry.getId() + "_1.jpg";
            e.deletePicFile(fileName);
            pic1.setImage(new Image("file:src/pictures/defaultPic.png"));
            entry.setPicture1("default.png");
        }
    }
    /**
     * calls the method to delete the picture from the folder and sets the default picture
     * @param event button clicked to delete second picture
     */
    @FXML
    void deletePic2(MouseEvent event) {
        if(!entry.getPicture2().contains("default")){
            String fileName = "src/pictures/image" + entry.getId() + "_2.jpg";
            e.deletePicFile(fileName);
            pic2.setImage(new Image("file:src/pictures/defaultPic.png"));
            entry.setPicture2("default.png");
        }
    }
    /**
     * calls the method to delete the picture from the folder and sets the default picture
     * @param event button clicked to delete third picture
     */
    @FXML
    void deletePic3(MouseEvent event) {
        if(!entry.getPicture3().contains("default")){
            String fileName = "src/pictures/image" + entry.getId() + "_3.jpg";
            e.deletePicFile(fileName);
            pic3.setImage(new Image("file:src/pictures/defaultPic.png"));
            entry.setPicture3("default.png");
        }
    }

    /**
     * calls the method to open a new window with the first picture
     * @param event button clicked to view only the first picture
     */
    @FXML
    void enlargePic1(MouseEvent event) {
        openNewWindowWithPic(entry.getPicture1());
    }

    /**
     * calls the method to open a new window with the second picture
     * @param event button clicked to view only the second picture
     */
    @FXML
    void enlargePic2(MouseEvent event) {
        openNewWindowWithPic(entry.getPicture2());
    }

    /**
     * calls the method to open a new window with the third picture
     * @param event button clicked to view only the third picture
     */
    @FXML
    void enlargePic3(MouseEvent event) {
        openNewWindowWithPic(entry.getPicture3());
    }

    /**
     * Method to open picture in new window
     * @param picture to be viewed
     */
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

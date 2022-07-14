package at.jku.se.diary.controller;

import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.StructInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *
 * this class handles the methods and action events of creating a completely new DiaryEntry
 * @author Team E
 *
 */
public class DiaryEntryController implements Initializable {
    private Diary diary = HelloFX.diary;
    private Stage stage;

    @FXML
    private TextField address;
    @FXML
    private Button buttonStructInfo;
    @FXML
    private DatePicker date;
    @FXML
    private HTMLEditor diaryText;
    @FXML
    private TextField title;
    @FXML
    private ImageView pic1;
    @FXML
    private ImageView pic2;
    @FXML
    private ImageView pic3;
    @FXML
    private ImageView btnJournalList;
    @FXML
    private String selectedCategory;

    /**
     * to switch to another scene (= Journal List Page)
     * @param mouseEvent clicked to switch the scene
     * @throws IOException
     */
    @FXML
    public void showJournalListPage(MouseEvent mouseEvent) throws IOException {
        if(diary.getCurrentEntry() != null) {
            diary.setCurrentEntry(null);
        }
        SceneSwitch s = new SceneSwitch("JournalList", btnJournalList.getScene());
        s.switchScene();
    }

    /**
     * calls the methods in the Diary class to create a new ID and a new DiaryEntry
     * @param event clicked to create a new DiaryEntry
     * @throws JAXBException
     */
    @FXML
    public void addEntry(ActionEvent event) throws JAXBException {
        int id = diary.createID();
        diary.createNewEntry(id, date.getValue(), title.getText(), address.getText(), diaryText.getHtmlText(),
                pic1.getImage().getUrl(), pic2.getImage().getUrl(), pic3.getImage().getUrl(),
                new ArrayList<StructInformation>());
    }

    /**
     * to show the selected picture in the first imageview
     * @param mouseEvent clicked to add a new picture
     */
    @FXML
    public void addPic1(MouseEvent mouseEvent){
        File selectedFile = diary.addPic(stage);
        if(selectedFile!=null){
            pic1.setImage(new Image(String.valueOf(selectedFile)));
        }
    }

    /**
     * to show the selected picture in the second imageview
     * @param mouseEvent clicked to add a new picture
     */
    @FXML
    public void addPic2(MouseEvent mouseEvent) {
        File selectedFile = diary.addPic(stage);
        if(selectedFile!=null){
            pic2.setImage(new Image(selectedFile.toURI().toString()));
        }
    }

    /**
     * to show the selected picture in the third imageview
     * @param mouseEvent clicked to add a new picture
     */
    @FXML
    public void addPic3(MouseEvent mouseEvent) {
        File selectedFile = diary.addPic(stage);
        if(selectedFile!=null){
            pic3.setImage(new Image(selectedFile.toURI().toString()));
        }
    }

    //when the boolean CurrentEntry() of diary is true, the last entrydata is initialized
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (diary.getCurrentEntry() != null) {
            title.setText(diary.getCurrentEntry().getTitle());
            date.setValue(diary.getCurrentEntry().getDate());
            address.setText(diary.getCurrentEntry().getAddress());
            diaryText.setHtmlText(diary.getCurrentEntry().getDiaryText());
            Image i1 = new Image(diary.getCurrentEntry().getPicture1());
            pic1.setImage(i1);
            Image i2 = new Image(diary.getCurrentEntry().getPicture2());
            pic2.setImage(i2);
            Image i3 = new Image(diary.getCurrentEntry().getPicture3());
            pic3.setImage(i3);
        }
    }

    @FXML
    public void addStructuredInfo(ActionEvent actionEvent) throws IOException, JAXBException {
        if(diary.getCurrentEntry() == null) {
            this.safeEntry();
        }
        SceneSwitch s = new SceneSwitch("StructInformationView", buttonStructInfo.getScene());
        s.switchScene();
    }

    //when the Categories or new structured Info is added, the current Input is saved in the diary Arraylist
    public void safeEntry() throws JAXBException {
        int id = diary.getEntryList().size() + 1;
        LocalDate currentDate = date.getValue();
        String currentTitle = ((title.getText() == null) ? " " : title.getText());
        String currentAddress = ((address.getText() == null) ? " " : address.getText());
        String currentDiaryText = ((diaryText.getHtmlText() == null) ? " " : diaryText.getHtmlText());
        DiaryEntry currentEntry = new DiaryEntry(id, currentDate, currentTitle, currentAddress, currentDiaryText,null);
        currentEntry.setPicture1(pic1.getImage().getUrl());
        currentEntry.setPicture2(pic2.getImage().getUrl());
        currentEntry.setPicture3(pic3.getImage().getUrl());
        diary.setCurrentEntry(currentEntry);
    }
}









package at.jku.se.diary.controller;

import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.SceneSwitch;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class DiaryEntryController implements Initializable {
    private Diary diary = HelloFX.diary;
    private Stage stage;
    private DiaryEntry newEntry;

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

    //Scene wechseln - auf JournalList
    //wenn Current Entry true ist und es wird auf eine andere View gewechselt, wird der derzeitige Input wieder aus Arraylist gelöscht
    @FXML
    void showJournalListPage(MouseEvent mouseEvent) throws IOException {
        if(diary.getCurrentEntry() != null) {
            diary.setCurrentEntry(null);
        }
        SceneSwitch s = new SceneSwitch("JournalList", btnJournalList.getScene());
        s.switchScene();
    }

/*    @FXML
    void addEntry(ActionEvent event) throws JAXBException {
        ArrayList<StructInformation> structuredInfo = new ArrayList<>();

       if(diary.getCurrentEntry() != null) {
            structuredInfo = diary.getCurrentEntry().getStructuredInfo();
            diary.setCurrentEntry(null);
        }
        int id = diary.getEntryList().size() + 1;

        newEntry = new DiaryEntry(id, date.getValue(), title.getText(), address.getText(), diaryText.getHtmlText(), structuredInfo);

        //Bilder zuerst in ordner "pictures" speichern und dann in das newDiary Objekt speichern
        //Bild1:
        String defaultPic = "Icons/pic.png";
        if(!pic1.getImage().getUrl().contains(defaultPic)){
            System.out.println("**** url: " + pic1.getImage().getUrl());
            String imgName1 = newEntry.saveImageToFile(pic1.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_1"));
            newEntry.setPicture1(imgName1);
        }
        //Bild 2:
        if(!pic2.getImage().getUrl().contains(defaultPic)){
            String imgName2 = newEntry.saveImageToFile(pic2.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_2"));
            newEntry.setPicture2(imgName2);
        }
        //Bild 3:
        if(!pic3.getImage().getUrl().contains(defaultPic)){
            String imgName3 = newEntry.saveImageToFile(pic3.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_3"));
            newEntry.setPicture3(imgName3);
        }

        diary.addNewEntry(newEntry);
    }*/

/*    @FXML
    void addEntry(ActionEvent event) throws JAXBException {

        ArrayList<StructInformation> structuredInfo = new ArrayList<>();

        if(diary.getCurrentEntry() != null) {
            structuredInfo = diary.getCurrentEntry().getStructuredInfo();
            diary.setCurrentEntry(null);
        }
        int id = diary.getEntryList().size() + 1;

        newEntry = new DiaryEntry(id, date.getValue(), title.getText(), address.getText(), diaryText.getHtmlText(), structuredInfo);

        //Bilder zuerst in ordner "pictures" speichern und dann in das newDiary Objekt speichern
        //Bild1:
        String defaultPic = "Icons/pic.png";
        if(!pic1.getImage().getUrl().contains(defaultPic)){
            System.out.println("**** url: " + pic1.getImage().getUrl());
            String imgName1 = newEntry.saveImageToFile(pic1.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_1"));
            newEntry.setPicture1(imgName1);
        }
        //Bild 2:
        if(!pic2.getImage().getUrl().contains(defaultPic)){
            String imgName2 = newEntry.saveImageToFile(pic2.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_2"));
            newEntry.setPicture2(imgName2);
        }
        //Bild 3:
        if(!pic3.getImage().getUrl().contains(defaultPic)){
            String imgName3 = newEntry.saveImageToFile(pic3.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_3"));
            newEntry.setPicture3(imgName3);
        }

        diary.addNewEntry(newEntry);
    }*/
    @FXML
    void addEntry(ActionEvent event) throws JAXBException {
        //ArrayList<StructInformation> structuredInfo = new ArrayList<>();
        int id = diary.getEntryList().size() + 1;
        diary.createNewEntry(id, date.getValue(), title.getText(), address.getText(), diaryText.getHtmlText(),
                pic1.getImage().getUrl(), pic2.getImage().getUrl(), pic3.getImage().getUrl(), new ArrayList<StructInformation>());
    }

    @FXML
    public void addPic1(MouseEvent mouseEvent) throws FileNotFoundException {
        File selectedFile = diary.addPic(stage);
        if(selectedFile!=null){
            //Image image = new Image(String.valueOf(selectedFile));
            pic1.setImage(new Image(String.valueOf(selectedFile)));
        }
    }
    @FXML
    public void addPic2(MouseEvent mouseEvent) {
        File selectedFile = diary.addPic(stage);
        if(selectedFile!=null){
            //Image image = new Image(selectedFile.toURI().toString());
            pic2.setImage(new Image(selectedFile.toURI().toString()));
        }
    }
    @FXML
    public void addPic3(MouseEvent mouseEvent) {
        File selectedFile = diary.addPic(stage);
        if(selectedFile!=null){
            //Image image = new Image(selectedFile.toURI().toString());
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

/*
    //calls safeEntry() to safe the current input and opens new view
    @FXML
    public void editCategories(MouseEvent mouseEvent) throws IOException, JAXBException {
        if(!diary.isCurrentEntry()) {
            this.safeEntry();
            diary.setCurrentEntry(true);
            System.out.println("EditCurrentEntry == " + diary.isCurrentEntry());
        }
        try {
            Scene scene = buttonEditCategories.getScene();
            URL url = new File("src/main/java/at/jku/se/diary/CategoryList.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            scene.setRoot(root);
        } catch (Exception e) {
            System.out.println("load categories" + e);
        }

    } */

/*    @FXML
    public void addStructuredInfo(ActionEvent actionEvent) throws IOException, JAXBException {
        if(diary.getCurrentEntry() == null) {
            this.safeEntry();
        }
        try {
           Scene scene = buttonStructInfo.getScene();
            URL url = new File("src/main/java/at/jku/se/diary/view/StructInformationView.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            scene.setRoot(root);
         SceneSwitch s = new SceneSwitch("StructInformationView", buttonStructInfo.getScene());
            s.switchScene();
        } catch (Exception e) {
            System.out.println("load newStructuredInfo" + e);
        }

    }  */

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









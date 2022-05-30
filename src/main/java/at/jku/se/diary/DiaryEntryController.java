package at.jku.se.diary;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.xml.bind.JAXBException;
import java.awt.image.BufferedImage;
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

    private String selectedCategory;

    //Scene wechseln - auf JournalList
    //wenn Current Entry true ist und es wird auf eine andere View gewechselt, wird der derzeitige Input wieder aus Arraylist gelöscht
    @FXML
    void showJournalListPage(MouseEvent mouseEvent) throws IOException {
        System.out.println("JLCurrentEntry == "+ diary.isCurrentEntry() );
        if(diary.isCurrentEntry() == true){
            diary.getEntryList().remove(diary.getEntryList().size()-1);
            diary.setCurrentEntry(false);
        }
        Scene scene = btnJournalList.getScene();
        URL url = new File("src/main/java/at/jku/se/diary/JournalList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    @FXML
    void addEntry(ActionEvent event) throws JAXBException {
        ArrayList<StructInformation> structuredInfo = new ArrayList<>();

        if(diary.isCurrentEntry()) {
            structuredInfo = diary.getEntryList().get(diary.getEntryList().size()-1).getStructuredInfo();
            diary.getEntryList().remove(diary.getEntryList().size() - 1);
            diary.setCurrentEntry(false);
        }
        int id = diary.getEntryList().size() + 1;

        String entryTitle = title.getText();
        LocalDate entryDate = date.getValue();
        String entryAddress = address.getText();
        String entryDiaryText = diaryText.getHtmlText();
        //test






        DiaryEntry newEntry = new DiaryEntry(id, entryDate, entryTitle, entryAddress, entryDiaryText, structuredInfo);

        //Bilder zuerst in ordner "pictures" speichern und dann in das newDiary Objekt speichern
        //Bild1:
        String defaultPic = "Icons/pic.png";
        if(!pic1.getImage().getUrl().contains(defaultPic)){
            String imgName1 = saveImageToFile(pic1.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_1"));
            newEntry.setPicture1(imgName1);
        }
        //Bild 2:
        if(!pic2.getImage().getUrl().contains(defaultPic)){
            String imgName2 = saveImageToFile(pic2.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_2"));
            newEntry.setPicture2(imgName2);
        }
        //Bild 3:
        if(!pic3.getImage().getUrl().contains(defaultPic)){
            String imgName3 = saveImageToFile(pic3.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_3"));
            newEntry.setPicture3(imgName3);
        }


        diary.addNewEntry(newEntry);

        System.out.println("AddCurrentEntry == "+ diary.isCurrentEntry());
    }

    public String saveImageToFile(String fileImg, String id){
        Image image = new Image(fileImg);
        String imageName = "image" + id + ".jpg";
        File imageFile = new File("src\\pictures\\"+imageName);
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

        try{
            ImageIO.write(bufferedImage, "jpg", imageFile);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return imageName;
    }

    @FXML
    public void addPic1(MouseEvent mouseEvent) throws FileNotFoundException {
        File selectedFile = addPic();
        Image image = new Image(String.valueOf(selectedFile));
        pic1.setImage(image);
    }
    @FXML
    public void addPic2(MouseEvent mouseEvent) {
        File selectedFile = addPic();
        Image image = new Image(selectedFile.toURI().toString());
        pic2.setImage(image);

    }
    @FXML
    public void addPic3(MouseEvent mouseEvent) {
        File selectedFile = addPic();
        Image image = new Image(selectedFile.toURI().toString());
        pic3.setImage(image);
    }

    public File addPic(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wähle ein Bild aus");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        //Festlegen welche Dateitypen wir zulassen - nur JPG:
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "*.jpg"));

        //in selectedFile bekomme ich den Pfad gespeichert
        File selectedFile = fileChooser.showOpenDialog(stage);
        System.out.println(selectedFile);
        return selectedFile;
    }

    //when the boolean CurrentEntry() of diary is true, the last entrydata is initialized
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialize == "+ diary.isCurrentEntry() );
        if (diary.isCurrentEntry() == true) {
            DiaryEntry lastEntry = diary.getEntryList().get(diary.getEntryList().size()-1);
            title.setText(lastEntry.getTitle());
            date.setValue(lastEntry.getDate());
            address.setText(lastEntry.getAddress());
            diaryText.setHtmlText(lastEntry.getDiaryText());
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

    @FXML
    public void addStructuredInfo(ActionEvent actionEvent) throws IOException, JAXBException {
        if(!diary.isCurrentEntry()) {
            this.safeEntry();
            diary.setCurrentEntry(true);
        }
        try {
            Scene scene = buttonStructInfo.getScene();
            URL url = new File("src/main/java/at/jku/se/diary/StructInformationView.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            scene.setRoot(root);
        } catch (Exception e) {
            System.out.println("load newStructuredInfo" + e);
        }
    }

    //when the Categories or new structured Info is added, the current Input is saved in the diary Arraylist
    public void safeEntry() throws JAXBException {
        ArrayList<StructInformation> structuredInfo = new ArrayList<>();
        int id = diary.getEntryList().size() + 1;

        LocalDate currentDate = date.getValue();
        String currentTitle = ((title.getText() == null) ? " " : title.getText());
        String currentAddress = ((address.getText() == null) ? " " : address.getText());
        String currentDiaryText = ((diaryText.getHtmlText() == null) ? " " : diaryText.getHtmlText());

        DiaryEntry newEntry = new DiaryEntry(id, currentDate, currentTitle, currentAddress, currentDiaryText,structuredInfo);
        diary.addNewEntry(newEntry);
        //newEntry.outPut();
    }


}

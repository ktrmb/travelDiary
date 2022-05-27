package at.jku.se.diary;

import javafx.embed.swing.SwingFXUtils;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EntryEditController {

    private DiaryEntry entry;
    private Diary diary = HelloFX.diary;
    private Stage stage;

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

    @FXML
    private ImageView btnEditPic1;

    @FXML
    private ImageView btnEditPic2;

    @FXML
    private ImageView btnEditPic3;

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
        Image image1 = new Image("file:src/pictures/"+entry.getPicture1());
        this.pic1.setImage(image1);
        Image image2 = new Image("file:src/pictures/"+entry.getPicture2());
        this.pic2.setImage(image2);
        Image image3 = new Image("file:src/pictures/"+entry.getPicture3());
        this.pic3.setImage(image3);
    }

    @FXML
    void editPic1(MouseEvent event) {
        File selectedFile = addPic();
        Image image = new Image(String.valueOf(selectedFile));
        pic1.setImage(image);
    }

    @FXML
    void editPic2(MouseEvent event) {
        File selectedFile = addPic();
        Image image = new Image(String.valueOf(selectedFile));
        pic2.setImage(image);

    }

    @FXML
    void editPic3(MouseEvent event) {
        File selectedFile = addPic();
        Image image = new Image(String.valueOf(selectedFile));
        pic3.setImage(image);

    }
    public File addPic(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wähle ein Bild aus");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        System.out.println(selectedFile);
        return selectedFile;
    }

    @FXML
    void deletePic1(MouseEvent event) {
        String fileName = "src/pictures/image"+entry.getId()+"_1.jpg";
        deletePic(fileName);
        //Default Image wird gesetzt
        pic1.setImage(new Image("file:src/pictures/defaultPic.png"));
        entry.setPicture1("defaultPic.png");
    }

    @FXML
    void deletePic2(MouseEvent event) {
        String fileName = "src/pictures/image"+entry.getId()+"_2.jpg";
        deletePic(fileName);
        //Default Image wird gesetzt
        pic2.setImage(new Image("file:src/pictures/defaultPic.png"));
        entry.setPicture2("defaultPic.png");
    }

    @FXML
    void deletePic3(MouseEvent event) {
        String fileName = "src/pictures/image"+entry.getId()+"_3.jpg";
        deletePic(fileName);
        //Default Image wird gesetzt
        pic3.setImage(new Image("file:src/pictures/defaultPic.png"));
        entry.setPicture3("defaultPic.png");
    }

    //Methode um Bilder aus dem Verzeichnis "pictures" zu löschen
    void deletePic(String fileName){
        File file = new File(fileName);
        String pathString = file.getAbsolutePath();
        Path path = Paths.get(pathString);
        try{
            Files.delete(path);
        }catch(IOException e){
            e.printStackTrace();
        }
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

        //Bild 1:
        if(!pic1.getImage().getUrl().contains("default")){
            String imgName1 = saveImageToFile(pic1.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_1"));
            Image image1 = new Image("file:src/pictures/"+imgName1);
            newEntry.setPicture1(imgName1);
        }
        //Bild 2:
        if(!pic2.getImage().getUrl().contains("default")){
            String imgName2 = saveImageToFile(pic2.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_2"));
            Image image2 = new Image("file:src/pictures/"+imgName2);
            newEntry.setPicture2(imgName2);
        }
        //Bild 3:
        if(!pic3.getImage().getUrl().contains("default")){
            String imgName3 = saveImageToFile(pic3.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_3"));
            Image image3 = new Image("file:src/pictures/"+imgName3);
            newEntry.setPicture3(imgName3);
        }

        //save to xml and switch page
        diary.getEntryList().remove(entry);
        diary.addNewEntry(newEntry);
        HelloFX.diaryDB.writeDiary(diary, HelloFX.diaryFile);

        try{
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
    void showStructuredInfo(MouseEvent event) {

    }
}

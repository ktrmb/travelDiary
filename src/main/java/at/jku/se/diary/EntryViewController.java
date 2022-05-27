package at.jku.se.diary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EntryViewController {

    DiaryEntry entry;
    Stage stage;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnTest;

    @FXML
    private Label txtDatePlace;

    @FXML
    private Label txtRating;

    @FXML
    private Label txtStrukt;

    @FXML
    private Label txtText;

    @FXML
    private Label txtTitel;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    //Edit und View verbinden um anzusehen und gleichzeitig editieren?

    public void setSelectedEntry (DiaryEntry entry) {
        this.entry = entry;
    }

    @FXML
    void showEntry(MouseEvent event) {
        System.out.println(entry.getTitle());
    }

    @FXML
    void deleteEntry(MouseEvent event) throws JAXBException, IOException {
        HelloFX.diary.getEntryList().remove(entry);
        HelloFX.diaryDB.writeDiary(HelloFX.diary, HelloFX.diaryFile);

        //Bilder werden auch aus Ordner "pictures" gelöscht
        String fileName1 = "src/pictures/"+entry.getPicture1();
        deletePic(fileName1);
        String fileName2 = "src/pictures/"+entry.getPicture2();
        deletePic(fileName2);
        String fileName3 = "src/pictures/"+entry.getPicture3();
        deletePic(fileName3);

        Scene scene = btnBack.getScene();
        URL url = new File("src/main/java/at/jku/se/diary/JournalList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }
    //Methode um Bilder aus dem Verzeichnis "pictures" zu löschen
    void deletePic(String fileName){
        if(!fileName.contains("default")){
            File file = new File(fileName);
            String pathString = file.getAbsolutePath();
            Path path = Paths.get(pathString);
            try{
                Files.delete(path);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void editEntry(MouseEvent event) {
        try {
            Scene scene = btnEdit.getScene();
            URL url = new File("src/main/java/at/jku/se/diary/EntryEdit.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            EntryEditController eController = loader.getController();
            eController.setSelectedEntry(entry);

            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showJournalList(MouseEvent event) throws IOException {
        Scene scene = btnBack.getScene();
        URL url = new File("src/main/java/at/jku/se/diary/JournalList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    public void initialize() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setEntryView(entry);
            }
        });
    }

    public void setEntryView (DiaryEntry entry) {
        this.txtTitel.setText(entry.getTitle());
        this.txtDatePlace.setText(entry.getDate() + " || " + entry.getAddress());
        this.txtText.setText(entry.getDiaryText());
        Image image1 = new Image("file:src/pictures/image"+entry.getId()+"_1.jpg");
        this.img1.setImage(image1);
        Image image2 = new Image("file:src/pictures/image"+entry.getId()+"_2.jpg");
        this.img2.setImage(image2);
        Image image3 = new Image("file:src/pictures/image"+entry.getId()+"_3.jpg");
        this.img3.setImage(image3);
    }
/*    @FXML
    void enlargePic1(MouseEvent event) {
        try {
            URL url = new File("src/main/java/at/jku/se/diary/EnlargedPicture.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            EnlargedPictureController eController = loader.getController();
            eController.setSelectedEntry(entry.getPicture1());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("enlarge Picture");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

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

    //Methode die das jeweilige Bild in einem neuen Fenster (neue Stage) öffnet
    void openNewWindowWithPic(String picture){
        try {
            URL url = new File("src/main/java/at/jku/se/diary/EnlargedPicture.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            EnlargedPictureController eController = loader.getController();
            eController.setSelectedEntry(picture);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("enlarge Picture");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

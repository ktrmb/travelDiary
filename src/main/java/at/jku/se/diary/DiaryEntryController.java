package at.jku.se.diary;

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DiaryEntryController implements Initializable {
    private Diary diary;

    private Stage stage;

    @FXML
    private TextField address;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonCancel;
    @FXML
    private Button buttonEditCategories;
    @FXML
    private ChoiceBox<String> category;
    @FXML
    private DatePicker date;
    @FXML
    private TextArea diaryText;
    @FXML
    private TextArea structuredText;
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
    private Rating rating;

    private String selectedCategory;

    //Scene wechseln - auf JournalList
    @FXML
    void showJournalListPage(MouseEvent mouseEvent) throws IOException {
        Scene scene = btnJournalList.getScene();
        URL url = new File("src/main/java/at/jku/se/diary/JournalList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    @FXML
    void addEntry(ActionEvent event) throws JAXBException {
        String entryTitle = title.getText();
        LocalDate entryDate = date.getValue();
        String entryAddress = address.getText();
        String entryDiaryText = diaryText.getText();

        double stars = rating.getRating();
        StructInformation structInfo = new StructInformation(0, selectedCategory, stars, structuredText.getText());

        ArrayList<StructInformation> structuredInfo = new ArrayList<>();

        structuredInfo.add(structInfo);

        diary = HelloFX.diary;
        int id = diary.getEntryList().size() + 1;

        DiaryEntry newEntry = new DiaryEntry(id, entryDate, entryTitle, entryAddress, entryDiaryText,structuredInfo);

        newEntry.addPicture(pic1.getImage());
        newEntry.addPicture(pic2.getImage());
        newEntry.addPicture(pic3.getImage());

        diary.addNewEntry(newEntry);
        newEntry.outPut();
    }

    @FXML
    public void addPic1(MouseEvent mouseEvent) throws FileNotFoundException {
        File selectedFile = addPic();
        Image image = new Image(selectedFile.toURI().toString());
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

        //Festlegen welche Dateitypen wir zulassen:
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        //in selectedFile bekomme ich den Pfad gespeichert
        File selectedFile = fileChooser.showOpenDialog(stage);
        System.out.println(selectedFile);
        return selectedFile;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        category.getItems().addAll(HelloFX.diary.getCategories());
        category.setOnAction(this::selectCategory);
    }

    public void selectCategory(ActionEvent event){
        this.selectedCategory = category.getValue();
    }

    @FXML
    public void editCategories(MouseEvent mouseEvent) throws IOException{
        Scene scene = buttonEditCategories.getScene();
        URL url = new File("src/main/java/at/jku/se/diary/CategoryList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);

    }
}

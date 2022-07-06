package at.jku.se.diary.controller;

import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.SceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CategoryListController implements Initializable {
    //ListView list with two buttons in it
    //https://stackoverflow.com/questions/53602086/having-two-button-in-a-list-view-in-javafx-with-xml-file

    @FXML
    private ImageView btnNewEntry;

    @FXML
    private ListView<String> lVcategoryList;

    @FXML
    private TextField newCategory;

    @FXML
    private Button btnOk;


    private DiaryEntry diaryEntry;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lVcategoryList.getItems().addAll(HelloFX.diary.getCategories());
        lVcategoryList.setEditable(true);
        lVcategoryList.setCellFactory(TextFieldListCell.forListView());
    }

    @FXML
    void addToList(ActionEvent event) throws JAXBException {
        lVcategoryList.getItems().add(newCategory.getText());
        newCategory.clear();
    }


    void setEntry(DiaryEntry diaryEntry){
        this.diaryEntry = diaryEntry;
    }

    @FXML
    void saveListOnClick(ActionEvent event) throws JAXBException {
        for(String category:lVcategoryList.getItems()) {
            if (!HelloFX.diary.getCategories().contains(category)) {
                HelloFX.diary.getCategories().add(category);
            }
        }
        try {
            SceneSwitch s = new SceneSwitch("StructInformationView", btnOk.getScene());
            s.switchSceneStructInfoController(diaryEntry);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void deleteCategory(MouseEvent event) throws JAXBException {
        String deletedCategory = lVcategoryList.getSelectionModel().getSelectedItem();
        lVcategoryList.getItems().remove(deletedCategory);
        HelloFX.diary.getCategories().remove(deletedCategory);
        HelloFX.diaryDB.writeDiary(HelloFX.diary, HelloFX.diaryFile);
    }


/*    @FXML
    void showStructInfoPage(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("StructInformationView", btnOk.getScene());
        s.switchScene();
    }*/
/*    @FXML
    void showStructInfoPage(MouseEvent event) throws IOException {

        try {
            Scene scene = btnOk.getScene();
            URL url = new File("src/main/java/at/jku/se/diary/view/StructInformationView.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            StructuredInfoController controller = loader.getController();
            controller.setEntryEdit(diaryEntry);
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*    @FXML
    void showStructInfoPage(MouseEvent event) throws IOException {
        Scene scene = lVcategoryList.getScene();
        URL url = new File("src/main/java/at/jku/se/diary/view/StructInformationView.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        scene.setRoot(root);
    }*/
}

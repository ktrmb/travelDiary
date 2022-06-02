package at.jku.se.diary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.xml.bind.JAXBException;
import java.io.File;
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
    void addToList(ActionEvent event) {
        lVcategoryList.getItems().add(newCategory.getText());
        newCategory.clear();
    }


    @FXML
    void saveListOnClick(ActionEvent event) throws JAXBException {
        for(String category:lVcategoryList.getItems()) {
            if (!HelloFX.diary.getCategories().contains(category)) {
                HelloFX.diary.getCategories().add(category);
                HelloFX.diaryDB.writeDiary(HelloFX.diary, HelloFX.diaryFile);
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Category" + HelloFX.diary.isCurrentEntry());
        lVcategoryList.getItems().addAll(HelloFX.diary.getCategories());
        lVcategoryList.setEditable(true);
        lVcategoryList.setCellFactory(TextFieldListCell.forListView());


    }

    @FXML
    void showStructInfoPage(MouseEvent event) throws IOException {
            Scene scene = btnNewEntry.getScene();
            URL url = new File("src/main/java/at/jku/se/diary/StructInformationView.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            scene.setRoot(root);
    }


    @FXML
    void deleteCategory(MouseEvent event) throws JAXBException {
        String deletedCategory = lVcategoryList.getSelectionModel().getSelectedItem();
        lVcategoryList.getItems().remove(deletedCategory);
        HelloFX.diary.getCategories().remove(deletedCategory);
        HelloFX.diaryDB.writeDiary(HelloFX.diary, HelloFX.diaryFile);
    }

}

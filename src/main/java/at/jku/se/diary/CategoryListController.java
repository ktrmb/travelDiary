package at.jku.se.diary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.json.JSONObject;


public class CategoryListController implements Initializable {
    //ListView list with two buttons in it
    //https://stackoverflow.com/questions/53602086/having-two-button-in-a-list-view-in-javafx-with-xml-file

    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnOk;

    @FXML
    private ImageView btnNewEntry;

    @FXML
    private ListView<String> LVcategoryList;

    @FXML
    private TextField newCategory;

    @FXML
    void AddToList(ActionEvent event) {
        LVcategoryList.getItems().add(newCategory.getText());
        newCategory.clear();
    }


    @FXML
    void SaveListOnClick(ActionEvent event) {
        for(String category:LVcategoryList.getItems()) {
            if (!HelloFX.diary.getCategories().contains(category)) {
                HelloFX.diary.getCategories().add(category);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LVcategoryList.getItems().addAll(HelloFX.diary.getCategories());
        LVcategoryList.setEditable(true);
        LVcategoryList.setCellFactory(TextFieldListCell.forListView());


    }

    @FXML
    void showNewEntryPage(MouseEvent event) throws IOException {
        Scene scene = btnNewEntry.getScene();
        URL url = new File("src/main/java/at/jku/se/diary/DiaryEntryView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);

    }


    @FXML
    void deleteCategory(MouseEvent event) {
        String deletedCategory = LVcategoryList.getSelectionModel().getSelectedItem();
        LVcategoryList.getItems().remove(deletedCategory);
        HelloFX.diary.getCategories().remove(deletedCategory);
    }

}

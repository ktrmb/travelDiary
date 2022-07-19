package at.jku.se.diary.controller;

import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.DiaryEntry;
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

/**
 *
 * this class contains the functions regarding categories
 * @author Team E
 *
 */
public class CategoryListController implements Initializable {

    @FXML
    private ImageView btnNewEntry;

    @FXML
    private ListView<String> lVcategoryList;

    @FXML
    private TextField newCategory;

    @FXML
    private Button btnOk;

    private DiaryEntry diaryEntry;

    /**
     * adds the existing categories to the tablelist
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lVcategoryList.getItems().addAll(HelloFX.diary.getCategories());
        lVcategoryList.setEditable(true);
        lVcategoryList.setCellFactory(TextFieldListCell.forListView());
    }

    /**
     * adds the Userinputcategory to the tablelist and categorylist
     * @param event clicked on add button
     */
    @FXML
    void addToList(ActionEvent event) throws JAXBException {
        lVcategoryList.getItems().add(newCategory.getText());
        newCategory.clear();
    }

    /**
     * sets the entry, which is edited to the param entry
     * @param diaryEntry the entry, which should be edited
     */
    void setEntry(DiaryEntry diaryEntry){
        this.diaryEntry = diaryEntry;
    }

    /**
     * safes all the category to categoryList in diary and switches View to Structured Info Page
     * @param event when Button is clicked
     */
    @FXML
    void saveListOnClick(ActionEvent event) {
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

    /**
     * deletes the selected category
     * @param event when deletebutton is clicked
     */
    @FXML
    void deleteCategory(MouseEvent event) throws JAXBException {
        String deletedCategory = lVcategoryList.getSelectionModel().getSelectedItem();
        lVcategoryList.getItems().remove(deletedCategory);
        HelloFX.diary.getCategories().remove(deletedCategory);
        HelloFX.diaryDB.writeDiary(HelloFX.diary, HelloFX.diaryFile);
    }
}

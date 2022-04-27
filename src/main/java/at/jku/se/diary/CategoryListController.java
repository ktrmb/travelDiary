package at.jku.se.diary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class CategoryListController {
    //ListView list with two buttons in it
    //https://stackoverflow.com/questions/53602086/having-two-button-in-a-list-view-in-javafx-with-xml-file

    @FXML
    private Button BtnOk;

    @FXML
    private ListView<?> LVcategoryList;

    @FXML
    void SaveListOnClick(ActionEvent event) {

    }

}

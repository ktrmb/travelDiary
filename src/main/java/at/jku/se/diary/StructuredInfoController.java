package at.jku.se.diary;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static at.jku.se.diary.HelloFX.diary;

public class StructuredInfoController implements Initializable {

        @FXML
        private Button BtnAdd;

        @FXML
        private Button BtnOk;

        @FXML
        private TableView<StructInformation> tableList;

        @FXML
        private ImageView btnNewEntry;

        @FXML
        private ChoiceBox<String> category;

        @FXML
        private ImageView delete;

        @FXML
        private TextField structuredText;
        @FXML
        private Rating rating;

        private String selectedCategory;


        @FXML
        void AddToList(ActionEvent event) {

                StructInformation structInfo = new StructInformation(0, selectedCategory, rating.getRating() , structuredText.getText());

                TableColumn<StructInformation, String> category = new TableColumn<StructInformation, String>("Category");
                category.setCellValueFactory(c -> new SimpleStringProperty((c.getValue().getCategory())));

                TableColumn<StructInformation, Double> stars = new TableColumn<StructInformation, Double>("Stars");
                stars.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getStars()));

                TableColumn<StructInformation, String> info = new TableColumn<StructInformation, String>("Information");
                info.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getStructuredText()));

                tableList.getColumns().addAll(category, stars, info);
        }

        @FXML
        void SaveListOnClick(ActionEvent event) {
        }

        @FXML
        void showNewEntryPage(MouseEvent event) {
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                category.getItems().addAll(diary.getCategories());
                category.setOnAction(this::selectCategory);
        }

        public void selectCategory(ActionEvent event){
                this.selectedCategory = category.getValue();
        }


}

package at.jku.se.diary.controller;

import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.StructInformation;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

import javax.swing.SwingUtilities;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

import static at.jku.se.diary.HelloFX.diary;

/**
 *
 * this class handles the methods and action events of structured infos of entries
 * @author Team E
 *
 */
public class StructuredInfoController {

        @FXML
        private Button btnAdd;

        @FXML
        private Button btnOk;

        @FXML
        private ChoiceBox<String> category;

        @FXML
        private Button btnDelete;

        @FXML
        private TextField structuredText;

        @FXML
        private TableView<StructInformation> tableList;

        @FXML
        public Rating rating;

        private String selectedCategory;
        private DiaryEntry entryEdit;

        /**
         * loads entry into interface before scene loads
         */
        public void initialize() {
                SwingUtilities.invokeLater(() -> setStructInfo());
        }

        /**
         * sets the entry, which is edited to the param entry
         * @param entry the entry, which should be edited
         */
        public void setEntryEdit (DiaryEntry entry) {
                this.entryEdit = entry;
        }

        /**
         * creates a table and its Columns and initializes the categories
         */
        public void setStructInfo(){
                TableColumn<StructInformation, String> columnCategory =
                        new TableColumn<StructInformation, String>("Category");
                columnCategory.setCellValueFactory(c -> new SimpleStringProperty((c.getValue().getCategory())));

                TableColumn<StructInformation, Double> columnStars =
                        new TableColumn<StructInformation, Double>("Stars");
                columnStars.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getStars()));

                TableColumn<StructInformation, String> columnInfo =
                        new TableColumn<StructInformation, String>("Information");
                columnInfo.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getStructuredText()));

                tableList.getColumns().addAll(columnCategory, columnStars, columnInfo);

                if (diary.getCurrentEntry() != null) {
                        if (diary.getCurrentEntry().getStructuredInfo() != null) {
                                ObservableList<StructInformation> diaryE = FXCollections.observableArrayList(
                                        diary.getCurrentEntry().getStructuredInfo());
                                tableList.setItems(diaryE);
                        }
                } else {
                        SwingUtilities.invokeLater(() -> setInfo());
                }
                category.getItems().addAll(diary.getCategories());
                category.setOnAction(this::selectCategory);
        }

        /**
         * creates a table and sets the structured information, if the entry has any
         */
        public void setInfo () {
                if(entryEdit.getStructuredInfo() != null) {
                        ObservableList<StructInformation> diaryE = FXCollections.observableArrayList(
                                entryEdit.getStructuredInfo());
                        diaryE.forEach(info -> System.out.println(info.getCategory() + " " + info.getStars()));
                        if (diaryE.size() > 0){
                                tableList.setItems(diaryE);
                        }
                }
        }


        /**
         * gets the input of the UI-Elements and adds Object to the table
         * @param event when Button is clicked
         */
        @FXML
        void addToList(ActionEvent event) {
                StructInformation structInfo = new StructInformation(tableList.getItems().size(),
                        selectedCategory, rating.getRating() , structuredText.getText());
                tableList.getItems().add(structInfo);
                category.setValue(" ");
                rating.setRating(0);
                structuredText.setText("");
        }

        /**
         * gets the items of the table and adds all the structured infos to DiaryEntry
         * @param event when Button is clicked
         */
        @FXML
        void saveListOnClick(ActionEvent event) throws IOException {
                ArrayList<StructInformation> infos = new ArrayList<>();
                infos.addAll(tableList.getItems());

                if (diary.getCurrentEntry() != null) {
                        diary.getCurrentEntry().setStructuredInfo(infos);
                        SceneSwitch s = new SceneSwitch("DiaryEntryView", btnOk.getScene());
                        s.switchScene();
                } else {
                        entryEdit.setStructuredInfo(infos);
                        try{
                                Scene scene = btnAdd.getScene();
                                URL url = new File("src/main/java/at/jku/se/diary/view/" +
                                        "EntryEdit.fxml").toURI().toURL();
                                FXMLLoader loader = new FXMLLoader(url);
                                Parent root = loader.load();

                                EntryEditController eController = loader.getController();
                                eController.setSelectedEntry(entryEdit);

                                scene.setRoot(root);
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }

        /**
         * deletes the selected structured info
         * @param event when deletebutton is clicked
         */
        @FXML
        void deleteStructuredInfo(MouseEvent event) {
                tableList.getItems().remove(tableList.getItems()
                        .indexOf(tableList.getSelectionModel().getSelectedItem()));
        }

        /**
         * to switch to another scene (= Category Page)
         * @param event when editCategorie is clicked
         */
        @FXML
        void openEditCategories(MouseEvent event) {
                if(diary.getCurrentEntry()==null){
                        ArrayList<StructInformation> infos = new ArrayList<>();
                        infos.addAll(tableList.getItems());
                        entryEdit.setStructuredInfo(infos);
                }

                try {
                        Scene scene = btnAdd.getScene();
                        URL url = new File("src/main/java/at/jku/se/diary/view/" +
                                "CategoryList.fxml").toURI().toURL();
                        FXMLLoader loader = new FXMLLoader(url);
                        Parent root = loader.load();
                        CategoryListController cController = loader.getController();
                        cController.setEntry(entryEdit);
                        scene.setRoot(root);
                } catch (Exception e) {
                        System.out.println("load categories" + e);
                }
        }

        /**
         * when no category is selected, the category will be set to an empty string
         */
        public void selectCategory(ActionEvent event){
                this.selectedCategory = Objects.equals(category.getValue(), "") ? " " : category.getValue();
        }

}

package at.jku.se.diary.controller;

import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.SceneSwitch;
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
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

import javax.swing.SwingUtilities;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static at.jku.se.diary.HelloFX.diary;

public class StructuredInfoController {

        @FXML
        private Button BtnAdd;

        @FXML
        private Button BtnOk;

        @FXML
        private ImageView btnNewEntry;

        @FXML
        private ChoiceBox<String> category;

        @FXML
        private Button btnDelete;

        @FXML
        private ImageView journalView;

        @FXML
        private TextField structuredText;

        @FXML
        private TableView<StructInformation> tableList;

        @FXML
        public Rating rating;

        private String selectedCategory;

        private DiaryEntry entryEdit;

        @FXML
        void addToList(ActionEvent event) {
                //UI
                StructInformation structInfo = new StructInformation(tableList.getItems().size(), selectedCategory, rating.getRating() , structuredText.getText());
                tableList.getItems().add(structInfo);
                category.setValue(" ");
                rating.setRating(0);
                structuredText.setText("");
        }

        @FXML
        void saveListOnClick(ActionEvent event) throws IOException {
                ArrayList<StructInformation> infos = new ArrayList<>();
                infos.addAll(tableList.getItems());
                if (diary.getCurrentEntry() != null) {
                        diary.getCurrentEntry().setStructuredInfo(infos);
                        SceneSwitch s = new SceneSwitch("DiaryEntryView", btnNewEntry.getScene());
                        s.switchScene();
                } else {
                        entryEdit.setStructuredInfo(infos);
                        try {
                                Scene scene = BtnAdd.getScene();
                                URL url = new File("src/main/java/at/jku/se/diary/view/EntryEdit.fxml").toURI().toURL();
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

        @FXML
        void showNewEntryPage(MouseEvent event) {

        }

        @FXML
        void deleteStructuredInfo(MouseEvent event) {
                tableList.getItems().remove(tableList.getSelectionModel().getSelectedItem().getId());
        }

        public void initialize() {

                //UI
                TableColumn<StructInformation, String> columnCategory = new TableColumn<StructInformation, String>("Category");
                columnCategory.setCellValueFactory(c -> new SimpleStringProperty((c.getValue().getCategory())));

                TableColumn<StructInformation, Double> columnStars = new TableColumn<StructInformation, Double>("Stars");
                columnStars.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getStars()));

                TableColumn<StructInformation, String> columnInfo = new TableColumn<StructInformation, String>("Information");
                columnInfo.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getStructuredText()));

                tableList.getColumns().addAll(columnCategory,columnStars, columnInfo);

                //End UI


                if (diary.getCurrentEntry() != null) {
                        if (diary.getCurrentEntry().getStructuredInfo() != null) {
                                ObservableList<StructInformation> diaryE = FXCollections.observableArrayList(diary.getCurrentEntry().getStructuredInfo());
                                tableList.setItems(diaryE);
                        }
                } else {
                        SwingUtilities.invokeLater(() -> setInfo());
                }
                //set categorie
                category.getItems().addAll(diary.getCategories());
                category.setOnAction(this::selectCategory);
        }

        @FXML
        void openEditCategories(MouseEvent event) {
                try {
                        SceneSwitch s = new SceneSwitch("CategoryList", tableList.getScene());
                        s.switchScene();
                } catch (Exception e) {
                        System.out.println("load categories" + e);
                }
        }

        public void selectCategory(ActionEvent event){
                this.selectedCategory = category.getValue();
        }

        public void setEntryEdit (DiaryEntry entry) {
                this.entryEdit = entry;
                System.out.println("entry set");
        }

        public void setInfo () {
                if(entryEdit.getStructuredInfo() != null) {
                        ObservableList<StructInformation> diaryE = FXCollections.observableArrayList(entryEdit.getStructuredInfo());
                        diaryE.forEach(info -> System.out.println(info.getCategory() + " " + info.getStars()));
                        if (diaryE.size() > 0) tableList.setItems(diaryE);
                }
        }
}

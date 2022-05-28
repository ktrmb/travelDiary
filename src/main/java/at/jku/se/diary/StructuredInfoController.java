package at.jku.se.diary;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static at.jku.se.diary.HelloFX.diary;

public class StructuredInfoController implements Initializable {


        @FXML
        private Button BtnAdd;

        @FXML
        private Button BtnOk;

        @FXML
        private ImageView btnNewEntry;

        @FXML
        private ChoiceBox<String> category;

        @FXML
        private ImageView delete;

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
        void AddToList(ActionEvent event) {
                StructInformation structInfo = new StructInformation(tableList.getItems().size(), selectedCategory, rating.getRating() , structuredText.getText());
                tableList.getItems().add(structInfo);
                category.setValue(" ");
                rating.setRating(0);
                structuredText.setText("");

        }

        @FXML
        void SaveListOnClick(ActionEvent event) throws IOException {
                if (diary.getCurrentEntry()) {

                        ArrayList<StructInformation> infos = new ArrayList<>();
                        infos.addAll(tableList.getItems());
                        diary.getEntryList().get(diary.getEntryList().size() - 1).setStructuredInfo(infos);
                        diary.getEntryList().get(diary.getEntryList().size() - 1).getStructuredInfo().forEach(info ->
                                System.out.println(info.getCategory() + " " + info.getStars()));

                        Scene scene = btnNewEntry.getScene();
                        URL url = new File("src/main/java/at/jku/se/diary/DiaryEntryView.fxml").toURI().toURL();
                        Parent root = FXMLLoader.load(url);
                        scene.setRoot(root);
                } else {
                        ArrayList<StructInformation> infos = new ArrayList<>();
                        infos.addAll(tableList.getItems());
                        entryEdit.setStructuredInfo(infos);

                        try {
                                URL url = new File("src/main/java/at/jku/se/diary/EntryEdit.fxml").toURI().toURL();
                                FXMLLoader loader = new FXMLLoader(url);
                                Parent root = loader.load();

                                EntryEditController eController = loader.getController();
                                eController.setSelectedEntry(entryEdit);

                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.setTitle("Entry Edit");
                                stage.show();
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

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                TableColumn<StructInformation, String> columnCategory = new TableColumn<StructInformation, String>("Category");
                columnCategory.setCellValueFactory(c -> new SimpleStringProperty((c.getValue().getCategory())));

                TableColumn<StructInformation, Double> columnStars = new TableColumn<StructInformation, Double>("Stars");
                columnStars.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getStars()));

                TableColumn<StructInformation, String> columnInfo = new TableColumn<StructInformation, String>("Information");
                columnInfo.setCellValueFactory(c -> new SimpleObjectProperty(c.getValue().getStructuredText()));

                tableList.getColumns().addAll(columnCategory,columnStars, columnInfo);

                if (diary.getCurrentEntry()) {
                        ObservableList<StructInformation> diaryE = FXCollections.observableArrayList(diary.getEntryList().get(diary.getEntryList().size() -1).getStructuredInfo());
                        diaryE.forEach(info -> System.out.println(info.getCategory() + " " + info.getStars()));
                        if (diaryE.size()>0)tableList.setItems(diaryE);
                } else {
                        SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                        setInfo();
                                }
                        });
                }

                //set categorie
                category.getItems().addAll(HelloFX.diary.getCategories());
                category.setOnAction(this::selectCategory);
        }

        @FXML
        void openEditCategories(MouseEvent event) {
                try {
                        Scene scene = tableList.getScene();
                        URL url = new File("src/main/java/at/jku/se/diary/CategoryList.fxml").toURI().toURL();
                        Parent root = FXMLLoader.load(url);
                        scene.setRoot(root);
                } catch (Exception e) {
                        System.out.println("load categories" + e);
                }
        }

        public void selectCategory(ActionEvent event){
                this.selectedCategory = category.getValue();
        }

        public void setEntryEdit (DiaryEntry entry) { this.entryEdit = entry;}

        public void setInfo () {
                if(entryEdit.getStructuredInfo() != null) {
                        ObservableList<StructInformation> diaryE = FXCollections.observableArrayList(entryEdit.getStructuredInfo());
                        diaryE.forEach(info -> System.out.println(info.getCategory() + " " + info.getStars()));
                        if (diaryE.size() > 0) tableList.setItems(diaryE);
                }
        }
}

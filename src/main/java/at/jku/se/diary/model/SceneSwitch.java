package at.jku.se.diary.model;

import at.jku.se.diary.controller.EntryEditController;
import at.jku.se.diary.controller.StructuredInfoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SceneSwitch {
    private String newScene;
    private Scene scene;

    public SceneSwitch(String newScene, Scene scene) {
        this.newScene = newScene;
        this.scene = scene;
    }

    public void switchScene()throws IOException {
        URL url = new File("src/main/java/at/jku/se/diary/view/" + newScene + ".fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }
    public void switchSceneStructInfoController(DiaryEntry entry)throws IOException {
        URL url = new File("src/main/java/at/jku/se/diary/view/" + newScene + ".fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        StructuredInfoController controller = loader.getController();
        controller.setEntryEdit(entry);
        scene.setRoot(root);
    }
    public void switchSceneEntryEditController(DiaryEntry entry)throws IOException {
        URL url = new File("src/main/java/at/jku/se/diary/view/" + newScene + ".fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        EntryEditController controller = loader.getController();
        controller.setSelectedEntry(entry);
        scene.setRoot(root);
    }



/*    public void switchScene () throws IOException {
        switch (newScene) {
            case "journalList":
                sceneJournalList();
                break;
            case "newEntry":
                sceneNewEntry();
                break;
            case "entryEdit":
                sceneEntryEdit();
                break;
            case "fileLocation":
                sceneFileLocation();
                break;
            case "structInfo":
                sceneStructInfo();
            case "category":
                sceneCategoryList();
            case "map":
                sceneMap();
            default:
                System.out.println("Scene not available!" + " " + newScene);
        }
    }*/


/*    public void sceneJournalList() throws IOException {
        URL url = new File("src/main/java/at/jku/se/diary/view/JournalList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    public void sceneNewEntry() throws IOException {
        URL url = new File("src/main/java/at/jku/se/diary/view/DiaryEntryView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    public void sceneEntryEdit() {

    }

    public void sceneFileLocation() throws IOException {
        URL url = new File("src/main/java/at/jku/se/diary/view/SelectFileLocation.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    public void sceneStructInfo() throws IOException {
        URL url = new File("src/main/java/at/jku/se/diary/view/StructInformationView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    public void sceneCategoryList() throws IOException {
        URL url = new File("src/main/java/at/jku/se/diary/view/CategoryList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    public void sceneMap() throws IOException {
        URL url = new File("src/main/java/at/jku/se/diary/view/MapView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }*/
}
